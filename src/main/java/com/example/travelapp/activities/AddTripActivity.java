package com.example.travelapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.travelapp.R;
import com.example.travelapp.models.Trip;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddTripActivity extends AppCompatActivity {

    private EditText etDestination, etDate, etDuration, etNotes;
    private Button btnUploadFile, btnSaveTrip;
    private Uri fileUri;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private static final int PICK_FILE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        etDestination = findViewById(R.id.et_destination);
        etDate = findViewById(R.id.et_date);
        etDuration = findViewById(R.id.et_duration);
        etNotes = findViewById(R.id.et_notes);
        btnUploadFile = findViewById(R.id.btn_upload_file);
        btnSaveTrip = findViewById(R.id.btn_save_trip);

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        btnUploadFile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            startActivityForResult(intent, PICK_FILE_REQUEST);
        });

        btnSaveTrip.setOnClickListener(v -> saveTrip());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null) {
            fileUri = data.getData();
        }
    }

    private void saveTrip() {
        String destination = etDestination.getText().toString();
        String date = etDate.getText().toString();
        String duration = etDuration.getText().toString();
        String notes = etNotes.getText().toString();

        if (fileUri != null) {
            StorageReference fileRef = storage.getReference().child("uploads/" + System.currentTimeMillis());
            fileRef.putFile(fileUri).addOnSuccessListener(taskSnapshot -> {
                fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    Trip trip = new Trip(null, destination, date, duration, notes, uri.toString());
                    db.collection("trips").add(trip).addOnSuccessListener(documentReference -> {
                        finish();
                    });
                });
            });
        } else {
            Trip trip = new Trip(null, destination, date, duration, notes, null);
            db.collection("trips").add(trip).addOnSuccessListener(documentReference -> {
                finish();
            });
        }
    }
}