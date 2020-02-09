package com.imooc.coupon.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.coupon.constant.CouponStatus;
import com.imooc.coupon.converter.CouponStatusConverter;
import com.imooc.coupon.serialization.CouponSerialize;
import com.imooc.coupon.vo.CouponTemplateSDK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  优惠券(用户领取的优惠券记录)实体表
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 19:20
 */
@Data
@Entity
@Table("coupon")
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = CouponSerialize.class)
@EntityListeners(AuditingEntityListener.class)
public class Coupon implements Serializable {

    private static final long serialVersionUID = 5031497974374681777L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关联优惠券模板的主键(逻辑外键)
     */
    @Column(name = "template_id", nullable = false)
    private Integer templateId;

    /**
     * 领取用户
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 优惠券码
     */
    @Column(name = "coupon_code", nullable = false)
    private String couponCode;

    /**
     * 领取时间
     */
    @CreatedDate
    @Column(name = "assign_time", nullable = false)
    private Date assignTime;

    /**
     * 优惠券状态
     */
    @Column(name = "status", nullable = false)
    @Convert(converter = CouponStatusConverter.class)
    private CouponStatus status;

    /**
     * 用户优惠券对应的模板信息
     */
    @Transient
    private CouponTemplateSDK templateSDK;


    /**
     * 返回一个无效的 Coupon 对象
     * @return
     */
    public static Coupon invalidCoupon() {
        Coupon coupon = new Coupon();
        coupon.setId(-1);
        return coupon;
    }

    /**
     * 构造优惠券
     * @param templateId 模板ID
     * @param userId    用户ID
     * @param couponCode 优惠券码
     * @param status    优惠券状态
     */
    public Coupon(Integer templateId, Long userId, String couponCode,
                  CouponStatus status) {
        this.templateId = templateId;
        this.userId = userId;
        this.couponCode = couponCode;
        this.status = status;
    }

}
