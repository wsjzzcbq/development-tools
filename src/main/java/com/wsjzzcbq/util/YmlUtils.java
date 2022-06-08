package com.wsjzzcbq.util;

import com.wsjzzcbq.web.bean.YmlConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class YmlUtils {

    private static final String bootstrap_file = "classpath:application.yml";

    public static Map<String,String> getYmlByFileName(String... keys) {
        Map<String,String> result = new HashMap<>();
        String fileAddr = bootstrap_file;
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(ResourceUtils.getFile(fileAddr)))){
            Yaml props = new Yaml();
            Map<String,Object> map = props.loadAs(inputStream, Map.class);

            map.forEach((k,v)-> System.out.println(k+ ":" + v));

            Object obj = props.loadAs(inputStream,Map.class);
            Map<String,Object> param = (Map<String, Object>) obj;

            return result;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public static int getServerPortByYml() {
        String fileAddr = bootstrap_file;
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(ResourceUtils.getFile(fileAddr)))){
            Yaml props = new Yaml();
            YmlConfiguration ymlConfiguration = props.loadAs(inputStream, YmlConfiguration.class);
            return ymlConfiguration.getServer().getPort();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return -1;
    }

    /**
     * 获取全部配置
     * @return
     */
    public static YmlConfiguration getYmlConfiguration() {
        String fileAddr = bootstrap_file;
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(ResourceUtils.getFile(fileAddr)))){
            Yaml props = new Yaml();
            return props.loadAs(inputStream, YmlConfiguration.class);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

}
