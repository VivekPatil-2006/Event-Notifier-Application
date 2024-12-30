package com.example.eventapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
public class Civil_Event extends AppCompatActivity {
    ImageView i1,i2,i3,i4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civil_event);
        //////////////////////////////////////////////////////////////////////////////////
        i1=findViewById(R.id.imageView43);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Civil_Event.this,car_event.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        i2=findViewById(R.id.imageView44);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Civil_Event.this,model_event.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        i3=findViewById(R.id.imageView45);
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Civil_Event.this,rangoli_event.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        i4=findViewById(R.id.imageView46);
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Civil_Event.this,painting_event.class);
                startActivity(intent);
            }
        });
    }
}