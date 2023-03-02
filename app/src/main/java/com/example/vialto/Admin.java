package com.example.vialto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    Database DB;
    MyAdapter adapter;
    TextView weather;
    private  ArrayList<weatherModel> weatherModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        DB = new Database(this);
        userArrayList=new ArrayList<>();
        weather = findViewById(R.id.Weather);

       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        weatherModelArrayList = new ArrayList<>();



        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
        adapter = new MyAdapter(this, userArrayList);
        recyclerView.setAdapter(adapter);
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
                User  user=new User();
                user.setName(cursor.getString(0));

                user.setAddress(cursor.getString(1));
                user.setSkill(cursor.getString(2));
                user.setExperience(cursor.getString(3));
                user.setContact(cursor.getString(4));
                user.setEmail(cursor.getString(5));
                user.setLocation(cursor.getString(6));
                user.setWeather(cursor.getString(7));
                userArrayList.add(user);
            }
        }
    }
}