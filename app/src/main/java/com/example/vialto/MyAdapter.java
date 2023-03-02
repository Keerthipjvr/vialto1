package com.example.vialto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
   // private ArrayList name, address, skill, experience, contact, email, location;
    private  ArrayList<User> userArrayList;

//    public MyAdapter(Context context, ArrayList name, ArrayList address, ArrayList skill, ArrayList experience, ArrayList
//            contact, ArrayList email, ArrayList location, ArrayList weather){
//        this.context = context;
//        this.name = name;
//        this.address = address;
//        this.skill = skill;
//        this.experience = experience;
//        this.contact = contact;
//        this.email = email;
//        this.location = location;
//        this.weatherModelArrayList = weather;
//    }

    public MyAdapter(Context context, ArrayList<User> weatherModelArrayList){
        this.context = context;
        this.userArrayList = weatherModelArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = userArrayList.get(position);
        holder.name.setText(String.valueOf(user.getName()));
        holder.address.setText(String.valueOf(user.getAddress()));
        holder.skill.setText(String.valueOf(user.getSkill()));
        holder.experience.setText(String.valueOf(user.getExperience()));
        holder.contact.setText(String.valueOf(user.getContact()));
        holder.email.setText(String.valueOf(user.getEmail()));
        holder.location.setText(String.valueOf(user.getLocation()));
        holder.weather.setText(String.valueOf(user.getWeather()));

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, skill, experience, contact, email, location, weather;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            address = itemView.findViewById(R.id.tvAddress);
            skill = itemView.findViewById(R.id.tvSkill);
            experience = itemView.findViewById(R.id.tvExperience);
            contact = itemView.findViewById(R.id.tvContact);
            email = itemView.findViewById(R.id.tvEmail);
            location = itemView.findViewById(R.id.tvLocation);
            weather = itemView.findViewById(R.id.tvWeather);

        }
    }
}
