package com.wsjzzcbq.util;

import javazoom.jl.player.Player;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

/**
 * AudioUtils
 *
 * @author wsjz
 * @date 2022/02/23
 */
public class AudioUtils {

    public static void play() {
        try {
            URI uri = AudioUtils.class.getClassLoader().getResource("file/1.mp3").toURI();
            File file = new File(uri);
            FileInputStream inputStream = new FileInputStream(file);
            Player player = new Player(inputStream);
            player.play();
        } catch (Exception e) {

        }
    }
}
