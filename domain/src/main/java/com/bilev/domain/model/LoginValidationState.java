package com.bilev.domain.model;

public class LoginValidationState {
    public final boolean emailValid;
    public final boolean passwordValid;
    public final boolean canLogin;

    public LoginValidationState(boolean emailValid, boolean passwordValid, boolean canLogin) {
        this.emailValid = emailValid;
        this.passwordValid = passwordValid;
        this.canLogin = canLogin;
    }
}
