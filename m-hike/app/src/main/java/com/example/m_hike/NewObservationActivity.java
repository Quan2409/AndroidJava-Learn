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
import com.example.m_hike.databinding.ActivityNewObservationBinding;

public class  NewObservationActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    ActivityNewObservationBinding binding;
    Spinner obsNameSpinner = binding.obsNameSpinner;
    Button chooseTime = binding.chooseTime;
    Button addBtn = binding.addBtn;
    TextView viewTime = binding.viewTime;
    TextView viewComment = binding.commentField;

    ObservationModal obsModal;
    DatabaseHelper dbHelper;
    Context context = NewObservationActivity.this;

    String[] listOBS = {"Choose a Observation", "Animal Tracks", "Rocks Formation", "Fruit from trees", "Creek beds and rivers", "Wild Turkeys"};
    ArrayAdapter<String> obsAdapter = new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listOBS);
    boolean isAllFieldCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_observation);
        getOBSClicked();

        addBtn.setOnClickListener(v -> {
            isAllFieldCheck = checkAllField();
            if (isAllFieldCheck) {
                saveNewObservation();
            }
        });
        chooseTime.setOnClickListener(v -> {
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time-picker");
        });

        obsNameSpinner.setAdapter(obsAdapter);
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

    public void getOBSClicked() {


    }

    public void saveNewObservation() {
        dbHelper = new DatabaseHelper(context);
        String obsNameSelected = obsNameSpinner.getSelectedItem().toString();
        String timeSelected = viewTime.getText().toString();
        String commentWrote = viewComment.getText().toString();
        obsModal = new ObservationModal(1, dbHelper.getHikeID() ,obsNameSelected, timeSelected, commentWrote);
        dbHelper.handleCreateObservation(obsModal);
        Intent intent = new Intent(context, DetailHikeActivity.class);
        startActivity(intent);
    }
}