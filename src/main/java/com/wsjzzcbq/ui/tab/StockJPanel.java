package com.wsjzzcbq.ui.tab;

import com.wsjzzcbq.constant.MsgConsts;
import com.wsjzzcbq.ui.rightclick.MJTextField;
import com.wsjzzcbq.util.AudioUtils;
import com.wsjzzcbq.util.PopMessageUtils;
import com.wsjzzcbq.util.StockUtils;
import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

/**
 * StockJPanel 股票计算
 *
 * @author wsjz
 * @date 2022/06/24
 */
public class StockJPanel extends JPanel {

    /**
     * 间隔
     */
    private int gap = 5;

    public StockJPanel() {
        init();
    }

    private void init() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        Box box1 = Box.createHorizontalBox();
        box1.add(Box.createHorizontalStrut(gap));
        //成本
        JLabel costLabel = new JLabel();
        costLabel.setText(MsgConsts.COST_LABEL);
        MJTextField costTextField = new MJTextField();
        costTextField.setColumns(10);
        box1.add(costLabel);
        box1.add(Box.createHorizontalStrut(gap));
        box1.add(costTextField);

        box1.add(Box.createHorizontalStrut(gap));

        //持仓
        JLabel positionsLabel = new JLabel();
        positionsLabel.setText(MsgConsts.POSITIONS_LABEL);
        MJTextField positionsTextField = new MJTextField();
        positionsTextField.setColumns(10);
        box1.add(positionsLabel);
        box1.add(Box.createHorizontalStrut(gap));
        box1.add(positionsTextField);

        box1.add(Box.createHorizontalStrut(gap));

        //市价
        JLabel priceLabel = new JLabel();
        priceLabel.setText(MsgConsts.PRICE_LABEL);
        MJTextField priceTextField = new MJTextField();
        priceTextField.setColumns(10);
        box1.add(priceLabel);
        box1.add(Box.createHorizontalStrut(gap));
        box1.add(priceTextField);

        box1.add(Box.createHorizontalStrut(gap));

        //数量
        JLabel quantityLabel = new JLabel();
        quantityLabel.setText(MsgConsts.QUANTITY_LABEL);
        MJTextField quantityTextField = new MJTextField();
        quantityTextField.setColumns(10);
        box1.add(quantityLabel);
        box1.add(Box.createHorizontalStrut(gap));
        box1.add(quantityTextField);

        Box box2 = Box.createHorizontalBox();
        box2.add(Box.createHorizontalStrut(gap));

        //购买后成本
        JLabel targetCostLabel = new JLabel();
        targetCostLabel.setText(MsgConsts.TARGET_COST_LABEL);
        MJTextField targetCostTextField = new MJTextField();
        targetCostTextField.setColumns(10);
        targetCostTextField.setEditable(false);
        box2.add(targetCostLabel);
        box2.add(Box.createHorizontalStrut(gap));
        box2.add(targetCostTextField);

        box2.add(Box.createHorizontalStrut(gap));

        //购买金额
        JLabel investmentAmountLabel = new JLabel();
        investmentAmountLabel.setText(MsgConsts.INVESTMENT_AMOUNT_LABEL);
        MJTextField investmentAmountTextField = new MJTextField();
        investmentAmountTextField.setColumns(10);
        investmentAmountTextField.setEditable(false);
        box2.add(investmentAmountLabel);
        box2.add(Box.createHorizontalStrut(gap));
        box2.add(investmentAmountTextField);

        box2.add(Box.createHorizontalStrut(gap));

        JButton calculationBtn = new JButton(MsgConsts.CALCULATION_BUTTON);
        box2.add(calculationBtn);

        box2.add(Box.createHorizontalStrut(gap));

        JButton clearBtn = new JButton(MsgConsts.CLEAR_BUTTON);
        box2.add(clearBtn);

        add(box1);
        add(box2);

