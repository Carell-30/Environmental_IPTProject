package com.example.environmental_iptproject;

public class PredictionResponse {
    private String prediction;

    // Constructor
    public PredictionResponse(String prediction) {
        this.prediction = prediction;
    }

    // Getter and Setter
    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
