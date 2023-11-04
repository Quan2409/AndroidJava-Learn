package com.example.m_hike;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    Button startBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListHike.class);
            startActivity(intent);
        });
    }
}