package com.example.eventapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

public class receive_notice extends AppCompatActivity {
    private StorageReference storageReference;
    private TextView textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_notice);

        textMessage = findViewById(R.id.textMessage);

        // Initialize Firebase Storage reference
        storageReference = FirebaseStorage.getInstance().getReference("messages");

        // Call method to retrieve the latest message file
        retrieveLatestMessage();
    }

    // Method to retrieve the latest message file from Firebase Storage
    private void retrieveLatestMessage() {
        storageReference.listAll()
                .addOnSuccessListener(listResult -> {
                    if (!listResult.getItems().isEmpty()) {
                        // Get the last item (latest message file)
                        StorageReference latestMessageFileRef = listResult.getItems().get(listResult.getItems().size() - 1);

                        // Get the download URL of the latest message file
                        latestMessageFileRef.getDownloadUrl()
                                .addOnSuccessListener(this::downloadMessageFile)
                                .addOnFailureListener(e -> showToast("Failed to get the download URL: " + e.getMessage()));
                    } else {
                        textMessage.setText("No message files available.");
                    }
                })
                .addOnFailureListener(e -> showToast("Failed to list message files: " + e.getMessage()));
    }

    // Method to download the message file content from Firebase Storage
    private void downloadMessageFile(Uri downloadUri) {
        StorageReference messageFileRef = FirebaseStorage.getInstance().getReferenceFromUrl(downloadUri.toString());

        // Download the file as bytes
        messageFileRef.getBytes(Long.MAX_VALUE)
                .addOnSuccessListener(bytes -> {
                    // Convert the byte array to a String (since it's a text file)
                    String messageContent = new String(bytes);

                    // Display the message content in the TextView
                    textMessage.setText(messageContent);
                })
                .addOnFailureListener(e -> showToast("Failed to download message file: " + e.getMessage()));
    }

    // Helper method to display a Toast message
    private void showToast(String message) {
        Toast.makeText(receive_notice.this, message, Toast.LENGTH_SHORT).show();
    }
}

