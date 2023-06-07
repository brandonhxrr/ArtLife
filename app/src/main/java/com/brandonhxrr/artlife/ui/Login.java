package com.brandonhxrr.artlife.ui;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.brandonhxrr.artlife.Main;
import com.brandonhxrr.artlife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    TextInputEditText txtEmail;
    TextInputEditText txtPassword;
    MaterialButton loginBtn;
    MaterialTextView btnSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

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
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent startHome = new Intent(Login.this, Main.class);
                            startActivity(startHome);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "No se pudo iniciar sesión",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}