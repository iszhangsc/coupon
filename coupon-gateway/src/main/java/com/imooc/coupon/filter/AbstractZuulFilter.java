package com.imooc.coupon.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * <p>
 * 定义通用抽象过滤器类
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-08 17:49
 */
public abstract class AbstractZuulFilter extends ZuulFilter {

    /**
     * 在过滤器之间传递信息,每个请求的数据保存在每个请求的ThreadLocal中
     */
    RequestContext context;

    private final static String NEXT = "next";

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return (boolean) context.getOrDefault(NEXT, true);
    }

    @Override
    public Object run() throws ZuulException {
        context = RequestContext.getCurrentContext();
        return cRun();
    }


    protected abstract Object cRun();


    Object fail(int code, String msg) {
        context.set(NEXT, false);
        context.setSendZuulResponse(false);
        context.getResponse().setContentType("text/html;charset=UTF-8");
        context.setResponseStatusCode(code);
        context.setResponseBody(String.format("{\"result\": \"%s!\"}", msg ));
        return null;
    }

    Object success() {
        context.set(NEXT, true);
        return null;
    }

}
