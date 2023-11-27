package com.example.m_hike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class UpdateObservationActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    
    Spinner newName;
    Button updateOBSButton, chooseNewTime;
    TextView viewNewTime, viewNewComment;
    String[] listOBS = new String[] {"Choose a Observation", "Animal Tracks", "Rocks Formation", "Fruit from trees", "Creek beds and rivers", "Wild Turkeys"};
    String id, name, time, comment;
    boolean isAllFieldCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_observation);
        mappingID();
        getIntentData();

        updateOBSButton.setOnClickListener(v -> {
            isAllFieldCheck = checkAllField();
            if (isAllFieldCheck) {
                updateObservation();
            }
        });
        chooseNewTime.setOnClickListener(v -> {
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time-picker");
        });
    }
    
    public void mappingID() {
        newName = findViewById(R.id.new_name_obs);
        updateOBSButton = findViewById(R.id.obs_update_button);
        chooseNewTime = findViewById(R.id.obs_choose_new_time);
        viewNewTime = findViewById(R.id.obs_new_time);
        viewNewComment = findViewById(R.id.obs_new_comment);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        viewNewTime.setText(hourOfDay + ":" + minute);
    }

    public boolean checkAllField() {
        if(newName.getSelectedItem().equals("Choose a Observation")) {
            TextView errorNameOBS = (TextView) newName.getSelectedView();
            errorNameOBS.setError("");
            errorNameOBS.setTextColor(Color.RED);
            errorNameOBS.setText("Pick a Observation, Please");
            return false;
        }

        if (viewNewTime.getText().equals("View Time")){
            viewNewTime.setText("Choose Time, Please");
            viewNewTime.setTextColor(Color.RED);
            return false;
        }
        return true;
    }

    public void getIntentData() {
        if(getIntent().hasExtra("id-value") && getIntent().hasExtra("name-value") && getIntent().hasExtra("time-value") && getIntent().hasExtra("comment-value")) {
            id = getIntent().getStringExtra("id-value");
            
            name = getIntent().getStringExtra("name-value");
            newName.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listOBS));
            if(name.equals("Animal Tracks")) {
                newName.setSelection(1);
            } else if (name.equals("Rocks Information")) {
                newName.setSelection(2);
            } else if (name.equals("Fruit from trees")) {
                newName.setSelection(3);
            } else if (name.equals("Creek beds and rivers")) {
                newName.setSelection(4);
            } else if (name.equals("Wild Turkeys")) {
                newName.setSelection(5);
            }

            time = getIntent().getStringExtra("time-value");
            viewNewTime.setText(time);
            
            comment = getIntent().getStringExtra("comment-value");
            viewNewComment.setText(comment);
        } else  {
            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();
        }
    }

    public void updateObservation() {
        DatabaseHelper DB = new DatabaseHelper(UpdateObservationActivity.this);
        String newOBSName = newName.getSelectedItem().toString();
        String newOBSTime = viewNewTime.getText().toString();
        String newOBSComment = viewNewComment.getText().toString();
        ObservationModal observationModal = new ObservationModal(Integer.parseInt(id), Integer.parseInt(id), newOBSName, newOBSTime, newOBSComment);
        DB.handleUpdateObservation(observationModal);
        Intent intent = new Intent(UpdateObservationActivity.this, DetailHikeActivity.class);
        startActivity(intent);
    }
}