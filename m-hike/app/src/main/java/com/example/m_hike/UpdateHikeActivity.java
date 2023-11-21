package com.example.m_hike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateHikeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Spinner update_name, update_location, update_length, update_level;
    RadioGroup radioGroup;
    RadioButton update_btn;
    TextView update_date, update_description, viewDate;
    Button updateItemButton, chooseDate;
    HikeAdapter hikeAdapter;


    String[] nameHike = new String[] {"Pick a Hike", "Sapa Bamboo Trail", "Mount Fansipan Trail", "West Lake Loop"};
    String[] locationHike = new String[] {"Location", "Sapa - LaoCai", "Hoang Lien National Park", "TayHo - HaNoi"};
    String[] lengthHike = new String[] {"Length", "6.3km", "8.4km", "15.0km"};
    String[] levels = new String[] { "Level", "Easy", "Moderate", "Hard" };
    String id, name, location, length, date, level, parking, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hike);
        mappingID();
        getIntentData();
        getItemClicked();

        updateItemButton.setOnClickListener(v -> {
            DatabaseHelper DB = new DatabaseHelper(UpdateHikeActivity.this);
            String newName = update_name.getSelectedItem().toString();
            String newLocation = update_location.getSelectedItem().toString();
            String newLength = update_length.getSelectedItem().toString();
            String newDate = viewDate.getText().toString();
            String newLevel = update_level.getSelectedItem().toString();
            String newParking = update_btn.getText().toString();
            String newDescription = update_description.getText().toString();

            HikeModal newHikeModal = new HikeModal(Integer.parseInt(id), newName, newLocation, newLength, newDate, newLevel, newParking, newDescription);
            DB.handleUpdate(newHikeModal);
            refreshUI();
            Intent intent = new Intent(this, ListHikeActivity.class);
            startActivity(intent);
        });

        //Setup Button Show Calender
        chooseDate.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        });
    }

    public void mappingID() {
        updateItemButton = findViewById(R.id.updateItemButton);
        chooseDate = findViewById(R.id.chooseDate);
        viewDate = findViewById(R.id.editDate);
        update_name = findViewById(R.id.name_edit);
        update_location = findViewById(R.id.location_edit);
        update_length = findViewById(R.id.length_edit);
        update_date = findViewById(R.id.editDate);
        update_level = findViewById(R.id.level_edit);
        update_description = findViewById(R.id.description_edit);
        radioGroup = findViewById(R.id.radio_group);
        update_btn = findViewById(R.id.yes_btn);
        update_btn = findViewById(R.id.no_btn);
    }

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

    public void getIntentData() {
        if (getIntent().hasExtra("id-value") && getIntent().hasExtra("name-value") && getIntent().hasExtra("name-value") && getIntent().hasExtra("location-value") && getIntent().hasExtra("length-value") &&
                getIntent().hasExtra("date-value") && getIntent().hasExtra("level-value") && getIntent().hasExtra("parking-value") && getIntent().hasExtra("description-value")) {

            id = getIntent().getStringExtra("id-value");

            description = getIntent().getStringExtra("description-value");
            update_description.setText(description);

            name = getIntent().getStringExtra("name-value");
           update_name.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nameHike));
           if (name.equals("Sapa Bamboo Trail")) {
               update_name.setSelection(1);
           } else if (name.equals("Mount Fansipan Trail")) {
               update_name.setSelection(2);
           } else if (name.equals("West Lake Loop")) {
               update_name.setSelection(3);
           }

           location = getIntent().getStringExtra("location-value");
           update_location.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, locationHike));
           if(location.equals("Sapa - LaoCai")){
               update_location.setSelection(1);
           } else if (location.equals("Hoang Lien National Park")) {
               update_location.setSelection(2);
           } else if (location.equals("TayHo - HaNoi")) {
               update_location.setSelection(3);
           }

           length = getIntent().getStringExtra("length-value");
           update_length.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lengthHike));
           if(length.equals("6.3km")) {
               update_length.setSelection(1);
           } else if (length.equals("8.4km")) {
               update_length.setSelection(2);
           } else if (length.equals("15.0km")) {
               update_length.setSelection(3);
           }

           date = getIntent().getStringExtra("date-value");
           update_date.setText(date);

           level = getIntent().getStringExtra("level-value");
           update_level.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, levels));
           if(level.equals("Easy")) {
               update_level.setSelection(1);
           } else if (level.equals("Moderate")) {
               update_level.setSelection(2);
           } else if (level.equals("Hard")) {
               update_level.setSelection(3);
           }

           parking = getIntent().getStringExtra("parking-value");
            if (parking.equals("Yes")) {
                update_btn = findViewById(R.id.yes_btn);
                update_btn.setChecked(true);
            } else {
                update_btn.setChecked(true);
            }
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();
        }
    }

    public void getItemClicked() {
        update_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(update_name.getSelectedItem().equals("Sapa Bamboo Trail")) {
                    update_location.setSelection(1);
                    update_length.setSelection(1);
                } else if (update_name.getSelectedItem().equals("Mount Fansipan Trail")) {
                    update_location.setSelection(2);
                    update_length.setSelection(2);
                } else if (update_name.getSelectedItem().equals("West Lake Loop")) {
                    update_location.setSelection(3);
                    update_length.setSelection(3);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        update_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(update_location.getSelectedItem().equals("Sapa - Laocai")) {
                    update_name.setSelection(1);
                    update_length.setSelection(1);
                } else if (update_location.getSelectedItem().equals("Hoang Lien National Park")) {
                    update_name.setSelection(2);
                    update_length.setSelection(2);
                } else if (update_location.getSelectedItem().equals("TayHo - HaNoi")) {
                    update_name.setSelection(3);
                    update_length.setSelection(3);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        update_length.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(update_length.getSelectedItem().equals("6.3km")) {
                    update_name.setSelection(1);
                    update_location.setSelection(1);
                } else if (update_length.getSelectedItem().equals("8.4km")) {
                    update_name.setSelection(2);
                    update_location.setSelection(2);
                } else if (update_length.getSelectedItem().equals("15.0km")) {
                    update_name.setSelection(3);
                    update_location.setSelection(3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //...
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (update_btn.equals(findViewById(R.id.yes_btn))) {
                    update_btn = findViewById(radioGroup.getCheckedRadioButtonId());
                } else if (update_btn.equals(findViewById(R.id.no_btn))) {
                    update_btn = findViewById(radioGroup.getCheckedRadioButtonId());
                }
            }
        });
    }
    private void refreshUI() {
        if (hikeAdapter != null) {
           hikeAdapter.notifyDataSetChanged();
        }
    }
}