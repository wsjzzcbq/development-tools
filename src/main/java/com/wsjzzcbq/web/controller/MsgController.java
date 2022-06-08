package com.wsjzzcbq.web.controller;

import com.wsjzzcbq.common.queue.MsgQueue;
import com.wsjzzcbq.util.DateUtils;
import com.wsjzzcbq.util.FileUtils;
import com.wsjzzcbq.web.bean.FilePLus;
import com.wsjzzcbq.web.bean.Msg;
import com.wsjzzcbq.web.bean.R;
import com.wsjzzcbq.web.service.FileService;
import com.wsjzzcbq.web.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequestMapping("/msg")
@RestController
public class MsgController {

    @Value("${fileAddr}")
    private String fileAddr;

    @Autowired
    private FileService fileService;

    @Autowired
    private MsgService msgService;

    /**
     * 获取 websocket连接地址
     * @return
     */
    @GetMapping("/get/websocket/info")
    public R<?> getWebSocketInfo() {
        return msgService.getWebSocketInfo();
    }

    @PostMapping("/send")
    public R<?> sendMsg(@RequestBody(required = false) Msg msg) {
        System.out.println(msg.getContent());
        MsgQueue.msgLinkedBlockingDeque.push(msg);
        return R.ok();
    }

    @PostMapping("/file/upload")
    public R<?> sendFile(MultipartFile file) {
        String originalFilename = FileUtils.getFileName(file.getOriginalFilename());
        String fileType = FileUtils.getFileType(file.getOriginalFilename());
        String fileName = originalFilename + "-" + DateUtils.nowDateString() + fileType;
        String newFileName = fileAddr + File.separator + fileName;

        File newFile = new File(newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        log.info("上传文件: " + newFileName);
        return R.ok();
    }

    @GetMapping("/file/list")
    public R<List<FilePLus>> fileList() {
        List<FilePLus> list = fileService.fileList();
        return R.ok(list);
    }
}
