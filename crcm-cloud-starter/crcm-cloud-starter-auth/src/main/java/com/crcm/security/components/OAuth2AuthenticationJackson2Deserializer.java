package com.crcm.security.components;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.crcm.core.constant.AuthConstants;
import com.crcm.security.bean.Authority;
import com.crcm.security.bean.Role;
import com.crcm.security.bean.UserAccount;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @ClassName OAuth2AuthenticationJackson2Deserializer
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/23
 **/
public class OAuth2AuthenticationJackson2Deserializer  extends StdDeserializer<OAuth2Authentication> {
    public OAuth2AuthenticationJackson2Deserializer(Class<?> vc) {
        super(vc);
    }

    private static String readString(ObjectMapper mapper, JsonNode jsonNode) {
        return readValue(mapper, jsonNode, String.class);
    }

    private static <T> T readValue(ObjectMapper mapper, JsonNode jsonNode, Class<T> clazz) {
        if (mapper == null || jsonNode == null || clazz == null) {
            return null;
        }
        try {
            return mapper.readValue(jsonNode.traverse(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T readValue(ObjectMapper mapper, JsonNode jsonNode, TypeReference<T> type) {
        if (mapper == null || jsonNode == null || type == null) {
            return null;
        }
        try {
            return mapper.readValue(jsonNode.traverse(), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OAuth2Authentication deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        JsonNode requestNode = readJsonNode(jsonNode, "oauth2Request");
        JsonNode userAuthenticationNode = readJsonNode(jsonNode, "userAuthentication");

        Authentication authentication = parseAuthentication(mapper, userAuthenticationNode);
        OAuth2Request request = parseOAuth2Request(mapper, requestNode);
        return new OAuth2Authentication(request, authentication);
    }

    private Authentication parseAuthentication(ObjectMapper mapper, JsonNode json) {
        if (mapper == null || json == null) {
            return null;
        }
        // 解析用户
        UserAccount principal = parseOAuth2User(mapper, json.get("principal"));
        Object credentials = readValue(mapper, json.get("credentials"), Object.class);
        // 解析角色/权限
        Set<GrantedAuthority> grantedAuthorities = parseSimpleGrantedAuthorities(mapper, json.get("authorities"));
        principal.setAuthorities(grantedAuthorities);
        return new UsernamePasswordAuthenticationToken(principal, credentials, grantedAuthorities);
    }

    private UserAccount parseOAuth2User(ObjectMapper mapper, JsonNode json) {
        if (mapper == null || json == null) {
            return null;
        }

        UserAccount account = new UserAccount();
        Field[] fields = account.getClass().getDeclaredFields();
        if (null != fields && fields.length > 0) {
            Arrays.stream(fields).forEach(f -> {
                try {
                    // 设为可以访问
                    f.setAccessible(true);
                    // 跳过静态常量
                    if( Modifier.isStatic(f.getModifiers())) {
                        return;
                    }
                    f.set(account,readValue(mapper,json.get(f.getName()),f.getType())); // 给属性设置值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
        return account;
    }

    private OAuth2Request parseOAuth2Request(ObjectMapper mapper, JsonNode json) {
        if (mapper == null || json == null) {
            return null;

        }
        HashMap<String, String> requestParameters = readValue(mapper, json.get("requestParameters"), HashMap.class);
        String clientId = readString(mapper, json.get("clientId"));
        String grantType = readString(mapper, json.get("grantType"));
        String redirectUri = readString(mapper, json.get("redirectUri"));
        Boolean approved = readValue(mapper, json.get("approved"), Boolean.class);

        Set<String> responseTypes = readValue(mapper, json.get("responseTypes"), Set.class);
        Set<String> scope = readValue(mapper, json.get("scope"), Set.class);
        Set<String> resourceIds = readValue(mapper, json.get("resourceIds"), Set.class);
        Map<String, Serializable> extensions = readValue(mapper, json.get("extensions"),
                new TypeReference<Map<String, Serializable>>() {
                });
        Set<GrantedAuthority> grantedAuthorities = parseSimpleGrantedAuthorities(mapper, json.get("authorities"));
        OAuth2Request request = new OAuth2Request(requestParameters, clientId,
                grantedAuthorities, approved, scope, resourceIds, redirectUri, responseTypes, extensions);
        TokenRequest tokenRequest = new TokenRequest(requestParameters, clientId, scope, grantType);
        request.refresh(tokenRequest);
        return request;
    }

    private Set<GrantedAuthority> parseSimpleGrantedAuthorities(ObjectMapper mapper, JsonNode json) {
        Set<LinkedHashMap<String, String>> authorities = readValue(mapper, json, Set.class);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(0);
        if (authorities != null && !authorities.isEmpty()) {
            authorities.forEach(s -> {
                if (s != null && !s.isEmpty()) {
                    // 判断是角色还是权限
                    if (s.containsKey("organize") || StringUtils.startsWith(s.get("authority"), AuthConstants.ROLE_PREFIX)) {
                        grantedAuthorities.add(BeanUtil.mapToBean(s, Role.class, false, CopyOptions.create()));
                    } else {
                        grantedAuthorities.add(BeanUtil.mapToBean(s, Authority.class, false, CopyOptions.create()));
                    }
                }
            });
        }
        return grantedAuthorities;
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}