package com.brandonhxrr.artlife.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    TextInputEditText txtEmail;
    TextInputEditText txtPassword;
    MaterialButton loginBtn;
    MaterialTextView btnSignUp;
    private FirebaseAuth mAuth;
    SignInButton signInButton;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("347410151845-bgp9nesepcu5scslrhgrl5d2rktd4uht.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(Login.this, gso);

        mAuth = FirebaseAuth.getInstance();
        signInButton = findViewById(R.id.sign_in_button);

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

        signInButton.setOnClickListener(v -> {
            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent, 100);
        });

    }

    void validateData(){
        Pattern pattern = Patterns.EMAIL_ADDRESS;

        String email = String.valueOf(txtEmail.getText());
        String password = String.valueOf(txtPassword.getText());

        if(email.equals("") || !pattern.matcher(email).matches()){
            txtEmail.setError("Correo inválido");
        }if(password.equals("")){
            txtEmail.setError("Llena todos los campos");
        }else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent startHome = new Intent(Login.this, Main.class);
                            startActivity(startHome);
                        } else {
                            Log.w("ART100: ", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "No se pudo iniciar sesión",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);

            if (signInAccountTask.isSuccessful()) {
                try {
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);

                    if (googleSignInAccount != null) {
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent startHome = new Intent(Login.this, Main.class);
                                    startActivity(startHome);
                                } else {
                                    Toast.makeText(Login.this, "No se pudo iniciar sesión", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}