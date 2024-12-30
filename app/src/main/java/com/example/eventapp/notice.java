package com.example.eventapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class notice extends AppCompatActivity {

    private static final String TAG = "NoticeActivity";
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private EditText editMessage;
    private ImageView btnSelectImage, home;

    private Button sendNoticeButton, clearDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        initializeViews();
        setupFirebaseReferences();
        setupListeners();
        logFCMToken();
    }

    private void initializeViews() {
        home = findViewById(R.id.home);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        editMessage = findViewById(R.id.editMessage);
        sendNoticeButton = findViewById(R.id.send);
        clearDataButton = findViewById(R.id.button10);
    }

    private void setupFirebaseReferences() {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("messages");
    }

    private void setupListeners() {
        home.setOnClickListener(view -> {
            Intent intent = new Intent(notice.this, userselection.class);
            startActivity(intent);
        });

        btnSelectImage.setOnClickListener(v -> openImageChooser());

        sendNoticeButton.setOnClickListener(v -> {
            String message = editMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                sendMessage(message);
                sendNotificationToAll(message);
                //Toast.makeText(notice.this, "Message Sent Successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(notice.this, "Message cannot be empty!", Toast.LENGTH_SHORT).show();
            }
        });

        clearDataButton.setOnClickListener(v -> {
            clearData();
            Toast.makeText(notice.this, "Previous data cleared!", Toast.LENGTH_SHORT).show();
        });
    }

    private void clearData() {
        databaseReference.child("User 1").removeValue();
    }

    private void sendNotificationToAll(String message) {
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Notification sent successfully.");
                    } else {
                        Log.e(TAG, "Failed to send notification: ", task.getException());
                    }
                });
    }

    private void logFCMToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String token = task.getResult();
                        Log.d(TAG, "FCM Token: " + token);
                    } else {
                        Log.e(TAG, "Failed to get FCM Token: ", task.getException());
                    }
                });
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uploadImage(data.getData());
        }
    }

    private void uploadImage(Uri imageUri) {
        String imageName = "User1_" + System.currentTimeMillis() + ".jpg";
        StorageReference imageRef = storageReference.child("images/" + imageName);

        imageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> imageRef.getDownloadUrl()
                        .addOnSuccessListener(downloadUri -> {
                            Log.d(TAG, "Image URL: " + downloadUri);
                            // Send image URL to Firebase if needed
                        })
                        .addOnFailureListener(e -> {
                            Log.e(TAG, "Failed to get image URL: ", e);
                            Toast.makeText(notice.this, "Failed to retrieve image URL", Toast.LENGTH_SHORT).show();
                        }))
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Failed to upload image: ", e);
                    Toast.makeText(notice.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                });
    }

    private void sendMessage(String message) {
        // Convert the message to a .txt file
        String fileName = "message_" + System.currentTimeMillis() + ".txt";
        StorageReference messageRef = storageReference.child("messages/" + fileName);

        // Convert message to byte array (for uploading to Firebase Storage)
        byte[] messageBytes = message.getBytes();

        // Upload the file to Firebase Storage
        messageRef.putBytes(messageBytes)
                .addOnSuccessListener(taskSnapshot -> {
                    messageRef.getDownloadUrl().addOnSuccessListener(downloadUri -> {
                        Log.d(TAG, "Message file URL: " + downloadUri);

                        // Now save the download URL in Firebase Realtime Database
                        DatabaseReference userRef = databaseReference.child("messages");
                        String messageId = userRef.push().getKey();

                        Map<String, Object> messageData = new HashMap<>();
                        messageData.put("messageFileUrl", downloadUri.toString());
                        messageData.put("timestamp", System.currentTimeMillis());

                        userRef.child(messageId).setValue(messageData)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Message sent successfully!");
                                        Toast.makeText(notice.this, "Message Sent Successfully!", Toast.LENGTH_SHORT).show();
                                        editMessage.setText("");
                                    } else {
                                        Log.e(TAG, "Failed to send message: ", task.getException());
                                        Toast.makeText(notice.this, "Message Sent Successfully!", Toast.LENGTH_SHORT).show();
                                        editMessage.setText("");
                                    }
                                });
                    }).addOnFailureListener(e -> {
                        Log.e(TAG, "Failed to get message file URL: ", e);
                        Toast.makeText(notice.this, "Failed to retrieve message file URL", Toast.LENGTH_SHORT).show();
                    });
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Failed to upload message file: ", e);
                    Toast.makeText(notice.this, "Failed to upload message file", Toast.LENGTH_SHORT).show();
                });
    }
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
