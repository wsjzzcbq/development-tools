package com.wsjzzcbq.ui.tab;

import com.wsjzzcbq.constant.MsgConsts;
import com.wsjzzcbq.ui.rightclick.MJTextField;
import com.wsjzzcbq.util.AudioUtils;
import com.wsjzzcbq.util.FileUtils;
import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.awt.*;

/**
 * FileJPanel
 *
 * @author wsjz
 * @date 2022/04/06
 */
public class FileJPanel extends JPanel {

    public FileJPanel() {
        this.init();
    }

    private void init() {
        //为JFrame顶层容器设置FlowLayout布局管理器
        setLayout(new FlowLayout(FlowLayout.LEFT ));
        JLabel label1 = new JLabel();
        label1.setText(MsgConsts.ADDRESS_LABEL);
        add(label1);
        MJTextField textField = new MJTextField();
        textField.setColumns(32);
        add(label1);
        add(textField);
        JButton btn1 = new JButton(MsgConsts.DELETE_BUTTON);
        add(btn1);
        JButton btn2 = new JButton(MsgConsts.CLEAR_BUTTON);
        add(btn2);

        btn1.addActionListener((v)->{
            if (StringUtils.isNotBlank(textField.getText())) {
                FileUtils.delete(textField.getText());
                textField.setText("");
                AudioUtils.play();
            } else {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, MsgConsts.PLEASE_ENTER_FOLDER_MSG);
            }
        });

        btn2.addActionListener((v)->{
            textField.setText("");
        });
    }
}
