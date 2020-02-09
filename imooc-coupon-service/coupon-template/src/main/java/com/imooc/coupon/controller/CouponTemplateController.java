package com.imooc.coupon.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.coupon.entity.CouponTemplate;
import com.imooc.coupon.exception.CouponException;
import com.imooc.coupon.service.IBuildTemplateService;
import com.imooc.coupon.service.ITemplateBaseService;
import com.imooc.coupon.vo.CouponTemplateSDK;
import com.imooc.coupon.vo.TemplateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠券模板相关功能控制器
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 13:05
 */
@Slf4j
@RestController
public class CouponTemplateController {

    private final IBuildTemplateService buildTemplateService;
    private final ITemplateBaseService templateBaseService;

    public CouponTemplateController(IBuildTemplateService buildTemplateService, ITemplateBaseService templateBaseService) {
        this.buildTemplateService = buildTemplateService;
        this.templateBaseService = templateBaseService;
    }


    /**
     * 构建优惠券模板
     * 127.0.0.1:7001/coupon-template/template/build
     * 127.0.0.1:9000/imooc/coupon-template/template/build
     * @param request
     * @return
     * @throws CouponException
     */
    @PostMapping("/template/build")
    public CouponTemplate buildTemplate(@RequestBody TemplateRequest request) throws CouponException {
        final CouponTemplate couponTemplate = buildTemplateService.buildTemplate(request);
        log.info("Build Template: {}", JSON.toJSONString(couponTemplate));
        return couponTemplate;
    }

    /**
     * 构造优惠券模板详情
     * 127.0.0.1:7001/coupon-template/template/info?id=1
     * 127.0.0.1:9000/imooc/coupon-template/template/info?id=1
     * @param id 优惠券ID
     * @throws CouponException
     */
    @GetMapping("/template/info")
    public CouponTemplate buildTemplateInfo(@RequestParam("id") Integer id) throws CouponException {
        log.info("Build Template Info For :{}", id);
        return templateBaseService.buildTemplateInfo(id);
    }

    /**
     * 获取所有可用的优惠券模板
     * 127.0.0.1:7001/coupon-template/template/sdk/all
     * 127.0.0.1:9000/imooc/coupon-template/template/sdk/all
     */
    @GetMapping("/template/sdk/all")
    public List<CouponTemplateSDK> findAllUsableTemplate() {
        return templateBaseService.findAllUsableTemplate();
    }

    /**
     * 获取模板 ids 到 CouponTemplateSDK 的映射
     * 127.0.0.1:7001/coupon-template/template/sdk/infos
     * 127.0.0.1:9000/imooc/coupon-template/template/sdk/infos
     * @param ids ids
     */
    @GetMapping("/template/sdk/infos")
    public Map<Integer, CouponTemplateSDK> findIds2Template(@RequestParam("ids") Collection<Integer> ids) {
        return templateBaseService.findIds2TemplateSDK(ids);
    }


}
