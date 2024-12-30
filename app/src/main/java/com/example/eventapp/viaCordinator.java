package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
public class viaCordinator extends AppCompatActivity {


    ImageView b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_via_cordinator);
        //////////////////////////////////////////////////////////////////////////
        b1 = findViewById(R.id.chatting);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(viaCordinator.this,phone_signup_activity.class);
                //Intent intent = new Intent(viaCordinator.this,coordinatorlogin.class);

                startActivity(intent);
            } });
        //////////////////////////////////////////////////////////////////////////
        b2 = findViewById(R.id.Notice);
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(viaCordinator.this,notice.class);
                startActivity(intent);
            } });
    }
}