package com.example.m_hike;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewHikeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    //Declare Widgets in XML Design
    EditText description;
    TextView viewDate;
    Button backToListButton, addNewHikeButton, chooseDate;
    Spinner name_spinner;
    Spinner level_spinner;
    Spinner location_spinner;
    Spinner length_spinner;
    RadioGroup radioGroup;
    RadioButton yes_no_btn;

    String[] nameHike = new String[] {"Pick a Hike", "Sapa Bamboo Trail", "Mount Fansipan Trail", "West Lake Loop"};
    String[] locationHike = new String[] {"Location", "Sapa - LaoCai", "Hoang Lien National Park", "TayHo - HaNoi"};
    String[] lengthHike = new String[] {"Length", "6.3km", "8.4km", "15.0km"};
    String[] levels = new String[] { "Level", "Easy", "Moderate", "Hard" };
    boolean isAllFieldChecked = false;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hike);
        //Call Function Initialize the widgets
        mappingID();

        addNewHikeButton.setOnClickListener(v -> {
            isAllFieldChecked = CheckAllFields();
            if (isAllFieldChecked) {
                showConfirmDialog();
            }
        });

        //Back To User Page
        backToListButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListHikeActivity.class);
            startActivity(intent);
        });

        //Name Spinner Setup
        ArrayAdapter<String> adapterName = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nameHike );
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
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        //Location Spinner Setup
        ArrayAdapter<String> adapterLocation = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, locationHike);
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
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        //Length Spinner Setup
        ArrayAdapter<String> adapterLength = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lengthHike);
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
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        // Level Spinner Setup
        ArrayAdapter<String> adapterLevel = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, levels);
        level_spinner.setAdapter(adapterLevel);
        level_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //...
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        //Setup Button Show Calender
        chooseDate.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        });
    }

    //Mapping ID from XLM Design
    public void mappingID() {
        viewDate = findViewById(R.id.viewDate);
        backToListButton = findViewById(R.id.backToListButton);
        addNewHikeButton = findViewById(R.id.addNewHikeButton);
        chooseDate = findViewById(R.id.chooseDate);
        name_spinner = findViewById(R.id.name_spinner);
        location_spinner = findViewById(R.id.location_spinner);
        length_spinner = findViewById(R.id.length_spinner);
        description = findViewById(R.id.description);
        level_spinner = findViewById(R.id.level_spinner);
        radioGroup = findViewById(R.id.radio_group);
        yes_no_btn = findViewById(R.id.yes_btn);
        yes_no_btn = findViewById(R.id.no_btn);
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
        TextView errorNameHike = (TextView) name_spinner.getSelectedView();
        if(name_spinner.getSelectedItem().equals("Pick a Hike")) {
            errorNameHike.setError("");
            errorNameHike.setTextColor(Color.RED);
            errorNameHike.setText("Pick A Hike Please");
            return false;
        }

        TextView errorLevelHike = (TextView) level_spinner.getSelectedView();
        if(level_spinner.getSelectedItem().equals("Level")){
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
        LayoutInflater inflater = getLayoutInflater();
        View customView = inflater.inflate(R.layout.activity_custom_dialog, null);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        yes_no_btn = findViewById(selectedId);

        TextView titleView = customView.findViewById(R.id.dialogTitle);
        TextView messageView = customView.findViewById(R.id.dialogMessages);
        Button positiveButton = customView.findViewById(R.id.positiveButton);
        Button negativeButton = customView.findViewById(R.id.negativeButton);
        titleView.setText("Confirm Hike Information");
        String message01 = "Name: " + name_spinner.getSelectedItem().toString();
        String message02 = "Location: " + location_spinner.getSelectedItem().toString();
        String message03 = "Length: " + length_spinner.getSelectedItem().toString();
        String message04 = "Date: " + viewDate.getText().toString();
        String message05 = "Level: " + level_spinner.getSelectedItem().toString();
        String message06 = "Parking: " + yes_no_btn.getText().toString();
        String finalMessage = message01 + "\n" + message02 + "\n" + message03 + "\n" + message04 + "\n" + message05 + "\n" + message06;
        messageView.setText(finalMessage);
        messageView.setTextColor(Color.BLACK);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewHike();
                Intent intent = new Intent(NewHikeActivity.this, ListHikeActivity.class);
                startActivity(intent);
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(customView);
        alertDialog = builder.create();
        alertDialog.show();
    }

    //Save New Data to Database
    public void saveNewHike() {
        DatabaseHelper DB = new DatabaseHelper(this);
        String nameSelected = name_spinner.getSelectedItem().toString();
        String locationSelected = location_spinner.getSelectedItem().toString();
        String lengthSelected = length_spinner.getSelectedItem().toString();
        String dateSelected = viewDate.getText().toString();
        String leveSelected = level_spinner.getSelectedItem().toString();
        String parkingSelected = yes_no_btn.getText().toString();
        String descriptionWrote = description.getText().toString();
        HikeModal newHike = new HikeModal(1, nameSelected, locationSelected, lengthSelected, dateSelected, leveSelected, parkingSelected, descriptionWrote);
        DB.handleCreate(newHike);
    }
}
