package com.imooc.coupon.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * <p>
 *  请求前过滤器
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-08 18:01
 * @see AbstractZuulFilter
 */
public abstract class AbstractPreZuulFilter extends AbstractZuulFilter {


    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

}
