package com.wsjzzcbq.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * FilePathConfig
 *
 * @author wsjz
 * @date 2022/06/01
 */
@Configuration
public class FilePathConfig implements WebMvcConfigurer {

    @Value("${fileAddr}")
    private String fileAddr;

    @Value("${filePath}")
    private String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(filePath).addResourceLocations("file:" + fileAddr);
    }
}
