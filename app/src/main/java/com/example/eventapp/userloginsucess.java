package com.example.eventapp;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class userloginsucess extends AppCompatActivity {
    Button Event_Notice,Event_img;
    ImageView home,Event_Enroll,feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userloginsucess);

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(userloginsucess.this, userselection.class);
                startActivity(intent);
            } });

        Event_img = findViewById(R.id.button4);
        Event_img.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(userloginsucess.this, take_image.class);
                startActivity(intent);
            }});

        feedback=findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String googleLink = "https://forms.gle/9KbhLoeZEHjGUiei9";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleLink));
                startActivity(intent);
            }
        });

        Event_Notice = findViewById(R.id.button5);
        Event_Notice.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(userloginsucess.this, receive_notice.class);
                startActivity(intent);
            } });

        Event_Enroll = findViewById(R.id.button6);
        Event_Enroll.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(userloginsucess.this, explore_via_branch.class);
                startActivity(intent);
            } });

    }
}