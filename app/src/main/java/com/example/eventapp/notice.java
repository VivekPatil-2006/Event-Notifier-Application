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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class notice extends AppCompatActivity {

    private static final String TAG = "NoticeActivity";
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private EditText editMessage;
    private ImageView btnSelectImage,home;
    private Button sendNoticeButton, clearDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(notice.this, userselection.class);
                startActivity(intent);
            } });


        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        // Initialize Firebase Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("messages");

        editMessage = findViewById(R.id.editMessage);
        sendNoticeButton = findViewById(R.id.send);
        clearDataButton = findViewById(R.id.button10);

        editMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDateTime = getCurrentDateTime();
                // Set the current date and time to the EditText
                editMessage.setText(currentDateTime);
                // Set cursor position after the date and time
                editMessage.setSelection(currentDateTime.length());
            }
        });

        sendNoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("User 1", editMessage.getText().toString());
                sendNotificationToAll(editMessage.getText().toString());
                // Show toast after sending image
                Toast.makeText(notice.this, "Message Sent Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        clearDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
                // Show toast after clearing data
                Toast.makeText(notice.this, "Previous data cleared!", Toast.LENGTH_SHORT).show();
            }
        });

        // Log FCM token
        logFCMToken();
    }

    private void sendMessage(String user, String message) {
        DatabaseReference userRef = databaseReference.child(user);
        userRef.push().setValue(message);
        editMessage.setText("");
    }

    private void clearData() {
        DatabaseReference userRef = databaseReference.child("User 1");
        userRef.setValue(null);
    }

    private void sendNotificationToAll(String message) {
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Message is sent to all devices subscribed to the topic "allDevices"
                            Log.d(TAG, "Notification sent successfully.");
                        } else {
                            Log.e(TAG, "Failed to send notification: " + task.getException());
                        }
                    }
                });
    }

    private void logFCMToken() {
        // Log FCM token for debugging
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            String token = task.getResult();
                            Log.d(TAG, "FCM Token: " + token);
                        } else {
                            Log.e(TAG, "Failed to get FCM Token: " + task.getException());
                        }
                    }
                });
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    // Handle the result from the image selection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            uploadImage(imageUri);
        }
    }

    private void uploadImage(Uri imageUri) {
        // Use a specific name for the image (e.g., timestamp.jpg)
        String imageName = "User1_" + System.currentTimeMillis() + ".jpg";
        StorageReference imageRef = storageReference.child("images/" + imageName);

        imageRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Image uploaded successfully
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri downloadUri) {
                                String imageUrl = downloadUri.toString();
                                sendMessage("User 1", imageUrl);
                                // Show toast after sending image
                                Toast.makeText(notice.this, "Image Sent Successfully!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                        Log.e(TAG, "Failed to upload image: " + e.getMessage());
                        Toast.makeText(notice.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
