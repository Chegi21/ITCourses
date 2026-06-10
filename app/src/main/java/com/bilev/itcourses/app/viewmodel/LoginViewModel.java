package com.bilev.itcourses.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bilev.domain.model.LoginValidationState;
import com.bilev.domain.usercase.ValidateLoginUseCase;


public class LoginViewModel extends ViewModel {


    private final ValidateLoginUseCase validateLoginUseCase;

    private final MutableLiveData<String> emailLiveData = new MutableLiveData<>("");
    private final MutableLiveData<String> passwordLiveData = new MutableLiveData<>("");

    private final MediatorLiveData<LoginValidationState> stateLiveData = new MediatorLiveData<>();

    public LoginViewModel(ValidateLoginUseCase validateLoginUseCase) {
        this.validateLoginUseCase = validateLoginUseCase;

        initSources();
    }

    private void initSources() {
        stateLiveData.addSource(emailLiveData, email -> update(email, passwordLiveData.getValue()));
        stateLiveData.addSource(passwordLiveData, password -> update(emailLiveData.getValue(), password));
    }

    public void onEmailChanged(String email) {
        emailLiveData.setValue(email);
    }

    public void onPasswordChanged(String password) {
        passwordLiveData.setValue(password);
    }

    private void update(String email, String password) {
        stateLiveData.setValue(validateLoginUseCase.validate(email, password));
    }

    public LiveData<LoginValidationState> getState() {
        return stateLiveData;
    }
}
