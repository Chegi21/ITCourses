package com.bilev.domain.repository;

import androidx.lifecycle.LiveData;

import com.bilev.domain.model.Course;

import java.util.List;

public interface CourseService {

    LiveData<List<Course>> getCourses();

    LiveData<List<Course>> getFavoriteCourses();

    void addToFavorites(Course course);

    void removeFromFavorites(int courseId);

    LiveData<Boolean> isFavorite(int courseId);
}
