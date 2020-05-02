package com.coderduckling.edaily.coupon.distribution.vo;

import com.coderduckling.edaily.coupon.common.vo.CouponTemplateSDK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Description   :  获取优惠券请求对象定义
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/2 11:57
 * @ Version       :  1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcquireTemplateRequest {

    /** 用户 id */
    private Long userId;

    /** 优惠券模板信息 */
    private CouponTemplateSDK templateSDK;
}
