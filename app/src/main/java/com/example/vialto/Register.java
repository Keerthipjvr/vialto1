package com.example.vialto;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText name, address, contact, email, location;
    AutoCompleteTextView skill,experience;
    Button save, cancel;
    String Name;
    String Address;
    String Skill;
    String Experience;
    String Contact;
    String Email;
    String Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.etName);
        address = findViewById(R.id.etAddress);
        contact = findViewById(R.id.etContact);
        email = findViewById(R.id.etEmail);
        location = findViewById(R.id.etLocation);

        save = findViewById(R.id.btnSave);
        cancel = findViewById(R.id.btnCancel);

        skill = findViewById(R.id.auto_com_text1);
        experience = findViewById(R.id.auto_com_text2);
        Database DB;
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


        DB = new Database(this);


        save.setOnClickListener(v-> {

            Name = name.getText().toString();
            Address = address.getText().toString();
            Skill = skill.getText().toString();
            Experience = experience.getText().toString();
            Contact = contact.getText().toString();
            Email = email.getText().toString();
            Location = location.getText().toString();

            validate();

            Boolean checkInsertEmp = DB.insertEmp(Name, Address, Skill, Experience, Contact, Email, Location);
            if(checkInsertEmp)
            {
                Toast.makeText(Register.this,"Data Saved Successfully", Toast.LENGTH_SHORT).show();                }
            else
            {
                Toast.makeText(Register.this, "Data not saved. Try again!!", Toast.LENGTH_SHORT).show();
            }
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
}