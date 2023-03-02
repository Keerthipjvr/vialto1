package com.example.vialto;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    EditText name, address, contact, email, location;
    AutoCompleteTextView skill,experience;
    Button save, cancel;
    String Name, Address, Skill, Experience, Contact, Email, Location;
    Database DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

      //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.etName);
        address = findViewById(R.id.etAddress);
        contact = findViewById(R.id.etContact);
        email = findViewById(R.id.etEmail);
        location = findViewById(R.id.etLocation);

        save = findViewById(R.id.btnSave);
        cancel = findViewById(R.id.btnCancel);

        skill = findViewById(R.id.auto_com_text1);
        experience = findViewById(R.id.auto_com_text2);

        String[] skills={"Android",".Net","Testing","PowerApps","Java","Python","Finance","BA","Recruiting" };
        String[] exp={"6months","1 yrs","2 yrs","3 yrs","4 yrs","5 yrs","6 yrs","7 yrs", "8 yrs","9 yrs","10 yrs", "10+yrs" };

        //for skill dropdown
        ArrayAdapter<String> adapterSkill = new ArrayAdapter<>(Register.this, R.layout.skill_list, skills);
        skill.setAdapter(adapterSkill);

        //for exp dropdown
        ArrayAdapter<String> adapterExp = new ArrayAdapter<>(Register.this, R.layout.emp_list, exp);
        experience.setAdapter(adapterExp);

        skill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String skill = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Register.this, "skill" + skill, Toast.LENGTH_SHORT).show();
            }});

        experience.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String experience = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Register.this, "experience" + experience, Toast.LENGTH_SHORT).show();
            }});

        cancel.setOnClickListener(v-> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        save.setOnClickListener(v-> {

            Name = name.getText().toString();
            Address = address.getText().toString();
            Skill = skill.getText().toString();
            Experience = experience.getText().toString();
            Contact = contact.getText().toString();
            Email = email.getText().toString();
            Location = location.getText().toString();

            validate();

            getWeatherInfo(Location);

        });

    }
    private void validate(){
        if(Name.isEmpty()){
            name.setError("Name can't be empty");
        }else if (Address.isEmpty()){
            address.setError("Fill Adress field!! ");
        }else if (Skill.isEmpty()) {
            skill.setError("Select your Skill");
        }else if (Experience.isEmpty()){
            experience.setError("Select your Experience");
        }else if (Contact.isEmpty()){
            contact.setError("Give your Contact number");
        }else if(Contact.length()!=10)
            contact.setError("Contact number must have 10 digits");
        else if (Email.isEmpty()) {
            email.setError("Email is required");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Please enter valid email address");
        }else if (Location.isEmpty()){
            location.setError("You forget to type Location");
        } else
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }

    private void getWeatherInfo(String location){
        String url = "http://api.weatherapi.com/v1/current.json?key=76b04327a5904d42aa292816230203&q=Delhi&aqi=no";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject currentJson = response.getJSONObject("current");
                            String weather = currentJson.getString("temp_f");
                            DB = new Database(Register.this);
                Boolean checkInsertEmp = DB.insertEmp(Name, Address, Skill, Experience, Contact, Email, Location,weather);
                if(checkInsertEmp)
                {
                    Toast.makeText(Register.this,"Data Saved Successfully", Toast.LENGTH_SHORT).show();                }
                else
                {
                    Toast.makeText(Register.this, "Data not saved. Try again!!", Toast.LENGTH_SHORT).show();
                }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
             @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);

    }
}