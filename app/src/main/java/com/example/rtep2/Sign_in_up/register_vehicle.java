package com.example.rtep2.Sign_in_up;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class register_vehicle extends AppCompatActivity {

    EditText vehicleId;
    EditText licenseNo;
    EditText manufacturer;
    EditText model;
    EditText type;
    EditText driverId;
    EditText transmission;
    EditText fuelType;
    EditText description;
    EditText departmentId;
    EditText engineNo;
    EditText chassisNo;
    Button addVehicle;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vehicle);

        vehicleId = findViewById(R.id.vehicle_id);
        licenseNo = findViewById(R.id.license_no);
        manufacturer = findViewById(R.id.manufacturer);
        model = findViewById(R.id.model);
        type = findViewById(R.id.type);
        engineNo = findViewById(R.id.engine_no);
        chassisNo = findViewById(R.id.chassis_no);
        driverId =findViewById(R.id.driver_id);
        transmission = findViewById(R.id.transmission);
        fuelType = findViewById(R.id.fuel_type);

        description = findViewById(R.id.description);
        departmentId = findViewById(R.id.Department_id);

        addVehicle = findViewById(R.id.Btn_add_vehicle);


        // Retrieve the access token from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("access_token")) {
            accessToken = extras.getString("access_token");
        }

        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String base_url = APIConstants.BASE_URL;
                String URL = base_url + "vehicles/";
                String vehicleIdValue = vehicleId.getText().toString();
                String licenseNoValue = licenseNo.getText().toString();
                String manufacturerValue = manufacturer.getText().toString();
                String modelValue = model.getText().toString();
                String typeValue = type.getText().toString();
                String engineNoValue = engineNo.getText().toString();
                String chassisNoValue = chassisNo.getText().toString();
                String DriverId = driverId.getText().toString();
                String vehicleTransmission = transmission.getText().toString();
                String vehicleFuelType = fuelType.getText().toString();

                String descriptionValue = description.getText().toString();
                String departmentIdValue = departmentId.getText().toString();


                OkHttpClient client = new OkHttpClient();

                RequestBody vehicleBody = new FormBody.Builder()
                        .add("vehicle_id", vehicleIdValue)
                        .add("license_no", licenseNoValue)
                        .add("manufacturer", manufacturerValue)
                        .add("model", modelValue)
                        .add("vehicle_type", typeValue)
                        .add("engine_no", engineNoValue)
                        .add("chassis_no", chassisNoValue)
                        .add("driver_id", DriverId)
                        .add("transmission", vehicleTransmission)
                        .add("fuel_type", vehicleFuelType)

                        .add("description", descriptionValue)
                        .add("department_id", departmentIdValue)

                        .build();

                Request request = new Request.Builder()
                        .url(URL)
                        .header("Authorization", "Bearer " + accessToken) // Pass the access token in the request header
                        .post(vehicleBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(register_vehicle.this, "Vehicle Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(register_vehicle.this, "Vehicle Registration successful", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            final String errorMessage = response.body().string(); // Get the error message from the response body
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(register_vehicle.this, "Vehicle Registration Failed" + errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
