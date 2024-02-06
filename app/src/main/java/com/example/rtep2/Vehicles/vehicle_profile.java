package com.example.rtep2.Vehicles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

public class vehicle_profile extends AppCompatActivity {

    Button BtnEdit;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);

        BtnEdit = findViewById(R.id.profile_BtnEdit);

        Intent intent1 = getIntent();
        if (intent1 != null && intent1.hasExtra("access_token")) {
            accessToken = intent1.getStringExtra("access_token");
            Log.d("access found", accessToken);
        } else {
            Log.d("Access found", "Access Token: " + accessToken);
            Toast.makeText(this, "access token not found", Toast.LENGTH_SHORT).show();
        }



        // Retrieve the vehicle details from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("vehicle")) {
            Vehicle vehicle = intent.getParcelableExtra("vehicle");
            if (vehicle != null) {
                // Bind the vehicle details to the corresponding views
                TextView vehicleId = findViewById(R.id.profile_vehicle_id);
                TextView licenseNumber = findViewById(R.id.profile_license_number);
                TextView manufacturer = findViewById(R.id.profile_manufacturer);
                TextView model = findViewById(R.id.profile_model);
                TextView transmission = findViewById(R.id.profile_transmission);
                TextView fuelType = findViewById(R.id.profile_fuel_type);
                TextView engineNo = findViewById(R.id.profile_engine_no);
                TextView chassisNo = findViewById(R.id.profile_chassis_no);
                TextView driverId = findViewById(R.id.profile_driver_id);
                TextView type = findViewById(R.id.profile_type);
                TextView currentMileage = findViewById(R.id.profile_current_mileage);
                TextView description = findViewById(R.id.profile_description);
                TextView distanceCovered = findViewById(R.id.profile_distance_covered);
                TextView fuelConsumed = findViewById(R.id.profile_fuel_consumed);
                TextView totalNoOfRepairs = findViewById(R.id.profile_total_repairs);
                TextView totalRepairCost = findViewById(R.id.profile_total_repair_cost);
                TextView totalCost = findViewById(R.id.profile_total_cost);
                TextView dateCreated = findViewById(R.id.profile_date_created);
                TextView department = findViewById(R.id.profile_department);

                vehicleId.setText(String.valueOf(vehicle.getVehicle_id()));
                licenseNumber.setText(vehicle.getLicense_no());
                model.setText(vehicle.getModel());
                transmission.setText(vehicle.getTransmission());
                fuelType.setText(vehicle.getFuel_type());
                engineNo.setText(vehicle.getEngine_no());
                chassisNo.setText(vehicle.getChassis_no());
                driverId.setText(String.valueOf(vehicle.getDriver_id()));
                type.setText(vehicle.getVehicle_type());
                currentMileage.setText(String.valueOf(vehicle.getCurrent_mileage()));
                description.setText(vehicle.getDescription());
                distanceCovered.setText(String.valueOf(vehicle.getDistance_covered()));
                fuelConsumed.setText(String.valueOf(vehicle.getFuel_consumed()));
                totalNoOfRepairs.setText(String.valueOf(vehicle.getTotal_repairs()));
                totalRepairCost.setText(String.valueOf(vehicle.getTotal_repair_cost()));
                totalCost.setText(String.valueOf(vehicle.getTotal_cost()));
                dateCreated.setText(vehicle.getDate_created());
                department.setText(vehicle.getDepartment());
                manufacturer.setText(vehicle.getManufacturer());
            }
        }

        BtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vehicle_profile.this, edit_vehicle_profile.class);

                // Retrieve the vehicle details from the intent
                Vehicle vehicle = getIntent().getParcelableExtra("vehicle");

                // Add the vehicle object as an extra to the intent
                intent.putExtra("vehicle", vehicle);
                intent.putExtra("access_token", accessToken);

                startActivity(intent);
            }
        });
    }
}
