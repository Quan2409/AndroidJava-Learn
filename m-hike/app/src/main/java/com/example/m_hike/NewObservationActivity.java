package com.example.m_hike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;


public class  NewObservationActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    Spinner obsNameSpinner;
    Button chooseTime;
    Button saveBtn;
    TextView viewTime;
    TextView viewComment;
    TextView viewID;
    String getID;

    ObservationModal obsModal;
    DatabaseHelper dbHelper;
    Context context = NewObservationActivity.this;

    String[] listOBS = {"Choose a Observation", "Animal Tracks", "Rocks Formation", "Fruit from trees", "Creek beds and rivers", "Wild Turkeys"};

    boolean isAllFieldCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_observation);
        mappingID();
        setOnClick();
        getID();
    }

    public void mappingID() {
        obsNameSpinner = findViewById(R.id.obsNameSpinner);
        chooseTime = findViewById(R.id.chooseTime);
        saveBtn = findViewById(R.id.saveBtn);
        viewTime = findViewById(R.id.viewTime);
        viewComment = findViewById(R.id.viewComment);
        viewID = findViewById(R.id.detailID);
    }

    public void setOnClick() {
        saveBtn.setOnClickListener(v -> {
            isAllFieldCheck = checkAllField();
            if (isAllFieldCheck) {
                saveNewObservation();
            }
        });

        chooseTime.setOnClickListener(v -> {
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time-picker");
        });

        ArrayAdapter<String> obsSpinnerAdapter = new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listOBS);
        obsNameSpinner.setAdapter(obsSpinnerAdapter);
    }

    public void getID() {
        Intent intent = getIntent();
        if (intent.hasExtra("detail-id")){
            getID = intent.getStringExtra("detail-id");
            viewID.setText(getID);
        }
    }

    public boolean checkAllField() {
        if(obsNameSpinner.getSelectedItem().equals("Choose a Observation")) {
            TextView errorNameOBS = (TextView) obsNameSpinner.getSelectedView();
            errorNameOBS.setError("");
            errorNameOBS.setTextColor(Color.RED);
            errorNameOBS.setText("Pick a Observation, Please");
            return false;
        }

        if (viewTime.getText().equals("View Time")){
            viewTime.setText("Choose Time, Please");
            viewTime.setTextColor(Color.RED);
            return false;
        }
        return true;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        viewTime.setText(hourOfDay + ":" + minute);
    }

    public void saveNewObservation() {
        dbHelper = new DatabaseHelper(context);
        String obsNameSelected = obsNameSpinner.getSelectedItem().toString();
        String timeSelected = viewTime.getText().toString();
        String commentWrote = viewComment.getText().toString();
        obsModal = new ObservationModal(1, dbHelper.getHikeID(Integer.parseInt(getID)) ,obsNameSelected, timeSelected, commentWrote);
        dbHelper.handleCreateObservation(obsModal);
        Intent intent = new Intent(context, ListHikeActivity.class);
        startActivity(intent);
    }
}