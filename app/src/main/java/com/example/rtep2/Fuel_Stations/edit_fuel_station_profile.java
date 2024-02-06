package com.example.rtep2.Fuel_Stations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rtep2.Fuel_Stations.Fuel_station;
import com.example.rtep2.Fuel_Stations.fuel_station_profile;
import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class edit_fuel_station_profile extends AppCompatActivity {


    private EditText station_Id;
    private EditText station_location;
    private EditText station_description;
    private EditText a_rations_diesel;
    private EditText a_rations_unit_price;
    private EditText b_rations_petrol;
    private EditText b_rations_unit_price;
    private EditText total_fuel_litres;
    private EditText oil_litres;
    private EditText otometer_reading_officer;
    private EditText otometer_reading_auto;
    private EditText date_created;
    private EditText date_updated;

    private Button saveButton;

    private Fuel_station fuelStation;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fuel_station_profile);



        station_Id = findViewById(R.id.edit_station_id);
        station_location = findViewById(R.id.edit_station_location);
        station_description = findViewById(R.id.station_description);
        a_rations_diesel = findViewById(R.id.station_a_rations_diesel);
        a_rations_unit_price = findViewById(R.id.station_a_rations_unit_price);
        b_rations_petrol = findViewById(R.id.station_b_rations_petrol);
        b_rations_unit_price = findViewById(R.id.b_rations_unit_price);
        total_fuel_litres = findViewById(R.id.station_total_fuel_litres);
        oil_litres = findViewById(R.id.station_oil_litres);
        otometer_reading_officer = findViewById(R.id.station_otometer_reading_officer);


        saveButton = findViewById(R.id.Btn_update);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("fuel_station") && intent.hasExtra("access_token")) {
            fuelStation = intent.getParcelableExtra("fuel_station");
            accessToken = intent.getStringExtra("access_token");

            if (fuelStation != null) {
                station_Id.setText(fuelStation.getStation_Id());
                station_location.setText(fuelStation.getLocation());
                station_description.setText(fuelStation.getDescription());
                a_rations_diesel.setText(fuelStation.getA_Rations_Diesel());
                a_rations_unit_price.setText(fuelStation.getA_Rations_Unit_Price());
                b_rations_petrol.setText(fuelStation.getB_Rations_Petrol());
                b_rations_unit_price.setText(fuelStation.getB_Rations_Unit_Price());
                total_fuel_litres.setText(fuelStation.getTotal_litres_fuel());
                oil_litres.setText(fuelStation.getLitres_oil());
//                otometer_reading_officer(fuelStation.getOtometer_Reading_By_Officer());

            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fuelStation != null) {
                    String updatedStationId = station_Id.getText().toString().trim();
                    String updatedStationLocation = station_location.getText().toString().trim();
                    String updatedStationDescription = station_description.getText().toString().trim();
                    String updated_a_rations_diesel = a_rations_diesel.getText().toString().trim();
                    String updated_a_rations_unit_price = a_rations_unit_price.getText().toString().trim();
                    String updated_b_rations_petrol = b_rations_petrol.getText().toString().trim();
                    String updated_b_rations_unit_price = b_rations_unit_price.getText().toString().trim();
                    String updated_total_fuel_litres = total_fuel_litres.getText().toString().trim();
                    String updated_oil_litres = oil_litres.getText().toString().trim();
                    String updated_otometer_reading_officer = otometer_reading_officer.getText().toString().trim();



                    fuelStation.setStation_Id(updatedStationId);
                    fuelStation.setLocation(updatedStationLocation);
                    fuelStation.setDescription(updatedStationDescription);
                    fuelStation.setA_Rations_Diesel(updated_a_rations_diesel);
                    fuelStation.setA_Rations_Unit_Price(updated_a_rations_unit_price);
                    fuelStation.setB_Rations_Petrol(updated_b_rations_petrol);
                    fuelStation.setB_Rations_Unit_Price(updated_b_rations_unit_price);
                    fuelStation.setTotal_litres_fuel(updated_total_fuel_litres);
                    fuelStation.setLitres_oil(updated_oil_litres);
                    fuelStation.setOtometer_Reading_By_Officer(updated_otometer_reading_officer);


                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("station_Id", updatedStationId);
                        jsonObject.put("location", updatedStationLocation);
                        jsonObject.put("description", updatedStationDescription);
                        jsonObject.put("a_Rations_Diesel", updated_a_rations_diesel);
                        jsonObject.put("a_Rations_Unit_Price", updated_a_rations_unit_price);
                        jsonObject.put("total_litres_fuel", updated_total_fuel_litres);
                        jsonObject.put("litres_oil", updated_oil_litres);
                        jsonObject.put("otometer_Reading_By_Officer", updated_otometer_reading_officer);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                    RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());

                    String base_url = APIConstants.BASE_URL;
                    String url = base_url + "fuel-station/";

                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url(url + station_Id + "/")
                            .put(requestBody)
                            .addHeader("Authorization", "Bearer " + accessToken)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            if (response.isSuccessful()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(edit_fuel_station_profile.this, "Fuel station profile updated", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                Intent profileIntent = new Intent(edit_fuel_station_profile.this, fuel_station_profile.class);
                                profileIntent.putExtra("fuel_station", fuelStation);
                                profileIntent.putExtra("access_token", accessToken);
                                startActivity(profileIntent);
                                finish();
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(edit_fuel_station_profile.this, "Failed to update fuel station profile", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(edit_fuel_station_profile.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}
