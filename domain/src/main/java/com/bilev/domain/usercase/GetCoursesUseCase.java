package com.bilev.domain.usercase;

import androidx.lifecycle.LiveData;

import com.bilev.domain.model.Course;
import com.bilev.domain.repository.CourseService;

import java.util.List;

public class GetCoursesUseCase {

    private final CourseService repository;

    public GetCoursesUseCase(CourseService repository) {
        this.repository = repository;
    }

    public LiveData<List<Course>> execute() {
        return repository.getCourses();
    }
}
