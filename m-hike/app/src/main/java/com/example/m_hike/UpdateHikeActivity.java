package com.example.m_hike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m_hike.databinding.ActivityUpdateHikeBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateHikeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Spinner getName;
    Spinner getLocation;
    Spinner getLength;
    Spinner getLevel;
    TextView getDescription;
    TextView getDate;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
    Button chooseDate;
    Button updateButton;

    DatabaseHelper dbHelper;
    HikeModal hikeModal;
    Context context = UpdateHikeActivity.this;

    String[] allHike = new String[] {"Pick a Hike", "Sapa Bamboo Trail", "Mount Fansipan Trail", "West Lake Loop"};
    String[] allLocation = new String[] {"Location", "Sapa - LaoCai", "Hoang Lien National Park", "TayHo - HaNoi"};
    String[] allLength = new String[] {"Length", "6.3km", "8.4km", "15.0km"};
    String[] allLevel = new String[] { "Level", "Easy", "Moderate", "Hard" };
    String id, name, location, length, date, level, parking, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hike);
        mappingID();
        getIntentDataFromDetail();
        setOnClick();
    }

    public void mappingID() {
       getName = findViewById(R.id.nameSelected);
       getLocation = findViewById(R.id.locationSelected);
       getLength = findViewById(R.id.lengthSelected);
       getDate = findViewById(R.id.dateSelected);
       getLevel = findViewById(R.id.levelSelected);
       getDescription = findViewById(R.id.descriptionSelected);
       radioGroup = findViewById(R.id.radioGroup);
//       selectedRadioButton = findViewById(R.id.yesBtn);
//       selectedRadioButton = findViewById(R.id.noBtn);
       chooseDate = findViewById(R.id.chooseDate);
       updateButton = findViewById(R.id.updateButton);
    }

    public void getIntentDataFromDetail() {
        Intent intent = getIntent();
        if (intent.hasExtra("id-value") && intent.hasExtra("name-value") && intent.hasExtra("name-value") && intent.hasExtra("location-value") && intent.hasExtra("length-value") &&
                intent.hasExtra("date-value") && intent.hasExtra("level-value") && intent.hasExtra("parking-value") && intent.hasExtra("description-value")) {

            id = intent.getStringExtra("id-value");

            name = intent.getStringExtra("name-value");
            getName.setAdapter(new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, allHike));
            if (name.equals("Sapa Bamboo Trail")) {
                getName.setSelection(1);
            }else if (name.equals("Mount Fansipan Trail")) {
                getName.setSelection(2);
            }else if (name.equals("West Lake Loop")) {
                getName.setSelection(3);
            }

            location = intent.getStringExtra("location-value");
            getLocation.setAdapter(new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, allLocation));
            if(location.equals("Sapa - LaoCai")){
                getLocation.setSelection(1);
            } else if (location.equals("Hoang Lien National Park")) {
                getLocation.setSelection(2);
            } else if (location.equals("TayHo - HaNoi")) {
                getLocation.setSelection(3);
            }

            length = intent.getStringExtra("length-value");
            getLength.setAdapter(new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, allLength));
            if(length.equals("6.3km")) {
                getLength.setSelection(1);
            } else if (length.equals("8.4km")) {
                getLength.setSelection(2);
            } else if (length.equals("15.0km")) {
                getLength.setSelection(3);
            }

            level = intent.getStringExtra("level-value");
            getLevel.setAdapter(new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, allLevel));
            if(level.equals("Easy")) {
                getLevel.setSelection(1);
            } else if (level.equals("Moderate")) {
                getLevel.setSelection(2);
            } else if (level.equals("Hard")) {
                getLevel.setSelection(3);
            }

            date = intent.getStringExtra("date-value");
            getDate.setText(date);

            description = intent.getStringExtra("description-value");
            getDescription.setText(description);

            parking = intent.getStringExtra("parking-value");
            if (parking.equals("Yes")) {
                selectedRadioButton = findViewById(R.id.yesBtn);
                selectedRadioButton.setChecked(true);
            } else if (parking.equals("No")) {
                selectedRadioButton = findViewById(R.id.noBtn);
                selectedRadioButton.setChecked(true);
            }
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();
        }
    }

    public void setOnClick() {
        updateButton.setOnClickListener(v -> {
            updateHike();
        });

        chooseDate.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date-picker");
        });

        radioGroup.getCheckedRadioButtonId();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioButton = findViewById(checkedId);
            }
        });

        getName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (getName.getSelectedItem().equals("Sapa Bamboo Trail")) {
                    getLocation.setSelection(1);
                    getLength.setSelection(1);
                } else if (getName.getSelectedItem().equals("Mount Fansipan Trail")) {
                    getLocation.setSelection(2);
                    getLength.setSelection(2);
                } else if (getName.getSelectedItem().equals("West Lake Loop")) {
                    getLocation.setSelection(3);
                    getLength.setSelection(3);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        getLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (getLocation.getSelectedItem().equals("Sapa - Laocai")) {
                    getName.setSelection(1);
                    getLength.setSelection(1);
                } else if (getLocation.getSelectedItem().equals("Hoang Lien National Park")) {
                    getName.setSelection(2);
                    getLength.setSelection(2);
                } else if (getLocation.getSelectedItem().equals("TayHo - HaNoi")) {
                    getName.setSelection(3);
                    getLength.setSelection(3);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        getLength.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (getLength.getSelectedItem().equals("6.3km")) {
                    getName.setSelection(1);
                    getLocation.setSelection(1);
                } else if (getLength.getSelectedItem().equals("8.4km")) {
                    getName.setSelection(2);
                    getLocation.setSelection(2);
                } else if (getLength.getSelectedItem().equals("15.0km")) {
                    getName.setSelection(3);
                    getLocation.setSelection(3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.YEAR, year);
        calender.set(Calendar.MONTH, month);
        calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormat.getDateInstance().format(calender.getTime());
        getDate.setText(currentDate);
        if(getDate.getText().toString().equals("View Date")) {
            getDate.setTextColor(Color.RED);
            getDate.setText("Pick A Date, Please");
        }
    }

    public void updateHike() {
        dbHelper = new DatabaseHelper(context);
        String newName = getName.getSelectedItem().toString();
        String newLocation = getLocation.getSelectedItem().toString();
        String newLength = getLength.getSelectedItem().toString();
        String newDate = getDate.getText().toString();
        String newLevel = getLevel.getSelectedItem().toString();
        String newParking = selectedRadioButton.getText().toString();
        String newDescription = getDescription.getText().toString();
        hikeModal = new HikeModal(Integer.parseInt(id), newName, newLocation, newLength, newDate, newLevel, newParking, newDescription);
        dbHelper.handleUpdateHike(hikeModal);
        Intent intent = new Intent(context, ListHikeActivity.class);
        startActivity(intent);
    }
}