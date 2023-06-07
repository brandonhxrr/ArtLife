package com.brandonhxrr.artlife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;

import com.brandonhxrr.artlife.MainActivity;
import com.brandonhxrr.artlife.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    TextInputEditText txtEmail;
    TextInputEditText txtPassword;
    MaterialButton loginBtn;
    MaterialTextView btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        loginBtn = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_signup);

        loginBtn.setOnClickListener(v -> {
            validateData();
        });

        btnSignUp.setOnClickListener(v -> {
            Intent startSignUp = new Intent(Login.this, SignUp.class);
            startActivity(startSignUp);
        });

        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtEmail.setError(null, null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    void validateData(){
        Pattern pattern = Patterns.EMAIL_ADDRESS;

        String email = String.valueOf(txtEmail.getText());
        String password = String.valueOf(txtPassword.getText());

        if(email.equals("") || !pattern.matcher(email).matches()){
            txtEmail.setError("Correo inválido");
        }else{
            //TODO: Delete this and implement login with Firebase
            Intent startHome = new Intent(Login.this, MainActivity.class);
            startActivity(startHome);
        }
    }
}