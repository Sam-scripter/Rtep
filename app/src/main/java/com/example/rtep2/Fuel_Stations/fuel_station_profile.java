package com.example.rtep2.Fuel_Stations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rtep2.R;

public class fuel_station_profile extends AppCompatActivity {

    private TextView stationId;
    private TextView stationName;
    private TextView location;
    private TextView description;
    private TextView ArationsFuel;
    private TextView BrationsFuel;
    private TextView TotalFuelLitres;
    private TextView litresOil;
    private TextView otometerReadingAuto;
    private TextView otometerReadingOfficer;

    private Button editProfile;
    private Button deleteProfile;


    private Fuel_station fuelStation;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_station_profile);

        stationId = findViewById(R.id.station_profile_station_Id);
        stationName = findViewById(R.id.station_profile_station_name);
        location = findViewById(R.id.station_profile_station_location);
        description = findViewById(R.id.station_profile_description);
        ArationsFuel = findViewById(R.id.station_a_profile_rations_fuel);
        BrationsFuel = findViewById(R.id.station_profile_b_rations_fuel);
        TotalFuelLitres = findViewById(R.id.station_profile_total_litres);
        litresOil = findViewById(R.id.station_profile_litres_oil);
        otometerReadingAuto = findViewById(R.id.station_profile_otometer_reading_auto);
        otometerReadingOfficer = findViewById(R.id.station_profile_otometer_reading_officer);
        editProfile = findViewById(R.id.profile_BtnEdit);
        deleteProfile = findViewById(R.id.profile_BtnDelete);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("fuel_station") && intent.hasExtra("access_token")) {
            fuelStation = intent.getParcelableExtra("fuel_station");
            accessToken = intent.getStringExtra("access_token");

            if (fuelStation != null) {
                stationId.setText(fuelStation.getStation_Id());
                stationName.setText(fuelStation.getFuel_Station_Name());
                location.setText(fuelStation.getLocation());
                description.setText(fuelStation.getDescription());
                ArationsFuel.setText(fuelStation.getA_Rations_Diesel());
                BrationsFuel.setText(fuelStation.getB_Rations_Petrol());
                TotalFuelLitres.setText(fuelStation.getTotal_litres_fuel());
                litresOil.setText(fuelStation.getLitres_oil());
                otometerReadingAuto.setText(fuelStation.getOtometer_Reading_Auto_Created());
                otometerReadingOfficer.setText(fuelStation.getOtometer_Reading_By_Officer());
            }
        }

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fuelStation != null) {
                    Intent editIntent = new Intent(fuel_station_profile.this, edit_fuel_station_profile.class);
                    editIntent.putExtra("fuel_station", fuelStation);
                    editIntent.putExtra("access_token", accessToken);
                    startActivity(editIntent);
                }
            }
        });

        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accessToken != null) {
                    // Make an API call to delete the fuel station using the access token
                    deleteFuelStation();
                }
            }
        });
    }

    private void deleteFuelStation() {
        // TODO: Implement the API call to delete the fuel station using the access token
        // You can use any networking library like OkHttp or Retrofit for this task

        // Placeholder code to display a toast message
        Toast.makeText(this, "Fuel station deleted", Toast.LENGTH_SHORT).show();

        // Redirect to the fuel stations activity
        Intent intent = new Intent(fuel_station_profile.this, fuel_stations.class);
        intent.putExtra("access_token", accessToken);
        startActivity(intent);
        finish();
    }
}
