package com.wsjzzcbq.ui.tab;

import com.wsjzzcbq.constant.MsgConsts;
import com.wsjzzcbq.ui.rightclick.MJTextField;
import com.wsjzzcbq.util.AudioUtils;
import com.wsjzzcbq.util.PopMessageUtils;
import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.awt.*;

/**
 * LetterJPanel 字母大小写转换
 *
 * @author wsjz
 * @date 2022/06/24
 */
public class LetterJPanel extends JPanel {

    /**
     * 间隔
     */
    private int gap = 5;

    public LetterJPanel() {
        init();
    }

    private void init() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        Box box1 = Box.createHorizontalBox();
        box1.add(Box.createHorizontalStrut(gap));
        //字母
        JLabel letterLabel = new JLabel();
        letterLabel.setText(MsgConsts.LETTER_LABEL);
        MJTextField letterTextField = new MJTextField();
        letterTextField.setColumns(30);
        box1.add(letterLabel);
        box1.add(Box.createHorizontalStrut(gap));
        box1.add(letterTextField);

        //间隔5
        box1.add(Box.createHorizontalStrut(gap));

        JButton upperBtn = new JButton(MsgConsts.UPPER_BUTTON);
        box1.add(upperBtn);
        box1.add(Box.createHorizontalStrut(gap));

        JButton lowerBtn = new JButton(MsgConsts.LOWER_BUTTON);
        box1.add(lowerBtn);
        box1.add(Box.createHorizontalStrut(gap));

        JButton clearBtn = new JButton(MsgConsts.CLEAR_BUTTON);
        box1.add(clearBtn);

        Box box2 = Box.createHorizontalBox();
        box2.add(Box.createHorizontalStrut(gap));

        //转换后的字母
        JLabel convertLetterLabel = new JLabel();
        convertLetterLabel.setText(MsgConsts.LETTER_CONVERT_LABEL);
        MJTextField convertLetterTextField = new MJTextField();
        convertLetterTextField.setColumns(30);
        convertLetterTextField.setEditable(false);
        box2.add(convertLetterLabel);
        box2.add(Box.createHorizontalStrut(gap));
        box2.add(convertLetterTextField);

        box2.add(Box.createHorizontalStrut(gap));

        JButton copyButton = new JButton(MsgConsts.COPY_BUTTON);
        box2.add(copyButton);

        add(box1);
        add(box2);

        upperBtn.addActionListener((e)->{
            convertLetterTextField.setText(letterTextField.getText().toUpperCase());
        });

        lowerBtn.addActionListener((e)->{
            convertLetterTextField.setText(letterTextField.getText().toLowerCase());
        });

        clearBtn.addActionListener((e)->{
            letterTextField.setText("");
            convertLetterTextField.setText("");
        });

        copyButton.addActionListener((e)->{
            if (StringUtils.isNotBlank(convertLetterTextField.getText())) {
                //先设置光标选中文本，之后才能复制,从0开始到字符结尾
                convertLetterTextField.setSelectionStart(0);
                convertLetterTextField.setSelectionEnd(convertLetterTextField.getText().length());
                convertLetterTextField.copy();

                PopMessageUtils.success(MsgConsts.COPY_SUCCESS_MSG);
            }
        });

    }
}
