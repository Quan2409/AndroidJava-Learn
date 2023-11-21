package com.example.m_hike;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {
    Button beginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        beginButton = findViewById(R.id.begin_button);
        beginButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListHikeActivity.class);
            startActivity(intent);
        });
    }
}