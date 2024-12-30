package com.example.eventapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class robotics_event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robotics_event);

        Button openGoogleButton = findViewById(R.id.robotics);
        openGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleLink();
            }
        });
    }

    private void openGoogleLink() {
        String googleLink = "https://docs.google.com/forms/d/e/1FAIpQLSe3f-0418KjxD1vYXgUt5Jyxq7dwGF9-3luuD_bvIw1aBI04Q/viewform?usp=dialog";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleLink));
        startActivity(intent);
    }
}
