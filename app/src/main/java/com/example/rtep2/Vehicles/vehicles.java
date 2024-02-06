package com.example.rtep2.Vehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class vehicles extends AppCompatActivity implements VehicleAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    VehicleAdapter vehicleAdapter;
    List<Vehicle> vehicleList;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);

        recyclerView = findViewById(R.id.recyclerView_vehicle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        vehicleList = new ArrayList<>();
        vehicleAdapter = new VehicleAdapter(this, vehicleList);
        vehicleAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(vehicleAdapter);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("access_token")) {
            accessToken = intent.getStringExtra("access_token");
            Log.d("TAG", "Access Token: " + accessToken);
            retrieveVehicleData();
        } else {
            Toast.makeText(this, "Access token not found", Toast.LENGTH_SHORT).show();
        }


    }

    private void retrieveVehicleData() {
        String base_url = APIConstants.BASE_URL;
        String URL = base_url + "vehicles/";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL)
                .header("Authorization", "Bearer " + accessToken) // Pass the access token in the request header
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(vehicles.this, "Failed to fetch data", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Log.e("response body", responseBody);
                    List<Vehicle> vehicles = parseVehicleData(responseBody);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            vehicleList.clear();
                            vehicleList.addAll(vehicles);
                            vehicleAdapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(vehicles.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

    private List<Vehicle> parseVehicleData(String responseBody) {
        List<Vehicle> vehicles = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(responseBody);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String vehicleId = jsonObject.getString("vehicle_id");
                String licenseNo = jsonObject.getString("license_no");
                String manufacturer = jsonObject.getString("manufacturer");
                String model = jsonObject.getString("model");
                String vehicleType = jsonObject.getString("vehicle_type");
                String currentMileage = jsonObject.getString("current_mileage");
                String description = jsonObject.getString("description");
                String distanceCovered = jsonObject.getString("distance_covered_kms");
                String fuelConsumed = jsonObject.getString("fuel_consumed_litres");
                String fuelConsumedCost = jsonObject.getString("fuel_consumed_cost");
                String totalRepairs = jsonObject.getString("total_no_repairs");
                String totalRepairCost = jsonObject.getString("total_repair_cost");
                String totalCost = jsonObject.getString("total_cost");
                String dateCreated = jsonObject.getString("date_created");
                String department = jsonObject.getString("department_id");

                Vehicle vehicle = new Vehicle(vehicleId, licenseNo, manufacturer, model, vehicleType, currentMileage, description, distanceCovered, fuelConsumed, fuelConsumedCost, totalRepairs, totalRepairCost, totalCost, dateCreated, department);
                vehicles.add(vehicle);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("error", vehicles.toString());
        return vehicles;
    }

    @Override
    public void onItemClick(int position, Vehicle vehicle) {
        // Open the vehicle profile activity with the vehicle details
        Intent intent = new Intent(this, vehicle_profile.class);
        intent.putExtra("access_token", accessToken);
        intent.putExtra("vehicle", vehicle);
        Log.d("vehicle is:", String.valueOf(vehicle));
        startActivity(intent);
    }
}
