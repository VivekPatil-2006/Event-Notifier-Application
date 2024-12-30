package com.example.eventapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
public class Entc_Event extends AppCompatActivity {
    ImageView i1,i2,i3,i4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entc_event);
        //////////////////////////////////////////////////////////////////////////////////
        i1=findViewById(R.id.imageView47);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Entc_Event.this,chess_event.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        i2=findViewById(R.id.imageView48);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Entc_Event.this,coding_event.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        i3=findViewById(R.id.imageView49);
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Entc_Event.this,circuit_designevent.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        i4=findViewById(R.id.imageView50);
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Entc_Event.this,dance_event.class);
                startActivity(intent);
            }
        });
    }
}