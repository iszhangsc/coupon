package com.imooc.coupon.filter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 限流过滤器，基于Guava 令牌桶算法
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-08 18:13
 */
@Slf4j
@Component
public class RateLimiterFilter extends AbstractPreZuulFilter {

    /**
     * 每秒可以获取到两个令牌
     */
    final RateLimiter rateLimiter = RateLimiter.create(2.0);

    @Override
    protected Object cRun() {
        final HttpServletRequest request = context.getRequest();
        if (rateLimiter.tryAcquire()) {
            log.info("get rate token success");
            return success();
        } else {
            log.error("rate limit {}", request.getRequestURI());
            return fail(402, "get rate token error");
        }
    }

    @Override
    public int filterOrder() {
        return 2;
    }

}
