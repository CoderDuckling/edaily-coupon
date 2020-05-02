package com.coderduckling.edaily.coupon.template.dao;

import com.coderduckling.edaily.coupon.template.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Description   :  定义接口类
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/1 17:22
 * @ Version       :  1.0
 */

public interface CouponTemplateDao extends JpaRepository<CouponTemplate,Integer> {
    /**
     * 根据模板名查询模板
     */
    CouponTemplate findByName(String name);

    /**
     * 根据available和expired标记查找模板记录
     */
    List<CouponTemplate> findAllByAvailableAndExpired(Boolean available, Boolean expired);

    /**
     * 根据 expired 标记查找模板记录
     */
    List<CouponTemplate> findAllByExpired(Boolean expired);
}
