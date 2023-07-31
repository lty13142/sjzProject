package com.crcm.security.components;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import com.crcm.security.bean.Role;
import com.crcm.security.bean.UserAccount;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @ClassName CustomUserAuthenticationConverter
 * @Description 自定义用户权限信息转换器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/25
 **/
public class CustomUserAuthenticationConverter implements UserAuthenticationConverter {
    private static final String PASSWORD = "password";
    private static final String N_A = "N/A";
    private static final String USERNAME = "user_name";
    private static final String AUTHORITIES = "authorities";
    private static final String ACCOUNT_NON_EXPIRED = "accountNonExpired";
    private static final String ACCOUNT_NON_LOCKED = "accountNonLocked";
    private static final String CREDENTIALS_NON_EXPIRED = "credentialsNonExpired";
    private static final String ENABLED = "enabled";

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap();
        response.put("user_name", authentication.getName());
        if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserAccount) {
            UserAccount account = (UserAccount) authentication.getPrincipal();
            if (Objects.nonNull(account)) {
                Map<String, Object> accountMap = BeanUtil.beanToMap(account);
                accountMap.remove(PASSWORD);
                accountMap.remove(AUTHORITIES);
                accountMap.remove(ACCOUNT_NON_EXPIRED);
                accountMap.remove(ACCOUNT_NON_LOCKED);
                accountMap.remove(CREDENTIALS_NON_EXPIRED);
                accountMap.remove(ENABLED);
                response.putAll(accountMap);
            }
        }
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(USERNAME)) {
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
            Set<Role> authoritySet = new HashSet<>();
            if (CollectionUtil.isNotEmpty(authorities)) {
                authorities.stream().forEach(authority ->
                        authoritySet.add(new Role().setValue(authority.getAuthority()))
                );
            }
            CopyOptions options = CopyOptions.create();
            options.setIgnoreProperties(AUTHORITIES);
            UserAccount account = BeanUtil.mapToBean(map, UserAccount.class, false, options);
            account.setPassword(N_A);
            account.setAuthorities(authoritySet);
            return new UsernamePasswordAuthenticationToken(account, N_A, authorities);
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new AccessDeniedException("Authorities must be either a String or a Collection");
    }
}
