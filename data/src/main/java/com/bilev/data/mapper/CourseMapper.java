package com.bilev.data.mapper;

import com.bilev.data.dto.CourseDto;
import com.bilev.data.local.entity.FavoriteCourseEntity;
import com.bilev.domain.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {
    public static Course fromDto(CourseDto dto, boolean isFavorite) {
        return new Course(
                dto.getId(),
                dto.getTitle(),
                dto.getText(),
                dto.getPrice(),
                dto.getRate(),
                dto.getStartDate(),
                isFavorite,
                dto.getPublishDate()
        );
    }

    public static List<Course> fromDtoList(List<CourseDto> dtos, List<Integer> favoriteIds) {
        List<Course> result = new ArrayList<>();
        if (dtos == null) return result;
        for (CourseDto dto : dtos) {
            boolean isFav = favoriteIds != null && favoriteIds.contains(dto.getId());
            result.add(fromDto(dto, isFav));
        }
        return result;
    }

    public static Course fromEntity(FavoriteCourseEntity entity) {
        return new Course(
                entity.getId(),
                entity.getTitle(),
                entity.getText(),
                entity.getPrice(),
                entity.getRate(),
                entity.getStartDate(),
                true,
                entity.getPublishDate()
        );
    }

    public static List<Course> fromEntityList(List<FavoriteCourseEntity> entities) {
        List<Course> result = new ArrayList<>();
        if (entities == null) return result;
        for (FavoriteCourseEntity e : entities) {
            result.add(fromEntity(e));
        }
        return result;
    }

    public static FavoriteCourseEntity toEntity(Course course) {
        return new FavoriteCourseEntity(
                course.getId(),
                course.getTitle(),
                course.getText(),
                course.getPrice(),
                course.getRate(),
                course.getStartDate(),
                course.getPublishDate()
        );
    }
}
