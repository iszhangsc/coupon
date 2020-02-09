package com.imooc.coupon.repository;

import com.imooc.coupon.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * CouponTemplate DAO 接口定义
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 10:46
 */
@Repository
public interface CouponTemplateDao
        extends JpaRepository<CouponTemplate, Integer>, JpaSpecificationExecutor<CouponTemplate> {

    /**
     * 根据模板名称查询模板
     * @param name 模板名称
     * @return CouponTemplate
     */
    CouponTemplate findByName(String name);

    /**
     * 根据 available 和 expired 标记查找模板记录
     *
     * @param available     是否可用
     * @param expired       是否过期
     * @return      List
     */
    List<CouponTemplate> findAllByAvailableAndExpired(Boolean available, Boolean expired);

    /**
     * 根据 expired 标记查找模板记录
     * @param expired       是否过期
     * @return      List
     */
    List<CouponTemplate> findAllByExpired(Boolean expired);

}
