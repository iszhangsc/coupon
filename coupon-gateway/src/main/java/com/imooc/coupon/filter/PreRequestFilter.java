package com.imooc.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 在过滤器中存储客户端发起请求的时间戳
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-08 18:25
 */
@Slf4j
@Component
public class PreRequestFilter extends AbstractPreZuulFilter {

    @Override
    protected Object cRun() {
        context.set("startTime", System.currentTimeMillis());
        return success();
    }

    @Override
    public int filterOrder() {
        // 优先级最高，记录所有请求的时间戳
        return 0;
    }

}
