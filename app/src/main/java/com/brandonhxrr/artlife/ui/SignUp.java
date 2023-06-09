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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    TextInputEditText txtName;
    TextInputEditText txtEmail;
    TextInputEditText txtPassword;
    TextInputEditText txtRepeatPassword;
    MaterialButton signUpButton;
    MaterialTextView btnLogin;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    SignInButton signInButton;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        txtName = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        txtRepeatPassword = findViewById(R.id.txt_repeat_password);
        signInButton = findViewById(R.id.sign_in_button);

        signUpButton = findViewById(R.id.btn_signup);
        btnLogin = findViewById(R.id.btn_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("347410151845-bgp9nesepcu5scslrhgrl5d2rktd4uht.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(SignUp.this, gso);

        signUpButton.setOnClickListener(v -> {
            validate();
        });

        btnLogin.setOnClickListener(v -> {
            finish();
        });

        signInButton.setOnClickListener(v -> {
            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent, 100);

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
        }else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        FirebaseUser user = mAuth.getCurrentUser();

                        if (task.isSuccessful()) {
                            UserProfileChangeRequest changeDisplayName = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();

                            assert user != null;
                            user.updateProfile(changeDisplayName)
                                    .addOnSuccessListener(unused -> {
                                        Intent start = new Intent(SignUp.this, Main.class);
                                        startActivity(start);
                                    }).addOnFailureListener(e -> {
                                        Toast.makeText(SignUp.this, "No se pudo crear el usuario", Toast.LENGTH_SHORT).show();
                                        Log.d("ART100-ERROR: ", e.getLocalizedMessage());
                                    });
                        } else {
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
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
                                    Intent startHome = new Intent(SignUp.this, Main.class);
                                    startActivity(startHome);
                                } else {
                                    Toast.makeText(SignUp.this, "No se pudo iniciar sesión", Toast.LENGTH_SHORT).show();
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