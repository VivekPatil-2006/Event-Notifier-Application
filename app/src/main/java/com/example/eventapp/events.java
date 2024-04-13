package com.example.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class events extends AppCompatActivity {

    ImageView car_event,chess_event,coding_event,debate_event,gaming_event,model_event,circuit_designevent,rangoli_event,drama_event,spelling_bee_event,robotics_event,maths_event,dance_event,painting_event;
    ImageView call,msg,info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        //////////////////////////////////////////////////////////////////////////
        car_event = findViewById(R.id.imageView5);
        car_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, car_event.class);
                startActivity(intent);
            } });

        //////////////////////////////////////////////////////////////////////////
        chess_event = findViewById(R.id.imageView6);
        chess_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, chess_event.class);
                startActivity(intent);
            } });

        //////////////////////////////////////////////////////////////////////////
        coding_event = findViewById(R.id.imageView7);
        coding_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, coding_event.class);
                startActivity(intent);
            } });

        //////////////////////////////////////////////////////////////////////////
        debate_event = findViewById(R.id.imageView8);
        debate_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, debate_event.class);
                startActivity(intent);
            } });

        //////////////////////////////////////////////////////////////////////////

        gaming_event = findViewById(R.id.imageView9);
        gaming_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, gaming_event.class);
                startActivity(intent);
            } });

        //////////////////////////////////////////////////////////////////////////

        model_event = findViewById(R.id.imageView10);
        model_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, model_event.class);
                startActivity(intent);
            } });

        //////////////////////////////////////////////////////////////////////////

        circuit_designevent = findViewById(R.id.imageView17);
        circuit_designevent.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, circuit_designevent.class);
                startActivity(intent);
            } });

        //////////////////////////////////////////////////////////////////////////
        rangoli_event = findViewById(R.id.imageView16);
        rangoli_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, rangoli_event.class);
                startActivity(intent);
            } });
        //////////////////////////////////////////////////////////////////////////
        drama_event = findViewById(R.id.imageView24);
        drama_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, drama_event.class);
                startActivity(intent);
            } });
        //////////////////////////////////////////////////////////////////////////
        spelling_bee_event = findViewById(R.id.imageView25);
        spelling_bee_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, spelling_bee_event.class);
                startActivity(intent);
            } });
        //////////////////////////////////////////////////////////////////////////
        robotics_event = findViewById(R.id.imageView26);
        robotics_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, robotics_event.class);
                startActivity(intent);
            } });
        //////////////////////////////////////////////////////////////////////////
        maths_event = findViewById(R.id.imageView27);
        maths_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, maths_event.class);
                startActivity(intent);
            } });
        //////////////////////////////////////////////////////////////////////////
        dance_event = findViewById(R.id.imageView28);
        dance_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, dance_event.class);
                startActivity(intent);
            } });
        //////////////////////////////////////////////////////////////////////////
        painting_event = findViewById(R.id.imageView29);
        painting_event.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(events.this, painting_event.class);
                startActivity(intent);
            } });

    }
}