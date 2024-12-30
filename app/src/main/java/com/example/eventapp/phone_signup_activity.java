package com.example.eventapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.eventapp.databinding.ActivityPhoneSignupBinding;
import com.google.firebase.auth.FirebaseAuth;
public class phone_signup_activity extends AppCompatActivity {
    private ActivityPhoneSignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize view binding
        binding = ActivityPhoneSignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Auto-login check
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // User already logged in, navigate to MainActivity
            startActivity(new Intent(phone_signup_activity.this, MainActivity.class));
            finishAffinity();
            return; // Prevent further execution
        }

        // Automatically focus on the phone number input field
        binding.edtPhoneNo.requestFocus();

        // Handle continue button click
        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNum = binding.edtPhoneNo.getText().toString().trim();

                if (isValidPhoneNumber(phoneNum)) {
                    // Navigate to OTP verification activity
                    Intent signupToOtp = new Intent(phone_signup_activity.this, OtpVerificationActivity.class);
                    signupToOtp.putExtra("phone_num", phoneNum);
                    startActivity(signupToOtp);
                } else {
                    // Show an error for invalid phone number
                    binding.edtPhoneNo.setError("Enter a valid phone number");
                }
            }
        });
    }

    private boolean isValidPhoneNumber(String phoneNum) {
        // Ensure the phone number is not empty and has at least 10 digits
        return !TextUtils.isEmpty(phoneNum) && phoneNum.length() == 10 && phoneNum.matches("\\d+");
    }
}
