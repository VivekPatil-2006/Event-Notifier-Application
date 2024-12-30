package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.eventapp.databinding.ActivitySplashScreenBinding;
public class SplashScreenActivity extends AppCompatActivity {
    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        showMainActivity();
    }
    private void showMainActivity() {
        // Changing screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash_to_Signup=new Intent(SplashScreenActivity.this, userselection.class);
                startActivity(splash_to_Signup);
                finish();
            }
        },2000);
    }
}


