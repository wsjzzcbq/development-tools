package com.wsjzzcbq.web.service;

import com.wsjzzcbq.web.bean.FilePLus;
import java.util.List;

/**
 * FileService
 *
 * @author wsjz
 * @date 2022/06/01
 */
public interface FileService {

    /**
     * 获取文件列表
     * @return
     */
    List<FilePLus> fileList();
}
