package com.coderduckling.edaily.coupon.settlement.controller;

import com.alibaba.fastjson.JSON;
import com.coderduckling.edaily.coupon.common.exception.CouponException;
import com.coderduckling.edaily.coupon.common.vo.SettlementInfo;
import com.coderduckling.edaily.coupon.settlement.executor.ExecuteManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Description   :  结算服务 Controller
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/4 13:00
 * @ Version       :  1.0
 */

@Slf4j
@RestController
public class SettlementController {

    /** 结算规则执行管理器 */
    private final ExecuteManager executeManager;

    @Autowired
    public SettlementController(ExecuteManager executeManager) {
        this.executeManager = executeManager;
    }

    /**
     * 优惠券结算
     * */
    @PostMapping("/settlement/compute")
    public SettlementInfo computeRule(@RequestBody SettlementInfo settlement)
            throws CouponException {
        log.info("settlement: {}", JSON.toJSONString(settlement));
        return executeManager.computeRule(settlement);
    }
}
