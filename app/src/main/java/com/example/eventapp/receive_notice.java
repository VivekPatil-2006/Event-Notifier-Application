package com.example.eventapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// Import necessary packages
public class receive_notice extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_notice);

        // Initialize Firebase Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("messages").child("User 1");

        textMessage = findViewById(R.id.textMessage);

        // Attach a ValueEventListener to fetch messages
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder messages = new StringBuilder();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String message = dataSnapshot.getValue(String.class);
                    messages.append(message).append("\n");
                }
                textMessage.setText(messages.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}
