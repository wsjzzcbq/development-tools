package com.wsjzzcbq.ui.tab;

import com.wsjzzcbq.constant.AppConsts;
import com.wsjzzcbq.constant.MsgConsts;
import com.wsjzzcbq.constant.NetworkProtocolConsts;
import com.wsjzzcbq.ui.drop.DropFileFrame;
import com.wsjzzcbq.ui.rightclick.MJTextField;
import com.wsjzzcbq.util.*;
import com.wsjzzcbq.web.websocket.WebSocketBroadcast;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * QRCodeJPanel
 *
 * @author wsjz
 * @date 2022/04/06
 */
@Slf4j
public class QRCodeJPanel extends JPanel {

    public QRCodeJPanel() {
        init();
    }

    private void init() {
        int buttonWidth = 92;
        //消息接收
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setColumns(8);
        textField.setBounds(20,30, AppConsts.GLOBAL_SIZE -150,30);
        this.add(textField);

        JButton copyButton = new JButton(MsgConsts.COPY_BUTTON);
        copyButton.setBounds(30 + AppConsts.GLOBAL_SIZE -150, 30, buttonWidth, 30);
        this.add(copyButton);

        JButton clearButton = new JButton(MsgConsts.CLEAR_BUTTON);
        clearButton.setBounds(30 + AppConsts.GLOBAL_SIZE -150, 70, buttonWidth, 30);
        this.add(clearButton);

        //消息发送
        MJTextField sendMsgTextField = new MJTextField();
        sendMsgTextField.setColumns(8);
        sendMsgTextField.setBounds(20,110,AppConsts.GLOBAL_SIZE -150,30);
        this.add(sendMsgTextField);

        JButton sendMsgButton = new JButton(MsgConsts.SEND_MSG_BUTTON);
        sendMsgButton.setBounds(30 + AppConsts.GLOBAL_SIZE -150, 110, buttonWidth, 30);
        this.add(sendMsgButton);

        JButton sendFileButton = new JButton(MsgConsts.SEND_FILE_BUTTON);
        sendFileButton.setBounds(30 + AppConsts.GLOBAL_SIZE -150, 150, buttonWidth, 30);
        this.add(sendFileButton);

        JButton clearMsgButton = new JButton(MsgConsts.CLEAR_BUTTON);
        clearMsgButton.setBounds(30 + AppConsts.GLOBAL_SIZE -150, 190, buttonWidth, 30);
        this.add(clearMsgButton);


        int port = YmlUtils.getYmlConfiguration().getServer().getPort();
        String ipv4 = IPUtils.getIpv4();
        String web = NetworkProtocolConsts.HTTP + ipv4 + ":" + port + AppConsts.APP_WEB_PAGE;
        log.info(web);
        ImageIcon imageIcon = null;
        int qrCodeSize = AppConsts.QR_CODE_SIZE;
        //生成二维码
        try {
            BufferedImage bufferedImage = QRCodeUtils.generateQRcode(qrCodeSize, qrCodeSize, web);
            imageIcon = new ImageIcon(bufferedImage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        JLabel qrCodeLabel = new JLabel(imageIcon);
        qrCodeLabel.setBounds(
                SysUtils.location(qrCodeSize, AppConsts.GLOBAL_SIZE),
                SysUtils.location(qrCodeSize, AppConsts.GLOBAL_SIZE),
                qrCodeSize, qrCodeSize);
        this.add(qrCodeLabel);

        // new GridLayout()
        this.setLayout(null);

        copyButton.addActionListener(v-> {
            //先设置光标选中文本，之后才能复制,从0开始到字符结尾
            textField.setSelectionStart(0);
            textField.setSelectionEnd(textField.getText().length());
            textField.copy();

            PopMessageUtils.success(MsgConsts.COPY_SUCCESS_MSG);
        });

        sendFileButton.addActionListener(e->{
            DropFileFrame dropFileFrame = new DropFileFrame("拖拽文件");
            dropFileFrame.showFrame();
        });

        clearButton.addActionListener(v->textField.setText(""));

        sendMsgButton.addActionListener(e->{
            System.out.println(sendMsgTextField.getText());
            WebSocketBroadcast.send(sendMsgTextField.getText());
        });
        clearMsgButton.addActionListener(v->sendMsgTextField.setText(""));

    }
}
