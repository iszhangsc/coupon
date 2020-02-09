package com.imooc.coupon.service;

import com.imooc.coupon.entity.CouponTemplate;
import com.imooc.coupon.exception.CouponException;
import com.imooc.coupon.vo.CouponTemplateSDK;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠券模板基础(view、delete....)服务定义
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 11:28
 */
public interface ITemplateBaseService {

    /**
     * 根据优惠券模板 id 获取模板信息
     *
     * @param id 模板id
     * @return {@link CouponTemplate} 优惠券模板实体
     * @throws CouponException
     */
    CouponTemplate buildTemplateInfo(Integer id) throws CouponException;

    /**
     * 查找所有可用的优惠券模板
     *
     * @return {@link CouponTemplateSDK}s
     */
    List<CouponTemplateSDK> findAllUsableTemplate();


    /**
     * 获取模板 ids 到 CouponTemplateSDK 的映射
     *
     * @param ids 模板ids
     * @return Map<Key: 模板id, value: CouponTemplateSDK>
     */
    Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids);

}
