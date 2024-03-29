package com.wsjzzcbq.ui.rightclick;

import javax.swing.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;

/**
 * MJTextField
 *
 * @author wsjz
 * @date 2022/04/06
 */
public class MJTextField extends JTextField implements MouseListener {

    /**
     * 弹出菜单
     */
    private JPopupMenu pop = null;

    /**
     * 三个功能菜单
     */
    private JMenuItem copy = null, paste = null, cut = null;

    public MJTextField() {
        super();
        init();
    }

    private void init() {
        this.addMouseListener(this);
        pop = new JPopupMenu();
        pop.add(copy = new JMenuItem("复制"));
        pop.add(paste = new JMenuItem("粘贴"));
        pop.add(cut = new JMenuItem("剪切"));
        copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
        copy.addActionListener((e)->action(e));
        paste.addActionListener((e)->action(e));
        cut.addActionListener((e)->action(e));
        this.add(pop);
    }

    /**
     * 菜单动作
     *
     * @param e
     */
    public void action(ActionEvent e) {
        String str = e.getActionCommand();
        // 复制
        if (str.equals(copy.getText())) {
            this.copy();
        // 粘贴
        } else if (str.equals(paste.getText())) {
            this.paste();
        // 剪切
        } else if (str.equals(cut.getText())) {
            this.cut();
        }
    }

    public JPopupMenu getPop() {
        return pop;
    }

    public void setPop(JPopupMenu pop) {
        this.pop = pop;
    }

    /**
     * 剪切板中是否有文本数据可供粘贴
     *
     * @return true为有文本数据
     */
    public boolean isClipboardString() {
        boolean b = false;
        Clipboard clipboard = this.getToolkit().getSystemClipboard();
        Transferable content = clipboard.getContents(this);
        try {
            if (content.getTransferData(DataFlavor.stringFlavor) instanceof String) {
                b = true;
            }
        } catch (Exception e) {
        }
        return b;
    }

    /**
     * 文本组件中是否具备复制的条件
     *
     * @return true为具备
     */
    public boolean isCanCopy() {
        boolean b = false;
        int start = this.getSelectionStart();
        int end = this.getSelectionEnd();
        if (start != end) {
            b = true;
        }
        return b;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //MouseEvent.BUTTON3鼠标右键事件
        if (e.getButton() == MouseEvent.BUTTON3) {
            copy.setEnabled(isCanCopy());
            paste.setEnabled(isClipboardString());
            cut.setEnabled(isCanCopy());
            pop.show(this, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
