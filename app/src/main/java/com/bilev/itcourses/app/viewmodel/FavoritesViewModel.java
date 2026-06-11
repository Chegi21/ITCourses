package com.bilev.itcourses.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bilev.domain.model.Course;
import com.bilev.domain.usercase.GetFavoriteCoursesUseCase;
import com.bilev.domain.usercase.ToggleFavoriteUseCase;

import java.util.List;

public class FavoritesViewModel extends ViewModel {

    private final GetFavoriteCoursesUseCase getFavoriteCoursesUseCase;
    private final ToggleFavoriteUseCase toggleFavoriteUseCase;

    public FavoritesViewModel(GetFavoriteCoursesUseCase getFavoriteCoursesUseCase,
                              ToggleFavoriteUseCase toggleFavoriteUseCase) {
        this.getFavoriteCoursesUseCase = getFavoriteCoursesUseCase;
        this.toggleFavoriteUseCase = toggleFavoriteUseCase;
    }

    public LiveData<List<Course>> getFavoriteCourses() {
        return getFavoriteCoursesUseCase.execute();
    }

    public void removeFromFavorites(Course course) {
        toggleFavoriteUseCase.execute(course, true);
    }
}
