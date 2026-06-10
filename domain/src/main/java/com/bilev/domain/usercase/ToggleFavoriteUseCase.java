package com.bilev.domain.usercase;

import com.bilev.domain.model.Course;
import com.bilev.domain.repository.CourseService;

public class ToggleFavoriteUseCase {

    private final CourseService repository;

    public ToggleFavoriteUseCase(CourseService repository) {
        this.repository = repository;
    }

    public void execute(Course course, boolean currentLikeState) {
        if (currentLikeState) {
            repository.removeFromFavorites(course.getId());
        } else {
            repository.addToFavorites(course);
        }
    }
}
