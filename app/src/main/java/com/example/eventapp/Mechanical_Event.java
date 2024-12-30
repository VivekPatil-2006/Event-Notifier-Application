package com.example.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
public class Mechanical_Event extends AppCompatActivity {
    ImageView i1,i2,i3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanical_event);

        //////////////////////////////////////////////////////////////////////////////////
        i1=findViewById(R.id.imageView51);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Mechanical_Event.this,car_event.class);
                startActivity(intent);
            }

        });
        //////////////////////////////////////////////////////////////////////////////////
        i2=findViewById(R.id.imageView52);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Mechanical_Event.this,drama_event.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        i3=findViewById(R.id.imageView53);
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Mechanical_Event.this,maths_event.class);
                startActivity(intent);
            }
        });


    }
}