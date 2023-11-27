package com.example.m_hike;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Filter;
import android.widget.TextView;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

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
        return new HikeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycle_hike, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HikeViewHolder holder, int position) {
        HikeModal hikeModal = listHike.get(position);
        holder.rcNameHike.setText(hikeModal.getHikeName());
        holder.rcLocationHike.setText(hikeModal.getHikeLocation());
        holder.rcLengthHike.setText(hikeModal.getHikeLength());
        holder.rcDateHike.setText(hikeModal.getHikeDate());
        holder.rcLevelHike.setText(hikeModal.getHikeLevel());
        holder.rcLayout.setOnClickListener(v -> sendDataToDetailPage(hikeModal));
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
                String charString = constraint.toString().toLowerCase();
                if (charString.isEmpty()) {
                    listHike = filterHike;
                } else {
                    ArrayList<HikeModal> filteredList = new ArrayList<>();
                    for(HikeModal hikeModal : filterHike) {
                        String name = hikeModal.getHikeName().toLowerCase();
                        if(name.toLowerCase().contains(charString) || name.toUpperCase().contains(charString) || name.startsWith(charString)) {
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

    public void sendDataToDetailPage(HikeModal hikeModal) {
            Intent intent = new Intent(context, DetailHikeActivity.class);
            intent.putExtra("hike-id", String.valueOf(hikeModal.getHikeID()));
            intent.putExtra("hike-name", hikeModal.getHikeName());
            intent.putExtra("hike-location", hikeModal.getHikeLocation());
            intent.putExtra("hike-length", hikeModal.getHikeLength());
            intent.putExtra("hike-date", hikeModal.getHikeDate());
            intent.putExtra("hike-level", hikeModal.getHikeLevel());
            intent.putExtra("hike-parking", hikeModal.getHikeParking());
            intent.putExtra("hike-description",hikeModal.getHikeDescription());
            activity.startActivityForResult(intent, 1);
    }
}
