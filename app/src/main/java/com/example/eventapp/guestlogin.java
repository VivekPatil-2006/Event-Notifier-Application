package com.example.eventapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class guestlogin extends AppCompatActivity {


    Button events,click_here;
    ImageView call,msg,info,new_event;
    private static final int REQUEST_PHONE_CALL = 1;
    private static final int REQUEST_SEND_MESSAGE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestlogin);

        events=findViewById(R.id.button6);
        events.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(guestlogin.this,events.class);
                startActivity(intent);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////
        new_event=findViewById(R.id.imageView14);
        new_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(guestlogin.this,NewEvent.class);
                startActivity(intent);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////
        click_here=findViewById(R.id.button7);
        click_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(guestlogin.this,explore_via_branch.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////
        call = findViewById(R.id.calling);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+"7387003940"));
                startActivity(i);
            } });
        //////////////////////////////////////////////////////////////////////////
        msg = findViewById(R.id.messging);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            } });
        //////////////////////////////////////////////////////////////////////////
        info = findViewById(R.id.imageView15);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(guestlogin.this,instruction.class);
                startActivity(intent);
            } });

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void makePhoneCall() {
        String phoneNumber = "tel:" + "9529592099";

        // Check for permission before making the call
        if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber));
            startActivity(dialIntent);
        } else {
            // Request permission if not granted
            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sendMessage() {
        String phoneNumber = "smsto:" + "9529592099";
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(phoneNumber));
        smsIntent.putExtra("sms_body", "Hello, I'm sending you a message!");

        // Check for permission before sending the message
        if (checkSelfPermission(android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            startActivity(smsIntent);
        } else {
            // Request permission if not granted
            requestPermissions(new String[]{android.Manifest.permission.SEND_SMS}, REQUEST_SEND_MESSAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PHONE_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            }
        } else if (requestCode == REQUEST_SEND_MESSAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendMessage();
            }
        }
    }
}