package com.wsjzzcbq;

import com.wsjzzcbq.constant.AppConsts;
import com.wsjzzcbq.constant.MsgConsts;
import com.wsjzzcbq.ui.JTabbedPaneUI;
import com.wsjzzcbq.util.SysUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.*;
import java.awt.*;

/**
 * MainApp
 *
 * @author wsjz
 * @date 2022/04/06
 */
@Slf4j
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        ImageIcon icon = new ImageIcon(MainApp.class.getClassLoader().getResource("images/logo/title_taiji.jpg").getPath());

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(MsgConsts.TITLE);
            frame.setLayout(null);
            frame.setContentPane(new JTabbedPaneUI());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setIconImage(icon.getImage());

            //计算窗体弹出的位置
            int width = SysUtils.location(AppConsts.GLOBAL_SIZE, Toolkit.getDefaultToolkit().getScreenSize().width);
            int height = SysUtils.location(AppConsts.GLOBAL_SIZE, Toolkit.getDefaultToolkit().getScreenSize().height);
            frame.setLocation(width, height);
            frame.setSize(AppConsts.GLOBAL_SIZE, AppConsts.GLOBAL_SIZE);
            frame.setVisible(true);
        });

        SpringApplication.run(MainApp.class, args);
        log.info("启动成功");
    }
}
