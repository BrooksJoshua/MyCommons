package org.example.service.impl;

import org.example.DTO.Episode;
import org.example.mapper.CourseMapper;
import org.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-05-13 15:56
 */
@Service("CourseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public void insertBatch(List<Episode> episodes) {
        courseMapper.insertBatch(episodes);
    }
}
