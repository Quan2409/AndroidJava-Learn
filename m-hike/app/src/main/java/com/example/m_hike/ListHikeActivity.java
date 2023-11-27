package com.example.m_hike;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m_hike.databinding.ActivityListHikeBinding;

import java.util.ArrayList;

public class ListHikeActivity extends AppCompatActivity {
    ActivityListHikeBinding binding;
    Button addNewButton;
    SearchView searchBar;
    Cursor cursor;
    DatabaseHelper dbHelper;
    HikeAdapter hikeAdapter;
    RecyclerView hikeRecyclerView;
    Context context = ListHikeActivity.this;
    ArrayList<HikeModal> allHike = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListHikeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        readAllHike();
        setOnClick();

        //Binding ID
        searchBar = binding.searchBar;
        addNewButton = binding.hikeAddNewButton;
    }

    public void setOnClick() {
        addNewButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewHikeActivity.class);
            startActivity(intent);
        });

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    public void readAllHike() {
        hikeRecyclerView = binding.hikeRecycleIew;
        dbHelper = new DatabaseHelper(context);
        allHike = dbHelper.handleReadAllHike();
        hikeRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        hikeRecyclerView.setHasFixedSize(true);
        hikeAdapter = new HikeAdapter(this, context, cursor, allHike);
        hikeRecyclerView.setAdapter(hikeAdapter);
    }
}



