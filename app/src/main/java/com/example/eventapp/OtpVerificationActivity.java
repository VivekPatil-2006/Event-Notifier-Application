package com.example.eventapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import com.example.eventapp.databinding.ActivityOtpVerificationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerificationActivity extends AppCompatActivity {
    private ActivityOtpVerificationBinding binding;
    private String phoneNum, verificationCode;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final String countryCode = "+91";
    private long timeout = 60;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize view binding
        binding = ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get phone number and set screen
        getPhoneNumberAndSetScreen();

        // Send OTP
        sendOtp(phoneNum, false);

        // Verify button click listener
        binding.btnVerify.setOnClickListener(view -> {
            String otp = binding.edtOtp.getText().toString();
            if (otp.isEmpty() || otp.length() < 6) {
                binding.edtOtp.setError("Enter a valid OTP");
                return;
            }
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
            signIn(credential);
        });

        // Resend OTP button click listener
        binding.btnSendAgainOtp.setOnClickListener(view -> {
            binding.progressBarSignup.setVisibility(View.VISIBLE);
            sendOtp(phoneNum, true);
        });
    }

    /**
     * Sends OTP to the provided phone number.
     */
    private void sendOtp(String phoneNum, boolean isResend) {
        startResendTimer();

        PhoneAuthOptions.Builder options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(countryCode + phoneNum)
                        .setTimeout(timeout, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                                signIn(credential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(OtpVerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                navigateToPhoneSignupActivity();
                            }

                            @Override
                            public void onCodeSent(@NonNull String code, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                Toast.makeText(OtpVerificationActivity.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                                binding.edtOtp.requestFocus();
                                verificationCode = code;
                                resendToken = token;
                                binding.llyoutOtp.setVisibility(View.VISIBLE);
                                binding.progressBarSignup.setVisibility(View.INVISIBLE);
                                binding.txtSendingOtp.setText(R.string.otp_send);
                            }
                        });

        if (isResend) {
            PhoneAuthProvider.verifyPhoneNumber(options.setForceResendingToken(resendToken).build());
        } else {
            PhoneAuthProvider.verifyPhoneNumber(options.build());
        }
    }

    /**
     * Retrieves the phone number from intent and sets the screen text.
     */
    private void getPhoneNumberAndSetScreen() {
        Intent intent = getIntent();
        phoneNum = intent.getStringExtra("phone_num");
        if (phoneNum != null) {
            binding.txtPhoneNum.setText(String.format("%s %s", countryCode, phoneNum));
        }
    }

    /**
     * Signs in the user with the provided credential.
     */
    private void signIn(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(OtpVerificationActivity.this, "Successfully Signed In", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OtpVerificationActivity.this, NameActivity.class));
                finishAffinity();
            } else {
                Toast.makeText(OtpVerificationActivity.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                navigateToPhoneSignupActivity();
            }
        });
    }

    /**
     * Starts the resend timer.
     */
    private void startResendTimer() {
        binding.btnSendAgainOtp.setEnabled(false);
        timeout = 60;

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (timeout > 0) {
                    binding.btnSendAgainOtp.setText(String.format("Resend OTP in %d seconds", timeout));
                    timeout--;
                    handler.postDelayed(this, 1000);
                } else {
                    binding.btnSendAgainOtp.setEnabled(true);
                    binding.btnSendAgainOtp.setText("Resend OTP");
                }
            }
        };
        handler.post(runnable);
    }

    /**
     * Navigates to the Phone Signup Activity.
     */
    private void navigateToPhoneSignupActivity() {
        startActivity(new Intent(OtpVerificationActivity.this, phone_signup_activity.class));
        //startActivity(new Intent(OtpVerificationActivity.this, coordinatorlogin.class));

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop timer to avoid memory leaks
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}

