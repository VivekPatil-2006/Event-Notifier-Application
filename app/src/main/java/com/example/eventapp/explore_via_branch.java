package com.example.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class explore_via_branch extends AppCompatActivity {

    ImageView computerEvent, civilEvent, entcEvent, mechanicalEvent, others;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_via_branch);

        computerEvent = findViewById(R.id.imageView18);
        computerEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(explore_via_branch.this, Computer_Event.class);
                startActivity(intent);
            }
        });

        civilEvent = findViewById(R.id.imageView19);
        civilEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(explore_via_branch.this, Civil_Event.class);
                startActivity(intent);
            }
        });

        entcEvent = findViewById(R.id.imageView20);
        entcEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(explore_via_branch.this, Entc_Event.class);
                startActivity(intent);
            }
        });

        mechanicalEvent = findViewById(R.id.imageView21);
        mechanicalEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(explore_via_branch.this, Mechanical_Event.class);
                startActivity(intent);
            }
        });

        others = findViewById(R.id.imageView22);
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(explore_via_branch.this, Others.class);
                startActivity(intent);
            }
        });
    }
}
