package com.crcm.auth.extend;

import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.Oauth2Constants;
import com.crcm.core.utils.SpringContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName IntegrationAuthenticationFilter
 * @Description 集成认证拦截器
 * <p>
 * 实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中的所有bean.
 * 换句话说，就是这个类可以直接获取spring配置文件中，所有有引用到的bean对象.
 * </p>
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Component
public class IntegrationAuthenticationFilter extends GenericFilterBean {

    /**
     * 需要拦截的路由
     */
    private static final String OAUTH_TOKEN_URL = "/oauth/token";
    private RequestMatcher requestMatcher;
    private Collection<IntegrationAuthenticator> authenticators;

    public IntegrationAuthenticationFilter() {
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "GET"),
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "POST")
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (requestMatcher.matches(request)) {
            RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(request);
            // 当password 不存在的时候添加password参数，避免oauth2框架 报错
            if (request.getParameter(AuthConstants.PASSWORD_PARAMETER_NAME) == null) {
                requestParameterWrapper.addParameter(AuthConstants.PASSWORD_PARAMETER_NAME, "");
            }
            IntegrationAuthenticationEntity entity = new IntegrationAuthenticationEntity();
            // 获取授权模式
            String grantType = requestParameterWrapper.getParameter(Oauth2Constants.GRANT_MODE);
            // 授权模式不是密码模式时设置认证方式为授权模式（仅仅refresh_token有具体使用）
            if (!Oauth2Constants.GRANT_MODE_PASSWORD.equals(grantType)) {
                entity.setAuthType(grantType);
            } else {
                entity.setAuthType(requestParameterWrapper.getParameter(Oauth2Constants.EXTENSION_AUTH_MODE));
            }
            entity.setAuthParameters(requestParameterWrapper.getParameterMap());
            IntegrationAuthenticationContext.set(entity);
            try {
                this.prepare(entity);
                filterChain.doFilter(requestParameterWrapper, servletResponse);
                this.complete(entity);
            } finally {
                // 必须回收自定义的ThreadLocal变量
                IntegrationAuthenticationContext.clear();
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    /**
     * 认证前预处理
     * @param entity 集成认证实体
     */
    private void prepare(IntegrationAuthenticationEntity entity) {
        if (null == this.authenticators) {
            synchronized (this) {
                if(null == this.authenticators){
                    Map<String, IntegrationAuthenticator> map = SpringContextHolder.getApplicationContext().getBeansOfType(IntegrationAuthenticator.class);
                    this.authenticators = map.values();
                }
            }
        }
        // 执行预处理
        for (IntegrationAuthenticator authenticator : this.authenticators) {
            if (authenticator.support(entity)) {
                authenticator.prepare(entity);
            }
        }
    }

    /**
     * 认证结束后回调
     * @param entity 集成认证实体
     * @author qipp
     * @date 2020/2/28 16:30
     */
    private void complete(IntegrationAuthenticationEntity entity) {
        for (IntegrationAuthenticator authenticator : authenticators) {
            if (authenticator.support(entity)) {
                authenticator.complete(entity);
            }
        }
    }


    /**
     * 用途：在拦截时给Request添加参数
     * Cloud OAuth2 密码模式需要判断Request是否存在password参数，
     * 如果不存在会抛异常结束认证
     * 所以在调用doFilter方法前添加password参数
     */
    static class RequestParameterWrapper extends HttpServletRequestWrapper {
        private Map<String, String[]> params = new HashMap<>();

        RequestParameterWrapper(HttpServletRequest request) {
            super(request);
            this.params.putAll(request.getParameterMap());
        }

        public RequestParameterWrapper(HttpServletRequest request, Map<String, Object> extraParams) {
            this(request);
            addParameters(extraParams);
        }

        void addParameters(Map<String, Object> extraParams) {
            for (Map.Entry<String, Object> entry : extraParams.entrySet()) {
                addParameter(entry.getKey(), entry.getValue());
            }
        }

        @Override
        public String getParameter(String name) {
            String[] values = params.get(name);
            if (values == null || values.length == 0) {
                return null;
            }
            return values[0];
        }

        @Override
        public String[] getParameterValues(String name) {
            return params.get(name);
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return params;
        }

        void addParameter(String name, Object value) {
            if (value != null) {
                if (value instanceof String[]) {
                    params.put(name, (String[]) value);
                } else if (value instanceof String) {
                    params.put(name, new String[]{(String) value});
                } else {
                    params.put(name, new String[]{String.valueOf(value)});
                }
            }
        }

    }
}
