package com.example.environmental_iptproject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PredictionApi {
    @POST("/predict")
    Call<PredictionResponse> getPrediction(@Body PredictionRequest request);
}
