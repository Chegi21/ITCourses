package com.bilev.domain.usercase;

import androidx.lifecycle.LiveData;

import com.bilev.domain.model.Course;
import com.bilev.domain.repository.CourseService;

import java.util.List;

public class GetFavoriteCoursesUseCase {

    private final CourseService repository;

    public GetFavoriteCoursesUseCase(CourseService repository) {
        this.repository = repository;
    }

    public LiveData<List<Course>> execute() {
        return repository.getFavoriteCourses();
    }
}
