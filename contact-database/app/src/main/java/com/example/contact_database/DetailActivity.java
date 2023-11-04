package com.example.contact_database;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DatabaseHelper db = new DatabaseHelper(this);

        String details = db.getDetails();

        TextView detailsTxt = findViewById(R.id.detailsText);

        detailsTxt.setText(details);
    }
}
