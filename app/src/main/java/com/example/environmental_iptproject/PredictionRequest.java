package com.example.environmental_iptproject;

import com.google.gson.annotations.SerializedName;

public class PredictionRequest {

    @SerializedName("PM2.5")
    private double pm25;

    @SerializedName("CO2_Level")
    private double co2Level;

    @SerializedName("Noise_Level")
    private double noiseLevel;

    @SerializedName("Water_pH")
    private double waterPh;

    // Constructor
    public PredictionRequest(double pm25, double co2Level, double noiseLevel, double waterPh) {
        this.pm25 = pm25;
        this.co2Level = co2Level;
        this.noiseLevel = noiseLevel;
        this.waterPh = waterPh;
    }

    // Getters and Setters
    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getCo2Level() {
        return co2Level;
    }

    public void setCo2Level(double co2Level) {
        this.co2Level = co2Level;
    }

    public double getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(double noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public double getWaterPh() {
        return waterPh;
    }

    public void setWaterPh(double waterPh) {
        this.waterPh = waterPh;
    }
}
