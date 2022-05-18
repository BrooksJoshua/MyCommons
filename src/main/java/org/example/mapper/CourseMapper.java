package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.DTO.Episode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-05-13 15:48
 */
@Mapper
public interface CourseMapper {
    /**
     * 将episodes批量插入表
     * @param episodes
     */
    public void insertBatch(@Param("episodes") List<Episode> episodes);
}
