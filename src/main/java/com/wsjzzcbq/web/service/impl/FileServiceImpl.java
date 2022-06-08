package com.wsjzzcbq.web.service.impl;

import com.wsjzzcbq.constant.NetworkProtocolConsts;
import com.wsjzzcbq.util.IPUtils;
import com.wsjzzcbq.web.bean.FilePLus;
import com.wsjzzcbq.web.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * FileServiceImpl
 *
 * @author wsjz
 * @date 2022/06/01
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${fileAddr}")
    private String fileAddr;

    @Value("${filePath}")
    private String filePath;

    @Autowired
    private Environment env;

    @Override
    public List<FilePLus> fileList() {
        String ip = IPUtils.getIpv4();
        String port = env.getProperty("server.port");
        //拼接下载地址前缀
        String http = NetworkProtocolConsts.HTTP + ip + ":" + port + "/" + filePath.substring(0, filePath.length()-2);

        List<File> fileList = scanFile(new File(fileAddr));
        List<FilePLus> files = new ArrayList<>();
        fileList.forEach(f->{
            Long createTime = getFileCreateTime(f);
            String createDate = getDateString(createTime);
            String fileUrl = f.getAbsolutePath().substring(fileAddr.length()).replaceAll("\\\\", "\\/");

            FilePLus filePLus = FilePLus.builder()
                    .name(f.getName())
                    .addr(f.getAbsolutePath())
                    .path(http + fileUrl)
                    .createTime(createTime)
                    .createDate(createDate)
                    .build();
            files.add(filePLus);
        });
        //根据创建时间排序
        files.sort(Comparator.comparing(FilePLus::getCreateTime).reversed());

        return files;
    }

    /**
     * 扫描文件
     * @param file
     * @return
     */
    private List<File> scanFile(File file) {
        List<File> list = new LinkedList<>();
        recursion(file, list);
        return list;
    }

    private void recursion(File file, List<File> list) {
        if (file.isDirectory() && file.listFiles() != null && file.listFiles().length > 0) {
            for (File f : file.listFiles()) {
                recursion(f, list);
            }
        }

        if (file.isFile()) {
            list.add(file);
        }
    }

    /**
     * 获取文件创建时间
     * @param file
     * @return
     */
    private long getFileCreateTime(File file) {
        try {
            FileTime fileTime = Files.readAttributes(file.toPath(), BasicFileAttributes.class).creationTime();
            return fileTime.toMillis();
        } catch (Exception e) {
            return 0L;
        }
    }

    /**
     * Long类型时间转字符串
     * @param time
     * @return
     */
    private String getDateString(long time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
        return date;
    }
}
