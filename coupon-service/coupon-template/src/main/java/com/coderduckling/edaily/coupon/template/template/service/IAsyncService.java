package com.coderduckling.edaily.coupon.template.template.service;

import com.coderduckling.edaily.coupon.template.template.entity.CouponTemplate;

/**
 * @ Description   :  定义异步服务接口
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/1 17:28
 * @ Version       :  1.0
 */

public interface IAsyncService {
    /**
     * 根据模板，异步创建优惠券码
     *  @param template {@link CouponTemplate} 优惠券模板实体
     */
    void asyncConstructCouponByTemplate(CouponTemplate template);
}
