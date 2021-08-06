package com.neon.devmobile.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;

import androidx.appcompat.app.AppCompatActivity;

import com.neon.devmobile.R;
import com.neon.devmobile.databinding.ActivityLoginBinding;
import com.neon.devmobile.utils.ErrorUtils;
import com.neon.devmobile.utils.KeyboardUtils;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configActivity();
    }

    @Override
    public void onBackPressed() {
    }

    public void configActivity() {
        ErrorUtils.setContext(LoginActivity.this);
        KeyboardUtils.setActivity(LoginActivity.this);
        loadActions();
        validateLogin();
    }

    public void loadActions() {
        ErrorUtils.addErrorListener(binding.etUsername, binding.llUserName, binding.tvErrorUserName);
        ErrorUtils.addErrorListener(binding.etPassword, binding.llPassword, binding.tvErrorPassword);
        KeyboardUtils.clearFocusImeDone(binding.etUsername);
        KeyboardUtils.clearFocusImeDone(binding.etPassword);
        togglePassword();
    }

    public void validateLogin() {
        binding.llSignIn.setOnClickListener(view -> {
            if (validFields()) {
                KeyboardUtils.hideKeyboard();
                binding.etPassword.clearFocus();
                startActivity(new Intent(LoginActivity.this, UploadActivity.class));
            }
        });
    }

    public boolean validFields() {
        ErrorUtils.setError(binding.llUserName, binding.tvErrorUserName, false);
        ErrorUtils.setError(binding.llPassword, binding.tvErrorPassword, false);
        return isValidUsername() && isValidPassword();
    }

    public boolean isValidUsername() {
        if (ErrorUtils.isEmpty(binding.etUsername)) {
            ErrorUtils.setError(binding.llUserName, binding.tvErrorUserName, true);
            KeyboardUtils.toggleKeyboard(binding.etUsername);
            return false;
        }
        return true;
    }

    public boolean isValidPassword() {
        if (ErrorUtils.isEmpty(binding.etPassword)) {
            ErrorUtils.setError(binding.llPassword, binding.tvErrorPassword, getString(R.string.insert_valid_password), true);
            KeyboardUtils.toggleKeyboard(binding.etPassword);
            return false;
        } else if (ErrorUtils.isValidPassword(binding.etPassword)) {
            ErrorUtils.setError(binding.llPassword, binding.tvErrorPassword, getString(R.string.insert_valid_password_lenght), true);
            KeyboardUtils.toggleKeyboard(binding.etPassword);
            return false;
        }
        return true;
    }

    public void togglePassword() {
        binding.ivPasswordLocked.setOnClickListener(view -> {
            switch (binding.etPassword.getInputType()) {
                case (InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT): {
                    lockPassword(false);
                }
                break;
                case (InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT): {
                    lockPassword(true);
                }
                break;
            }
        });
    }

    public void lockPassword(boolean lock) {
        binding.etPassword.setInputType(lock ? InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT : InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT);
        binding.etPassword.setTypeface(binding.etUsername.getTypeface());
        binding.etPassword.setSelection(binding.etPassword.getText().length());
        binding.ivPasswordLocked.setImageResource(lock ? R.drawable.ic_locked : R.drawable.ic_unlocked);
    }

}