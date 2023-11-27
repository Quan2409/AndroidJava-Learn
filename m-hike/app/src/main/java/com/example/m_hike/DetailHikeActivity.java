package com.example.m_hike;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m_hike.databinding.ActivityDetailHikeBinding;

import java.util.ArrayList;

public class DetailHikeActivity extends AppCompatActivity {
    ActivityDetailHikeBinding binding;
    TextView detailID;
    TextView detailName;
    TextView detailLocation;
    TextView detailLength;
    TextView detailDate;
    TextView detailLevel;
    TextView detailParking;
    TextView detailDescription;
    ImageButton editButton;
    ImageButton observationButton;
    Button deleteButton;
    RecyclerView obsRecycle;

    Cursor cursor;
    DatabaseHelper dbHelper;
    Context context = DetailHikeActivity.this;
    String id, name, location, length, date, level, parking, description;
    ArrayList<ObservationModal> allOBS = new ArrayList<>();
    ObservationAdapter obsAdapter;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailHikeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bindingID();
        setOnCLick();
        getIntentDataFromAdapter();
        readAllObservation();
    }

    public void bindingID() {
        detailID = binding.detailId;
        detailName = binding.detailName;
        detailLocation = binding.detailLocation;
        detailLength = binding.detailLength;
        detailDate = binding.detailDate;
        detailLevel = binding.detailLevel;
        detailParking = binding.detailParking;
        detailDescription = binding.detailDescription;
        editButton = binding.editButton;
        observationButton = binding.observationButton;
        deleteButton = binding.deleteButton;
        obsRecycle = binding.observationRecycleView;
    }

    public void setOnCLick() {
        editButton.setOnClickListener(v -> {
            senDataToUpdatePage();
        });

        observationButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewObservationActivity.class);
            startActivity(intent);
        });
        deleteButton.setOnClickListener(v-> {
            showAlertDialog();
        });
    }

    public void getIntentDataFromAdapter() {
        Intent intent = getIntent();
        if (intent.hasExtra("hike-id") && intent.hasExtra("hike-name") && intent.hasExtra("hike-location") &&
                intent.hasExtra("hike-length") && intent.hasExtra("hike-date") &&
                intent.hasExtra("hike-level") && getIntent().hasExtra("hike-description")
                && intent.hasExtra("hike-parking")) {

            id = intent.getStringExtra("hike-id");
            name = intent.getStringExtra("hike-name");
            location = intent.getStringExtra("hike-location");
            length = intent.getStringExtra("hike-length");
            date = intent.getStringExtra("hike-date");
            level = intent.getStringExtra("hike-level");
            parking = intent.getStringExtra("hike-parking");
            description = intent.getStringExtra("hike-description");

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

    public void senDataToUpdatePage() {
        Intent intent = new Intent(context, UpdateHikeActivity.class);
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

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete Hike");
        builder.setMessage("Are you sure you want to delete all data ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteHike();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void deleteHike() {
       dbHelper = new DatabaseHelper(context);
       dbHelper.handleDeleteHike(Integer.parseInt(id));
       Intent intent = new Intent(context, ListHikeActivity.class);
       startActivity(intent);
    }

    public void readAllObservation() {
        dbHelper = new DatabaseHelper(context);
        allOBS = dbHelper.handleReadObservation();
        obsRecycle.setLayoutManager(new LinearLayoutManager(context));
        obsRecycle.setHasFixedSize(true);
        obsAdapter = new ObservationAdapter(this, context, cursor, allOBS);
        obsRecycle.setAdapter(obsAdapter);
    }
}
