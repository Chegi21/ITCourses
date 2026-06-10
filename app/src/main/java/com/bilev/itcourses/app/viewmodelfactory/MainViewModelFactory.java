package com.bilev.itcourses.app.viewmodelfactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bilev.domain.repository.CourseService;
import com.bilev.domain.usercase.GetCoursesUseCase;
import com.bilev.domain.usercase.ToggleFavoriteUseCase;
import com.bilev.itcourses.app.viewmodel.MainViewModel;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final CourseService repository;

    public MainViewModelFactory(CourseService repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(
                    new GetCoursesUseCase(repository),
                    new ToggleFavoriteUseCase(repository)
            );
        }
        throw new IllegalArgumentException("Unknown ViewModel: " + modelClass.getName());
    }
}
