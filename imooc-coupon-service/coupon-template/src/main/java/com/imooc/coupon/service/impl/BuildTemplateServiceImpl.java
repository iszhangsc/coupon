package com.imooc.coupon.service.impl;

import com.imooc.coupon.entity.CouponTemplate;
import com.imooc.coupon.exception.CouponException;
import com.imooc.coupon.repository.CouponTemplateDao;
import com.imooc.coupon.service.IAsyncService;
import com.imooc.coupon.service.IBuildTemplateService;
import com.imooc.coupon.vo.TemplateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  构建优惠券模板接口实现
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 12:11
 */
@Slf4j
@Service
public class BuildTemplateServiceImpl implements IBuildTemplateService {

    private final CouponTemplateDao couponTemplateDao;
    private final IAsyncService iAsyncService;

    public BuildTemplateServiceImpl(CouponTemplateDao couponTemplateDao, IAsyncService iAsyncService) {
        this.couponTemplateDao = couponTemplateDao;
        this.iAsyncService = iAsyncService;
    }

    @Override
    public CouponTemplate buildTemplate(TemplateRequest request) throws CouponException {
        // 参数校验
        if (!request.validate()) {
            throw new CouponException("BuildTemplate Param Is Not Valida");
        }
        // 判断同名的优惠券模板是否存在
        if (couponTemplateDao.findByName(request.getName()) != null) {
            throw new CouponException("Exist Same Name Template!");
        }
        CouponTemplate couponTemplate = requestToTemplate(request);
        couponTemplate = couponTemplateDao.save(couponTemplate);
        // 根据优惠券模板异步生成优惠券码
        iAsyncService.asyncConstructCouponByTemplate(couponTemplate);

        return couponTemplate;
    }


    /**
     * 将 TemplateRequest 转换位 CouponTemPlate
     * @param request
     * @return
     */
    private CouponTemplate requestToTemplate(TemplateRequest request) {
        return new CouponTemplate(
                request.getName(), request.getLogo(), request.getIntro(),
                request.getCategory(), request.getProductLine(),
                request.getCouponCount(), request.getUserId(),
                request.getTarget(), request.getRule()
        );
    }

}
