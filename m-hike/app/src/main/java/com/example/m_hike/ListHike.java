package com.example.m_hike;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ListHike extends AppCompatActivity {

    Button addBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listhike);

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddHike.class);
            startActivity(intent);
        });

        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        });
    }
}
