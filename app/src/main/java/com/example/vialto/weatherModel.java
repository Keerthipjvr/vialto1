package com.example.vialto;

public class weatherModel {
    private  String temperature;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public  weatherModel(String weather){
        this.temperature = weather;
    }
}
