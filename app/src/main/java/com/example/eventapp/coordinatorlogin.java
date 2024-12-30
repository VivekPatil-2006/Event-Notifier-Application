package com.example.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class coordinatorlogin extends AppCompatActivity {

    EditText enterNumber;
    Button getOtp, cameraOpen;
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlogin);

        enterNumber = findViewById(R.id.edittext);
        getOtp = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressbar_sending_otp);
        auth = FirebaseAuth.getInstance();

        getOtp.setOnClickListener(view -> {
            String phoneNumber = enterNumber.getText().toString().trim();

            if (phoneNumber != null && !phoneNumber.isEmpty() && phoneNumber.length() == 10) {
                progressBar.setVisibility(View.VISIBLE);
                getOtp.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + phoneNumber,
                        60, TimeUnit.SECONDS,
                        coordinatorlogin.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                                progressBar.setVisibility(View.INVISIBLE);
                                getOtp.setVisibility(View.VISIBLE);
                                signInWithPhoneAuthCredential(credential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.INVISIBLE);
                                getOtp.setVisibility(View.VISIBLE);
                                Toast.makeText(coordinatorlogin.this, "Error, please check internet connection", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String backendOtp, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                progressBar.setVisibility(View.INVISIBLE);
                                getOtp.setVisibility(View.VISIBLE);

                                Intent intent = new Intent(coordinatorlogin.this, OtpVerificationActivity.class);
                                intent.putExtra("mobile", phoneNumber);
                                intent.putExtra("backendotp", backendOtp);
                                startActivity(intent);
                            }
                        });
            } else {
                Toast.makeText(coordinatorlogin.this, "Please enter a correct 10-digit number", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Verification successful, handle sign-in
                        Toast.makeText(coordinatorlogin.this, "Verification successful", Toast.LENGTH_SHORT).show();
                    } else {
                        // Verification failed, display an error message
                        Toast.makeText(coordinatorlogin.this, "Verification failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

