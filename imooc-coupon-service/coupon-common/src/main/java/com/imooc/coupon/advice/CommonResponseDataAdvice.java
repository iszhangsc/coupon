package com.imooc.coupon.advice;

import com.imooc.coupon.annotation.IgnoreResponseAdvice;
import com.imooc.coupon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>
 *  统一响应 Advice 定义
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-08 21:39
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要对响应进行后处理
     * @param methodParameter   方法参数
     * @param aClass            消息转换器
     * @return boolean
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果当前方法所在类标识该注解 则不需要处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        // 如果当前的方法标识该注解，则不需要处理
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        // 对响应做处理，执行 beforeBodyWrite()
        return true;
    }

    /**
     * 响应返回之前的处理
     * @param o controller 返回对象
     * @param methodParameter 方法参数
     * @param mediaType     媒体类型
     * @param aClass        消息转换器
     * @param serverHttpRequest 请求
     * @param serverHttpResponse    响应
     * @return
     */
    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 定义最终的返回对象.
        CommonResponse<Object> res = new CommonResponse<>(0, "");

        // 如果 o 是null，res 不需要设置data
        if (o == null) {
            return res;
        }
        // 如果 o 已经是 CommonResponse ， 不需要再次处理
        else if (o instanceof CommonResponse) {
            res = (CommonResponse<Object>) o;
        }
        // 否则,把响应对象作为 CommonResponse 的 data 部分
        else {
            res.setData(o);
        }
        return res;
    }

}
