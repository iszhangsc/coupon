package com.imooc.coupon.advice;

import com.imooc.coupon.exception.CouponException;
import com.imooc.coupon.exception.ParameterException;
import com.imooc.coupon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-08 21:56
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 对 CouponException 进行统一处理
     * @param request 请求体
     * @param ex    异常
     * @return      CommonResponse
     */
    @ExceptionHandler(value = CouponException.class)
    public CommonResponse<String> handlerCouponException(HttpServletRequest request, CouponException ex) {
        return new CommonResponse<>(-1, "business error", ex.getMessage());
    }

    /**
     * 对 ParameterException 进行统一处理
     * @param request 请求体
     * @param ex    异常
     * @return      CommonResponse
     */
    @ExceptionHandler(value = ParameterException.class)
    public CommonResponse<String> handlerCouponException(HttpServletRequest request, ParameterException ex) {
        return new CommonResponse<>(-1, ex.getMessage());
    }

}
