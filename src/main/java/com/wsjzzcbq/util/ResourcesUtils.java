package com.wsjzzcbq.util;

import org.springframework.core.io.ClassPathResource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ResourcesUtils
 *
 * @author wsjz
 * @date 2022/09/19
 */
public class ResourcesUtils {

    /**
     * 获取文件资源的字节数组
     * @param filePath
     * @return
     */
    public static byte[] getBytesByFilePath(String filePath) {
        ClassPathResource classPathResource = new ClassPathResource(filePath);
        try (InputStream inputStream = classPathResource.getInputStream();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] bytes = new byte[1024];
            while (inputStream.read(bytes) > 0) {
                byteArrayOutputStream.write(bytes);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("filePath 不正确");
        }
    }
}
