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

    ActivityNewHikeBinding binding;
    Spinner name_spinner;
    Spinner level_spinner;
    Spinner location_spinner;
    Spinner length_spinner;
    EditText description;
    TextView viewDate;
    Button chooseDate;
    Button addNewHikeButton;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
    Context context = NewHikeActivity.this;

    String[] nameHike = new String[] {"Pick a Hike", "Sapa Bamboo Trail", "Mount Fansipan Trail", "West Lake Loop"};
    String[] locationHike = new String[] {"Location", "Sapa - LaoCai", "Hoang Lien National Park", "TayHo - HaNoi"};
    String[] lengthHike = new String[] {"Length", "6.3km", "8.4km", "15.0km"};
    String[] levels = new String[] { "Level", "Easy", "Moderate", "Hard" };

    ArrayAdapter<String> adapterLength = new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lengthHike);
    ArrayAdapter<String> adapterName = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nameHike );
    ArrayAdapter<String> adapterLocation = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, locationHike);

    boolean isAllFieldChecked = false;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewHikeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bindingID();
        setOnCLick();
    }

    //Mapping ID from XML
    public void bindingID() {
        name_spinner = binding.nameSpinner;
        location_spinner = binding.locationSpinner;
        length_spinner = binding.lengthSpinner;
        level_spinner = binding.levelSpinner;
        description = binding.description;
        viewDate = binding.viewDate;
        addNewHikeButton = binding.addNewHikeButton;
        chooseDate = binding.chooseDate;
        radioGroup = binding.radioGroup;
    }

    public void setOnCLick() {
        //onCLick() for add button
        addNewHikeButton.setOnClickListener(v -> {
            isAllFieldChecked = CheckAllFields();
            if (isAllFieldChecked) {
                showConfirmDialog();
            }
        });

        //onClick() for choose date
        chooseDate.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date-picker");
        });

        //onChecked() for radio group
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioButton = findViewById(checkedId);
            }
        });

        //Call Item Selected for Spinner
        getItemSelected();
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
        if(name_spinner.getSelectedItem().equals("Pick a Hike")) {
            TextView errorNameHike = (TextView) name_spinner.getSelectedView();
            errorNameHike.setError("");
            errorNameHike.setTextColor(Color.RED);
            errorNameHike.setText("Pick A Hike Please");
        }

        if(level_spinner.getSelectedItem().equals("Level")){
            TextView errorLevelHike = (TextView) level_spinner.getSelectedView();
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

    public void getItemSelected() {
        //Name Spinner Setup
        name_spinner.setAdapter(adapterName);
        name_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(name_spinner.getSelectedItem().equals("Sapa Bamboo Trail")){
                    location_spinner.setSelection(1);
                    length_spinner.setSelection(1);
                } else if(name_spinner.getSelectedItem().equals("Mount Fansipan Trail")) {
                    location_spinner.setSelection(2);
                    length_spinner.setSelection(2);
                } else if(name_spinner.getSelectedItem().equals("West Lake Loop")) {
                    location_spinner.setSelection(3);
                    length_spinner.setSelection(3);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //Length Spinner Setup
        length_spinner.setAdapter(adapterLength);
        length_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (length_spinner.getSelectedItem().equals("6.3km")) {
                    name_spinner.setSelection(1);
                    location_spinner.setSelection(1);
                } else if (length_spinner.getSelectedItem().equals("8.4km")) {
                    name_spinner.setSelection(2);
                    location_spinner.setSelection(2);
                } else if (length_spinner.getSelectedItem().equals("15.0km")) {
                    name_spinner.setSelection(3);
                    location_spinner.setSelection(3);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //Location Spinner Setup
        location_spinner.setAdapter(adapterLocation);
        location_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(location_spinner.getSelectedItem().equals("Sapa - Lao Cai")){
                    name_spinner.setSelection(1);
                    length_spinner.setSelection(1);
                } else if(location_spinner.getSelectedItem().equals("Hoang Lien National Park")) {
                    name_spinner.setSelection(2);
                    length_spinner.setSelection(2);
                } else if (location_spinner.getSelectedItem().equals("TayHo - HaNoi")) {
                    name_spinner.setSelection(3);
                    length_spinner.setSelection(3);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Level Spinner Setup
        ArrayAdapter<String> adapterLevel = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, levels);
        level_spinner.setAdapter(adapterLevel);
    }

    //Show Confirm Dialog
    public void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Hike Information");
        String message01 = "Name: " + name_spinner.getSelectedItem().toString();
        String message02 = "Location: " + location_spinner.getSelectedItem().toString();
        String message03 = "Length: " + length_spinner.getSelectedItem().toString();
        String message04 = "Date: " + viewDate.getText().toString();
        String message05 = "Level: " + level_spinner.getSelectedItem().toString();
        String message06 = "Parking: " + selectedRadioButton.getText().toString();
        String finalMessage = message01 + "\n" + message02 + "\n" + message03 + "\n" + message04 + "\n" + message05 + "\n" + message06;
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
                alertDialog.dismiss();
            }
        });
        builder.create().show();
    }

    //Save New Data to Database
    public void saveNewHike() {
        DatabaseHelper DB = new DatabaseHelper(this);
        String nameSelected = name_spinner.getSelectedItem().toString();
        String locationSelected = location_spinner.getSelectedItem().toString();
        String lengthSelected = length_spinner.getSelectedItem().toString();
        String dateSelected = viewDate.getText().toString();
        String leveSelected = level_spinner.getSelectedItem().toString();
        String parkingSelected = selectedRadioButton.getText().toString();
        String descriptionWrote = description.getText().toString();
        HikeModal newHike = new HikeModal(1, nameSelected, locationSelected, lengthSelected, dateSelected, leveSelected, parkingSelected, descriptionWrote);
        DB.handleCreateNewHike(newHike);
    }
}
