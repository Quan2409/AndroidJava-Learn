package com.example.m_hike;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Filter;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.HikeViewHolder> {
    Activity activity;
    Context context;
    Cursor cursor;
    ArrayList<HikeModal> listHike;
    ArrayList<HikeModal> filterHike;

    public HikeAdapter(Activity activity, Context context, Cursor cursor, ArrayList<HikeModal> listHike) {
        this.activity = activity;
        this.context = context;
        this.cursor = cursor;
        this.listHike = listHike;
        this.filterHike = listHike;
    }

    @NonNull
    @Override
    public HikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycle_hike, parent, false);
        return new HikeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HikeViewHolder holder, int position) {

        HikeModal hikeModal = listHike.get(position);
        holder.rcNameHike.setText(hikeModal.getName());
        holder.rcLocationHike.setText(hikeModal.getLocation());
        holder.rcLengthHike.setText(hikeModal.getLength());
        holder.rcDateHike.setText(hikeModal.getDate());
        holder.rcLevelHike.setText(hikeModal.getLevel());
        holder.rcLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context != null) {
                    Intent intent = new Intent(context, DetailHikeActivity.class);
                    intent.putExtra("id", String.valueOf(hikeModal.getId()));
                    intent.putExtra("name", hikeModal.getName());
                    intent.putExtra("location", hikeModal.getLocation());
                    intent.putExtra("length", hikeModal.getLength());
                    intent.putExtra("date", hikeModal.getDate());
                    intent.putExtra("level", hikeModal.getLevel());
                    intent.putExtra("parking", hikeModal.getParking());
                    intent.putExtra("description",hikeModal.getDescription());
                    activity.startActivityForResult(intent, 1);
                } else {
                    Log.e("HikeAdapter", "Context is null. Unable to start DetailHikeActivity.");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHike.size();
    }

    public class HikeViewHolder extends RecyclerView.ViewHolder {
        TextView rcNameHike, rcLocationHike, rcLengthHike, rcDateHike, rcLevelHike;
        CardView rcLayout;

        public HikeViewHolder(@NonNull View itemView) {
            super(itemView);
            rcNameHike = itemView.findViewById(R.id.rc_name_hike);
            rcLocationHike = itemView.findViewById(R.id.rc_location_hike);
            rcLengthHike = itemView.findViewById(R.id.rc_length_hike);
            rcDateHike = itemView.findViewById(R.id.rc_date_hike);
            rcLevelHike = itemView.findViewById(R.id.rc_level_hike);
            rcLayout = itemView.findViewById(R.id.rc_layout);
        }
    }

    //Filter Data
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    listHike = filterHike;
                } else {
                    ArrayList<HikeModal> filteredList = new ArrayList<>();
                    for(HikeModal hikeModal : filterHike) {
                        if(hikeModal.getName().toLowerCase().contains(charString)) {
                            filteredList.add(hikeModal);
                        }
                    }
                    listHike = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listHike;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listHike = (ArrayList<HikeModal>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
