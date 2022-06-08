package com.wsjzzcbq.ui;

import com.wsjzzcbq.common.queue.MsgTask;
import com.wsjzzcbq.constant.MsgConsts;
import com.wsjzzcbq.ui.tab.CloudSignalPanel;
import com.wsjzzcbq.ui.tab.FileJPanel;
import com.wsjzzcbq.ui.tab.QRCodeJPanel;
import com.wsjzzcbq.ui.tab.VideoComputeJPanel;
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
    /**
     * tab名称
     */
    private String[] tabNames = {
            MsgConsts.LAN_COMMUNICATION,
            MsgConsts.VIDEO_DURATION_CALCULATION,
            MsgConsts.DELETE_FILE,
            MsgConsts.TELE_COMMUNICATION
    };
    /**
     * icon图标
     */
    private String[] icons = { "images/net.png", "images/video.png", "images/file.png", "images/cloud-upload.png" };

    /**
     * tab内容
     */
    private Class[] jpanelClasss = {
            QRCodeJPanel.class,
            VideoComputeJPanel.class,
            FileJPanel.class,
            CloudSignalPanel.class
    };

    public JTabbedPaneUI() {
        layoutComponents();
    }

    private void layoutComponents() {
        for (int i=0; i<tabNames.length; i++) {
            try {
                JPanel jpanel = (JPanel)jpanelClasss[i].newInstance();
                jTabbedpane.addTab(tabNames[i], createImageIcon(icons[i]), jpanel, tabNames[i]);
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
