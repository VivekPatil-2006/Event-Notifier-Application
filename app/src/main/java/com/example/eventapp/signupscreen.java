package com.example.eventapp;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupscreen extends AppCompatActivity {
    // TextView signuptologin;
    EditText signup_name, signup_email, signup_password;
    Button signup_button;
    FirebaseAuth mauth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupscreen);
        signup_button = findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(signupscreen.this, null);
                startActivity(intent);
            }
        });


        mauth = FirebaseAuth.getInstance();
        signup_name = findViewById(R.id.signup_name);
        signup_email = findViewById(R.id.signup_email);
        signup_password = findViewById(R.id.signup_password);
        signup_button = findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                createuser();
            }
        });
    }

    private void createuser() {
        String user_email = signup_email.getText().toString();
        String user_password = signup_password.getText().toString();
        if (TextUtils.isEmpty(user_email) || TextUtils.isEmpty(user_password)) {
            Toast.makeText(signupscreen.this, "Please enter the details", Toast.LENGTH_LONG).show();
        } else {
            mauth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(signupscreen.this, new OnCompleteListener<AuthResult>() {
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(signupscreen.this, "Successfully registered", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(signupscreen.this, null));
                    } else {
                        Toast.makeText(signupscreen.this, "Error Occured", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }
}