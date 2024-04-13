package com.example.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Computer_Event extends AppCompatActivity {

    ImageView i1,i2,i3,i4,i5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_event);

        //////////////////////////////////////////////////////////////////////////////////
        i1=findViewById(R.id.imageView38);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Computer_Event.this,chess_event.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        i2=findViewById(R.id.imageView39);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Computer_Event.this,coding_event.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        i3=findViewById(R.id.imageView40);
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Computer_Event.this,gaming_event.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        i4=findViewById(R.id.imageView41);
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Computer_Event.this,rangoli_event.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        i5=findViewById(R.id.imageView42);
        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Computer_Event.this,robotics_event.class);
                startActivity(intent);
            }
        });
    }
}