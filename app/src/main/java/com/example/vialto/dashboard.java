package com.example.vialto;


import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {
    BarChart barChart;
    int[] colorClassArray = new int[]{Color.GREEN,  Color.YELLOW , Color.BLUE};
    @Override
    protected void onCreate(Bundle savedInstanceState){

       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        barChart = findViewById(R.id.barChart);

        BarDataSet barDataSet = new BarDataSet(dataValue1(), "Bar Set");
        barDataSet.setColors(colorClassArray);

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
    }

    private ArrayList<BarEntry> dataValue1(){
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        dataVals.add(new BarEntry(0, new float[]{2,4,6}));
        dataVals.add(new BarEntry(1, new float[]{1,4,3} ));
        dataVals.add(new BarEntry(2, new float[]{5,3,1}));

        return dataVals;
    }
}

