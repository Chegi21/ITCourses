package com.bilev.domain.usercase;

import com.bilev.domain.model.LoginValidationState;

import java.util.regex.Pattern;

public class ValidateLoginUseCase {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");

    public LoginValidationState validate(String email, String password) {

        boolean emailValid = isEmailValid(email);
        boolean passwordValid = isPasswordValid(password);

        return new LoginValidationState(
                emailValid,
                passwordValid,
                emailValid && passwordValid
        );
    }

    private boolean isEmailValid(String email) {
        return email != null
                && !email.isEmpty()
                && EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        return password != null && !password.isEmpty();
    }
}
