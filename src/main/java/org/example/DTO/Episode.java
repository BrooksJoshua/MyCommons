package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-05-13 11:39
 */
@Data
@AllArgsConstructor
@ToString
public class Episode {
    /**
     * 课程名， 会从文件名获取
     */
    private String courseName;
    /**
     * 本集序号
     */
    private String pageNum;
    /**
     * 本集民称
     */
    private String title;
    /**
     * 本集时长 mm:SS时间格式
     */
    private String duration;

    /**
     * 本集时长 minutes
     */
    private Integer minutes;
    /**
     * 本集url后半部分
     */
    private String href;
    /**
     * 时间
     */
    private Date createTime;
}
