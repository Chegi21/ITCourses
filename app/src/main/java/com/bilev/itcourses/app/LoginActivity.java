package com.bilev.itcourses.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bilev.itcourses.R;
import com.bilev.itcourses.app.viewmodel.LoginViewModel;
import com.bilev.itcourses.app.viewmodelfactory.LoginViewModelFactory;
import com.bilev.itcourses.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private boolean emailTouched = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViewModel();
        observeState();
        setupFields();
        setupButtons();
        setupFocusListeners();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
    }

    private void observeState() {
        viewModel.getState().observe(this, state -> {
            binding.btnLogin.setEnabled(state.canLogin);

            if (emailTouched) {
                binding.etEmail.setError(
                        state.emailValid ? null : getString(R.string.error_email)
                );
            }
        });
    }

    private void setupFields() {
        InputFilter cyrillicFilter = (source, start, end, dest, dstart, dend) -> {
            StringBuilder result = new StringBuilder();

            for (int i = start; i < end; i++) {
                char c = source.charAt(i);
                if (!(c >= 'Ѐ' && c <= 'ӿ')) {
                    result.append(c);
                }
            }

            return result.toString();
        };

        binding.etEmail.setFilters(new InputFilter[]{cyrillicFilter});

        binding.etEmail.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                viewModel.onEmailChanged(s.toString());
            }
        });

        binding.etPassword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                viewModel.onPasswordChanged(s.toString());
            }
        });
    }

    private void setupButtons() {
        binding.btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        binding.btnVk.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_vk)))));

        binding.btnOk.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_ok)))));
    }

    private void setupFocusListeners() {
        binding.etEmail.setOnFocusChangeListener((v, hasFocus) -> {
            emailTouched = hasFocus;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private abstract static class SimpleTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }
}
