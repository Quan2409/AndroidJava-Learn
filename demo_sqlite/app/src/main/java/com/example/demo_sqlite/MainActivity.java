package com.example.demo_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //references to button and other controls layout
    Button viewBtn, addBtn;
    EditText inputName, inputAge;
    Switch activeBtn;
    ListView list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewBtn = findViewById(R.id.viewBtn);
        addBtn = findViewById(R.id.addBtn);
        inputName = findViewById(R.id.inputName);
        inputAge = findViewById(R.id.inputAge);
        activeBtn = findViewById(R.id.activeBtn);
        list_view = findViewById(R.id.list_view);

        //button onClick();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;
                try {
                    customerModel = new CustomerModel(-1, inputName.getText().toString(), Integer.parseInt(inputAge.getText().toString()), activeBtn.isChecked());
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Enter Input Please", Toast.LENGTH_LONG).show();
                    customerModel = new CustomerModel(-1, "error", 0, false);
                }
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                boolean success = databaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "Success=" + success, Toast.LENGTH_LONG).show();
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "View Button", Toast.LENGTH_LONG).show();
            }
        });
    }
}

