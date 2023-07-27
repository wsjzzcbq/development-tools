package com.wsjzzcbq.ui;

import com.wsjzzcbq.common.queue.MsgTask;
import com.wsjzzcbq.constant.MsgConsts;
import com.wsjzzcbq.ui.tab.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JTabbedPaneUI
 *
 * @author wsjz
 * @date 2022/04/06
 */
@Slf4j
public class JTabbedPaneUI extends JPanel {

    /**
     * 选项卡
     */
    private JTabbedPane jTabbedpane = new JTabbedPane();

    @AllArgsConstructor
    private enum TabEnum {

        /**
         *局域网通信
         */
        QRCodeJPanel(QRCodeJPanel.class, MsgConsts.LAN_COMMUNICATION, "images/net.png"),
        /**
         *视频时长计算
         */
        VideoComputeJPanel(VideoComputeJPanel.class, MsgConsts.VIDEO_DURATION_CALCULATION, "images/video.png"),
        /**
         *删除文件
         */
        FileJPanel(FileJPanel.class, MsgConsts.DELETE_FILE, "images/file.png"),
        /**
         *股票成本计算
         */
        StockJPanel(StockJPanel.class, MsgConsts.STOCK_CALCULATION, "images/fund-fill.png"),
        /**
         *字母转换
         */
        LetterJPanel(com.wsjzzcbq.ui.tab.LetterJPanel.class, MsgConsts.LETTER_CONVERSION, "images/L.png"),
        /**
         *远程通信
         */
        CloudSignalPanel(CloudSignalPanel.class, MsgConsts.TELE_COMMUNICATION, "images/cloud-upload.png");

        /**
         * tab类
         */
        public Class cls;

        /**
         * tab名称
         */
        public String name;

        /**
         * icon图标
         */
        public String icon;
    }

    public JTabbedPaneUI() {
        layoutComponents();
    }

    private void layoutComponents() {
        TabEnum[] tabEnums = TabEnum.values();
        for (int i=0; i<tabEnums.length; i++) {
            try {
                JPanel jpanel = (JPanel)tabEnums[i].cls.newInstance();
                jTabbedpane.addTab(tabEnums[i].name, createImageIcon(tabEnums[i].icon), jpanel, tabEnums[i].name);
                if (i != 0) {
                    setLayout(new GridLayout(1, 1));
                    add(jTabbedpane);
                }
                //开启接收消息任务
                if (jpanel instanceof QRCodeJPanel) {
                    startTask(jpanel);
                }
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    private ImageIcon createImageIcon(String path) {
        URL url = JTabbedPaneUI.class.getClassLoader().getResource(path);
        if (url == null) {
            System.out.println("the image " + path + " is not exist!");
            return null;
        }
        return new ImageIcon(url);
    }

    private JPanel createTab1Panel(){
        JPanel panel = new JPanel(false);
        panel.setLayout(null);

        JLabel importDir = new JLabel("Import Folder:");

        JTextField textImportDir = new JTextField(15);

        JButton btnSubmit = new JButton("Submit");

        panel.add(importDir);
        panel.add(textImportDir);
        panel.add(btnSubmit);

        importDir.setBounds(100, 75, 100, 25);
        textImportDir.setBounds(200, 75, 200, 25);
        btnSubmit.setBounds(100, 125, 80, 25);

        return panel;
    }

    private void startTask(JPanel panel) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new MsgTask(panel));
    }
}
