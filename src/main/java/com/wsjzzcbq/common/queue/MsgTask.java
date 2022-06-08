package com.wsjzzcbq.common.queue;

import com.wsjzzcbq.web.bean.Msg;
import javax.swing.*;

/**
 * MsgTask
 *
 * @author wsjz
 * @date 2022/04/24
 */
public class MsgTask implements Runnable{

    private JPanel jPanel;

    public MsgTask(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    @Override
    public void run() {
        try {
            for(;;) {
                Msg msg =  MsgQueue.msgLinkedBlockingDeque.take();
                JTextField textField = (JTextField)jPanel.getComponent(0);
                textField.setText(msg.getContent());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
