package com.example.m_hike;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListHikeActivity extends AppCompatActivity {

    SearchView searchBar;
    Button addButton;
    RecyclerView hikeRecycleView;
    Cursor cursor;
    DatabaseHelper dbHelper;
    HikeAdapter hikeAdapter;
    Context context = ListHikeActivity.this;
    ArrayList<HikeModal> allHike = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hike);
        mappingID();
        setOnClick();
        readAllHike();
    }

    public void mappingID() {
        searchBar = findViewById(R.id.searchBar);
        addButton = findViewById(R.id.addButton);
        hikeRecycleView = findViewById(R.id.hikeRecycleView);
    }

    public void setOnClick() {
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (hikeAdapter != null) {
                    hikeAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewHikeActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    public void readAllHike() {
        dbHelper = new DatabaseHelper(context);
        allHike = dbHelper.handleReadAllHike();
        hikeRecycleView.setLayoutManager(new LinearLayoutManager(context));
        hikeRecycleView.setHasFixedSize(true);
        hikeAdapter = new HikeAdapter(this, context, cursor, allHike);
        hikeRecycleView.setAdapter(hikeAdapter);
    }
}



