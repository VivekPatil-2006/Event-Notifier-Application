package com.example.eventapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class car_event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_event);

        Button openGoogleButton = findViewById(R.id.car);
        openGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleLink();
            }
        });
    }

    private void openGoogleLink() {
        String googleLink = "https://forms.gle/fa2krRoUDdFiAExQ8";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleLink));
        startActivity(intent);
    }
}
