package com.coderduckling.edaily.coupon.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Description   :  商品信息
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/2 13:05
 * @ Version       :  1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsInfo {

    /** 商品类型 **/
    private Integer type;

    /** 商品价格 **/
    private Double price;

    /** 商品数量 **/
    private Integer count;

    // TODO 名称、使用信息·····
}
