package com.coderduckling.edaily.coupon.distribution.service;

import com.coderduckling.edaily.coupon.common.exception.CouponException;
import com.coderduckling.edaily.coupon.common.vo.CouponTemplateSDK;
import com.coderduckling.edaily.coupon.common.vo.SettlementInfo;
import com.coderduckling.edaily.coupon.distribution.entity.Coupon;
import com.coderduckling.edaily.coupon.distribution.vo.AcquireTemplateRequest;

import java.util.List;

/**
 * @ Description   :  用户服务相关的接口定义
 *          1. 用户三类状态优惠券信息展示服务
 *          2. 查看用户当前可以领取的优惠券模板 - coupon-template 微服务配合实现
 *          3. 用户领取优惠券服务
 *          4. 用户消费优惠券服务 - coupon-settlement 微服务配合实现
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/2 12:07
 * @ Version       :  1.0
 */
public interface IUserService {

    //根据用户 id 和状态查询优惠券记录
    List<Coupon> findCouponsByStatus(Long userId, Integer status)
            throws CouponException;

    //根据用户 id 查找当前可以领取的优惠券模板
    List<CouponTemplateSDK> findAvailableTemplate(Long userId)
            throws CouponException;

    //用户领取优惠券
    Coupon acquireTemplate(AcquireTemplateRequest request)
            throws CouponException;

    //结算(核销)优惠券
    SettlementInfo settlement(SettlementInfo info) throws CouponException;
}
