package com.example.m_hike;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ObservationAdapter extends RecyclerView.Adapter<ObservationAdapter.ObservationViewHolder> {
    Activity activity;
    Context context;
    Cursor cursor;
    ArrayList<ObservationModal> listOBS;

    public ObservationAdapter(Activity activity, Context context, Cursor cursor, ArrayList<ObservationModal> listOBS) {
        this.activity = activity;
        this.context = context;
        this.cursor = cursor;
        this.listOBS = listOBS;
    }

    public class ObservationViewHolder extends RecyclerView.ViewHolder {
        TextView rcObsName, rcObsTime;
        CardView rcObsLayout;

        public ObservationViewHolder(@NonNull View itemView) {
            super(itemView);
            rcObsName = itemView.findViewById(R.id.rc_name_obs);
            rcObsTime = itemView.findViewById(R.id.rc_time_obs);
            rcObsLayout = itemView.findViewById(R.id.rc_obs_layout);
        }
    }

    @NonNull
    @Override
    public ObservationAdapter.ObservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycle_observation, parent, false);
        return new ObservationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ObservationViewHolder holder, int position) {
       ObservationModal observationModal = listOBS.get(position);
       holder.rcObsName.setText(observationModal.getObservationName());
       holder.rcObsTime.setText(observationModal.getObservationTime());
       holder.rcObsLayout.setOnClickListener(v -> sendDataToDetailOBS(observationModal));
    }

    @Override
    public int getItemCount() {
        return listOBS.size();
    }

    public void sendDataToDetailOBS(ObservationModal observationModal) {
        Intent intent = new Intent(context, DetailObservationActivity.class);
        intent.putExtra("observation-id", String.valueOf(observationModal.getObservationID()));
        intent.putExtra("observation-name", observationModal.getObservationName());
        intent.putExtra("observation-time", observationModal.getObservationTime());
        intent.putExtra("observation-comment", observationModal.getObservationComment());
        activity.startActivityForResult(intent, 1);
    }
}
