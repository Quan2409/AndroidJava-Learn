package com.example.m_hike;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListHikeActivity extends AppCompatActivity {

    SearchView searchBar;
    Button addNewButton;
    RecyclerView recyclerView;

    Context context = ListHikeActivity.this;
    Cursor cursor;
    ArrayList<HikeModal> allHike = new ArrayList<>();
    HikeAdapter hikeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hike);
        mappingID();

        DatabaseHelper DB = new DatabaseHelper(this);
        allHike = DB.handleRead();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        if (allHike.size() > 0){
            recyclerView.setVisibility(View.VISIBLE);
            hikeAdapter = new HikeAdapter(this, context, cursor, allHike);
            recyclerView.setAdapter(hikeAdapter);
        } else  {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();
        }

        addNewButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewHikeActivity.class);
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

    public void mappingID() {
       addNewButton = findViewById(R.id.add_new_button);
       searchBar = findViewById(R.id.search_bar);
       recyclerView = findViewById(R.id.recycleView);
    }
}



