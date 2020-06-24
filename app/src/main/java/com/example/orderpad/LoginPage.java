package com.example.orderpad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignIn;
    TextView textV;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.username_reg);
        password = findViewById(R.id.password_reg);
        btnSignIn = findViewById(R.id.login);
//        btnSignIn = (Button) findViewById(R.id.login);

        textV = findViewById(R.id.sign_up_lg);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Toast.makeText(LoginPage.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginPage.this, NewOrdersTest.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginPage.this, "Please login", Toast.LENGTH_SHORT).show();

                }
            }


        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()) {
                    emailId.setError("Please enter an email");
                    emailId.requestFocus();
                }else if(pwd.isEmpty()) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                }else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginPage.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                }else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(
                            LoginPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(getApplicationContext(), LoginPage.class));
                                    }else {
                                        Toast.makeText(LoginPage.this, "Login Unsuccessful, please try again", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(LoginPage.this, "Error Occurred", Toast.LENGTH_SHORT).show();

                }
            }
        });

        textV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginPage.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }
}

