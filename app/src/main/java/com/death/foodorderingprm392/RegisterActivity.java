package com.death.foodorderingprm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.death.foodorderingprm392.model.RegisterModel;
import com.death.foodorderingprm392.services.AccountService;
import com.death.foodorderingprm392.utils.AppBarUtil;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextView txtLogin;
    TextInputEditText inputName, inputPhone, inputEmail, inputPassword;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppBarUtil.hideAppBar(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtLogin = findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(view -> {
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
            this.finish();
        });

        inputName = findViewById(R.id.inputRegisterName);
        inputPhone = findViewById(R.id.inputRegisterPhone);
        inputEmail = findViewById(R.id.inputRegisterEmail);
        inputPassword = findViewById(R.id.inputRegisterPassword);

        inputPassword.setTransformationMethod(new PasswordTransformationMethod());

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(view -> onClick());
    }

    private void onClick() {
        String name = inputName.getEditableText().toString();
        String phone = inputPhone.getEditableText().toString();
        String email = inputEmail.getEditableText().toString();
        String password = inputPassword.getEditableText().toString();

        if (!isInputValid(name, phone, email, password)) {
            Toast.makeText(this, "Please fill all the blank", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterModel model = new RegisterModel(name, email, phone, password);

        AccountService accountService = new AccountService(this, model);
        accountService.register();
    }

    private boolean isInputValid(String name, String phone, String email, String password) {
        return name.length() > 0 && phone.length() > 0 && email.length() > 0 && password.length() > 0;
    }
}