package com.example.m_hike;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.m_hike.databinding.ActivityHomePageBinding;

public class HomePageActivity extends AppCompatActivity {
    Button moveToListButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        moveToListButton = findViewById(R.id.begin_button);
        moveToListButton.setOnClickListener(v -> moveToListHikePage());
    }
    public void moveToListHikePage() {
        Intent intent = new Intent(HomePageActivity.this, ListHikeActivity.class);
        startActivity(intent);
    }
}