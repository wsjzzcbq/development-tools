package com.wsjzzcbq.ui.tab;

import com.wsjzzcbq.bean.VideoResult;
import com.wsjzzcbq.constant.MsgConsts;
import com.wsjzzcbq.ui.rightclick.MJTextField;
import com.wsjzzcbq.util.AudioUtils;
import com.wsjzzcbq.util.VideoUtils1;
import com.wsjzzcbq.util.parallel.VideoParallelUtils;
import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * VideoComputeJPanel
 *
 * @author wsjz
 * @date 2022/04/06
 */
public class VideoComputeJPanel extends JPanel {

    /**
     * 支持的视频格式
     */
    private String[] videos = {"mp4", "avi", "wmv", "flv", "ts"};

    public VideoComputeJPanel() {
        this.init();
    }

    private void init() {
        JLabel label1 = new JLabel();
        label1.setText(MsgConsts.ADDRESS_LABEL);
        MJTextField textField = new MJTextField();
        textField.setColumns(32);
        add(label1);
        add(textField);
        JButton btn1 = new JButton(MsgConsts.CALCULATION_BUTTON);
        add(btn1);
        JButton btn2 = new JButton(MsgConsts.CLEAR_BUTTON);
        add(btn2);

        JLabel label2 = new JLabel();
        label2.setText(MsgConsts.DURATION_LABEL);
        JTextField textField2 = new JTextField();
        textField2.setEditable(false);
        textField2.setColumns(8);
        add(label2);
        add(textField2);

        JLabel label3 = new JLabel();
        label3.setText(MsgConsts.DURATION_LABEL);
        JTextField textField3 = new JTextField();
        textField3.setEditable(false);
        textField3.setColumns(8);
        add(label3);
        add(textField3);

        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel(MsgConsts.SELECT_SUPPORTED_FORMATS_LABEL);
        jPanel.add(jLabel);
        LinkedList<JCheckBox> checkBoxs = new LinkedList<>();
        for (String video : videos) {
            JCheckBox checkBox = new JCheckBox(video, true);
            jPanel.add(checkBox);
            checkBoxs.add(checkBox);
        }

        add(jPanel);

        //添加线程选择
        JPanel threadJPanel = new JPanel();
        threadJPanel.add(new JLabel(MsgConsts.SELECT_THREAD_MODEL_LABEL));
        JCheckBox singleThread = new JCheckBox(MsgConsts.SINGLE_THREAD_LABEL, true);
        JCheckBox manyThread = new JCheckBox(MsgConsts.MANY_THREAD_LABEL, false);
        threadJPanel.add(singleThread);
        threadJPanel.add(manyThread);

        threadJPanel.add(new JLabel(MsgConsts.CALCULATION_TIME_LABEL));
        JTextField calculationTimeTextField = new JTextField();
        calculationTimeTextField.setEditable(false);
        calculationTimeTextField.setColumns(8);
        threadJPanel.add(calculationTimeTextField);
        add(threadJPanel);

        singleThread.addActionListener((e) -> {
            boolean many = manyThread.isSelected();

            if (many) {
                singleThread.setSelected(true);
                manyThread.setSelected(false);
            } else {
                singleThread.setSelected(true);
            }
        });
        manyThread.addActionListener((e) -> {
            boolean single = singleThread.isSelected();
            if (single) {
                singleThread.setSelected(false);
                manyThread.setSelected(true);
            } else {
                manyThread.setSelected(true);
            }
        });


        btn1.addActionListener((v)->{
            List<String> formats = new LinkedList<>();

            for (JCheckBox box : checkBoxs) {
                if (box.isSelected()) {
                    formats.add(box.getText());
                }
            }

            if (StringUtils.isBlank(textField.getText())) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, MsgConsts.PLEASE_ENTER_FOLDER_MSG);
            } else {
                textField2.setText(MsgConsts.CALCULATIONING_TEXT);
                try {
                    VideoResult videoResult = null;
                    //单线程计算
                    if (singleThread.isSelected()) {
                        videoResult = VideoUtils1.calculation(textField.getText(), formats);
                    }
                    //多线程计算
                    if (manyThread.isSelected()) {
                        videoResult = VideoParallelUtils.calculation(textField.getText(), formats);
                    }

                    //分钟
                    String minute = videoResult.getDurationMinute();
                    if (minute.length() > 8) {
                        textField2.setText(minute.substring(0, 8) + MsgConsts.MINUTE);
                    } else {
                        textField2.setText(minute + MsgConsts.MINUTE);
                    }

                    //小时
                    String hour = videoResult.getDurationHour();
                    if (hour.length() > 8) {
                        textField3.setText(hour.substring(0,8) + MsgConsts.HOUR);
                    } else {
                        textField3.setText(hour + MsgConsts.HOUR);
                    }

                    //计算耗时
                    calculationTimeTextField.setText(videoResult.getTime());

                    AudioUtils.play();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        btn2.addActionListener((v)->{
            textField.setText("");
            textField2.setText("");
            textField3.setText("");
            calculationTimeTextField.setText("");
        });
    }
}
