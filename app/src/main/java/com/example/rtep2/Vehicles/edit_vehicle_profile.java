package com.example.rtep2.Vehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class edit_vehicle_profile extends AppCompatActivity {

    EditText edit_vehicleId;
    EditText edit_licenseNo;
    EditText edit_manufacturer;
    EditText edit_model;
    EditText edit_type;
    EditText edit_engineNo;
    EditText edit_chassisNo;
    EditText edit_driverId;
    EditText edit_transmission;
    EditText edit_fuelType;
    EditText edit_description;
    EditText edit_departmentId;
    Button editVehicle;
    Vehicle vehicle;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vehicle_profile);

        edit_vehicleId = findViewById(R.id.edit_vehicle_id);
        edit_licenseNo = findViewById(R.id.edit_license_no);
        edit_manufacturer = findViewById(R.id.edit_manufacturer);
        edit_model = findViewById(R.id.edit_model);
        edit_type = findViewById(R.id.edit_type);
        edit_engineNo = findViewById(R.id.edit_engine_no);
        edit_chassisNo = findViewById(R.id.edit_chassis_no);
        edit_driverId = findViewById(R.id.edit_driver_id);
        edit_transmission = findViewById(R.id.edit_transmission);
        edit_fuelType = findViewById(R.id.edit_fuel_type);
        edit_description = findViewById(R.id.edit_description);
        edit_departmentId = findViewById(R.id.edit_department_id);
        editVehicle = findViewById(R.id.edit_vehicle);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("vehicle")) {
            vehicle = intent.getParcelableExtra("vehicle");
            populateFieldsWithVehicleData();
        } else {
            Toast.makeText(edit_vehicle_profile.this,"vehicle not found", Toast.LENGTH_LONG).show();
        }

        Intent intent1 = getIntent();
        if (intent1 != null && intent1.hasExtra("access_token")) {
            accessToken = intent1.getStringExtra("access_token");
            Log.d("access found", accessToken);
        } else {
            Log.d("Access found", "Access Token: " + accessToken);
            Toast.makeText(edit_vehicle_profile.this, "access token not found", Toast.LENGTH_SHORT).show();
        }

        editVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    try {
                        updateVehicleProfile();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        Toast.makeText(edit_vehicle_profile.this, "Failed to update: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void populateFieldsWithVehicleData() {
        edit_vehicleId.setText(vehicle.getVehicle_id());
        edit_licenseNo.setText(vehicle.getLicense_no());
        edit_manufacturer.setText(vehicle.getManufacturer());
        edit_model.setText(vehicle.getModel());
        edit_type.setText(vehicle.getVehicle_type());
        edit_engineNo.setText(vehicle.getEngine_no());
        edit_chassisNo.setText(vehicle.getChassis_no());
        edit_driverId.setText(vehicle.getDriver_id());
        edit_transmission.setText(vehicle.getTransmission());
        edit_fuelType.setText(vehicle.getFuel_type());
        edit_description.setText(vehicle.getDescription());
        edit_departmentId.setText(vehicle.getDepartment());
    }

    private boolean validateInput() {
        String licenseNo = edit_licenseNo.getText().toString().trim();
        String manufacturer = edit_manufacturer.getText().toString().trim();
        String model = edit_model.getText().toString().trim();
        String vehicleType = edit_type.getText().toString().trim();
        String engineNo = edit_engineNo.getText().toString().trim();
        String chassisNo = edit_chassisNo.getText().toString().trim();
        String driverId = edit_driverId.getText().toString().trim();
        String transmission = edit_transmission.getText().toString().trim();
        String fuelType = edit_fuelType.getText().toString().trim();
        String description = edit_description.getText().toString().trim();
        String departmentId = edit_departmentId.getText().toString().trim();

        if (TextUtils.isEmpty(licenseNo)) {
            edit_licenseNo.setError("License number is required");
            return false;
        }

        if (TextUtils.isEmpty(manufacturer)) {
            edit_manufacturer.setError("Manufacturer is required");
            return false;
        }

        if (TextUtils.isEmpty(model)) {
            edit_model.setError("Vehicle model is required");
            return false;
        }

        if (TextUtils.isEmpty(vehicleType)) {
            edit_type.setError("Vehicle type is required");
            return false;
        }

        if (TextUtils.isEmpty(engineNo)) {
            edit_engineNo.setError("Engine number is required");
            return false;
        }

        if (TextUtils.isEmpty(chassisNo)) {
            edit_chassisNo.setError("Chassis number is required");
            return false;
        }

        if (TextUtils.isEmpty(driverId)) {
            edit_driverId.setError("Driver ID is required");
            return false;
        }

        if (TextUtils.isEmpty(transmission)) {
            edit_transmission.setError("Vehicle transmission is required");
            return false;
        }

        if (TextUtils.isEmpty(fuelType)) {
            edit_fuelType.setError("Fuel type is required");
            return false;
        }

        if (TextUtils.isEmpty(description)) {
            edit_description.setError("Vehicle description is required");
            return false;
        }

        if (TextUtils.isEmpty(departmentId)) {
            edit_departmentId.setError("Department ID is required");
            return false;
        }

        return true;
    }

    private void updateVehicleProfile() throws UnsupportedEncodingException {
        String vehicleId = edit_vehicleId.getText().toString();
        String licenseNo = edit_licenseNo.getText().toString();
        String manufacturer = edit_manufacturer.getText().toString();
        String model = edit_model.getText().toString();
        String vehicleType = edit_type.getText().toString();
        String engineNo = edit_engineNo.getText().toString();
        String chassisNo = edit_chassisNo.getText().toString();
        String driverId = edit_driverId.getText().toString();
        String transmission = edit_transmission.getText().toString();
        String fuelType = edit_fuelType.getText().toString();
        String description = edit_description.getText().toString();
        String departmentId = edit_departmentId.getText().toString();

        JSONObject json = new JSONObject();
        try {
            json.put("vehicle_id", vehicleId);
            json.put("license_no", licenseNo);
            json.put("manufacturer", manufacturer);
            json.put("model", model);
            json.put("vehicle_type", vehicleType);
            json.put("engine_no", engineNo);
            json.put("chassis_no", chassisNo);
            json.put("driver_id", driverId);
            json.put("transmission", transmission);
            json.put("fuel_type", fuelType);
            json.put("description", description);
            json.put("department_id", departmentId);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(edit_vehicle_profile.this, "Failed to update: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        String base_url = APIConstants.BASE_URL;
        String url = base_url + "vehicles/";

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json.toString());

        String encodedVehicleId = URLEncoder.encode(vehicle.getVehicle_id(), "UTF-8");
        Request request = new Request.Builder()
                .url(url + encodedVehicleId + "/")
                .patch(requestBody)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        // Send the HTTP request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Show an error message with the specific reason for the failure
                        Toast.makeText(edit_vehicle_profile.this, "Failed to update: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Show a success message
                            Toast.makeText(edit_vehicle_profile.this, "Vehicle profile updated successfully", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Return to the profile activity
                    Intent intent = new Intent(edit_vehicle_profile.this, vehicle_profile.class);
                    intent.putExtra("vehicle", vehicle);
                    startActivity(intent);
                    finish();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Show an error message based on the response status code
                            Toast.makeText(edit_vehicle_profile.this, "Failed to update vehicle profile: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
