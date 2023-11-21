package com.example.m_hike;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailHikeActivity extends AppCompatActivity {

    TextView detailID,  detailName, detailLocation, detailLength, detailDate, detailLevel, detailParking ,detailDescription;
    Button deleteButton, backButton;
    ImageButton editButton, observationButton;
    String id, name, location, length, date, level, parking, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hike);
        mappingID();
        getIntentData();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailHikeActivity.this, UpdateHikeActivity.class);
                intent.putExtra("id-value", detailID.getText().toString());
                intent.putExtra("name-value", detailName.getText().toString());
                intent.putExtra("location-value", detailLocation.getText().toString());
                intent.putExtra("length-value", detailLength.getText().toString());
                intent.putExtra("date-value", detailDate.getText().toString());
                intent.putExtra("level-value", detailLevel.getText().toString());
                intent.putExtra("parking-value", detailParking.getText().toString());
                intent.putExtra("description-value", detailDescription.getText().toString());
                DetailHikeActivity.this.startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailHikeActivity.this, ListHikeActivity.class);
                startActivity(intent);
            }
        });

        observationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailHikeActivity.this, NewObservationActivity.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper DB = new DatabaseHelper(DetailHikeActivity.this);
                DB.handleDelete(Integer.parseInt(id));
                Intent intent = new Intent(DetailHikeActivity.this, ListHikeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void mappingID() {
        observationButton = findViewById(R.id.observation_button);
        editButton = findViewById(R.id.edit_button);
        backButton = findViewById(R.id.back_button);
        deleteButton = findViewById(R.id.delete_button);
        detailID = findViewById(R.id.detail_id);
        detailName = findViewById(R.id.detail_name);
        detailLocation = findViewById(R.id.detail_location);
        detailLength = findViewById(R.id.detail_length);
        detailDate = findViewById(R.id.detail_date);
        detailLevel = findViewById(R.id.detail_level);
        detailParking = findViewById(R.id.detail_parking);
        detailDescription = findViewById(R.id.detail_description);
    }

    public void getIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("location") &&
                getIntent().hasExtra("length") && getIntent().hasExtra("date") &&
                getIntent().hasExtra("level") && getIntent().hasExtra("description")
                && getIntent().hasExtra("parking")) {
            //Get Data From Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            location = getIntent().getStringExtra("location");
            length = getIntent().getStringExtra("length");
            date = getIntent().getStringExtra("date");
            level = getIntent().getStringExtra("level");
            parking = getIntent().getStringExtra("parking");
            description = getIntent().getStringExtra("description");

            //Set Data From Intent
            detailID.setText(id);
            detailName.setText(name);
            detailLocation.setText(location);
            detailLength.setText(length);
            detailDate.setText(date);
            detailLevel.setText(level);
            detailParking.setText(parking);
            detailDescription.setText(description);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();
        }
    }
}
