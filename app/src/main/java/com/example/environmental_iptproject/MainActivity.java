package com.example.environmental_iptproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Local variables instead of instance fields
        EditText pm25EditText = findViewById(R.id.pm25EditText);
        EditText co2EditText = findViewById(R.id.co2EditText);
        EditText noiseEditText = findViewById(R.id.noiseEditText);
        EditText waterPhEditText = findViewById(R.id.waterPhEditText);
        Button predictButton = findViewById(R.id.predictButton);
        TextView predictionResult = findViewById(R.id.predictionResult);

        // Lambda for OnClickListener
        predictButton.setOnClickListener(v -> {
            try {
                // Validate user input
                double pm25 = Double.parseDouble(pm25EditText.getText().toString());
                double co2 = Double.parseDouble(co2EditText.getText().toString());
                double noise = Double.parseDouble(noiseEditText.getText().toString());
                double waterPh = Double.parseDouble(waterPhEditText.getText().toString());

                // Create request object with input values
                PredictionRequest request = new PredictionRequest(pm25, co2, noise, waterPh);

                // Create Retrofit instance
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.177:5000/")  // Replace with your actual server IP or localhost if testing on the same machine
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Create PredictionApi instance
                PredictionApi predictionApi = retrofit.create(PredictionApi.class);

                // Make the API call
                Call<PredictionResponse> call = predictionApi.getPrediction(request);
                call.enqueue(new Callback<PredictionResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<PredictionResponse> call, @NonNull Response<PredictionResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            // Safely get the prediction and display the result
                            PredictionResponse predictionResponse = response.body();
                            String prediction = predictionResponse.getPrediction();
                            // Set the prediction result text directly
                            predictionResult.setText("Prediction: " + prediction);
                        } else {
                            // Handle unsuccessful response
                            Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<PredictionResponse> call, @NonNull Throwable t) {
                        // Handle failure
                        Toast.makeText(MainActivity.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (NumberFormatException e) {
                // Handle invalid input
                Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
