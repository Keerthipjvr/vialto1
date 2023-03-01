package com.example.vialto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    private ArrayList name, address, skill, experience, contact, email, location;

    public MyAdapter(Context context, ArrayList name, ArrayList address, ArrayList skill, ArrayList experience, ArrayList
            contact, ArrayList email, ArrayList location){
        this.context = context;
        this.name = name;
        this.address = address;
        this.skill = skill;
        this.experience = experience;
        this.contact = contact;
        this.email = email;
        this.location = location;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(String.valueOf(name.get(position)));
        holder.address.setText(String.valueOf(address.get(position)));
        holder.skill.setText(String.valueOf(skill.get(position)));
        holder.experience.setText(String.valueOf(experience.get(position)));
        holder.contact.setText(String.valueOf(contact.get(position)));
        holder.email.setText(String.valueOf(email.get(position)));
        holder.location.setText(String.valueOf(location.get(position)));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, skill, experience, contact, email, location;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            address = itemView.findViewById(R.id.tvAddress);
            skill = itemView.findViewById(R.id.tvSkill);
            experience = itemView.findViewById(R.id.tvExperience);
            contact = itemView.findViewById(R.id.tvContact);
            email = itemView.findViewById(R.id.tvEmail);
            location = itemView.findViewById(R.id.tvLocation);

        }
    }
}
