package com.gzz.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * 登录过滤器
 */
@Component
public class LoginFilter extends ZuulFilter {
    //过滤器类型,前置类型
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    // 过滤器顺序 值越小越执行
    @Override
    public int filterOrder() {
        return 0;
    }

    // 过滤器是否生效
    @Override
    public boolean shouldFilter() {
        RequestContext content = RequestContext.getCurrentContext();
        HttpServletRequest request = content.getRequest();
        // 判断是否要拦截。
        if ("/api/member".equalsIgnoreCase(request.getRequestURI()) ){
            return true;
        }
        return false;
    }

    // 业务逻辑
    @Override
    public Object run() throws ZuulException {
        RequestContext content = RequestContext.getCurrentContext();
        HttpServletRequest request = content.getRequest();
        // token
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }

        if(StringUtils.isBlank(token)){
            content.setSendZuulResponse(false);
            content.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }else{

        }

        return null;
    }
}
