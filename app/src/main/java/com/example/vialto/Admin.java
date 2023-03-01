package com.example.vialto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;

public class Admin extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> name, address, skill, experience, contact, email, location;
    Database DB;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        DB = new Database(this);
        name = new ArrayList<>();
        address = new ArrayList<>();
        skill = new ArrayList<>();
        experience = new ArrayList<>();
        contact = new ArrayList<>();
        email = new ArrayList<>();
        location = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, name, address, skill, experience, contact, email, location);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata()
    {
        Cursor cursor = DB.getData();
        if(cursor.getCount()==0){
            Toast.makeText(Admin.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while (cursor.moveToNext())
            {
                name.add(cursor.getString(0));
                address.add(cursor.getString(1));
                skill.add(cursor.getString(2));
                experience.add(cursor.getString(3));
                contact.add(cursor.getString(4));
                email.add(cursor.getString(5));
                location.add(cursor.getString(6));


            }
        }
    }
}