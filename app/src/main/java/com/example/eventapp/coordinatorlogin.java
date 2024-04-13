package com.example.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class coordinatorlogin extends AppCompatActivity {

    EditText enternumber;
    Button getotp,camera_open;
    ProgressBar progressBar;
    FirebaseAuth auth;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlogin);

        /*
        enternumber = findViewById(R.id.edittext);
        getotp = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressbar_sending_otp);
        auth = FirebaseAuth.getInstance();

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = enternumber.getText().toString().trim();

                if (!phoneNumber.isEmpty() && phoneNumber.length() == 10) {
                    progressBar.setVisibility(View.VISIBLE);
                    getotp.setVisibility(View.INVISIBLE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + phoneNumber,
                            60, TimeUnit.SECONDS,
                            coordinatorlogin.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    getotp.setVisibility(View.VISIBLE);
                                    signInWithPhoneAuthCredential(phoneAuthCredential);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    getotp.setVisibility(View.VISIBLE);
                                    Toast.makeText(coordinatorlogin.this, "Error, please check internet connection", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    getotp.setVisibility(View.VISIBLE);

                                    Intent intent = new Intent(coordinatorlogin.this, coordinatorloginreceived.class);
                                    intent.putExtra("mobile", phoneNumber);
                                    intent.putExtra("backendotp", backendotp);
                                    startActivity(intent);
                                }
                            });
                } else {
                    Toast.makeText(coordinatorlogin.this, "Please enter a correct 10-digit number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        camera_open = findViewById(R.id.button_camera);
        camera_open.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(coordinatorlogin.this, dashboard.class);
                startActivity(intent);
            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Verification successful, handle sign-in
                        Toast.makeText(coordinatorlogin.this, "Verification successful", Toast.LENGTH_SHORT).show();
                        // You can add additional actions here if needed
                    } else {
                        // Verification failed, display an error message
                        Toast.makeText(coordinatorlogin.this, "Verification failed", Toast.LENGTH_SHORT).show();
                    }
                });
     */
        //////////////////////////////////////////////////////////////////

        go=findViewById(R.id.button_camera);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(coordinatorlogin.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
