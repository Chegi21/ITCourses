package com.bilev.itcourses.app.viewmodelfactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bilev.domain.repository.CourseService;
import com.bilev.domain.usercase.GetFavoriteCoursesUseCase;
import com.bilev.domain.usercase.ToggleFavoriteUseCase;
import com.bilev.itcourses.app.viewmodel.FavoritesViewModel;

public class FavoritesViewModelFactory implements ViewModelProvider.Factory {

    private final CourseService repository;

    public FavoritesViewModelFactory(CourseService repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavoritesViewModel.class)) {
            return (T) new FavoritesViewModel(
                    new GetFavoriteCoursesUseCase(repository),
                    new ToggleFavoriteUseCase(repository)
            );
        }
        throw new IllegalArgumentException("Unknown ViewModel: " + modelClass.getName());
    }
}
