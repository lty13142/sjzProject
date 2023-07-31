package com.crcm.auth.model;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @ClassName Clients
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
public class Clients implements ClientDetails {

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 支持的资源服务ID
     */
    private String resourceIds;

    /**
     * 作用域
     */
    private String scope;

    /**
     * 支持的授权类型以及刷新token
     */
    private String authorizedGrantTypes;

    /**
     * 授权重定向路径
     */
    private String registeredRedirectUris;

    /**
     * 用来配置 authorities ，指定用户的权限范围，如果授权的过程需要用户登陆，该字段不生效，implicit和client_credentials需要
     */
    private String authorities;

    /**
     * token 过期时间
     */
    private Integer accessTokenValiditySeconds;

    /**
     * refreshToken 过期时间
     */
    private Integer refreshTokenValiditySeconds;

    /**
     * 补充信息
     */
    private String additionalInformation;

    /**
     * 是否自动批准
     */
    private String autoApproveScopes;

    public Clients(String clientId, String clientSecret, String resourceIds, String scope,
                   String authorizedGrantTypes, String registeredRedirectUris,
                   String authorities, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds,
                   String additionalInformation, String autoApproveScopes) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.resourceIds = resourceIds;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUris = registeredRedirectUris;
        this.authorities = authorities;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.additionalInformation = additionalInformation;
        this.autoApproveScopes = autoApproveScopes;
    }

    public Clients() {
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public boolean isSecretRequired() {
        return clientSecret != null;
    }

    @Override
    public Set<String> getResourceIds() {
        return getTargetStringSet(resourceIds);
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Override
    public Set<String> getScope() {
        return getTargetStringSet(scope);
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public boolean isScoped() {
        return StringUtils.isNotBlank(scope);
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return getTargetStringSet(authorizedGrantTypes);
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return getTargetStringSet(registeredRedirectUris);
    }

    public void setRegisteredRedirectUri(String registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (StringUtils.isBlank(authorities)) {
            return Collections.emptyList();
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        String[] authoritiesArray = authorities.split(",");
        for (String authority : authoritiesArray) {
            authorityList.add((GrantedAuthority) () -> authority);
        }
        return authorityList;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        if (StringUtils.isBlank(additionalInformation)) {
            return Collections.emptyMap();
        }
        return JSON.parseObject(additionalInformation);
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    private Set<String> getAutoApproveScopes() {
        if (StringUtils.isBlank(autoApproveScopes)) {
            return Collections.emptySet();
        }
        String[] approves = autoApproveScopes.split(",");
        return new HashSet<>(Arrays.asList(approves));
    }

    public void setAutoApproveScopes(String autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }

    /**
     * 作用域是否自动批准
     * <p>
     *     这里客户端可能会有多个作用域
     *     但数据库中autoApprove属性会决定哪些作用域自动批准哪些需要用户批准
     *     <ul>
     *         <li>true           此客户端所有作用域都为自动批准</li>
     *         <li>单个false       此客户端所有作用域都为自动批准</li>
     *         <li>false + scope  除了设置与scope相同的值为自动批准其余为手动批准</li>
     *         <li>为空时默认为手动批准</li>
     *     </ul>
     * </p>
     * @param scope  作用域
     * @return boolean
     */
    @Override
    public boolean isAutoApprove(String scope) {
        Set<String> autoApproveScopes = getAutoApproveScopes();
        // 为空时默认为手动批准
        if (CollectionUtils.isEmpty(autoApproveScopes)) {
            return false;
        } else {
            for (String approve:autoApproveScopes) {
                // true 此客户端所有作用域都为自动批准
                // false + scope  除了设置与scope相同的值为自动批准其余为手动批准
                if("true".equals(approve) || scope.matches(approve)){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 获取scope 或者 resourceId
     * @param target scope 或者 resourceId
     * @return java.util.Set<java.lang.String>
     */
    private Set<String> getTargetStringSet(String target) {
        if (StringUtils.isBlank(target)) {
            return Collections.emptySet();
        }
        String[] targets = target.split(",");
        return new LinkedHashSet<>(Arrays.asList(targets));
    }
}
