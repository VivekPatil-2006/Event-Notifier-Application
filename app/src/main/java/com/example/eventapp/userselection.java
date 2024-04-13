package com.example.eventapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class userselection extends AppCompatActivity
{
    ImageView studentlogin,coordinatorlogin,guestlogin;
    @SuppressLint("MissingInflatedId")
    @Override

    /////////////////////////////////////////////////////////////////////////////////////////////////
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userselection);
        studentlogin = findViewById(R.id.studentlogin);
        studentlogin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(userselection.this, loginsignupscreen.class);
                startActivity(intent);
            } });
        //////////////////////////////////////////////////////////////////////////
        coordinatorlogin = findViewById(R.id.coordinator_login);
        coordinatorlogin.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                //Intent intent = new Intent(userselection.this,phone_signup_activity.class);
                Intent intent = new Intent(userselection.this,Coordinator_security.class);
                startActivity(intent);
            } });
        ////////////////////////////////////////////////////////////////////////////
        guestlogin = findViewById(R.id.guest_login);
        guestlogin.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(userselection.this,guestlogin.class);
                startActivity(intent);
            } });

    } }
