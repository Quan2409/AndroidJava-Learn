package com.example.contact_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClick();
            }
        });
    }

    private void handleClick() {
        saveDetails();
    }

    private void saveDetails() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        EditText nameTxt = findViewById(R.id.nameText);
        EditText dobTxt = findViewById(R.id.dobText);
        EditText emailTxt = findViewById(R.id.emailText);

        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();

        long personId = dbHelper.insertDetails(name, dob, email);

        Toast.makeText(this, "Person has been created with id: " + personId,
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}