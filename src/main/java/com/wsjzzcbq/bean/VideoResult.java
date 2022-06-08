package com.wsjzzcbq.bean;

import lombok.Builder;
import lombok.Data;

/**
 * VideoResult
 *
 * @author wsjz
 * @date 2022/05/09
 */
@Builder
@Data
public class VideoResult {

    /**
     * 视频时长(分钟格式)
     */
    private String durationMinute;

    /**
     * 视频时长(小时格式)
     */
    private String durationHour;

    /**
     * 计算耗时
     */
    private String time;
}
