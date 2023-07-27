package com.wsjzzcbq.util;

import com.wsjzzcbq.web.bean.YmlConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class YmlUtils {

    private static final String bootstrap_file = "application.yml";

    /**
     * 获取全部配置
     * @return
     */
    public static YmlConfiguration getYmlConfiguration() {
        ClassPathResource classPathResource = new ClassPathResource(bootstrap_file);
        InputStream inputStream = null;
        try {
            inputStream = classPathResource.getInputStream();
            Yaml props = new Yaml();
            return props.loadAs(inputStream, YmlConfiguration.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

}
