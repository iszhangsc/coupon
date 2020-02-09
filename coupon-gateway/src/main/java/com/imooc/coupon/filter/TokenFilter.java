package com.imooc.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  校验请求中传递的token
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-08 18:04
 */
@Slf4j
@Component
public class TokenFilter extends AbstractPreZuulFilter {

    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        log.info(String.format("%s request to %s",
                request.getMethod(), request.getRequestURL().toString()));
        final String token = request.getParameter("token");
        if (token == null) {
            log.error("error: token is empty");
            return fail(401, "token 为空");
        }
        // todo 可以token格式校验一下
        return success();
    }

    @Override
    public int filterOrder() {
        return 1;
    }

}
