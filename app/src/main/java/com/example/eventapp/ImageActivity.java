package com.example.eventapp;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
public class ImageActivity extends AppCompatActivity {

    private ImageView imageView;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = findViewById(R.id.imageView);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        // Call the method to retrieve and display the latest uploaded image
        retrieveLatestImage();
    }

    private void retrieveLatestImage() {
        // Assuming "images" is the path where images are stored in Firebase Storage
        StorageReference imagesRef = storageReference.child("images");

        // List all items (images) in the "images" path
        imagesRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                if (!listResult.getItems().isEmpty()) {
                    // Get the last item (latest image)
                    StorageReference latestImageRef = listResult.getItems().get(listResult.getItems().size() - 1);

                    // Get the download URL of the latest image
                    latestImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri downloadUri) {
                            // Use a library like Glide to load the image into the ImageView
                            Glide.with(ImageActivity.this)
                                    .load(downloadUri)
                                    .into(imageView);
                        }
                    });
                } else {
                    // Handle the case when there are no images
                    // For example, show a default image or a placeholder
                    // imageView.setImageResource(R.drawable.placeholder);
                }
            }
        });
    }
}
