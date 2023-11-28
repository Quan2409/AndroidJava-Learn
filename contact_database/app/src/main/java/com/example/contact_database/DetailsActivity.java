package com.example.contact_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PersonAdapter personAdapter;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        recyclerView = findViewById(R.id.rvDetails);
        //Get Data From The Database
        DB = new DatabaseHelper(this);
        personAdapter = new PersonAdapter(DB.handleRead());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(personAdapter);
    }
}
