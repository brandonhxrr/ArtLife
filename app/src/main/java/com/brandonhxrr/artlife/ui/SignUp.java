package com.brandonhxrr.artlife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Toast;

import com.brandonhxrr.artlife.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    TextInputEditText txtName;
    TextInputEditText txtEmail;
    TextInputEditText txtPassword;
    TextInputEditText txtRepeatPassword;
    MaterialButton signUpButton;
    MaterialTextView btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtName = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        txtRepeatPassword = findViewById(R.id.txt_repeat_password);

        signUpButton = findViewById(R.id.btn_signup);
        btnLogin = findViewById(R.id.btn_login);

        signUpButton.setOnClickListener(v -> {
            validate();
        });

        btnLogin.setOnClickListener(v -> {
            //Intent startLogin = new Intent(SignUp.this, Login.class);
            //startActivity(startLogin);
            finish();
        });

        txtName.addTextChangedListener(textWatcher(txtName));
        txtEmail.addTextChangedListener(textWatcher(txtEmail));
    }

    void validate(){
        String name = String.valueOf(txtName.getText());
        String email = String.valueOf(txtEmail.getText());
        String password = String.valueOf(txtPassword.getText());
        String repeatPassword = String.valueOf(txtRepeatPassword.getText());

        Pattern pattern = Patterns.EMAIL_ADDRESS;

        if(name.equals("")){
            txtName.setError("El nombre no puede estar vacío");
        }else if(email.equals("") || !pattern.matcher(email).matches()){
            txtEmail.setError("Correo inválido");
        }else if(password.equals("")){
            Toast.makeText(SignUp.this, "Contraseña inválida", Toast.LENGTH_SHORT).show();
        }else if(!password.equals(repeatPassword)){
            Toast.makeText(SignUp.this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
        }else if(password.length() < 8){
            Toast.makeText(SignUp.this, "Contraseña muy corta", Toast.LENGTH_SHORT).show();
        }
    }

    TextWatcher textWatcher(TextInputEditText editText){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setError(null, null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }


}