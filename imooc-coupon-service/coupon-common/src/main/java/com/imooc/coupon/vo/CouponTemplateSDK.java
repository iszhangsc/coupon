package com.imooc.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 微服务之间用的优惠券模板定义
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 11:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponTemplateSDK {

    /**
     * 优惠券模板主键
     */
    private Integer id;

    /**
     * 优惠券模板名称
     */
    private String name;

    /**
     * 优惠券 LOGO
     */
    private String logo;

    /**
     * 优惠券描述
     */
    private String intro;

    /**
     * 优惠券分类
     */
    private String category;

    /**
     * 产品线
     */
    private Integer productLine;

    /**
     * 优惠券模板的编码
     */
    private String templateKey;

    /**
     * 目标用户
     */
    private Integer target;

    /**
     * 规则
     */
    private TemplateRule rule;

}
