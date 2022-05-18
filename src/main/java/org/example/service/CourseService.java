package org.example.service;

import org.example.DTO.Episode;

import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-05-13 15:57
 */
public interface CourseService {
    public void insertBatch(List<Episode> episodes);
}
