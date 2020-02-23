package com.gzz.cloud.gateway.filter;


import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单限流
 */
public class OrderRateLimitFilter extends ZuulFilter {

    // 每秒产生1000个临牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);
    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext content = RequestContext.getCurrentContext();
        //获取不到临牌，返回错
        if(!RATE_LIMITER.tryAcquire()){
            content.setSendZuulResponse(false);
            content.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        return null;
    }
}
