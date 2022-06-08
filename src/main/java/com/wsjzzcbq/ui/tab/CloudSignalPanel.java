package com.wsjzzcbq.ui.tab;

import com.wsjzzcbq.constant.AppConsts;
import com.wsjzzcbq.constant.MsgConsts;
import com.wsjzzcbq.constant.NetworkProtocolConsts;
import com.wsjzzcbq.util.IPUtils;
import com.wsjzzcbq.util.RuntimeUtils;
import com.wsjzzcbq.util.YmlUtils;
import lombok.extern.slf4j.Slf4j;
import javax.swing.*;
import java.io.IOException;

/**
 * CloudSignalPanel
 *
 * @author wsjz
 * @date 2022/04/27
 */
@Slf4j
public class CloudSignalPanel extends JPanel {

    public CloudSignalPanel() {
        this.init();
    }

    private void init() {
        JButton openButton = new JButton(MsgConsts.BROWSER_OPEN_BUTTON);
        openButton.setBounds(30 + AppConsts.GLOBAL_SIZE -150, 30, 80, 30);
        this.add(openButton);

        int port = YmlUtils.getYmlConfiguration().getServer().getPort();
        String ipv4 = IPUtils.getIpv4();
        String url = NetworkProtocolConsts.HTTP + ipv4 + ":" + port + AppConsts.PC_WEB_PAGE;

        openButton.addActionListener(v -> {
            try {
                RuntimeUtils.openDefaultBrowser(url);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, MsgConsts.BROWSER_OPEN_FAIL_MSG);
                log.error(e.getMessage(), e);
            }
        });
    }
}
