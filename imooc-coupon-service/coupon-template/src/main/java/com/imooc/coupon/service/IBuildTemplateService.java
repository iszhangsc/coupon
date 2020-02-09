package com.imooc.coupon.service;

import com.imooc.coupon.entity.CouponTemplate;
import com.imooc.coupon.exception.CouponException;
import com.imooc.coupon.vo.TemplateRequest;

/**
 * <p>
 * 构建优惠券模板接口定义
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 11:06
 */
public interface IBuildTemplateService {

    /**
     * 构建优惠券模板
     * @param request {@link TemplateRequest} 模板信息请求对象
     * @return {@link CouponTemplate} 优惠券模板实体
     * @throws CouponException 业务异常
     */
    CouponTemplate buildTemplate(TemplateRequest request) throws CouponException;

}
