package com.wsjzzcbq.ui.msg;

import com.wsjzzcbq.constant.MessageEnum;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * RightCornerPopMessage
 *
 * @author wsjz
 * @date 2022/04/24
 */
public class PopMessage extends JWindow implements Runnable {
    /**
     * 屏幕宽度
     */
    private Integer screenWidth;
    /**
     * 屏幕高度
     */
    private Integer screenHeight;
    /**
     * 设置提示窗口宽度
     */
    private Integer windowWidth = 380;
    /**
     * 设置提示窗口高度
     */
    private Integer windowHeight = 40;
    /**
     * 提示框停留时间
     */
    private Integer stayTime = 2000;
    /**
     * 窗口起始X坐标
     */
    private Integer x;
    /**
     * 窗口起始Y坐标
     */
    private Integer y;
    private String title;
    /**
     * 主面板
     */
    private JPanel mainPanel;
    /**
     * 标题栏标签
     */
    private JLabel titleLabel;

    private String icon;
    private String fontColor;
    private String backgroundColor;

    public PopMessage(MessageEnum messageEnum, String title) {
        this.title = title;
        this.icon = messageEnum.getIcon();
        this.fontColor = messageEnum.getFontColor();
        this.backgroundColor = messageEnum.getBackgroundColor();
        this.init();
        Thread thread = new Thread(this);
        thread.start();
    }

    private void init() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = dimension.width;
        screenHeight = dimension.height;
        x = (screenWidth - windowWidth) / 2;
        y = 30;
        this.setLocation(x, y);
        mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        titleLabel = new JLabel(title);

        ImageIcon imageIcon = createImageIcon(icon);
        //设置图标宽高
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        titleLabel.setIcon(imageIcon);
        titleLabel.setForeground(Color.decode(fontColor));
        titleLabel.setFont(new Font(null, 0, 18));

        mainPanel.add(titleLabel);
        mainPanel.setBackground(Color.decode(backgroundColor));

        this.setSize(windowWidth, windowHeight);
        //置顶
        this.setAlwaysOnTop(true);
        this.getContentPane().add(mainPanel);
        // 播放系统声音，提示一下
        Toolkit.getDefaultToolkit().beep();
        this.setVisible(true);

    }

    @Override
    public void run() {
        Integer delay = 2;
        Integer step = 1;
        Integer end = windowHeight;
        try {
            while (true) {
                step++;
                y++;
                this.setLocation(x, y);
                if (step > end) {
                    Thread.sleep(stayTime);
                    break;
                }
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        step = 1;
        while (true) {
            step++;
            y--;
            this.setLocation(x, y);
            if (step > end) {
                this.dispose();
                break;
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private ImageIcon createImageIcon(String path) {
        URL url = PopMessage.class.getClassLoader().getResource(path);
        if (url == null) {
            return null;
        }
        return new ImageIcon(url);
    }

}

