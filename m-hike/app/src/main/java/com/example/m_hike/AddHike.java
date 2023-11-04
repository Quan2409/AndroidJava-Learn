package com.example.m_hike;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class AddHike extends AppCompatActivity {

    //Declare the Widgets
    Button backBtn,addBtn;
    EditText nameHike, locationHike, dateHike, lengthHike, description;
    Spinner level_spinner;
    String[] levels = new String[] { "Level", "Easy", "Medium", "Hard" };
    boolean isAllFieldChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhike);

        //Call Function Initialize the widgets
        assignID();

        //Move to Detail Page
        addBtn.setOnClickListener(v -> {
            isAllFieldChecked = CheckAllFields();
            if (isAllFieldChecked) {
                Intent intent = new Intent (this, ConfirmHike.class);
                startActivity(intent);
            }
        });

        //Back To User Page
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListHike.class);
            startActivity(intent);
        });

        //Spinner Setup
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, levels);
        level_spinner.setAdapter(adapter);
        level_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Date Picker Setup
        dateHike.setOnClickListener(v -> {
            openDateDialog();
        });
    }

    //Function initialize the widgets
    private void assignID() {
        backBtn = findViewById(R.id.backBtn);
        addBtn = findViewById(R.id.addBtn);
        nameHike = findViewById(R.id.nameHike);
        locationHike = findViewById(R.id.locationHike);
        dateHike = findViewById(R.id.dateHike);
        lengthHike = findViewById(R.id.lengthHike);
        description = findViewById(R.id.description);
        level_spinner = findViewById(R.id.level_spinner);
    }

    //handle input validation
    private boolean CheckAllFields() {
        if(nameHike.getText().toString().isEmpty()) {
            nameHike.setError(getString(R.string.namehike_validation_msg));
            return false;
        }

        if(locationHike.getText().toString().isEmpty()) {
            locationHike.setError(getString(R.string.locationhike_validation_msg));
            return false;
        }

        if (lengthHike.getText().toString().isEmpty()) {
            lengthHike.setError(getString(R.string.lengthhike_validation_msg));
            return false;
        }

        if (dateHike.getText().toString().isEmpty()) {
            dateHike.setError("Date field is required");
            return false;
        }
        return true;
    }

    //handle Date Dialog
    private void openDateDialog() {
        DatePickerDialog datediaglog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                dateHike.setText(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
            }
        }, 2023, 11, 4);
        datediaglog.show();
    }
}
