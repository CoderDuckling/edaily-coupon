package com.coderduckling.edaily.coupon.settlement.executor;

import com.coderduckling.edaily.coupon.common.vo.SettlementInfo;
import com.coderduckling.edaily.coupon.settlement.constant.RuleFlag;

/**
 * @ Description   :  优惠券模板规则处理器接口定义
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/4 12:50
 * @ Version       :  1.0
 */

public interface RuleExecutor {
    //规则类型标记
    RuleFlag ruleConfig();

    //优惠券规则计算
    SettlementInfo computeRule(SettlementInfo settlement);
}
