package com.example.vialto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "EmpEntry.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table EmployeeDetails(name TEXT primary key, address TEXT, skill TEXT, experience TEXT, contact TEXT, email TEXT, location TEXT,weather TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists EmployeeDetails");

    }

    public Boolean insertEmp(String name, String address, String skill, String experience, String contact, String email, String location,String weather) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("address", address);
        contentValues.put("skill", skill);
        contentValues.put("experience", experience);
        contentValues.put("contact", contact);
        contentValues.put("email", email);
        contentValues.put("location", location);
        contentValues.put("weather",weather);

        long result = DB.insert("EmployeeDetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();

        //Retrieve data from database
        Cursor cursor = DB.rawQuery("Select * from EmployeeDetails", null);
        return cursor;
    }

    public Cursor getBarChart(){
        SQLiteDatabase DB2 = this.getWritableDatabase();

        Cursor cursor2 = DB2.rawQuery("Select skill, experience from EmployeeDetails", null);
        return cursor2;
    }
}
