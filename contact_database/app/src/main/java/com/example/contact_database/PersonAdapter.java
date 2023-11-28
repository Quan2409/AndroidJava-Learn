package com.example.contact_database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PersonAdapter extends  RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    ArrayList<PersonModal> people;
    public PersonAdapter(ArrayList<PersonModal> people) {
        this.people = people;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new PersonViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        PersonModal personModal = people.get(position);
        holder.rcName.setText(personModal.getName());
        holder.rcDoB.setText(personModal.getDob());
        holder.rcEmail.setText(personModal.getEmail());
        holder.rcImage.setImageBitmap(personModal.getImage());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        public TextView rcName, rcDoB, rcEmail;
        public ImageView rcImage;
        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            rcImage = itemView.findViewById(R.id.rc_image);
            rcName = itemView.findViewById(R.id.rc_name);
            rcDoB = itemView.findViewById(R.id.rc_dob);
            rcEmail = itemView.findViewById(R.id.rc_email);

        }
    }
}

