package com.example.eventapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class loginsignupscreen extends AppCompatActivity {

    Button LOGIN,SIGNUP,GUEST;
    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsignupscreen);
        LOGIN=findViewById(R.id.button2);
        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(loginsignupscreen.this,loginscreen.class);
                startActivity(intent);
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////
        SIGNUP=findViewById(R.id.button3);
        SIGNUP.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(loginsignupscreen.this, signupscreen.class);
                startActivity(intent);
            }
        });

    }
}
