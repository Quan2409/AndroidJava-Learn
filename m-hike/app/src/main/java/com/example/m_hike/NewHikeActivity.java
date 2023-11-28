package com.example.m_hike;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.m_hike.databinding.ActivityNewHikeBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewHikeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Spinner nameSpinner;
    Spinner levelSpinner;
    Spinner locationSpinner;
    Spinner lengthSpinner;
    EditText viewDescription;
    TextView viewDate;
    Button chooseDate;
    Button saveButton;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;

    Context context = NewHikeActivity.this;

    String[] allHike = new String[] {"Pick a Hike", "Sapa Bamboo Trail", "Mount Fansipan Trail", "West Lake Loop"};
    String[] allLocation = new String[] {"Location", "Sapa - LaoCai", "Hoang Lien National Park", "TayHo - HaNoi"};
    String[] allLength = new String[] {"Length", "6.3km", "8.4km", "15.0km"};
    String[] allLevel = new String[] { "Level", "Easy", "Moderate", "Hard" };

    boolean isAllFieldChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hike);
        mappingID();
        setOnCLick();
    }

//    Mapping ID from XML
    public void mappingID() {
      nameSpinner = findViewById(R.id.nameSpinner);
      locationSpinner = findViewById(R.id.locationSpinner);
      lengthSpinner = findViewById(R.id.lengthSpinner);
      levelSpinner = findViewById(R.id.levelSpinner);
      viewDescription = findViewById(R.id.viewDescription);
      viewDate = findViewById(R.id.viewDate);
      chooseDate = findViewById(R.id.chooseDate);
      saveButton = findViewById(R.id.saveButton);
      radioGroup = findViewById(R.id.radioGroup);
    }

    public void setOnCLick() {

        //Save Button
        saveButton.setOnClickListener(v -> {
            isAllFieldChecked = CheckAllFields();
            if (isAllFieldChecked) {
                showConfirmDialog();
            }
        });

        //Choose Date Button
        chooseDate.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date-picker");
        });

        //Radio group Checked
        radioGroup.getCheckedRadioButtonId();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioButton = findViewById(checkedId);
            }
        });

        //Spinner for Name
        ArrayAdapter<String> nameSpinnerAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, allHike );
        nameSpinner.setAdapter(nameSpinnerAdapter);
        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(nameSpinner.getSelectedItem().equals("Sapa Bamboo Trail")){
                    locationSpinner.setSelection(1);
                    lengthSpinner.setSelection(1);
                } else if(nameSpinner.getSelectedItem().equals("Mount Fansipan Trail")) {
                    locationSpinner.setSelection(2);
                    lengthSpinner.setSelection(2);
                } else if(nameSpinner.getSelectedItem().equals("West Lake Loop")) {
                    locationSpinner.setSelection(3);
                    lengthSpinner.setSelection(3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        //Spinner for Location
        ArrayAdapter<String> locationSpinnerAdapter = new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, allLocation);
        locationSpinner.setAdapter(locationSpinnerAdapter);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(locationSpinner.getSelectedItem().equals("Sapa - Lao Cai")){
                    nameSpinner.setSelection(1);
                    lengthSpinner.setSelection(1);
                } else if(locationSpinner.getSelectedItem().equals("Hoang Lien National Park")) {
                    nameSpinner.setSelection(2);
                    lengthSpinner.setSelection(2);
                } else if (locationSpinner.getSelectedItem().equals("TayHo - HaNoi")) {
                    nameSpinner.setSelection(3);
                    lengthSpinner.setSelection(3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        //Spinner for Length
        ArrayAdapter<String> lengthSpinnerAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, allLength);
        lengthSpinner.setAdapter(lengthSpinnerAdapter);
        lengthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (lengthSpinner.getSelectedItem().equals("6.3km")) {
                    nameSpinner.setSelection(1);
                    locationSpinner.setSelection(1);
                } else if (lengthSpinner.getSelectedItem().equals("8.4km")) {
                    nameSpinner.setSelection(2);
                    locationSpinner.setSelection(2);
                } else if (lengthSpinner.getSelectedItem().equals("15.0km")) {
                    nameSpinner.setSelection(3);
                    locationSpinner.setSelection(3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        //Spinner for Level
        ArrayAdapter<String> levelSpinnerAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, allLevel);
        levelSpinner.setAdapter(levelSpinnerAdapter);
    }

    //Date Picker Setup
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.YEAR, year);
        calender.set(Calendar.MONTH, month);
        calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormat.getDateInstance().format(calender.getTime());
        viewDate.setText(currentDate);
    }

    //Handle User Input Validation
    public boolean CheckAllFields() {
        if(nameSpinner.getSelectedItem().equals("Pick a Hike")) {
            TextView errorNameHike = (TextView) nameSpinner.getSelectedView();
            errorNameHike.setError("");
            errorNameHike.setTextColor(Color.RED);
            errorNameHike.setText("Pick A Hike Please");
        }

        if(levelSpinner.getSelectedItem().equals("Level")){
            TextView errorLevelHike = (TextView) levelSpinner.getSelectedView();
            errorLevelHike.setError("");
            errorLevelHike.setTextColor(Color.RED);
            errorLevelHike.setText("Choose Level of Hike");
            return false;
        }

        if(viewDate.getText().toString().equals("View Date")){
            viewDate.setTextColor(Color.RED);
            viewDate.setText("Pick A Date, Please");
            return false;
        }
        return true;
    }

    //Show Confirm Dialog
    public void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Hike Information");
        String message01 = "Name: " + nameSpinner.getSelectedItem().toString();
        String message02 = "Location: " + locationSpinner.getSelectedItem().toString();
        String message03 = "Length: " + lengthSpinner.getSelectedItem().toString();
        String message04 = "Date: " + viewDate.getText().toString();
        String message05 = "Level: " + levelSpinner.getSelectedItem().toString();
        String message06 = "Parking: " + selectedRadioButton.getText().toString();
        String message07 = "Description: " + viewDescription.getText().toString();
        String finalMessage = message01 + "\n" + message02 + "\n" + message03 + "\n" + message04 + "\n" + message05 + "\n" + message06 + "\n" + message07;
        builder.setMessage(finalMessage);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveNewHike();
                Intent intent = new Intent(NewHikeActivity.this, ListHikeActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Save New Data to Database
    public void saveNewHike() {
        DatabaseHelper DB = new DatabaseHelper(this);
        String nameSelected = nameSpinner.getSelectedItem().toString();
        String locationSelected = locationSpinner.getSelectedItem().toString();
        String lengthSelected = lengthSpinner.getSelectedItem().toString();
        String dateSelected = viewDate.getText().toString();
        String leveSelected = levelSpinner.getSelectedItem().toString();
        String parkingSelected = selectedRadioButton.getText().toString();
        String descriptionWrote = viewDescription.getText().toString();
        HikeModal newHike = new HikeModal(1, nameSelected, locationSelected, lengthSelected, dateSelected, leveSelected, parkingSelected, descriptionWrote);
        DB.handleCreateNewHike(newHike);
    }
}