        calculationBtn.addActionListener((e)->{
            if (
                    StringUtils.isNotBlank(costTextField.getText()) &&
                    StringUtils.isNotBlank(positionsTextField.getText()) &&
                    StringUtils.isNotBlank(priceTextField.getText()) &&
                    StringUtils.isNotBlank(quantityTextField.getText())
            ) {
                StockUtils.TargetCostInfo info = new StockUtils.TargetCostInfo();
                info.setCost(new BigDecimal(costTextField.getText()));
                info.setPositions(new BigDecimal(positionsTextField.getText()));
                info.setPrice(new BigDecimal(priceTextField.getText()));
                info.setQuantity(new BigDecimal(quantityTextField.getText()));

                StockUtils.TargetCost targetCost = StockUtils.targetCost(info);
                targetCostTextField.setText(targetCost.getCost().toString());
                investmentAmountTextField.setText(targetCost.getInvestmentAmount().toString());

                AudioUtils.play();
            } else {
                failTips();
            }
        });

        clearBtn.addActionListener((e)->{
            costTextField.setText("");
            positionsTextField.setText("");
            priceTextField.setText("");
            quantityTextField.setText("");
            targetCostTextField.setText("");
            investmentAmountTextField.setText("");
        });


        Box box3 = Box.createHorizontalBox();
        box3.add(Box.createHorizontalStrut(gap));
        //成本
        JLabel box3CostLabel = new JLabel();
        box3CostLabel.setText(MsgConsts.COST_LABEL);
        MJTextField box3CostTextField = new MJTextField();
        box3CostTextField.setColumns(10);
        box3.add(box3CostLabel);
        box3.add(Box.createHorizontalStrut(gap));
        box3.add(box3CostTextField);
        //持仓
        JLabel box3PositionsLabel = new JLabel();
        box3PositionsLabel.setText(MsgConsts.POSITIONS_LABEL);
        MJTextField box3PositionsTextField = new MJTextField();
        box3PositionsTextField.setColumns(10);
        box3.add(box3PositionsLabel);
        box3.add(Box.createHorizontalStrut(gap));
        box3.add(box3PositionsTextField);
        //股息
        JLabel box3DividendsLabel = new JLabel();
        box3DividendsLabel.setText(MsgConsts.DIVIDENDS_LABEL);
        MJTextField box3DividendsTextField = new MJTextField();
        box3DividendsTextField.setColumns(10);
        box3.add(box3DividendsLabel);
        box3.add(Box.createHorizontalStrut(gap));
        box3.add(box3DividendsTextField);

        Box box4 = Box.createHorizontalBox();
        box4.add(Box.createHorizontalStrut(gap));

        JLabel box4CostLabel = new JLabel();
        box4CostLabel.setText(MsgConsts.DIVIDENDS_AFTER_COST_LABEL);
        MJTextField box4CostTextField = new MJTextField();
        box4CostTextField.setColumns(10);
        box4CostTextField.setEditable(false);
        box4.add(box4CostLabel);
        box4.add(Box.createHorizontalStrut(gap));
        box4.add(box4CostTextField);
        box4.add(Box.createHorizontalStrut(gap));

        JButton box4CalculationBtn = new JButton(MsgConsts.CALCULATION_BUTTON);
        box4.add(box4CalculationBtn);

        box4.add(Box.createHorizontalStrut(gap));

        JButton box4ClearBtn = new JButton(MsgConsts.CLEAR_BUTTON);
        box4.add(box4ClearBtn);

        add(box3);
        add(box4);

        box4CalculationBtn.addActionListener((e)->{
            if (
                    StringUtils.isNotBlank(box3DividendsTextField.getText()) &&
                    StringUtils.isNotBlank(box3CostTextField.getText()) &&
                    StringUtils.isNotBlank(box3PositionsTextField.getText())
            ) {
                StockUtils.DividendsInfo dividendsInfo = new StockUtils.DividendsInfo();
                dividendsInfo.setDividends(new BigDecimal(box3DividendsTextField.getText()));
                dividendsInfo.setCost(new BigDecimal(box3CostTextField.getText()));
                dividendsInfo.setPositions(new BigDecimal(box3PositionsTextField.getText()));
                BigDecimal dividendsAfterCost = StockUtils.dividendsAfterCost(dividendsInfo);
                box4CostTextField.setText(dividendsAfterCost.toString());

                AudioUtils.play();
            } else {
                failTips();
            }

        });
        box4ClearBtn.addActionListener(e->{
            box3DividendsTextField.setText("");
            box3CostTextField.setText("");
            box3PositionsTextField.setText("");
            box4CostTextField.setText("");
        });
    }

    private void failTips() {
        Toolkit.getDefaultToolkit().beep();
        PopMessageUtils.warning(MsgConsts.PLEASE_ENTER_PARAMETERS);
    }

}
