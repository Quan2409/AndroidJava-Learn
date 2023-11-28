package com.example.contact_database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.contact_database.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    EditText nameText = binding.nameInput;
    EditText dobText = binding.dobInput;
    EditText emailText = binding.emailInput;
    Button viewDetailBtn = binding.viewBtn;
    Button saveBtn = binding.saveBtn;
    ImageView avatar = binding.avatar;
    DatabaseHelper databaseHelper;
    PersonModal personalModal;
    Uri imageFilePath;
    Bitmap imageToStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn.setOnClickListener(v -> {
            saveDetails();
        });

        viewDetailBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
            startActivity(intent);
        });
    }

    public void saveDetails() {
        personalModal = new PersonModal(nameText.getText().toString(), dobText.getText().toString(), emailText.getText().toString(), imageToStore);
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.handleCreate(personalModal);
        Toast.makeText(this, "Person has been created", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        startActivity(intent);
    }

    public void chooseImage(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK && data!=null && data.getData() != null) {
            imageFilePath = data.getData();
            try {
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            avatar.setImageBitmap(imageToStore);
        }
    }
}