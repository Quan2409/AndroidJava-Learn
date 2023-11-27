package com.example.m_hike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class DetailObservationActivity extends AppCompatActivity {

    TextView detailOBSID, detailOBSName, detailOBSTime, detailOBSComment;
    Button deleteBtn;
    ImageButton editBtn;
    String id, name, time, comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_observation);
        mappingID();
        getIntentData();

        editBtn.setOnClickListener(v -> sendDataToUpdateOBS());
        deleteBtn.setOnClickListener(v -> deleteObservation());
    }

    public void mappingID() {
        editBtn = findViewById(R.id.edit_button);
        deleteBtn = findViewById(R.id.obs_delete_button);
        detailOBSID = findViewById(R.id.detail_obs_id);
        detailOBSName = findViewById(R.id.detail_name);
        detailOBSTime = findViewById(R.id.detail_time);
        detailOBSComment = findViewById(R.id.detail_description);
    }

    public  void getIntentData() {
        if(getIntent().hasExtra("observation-id") && getIntent().hasExtra("observation-name") && getIntent().hasExtra("observation-time") && getIntent().hasExtra("observation-comment")){

            id = getIntent().getStringExtra("observation-id");
            name = getIntent().getStringExtra("observation-name");
            time = getIntent().getStringExtra("observation-time");
            comment = getIntent().getStringExtra("observation-comment");

            detailOBSID.setText(id);
            detailOBSName.setText(name);
            detailOBSTime.setText(time);
            detailOBSComment.setText(comment);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteObservation() {
        DatabaseHelper DB = new DatabaseHelper(DetailObservationActivity.this);
        DB.handleDeleteObservation(Integer.parseInt(id));
        Intent intent = new Intent(DetailObservationActivity.this, DetailHikeActivity.class);
        startActivity(intent);
    }

    public void sendDataToUpdateOBS() {
        Intent intent = new Intent (DetailObservationActivity.this, UpdateObservationActivity.class);
        intent.putExtra("id-value", detailOBSID.getText().toString());
        intent.putExtra("name-value", detailOBSName.getText().toString());
        intent.putExtra("time-value", detailOBSTime.getText().toString());
        intent.putExtra("comment-value", detailOBSComment.getText().toString());
        DetailObservationActivity.this.startActivity(intent);
    }
}