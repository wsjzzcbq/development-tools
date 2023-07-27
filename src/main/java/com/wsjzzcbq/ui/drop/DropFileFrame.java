package com.wsjzzcbq.ui.drop;

import com.wsjzzcbq.constant.AppConsts;
import com.wsjzzcbq.constant.MsgConsts;
import com.wsjzzcbq.util.PopMessageUtils;
import com.wsjzzcbq.util.ResourcesUtils;
import com.wsjzzcbq.util.SysUtils;
import com.wsjzzcbq.util.YmlUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
/**
 * DropFileFrame 可拖拽的 JFrame
 *
 * @author wsjz
 * @date 2023/03/23
 */
public class DropFileFrame extends JFrame{

    private DropFileFrame dropFileFrame;

    public DropFileFrame(String name) throws HeadlessException {
        super(name);
        dropFileFrame = this;

        ImageIcon icon = new ImageIcon(ResourcesUtils.getBytesByFilePath(AppConsts.ICON_FILE));
        this.setIconImage(icon.getImage());
        int size = 500;
        this.setSize(size, size);
        //计算窗体弹出的位置
        int width = SysUtils.location(size, Toolkit.getDefaultToolkit().getScreenSize().width);
        int height = SysUtils.location(size, Toolkit.getDefaultToolkit().getScreenSize().height);
        this.setLocation(width, height);

        JPanel root = new JPanel();
        this.setContentPane(root);
        this.setLayout(new BorderLayout());
        DropTarget dropTarget = new DropTarget(root,new FileDropTargetListener());
    }

    public void showFrame() {
        dropFileFrame.setVisible(true);
    }

    public void hideFrame() {
        dropFileFrame.setVisible(false);
    }

    private class FileDropTargetListener implements DropTargetListener {

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {

        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {

        }

        @Override
        public void dropActionChanged(DropTargetDragEvent dtde) {

        }

        @Override
        public void dragExit(DropTargetEvent dte) {

        }

        @Override
        public void drop(DropTargetDropEvent dtde) {
            Transferable transfer = dtde.getTransferable();
            //必须先调用acceptDrop
            dtde.acceptDrop(DnDConstants.ACTION_COPY);
            DataFlavor flav = DataFlavor.javaFileListFlavor;
            try {
                List<File> files = (List)transfer.getTransferData(flav);
                String fileAddr = YmlUtils.getYmlConfiguration().getFileAddr();
                for (File file : files) {
                    String newFile = fileAddr + file.getName();
                    FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                    Files.copy(file.toPath(), fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                PopMessageUtils.success(MsgConsts.SAVE_SUCCESS_MSG);
                //保存后自动关闭frame弹出层
                dropFileFrame.hideFrame();
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
