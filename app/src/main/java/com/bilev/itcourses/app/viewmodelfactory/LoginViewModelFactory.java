package com.bilev.itcourses.app.viewmodelfactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bilev.domain.usercase.ValidateLoginUseCase;
import com.bilev.itcourses.app.viewmodel.LoginViewModel;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(new ValidateLoginUseCase());
        }
        throw new IllegalArgumentException("Unknown ViewModel: " + modelClass.getName());
    }
}
