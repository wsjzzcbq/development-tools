package com.wsjzzcbq.util;

import lombok.Data;
import java.math.BigDecimal;

/**
 * StockUtils
 *
 * @author wsjz
 * @date 2022/06/24
 */
public class StockUtils {

    @Data
    public static class TargetCostInfo {

        /**
         * 成本
         */
        private BigDecimal cost;

        /**
         * 持仓
         */
        private BigDecimal positions;

        /**
         * 市价
         */
        private BigDecimal price;

        /**
         * 数量
         */
        private BigDecimal quantity;

    }

    @Data
    public static class TargetCost {

        /**
         * 购买后成本
         */
        private BigDecimal cost;

        /**
         * 投入金额
         */
        private BigDecimal investmentAmount;
    }

    /**
     * 目标成本计算
     * @param info
     * @return
     */
    public static TargetCost targetCost(TargetCostInfo info) {
        BigDecimal cost = info.getCost();
        BigDecimal positions = info.getPositions();
        BigDecimal price = info.getPrice();
        BigDecimal quantity = info.getQuantity();

        //总成本 = 成本价*持仓
        BigDecimal total = cost.multiply(positions);
        //投入金额 = 市价 * 数量
        BigDecimal investmentAmount = price.multiply(quantity);
        //购买后 新的总成本 = 总成本 + 投入金额
        BigDecimal newTotal = total.add(investmentAmount);
        //购买后 新的持仓 = 持仓 + 数量
        BigDecimal newPositions = positions.add(quantity);
        //购买后 成本 = 新的总成本 / 新的持仓
        BigDecimal newCost = newTotal.divide(newPositions, BigDecimal.ROUND_HALF_EVEN);

        TargetCost targetCost = new TargetCost();
        //购买后的成本
        targetCost.setCost(newCost);
        //购买后的金额
        targetCost.setInvestmentAmount(investmentAmount);

        return targetCost;
    }


    /**
     * 股票分红后成本计算参数
     */
    @Data
    public static class DividendsInfo {
        /**
         * 持仓
         */
        private BigDecimal positions;

        /**
         * 分红
         */
        private BigDecimal dividends;

        /**
         * 成本
         */
        private BigDecimal cost;
    }

    /**
     * 计算分红后成本
     * @param info
     * @return
     */
    public static BigDecimal dividendsAfterCost(DividendsInfo info) {
        //持仓
        BigDecimal positions = info.getPositions();
        //分红
        BigDecimal dividends = info.getDividends();
        //成本
        BigDecimal cost = info.getCost();

        //总成本 = 成本 * 持仓
        BigDecimal totalCost = cost.multiply(positions);
        //总分红金额 = 持仓 * 分红
        BigDecimal dividendsTotal = positions.multiply(dividends);
        //新的总成本 = 总成本 - 总分红金额
        BigDecimal newMarketValue = totalCost.subtract(dividendsTotal);
        //分红后成本 = 新的总成本 / 持仓
        BigDecimal afterCost = newMarketValue.divide(positions, BigDecimal.ROUND_HALF_EVEN);

        return afterCost;
    }

}
