package com.death.foodorderingprm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.death.foodorderingprm392.instances.StaticInstance;
import com.death.foodorderingprm392.model.LoginModel;
import com.death.foodorderingprm392.services.AccountService;
import com.death.foodorderingprm392.utils.AppBarUtil;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText inputEmail, inputPassword;
    TextView txtRegister;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkLogin();

        inputEmail = findViewById(R.id.inputLoginEmail);
        inputPassword = findViewById(R.id.inputLoginPassword);

        inputPassword.setTransformationMethod(new PasswordTransformationMethod());

        txtRegister = findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(view -> {
            Intent register = new Intent(this, RegisterActivity.class);
            startActivity(register);
            this.finish();
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> onClick());
    }

    private void onClick() {
        String email = inputEmail.getEditableText().toString().trim();
        String password = inputPassword.getEditableText().toString();

        if (!isInputValid(email, password)) {
            Toast.makeText(this, "Error: Please enter both email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginModel model = new LoginModel(email, password);

        AccountService accountService = new AccountService(this, model);
        accountService.login();
    }

    private boolean isInputValid(String email, String password) {
        return email.length() > 0 && password.length() > 0;
    }

    private void checkLogin() {
        if (StaticInstance.getUser() != null) {
            Intent main = new Intent(this, MainActivity.class);
            this.startActivity(main);
            this.finish();
        }
    }
}