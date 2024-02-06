package com.example.rtep2.Fuel_Stations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class fuel_stations extends AppCompatActivity implements fuel_stations_adapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private fuel_stations_adapter fuel_stations_adapter;
    private List<Fuel_station> fuelStationList;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_stations);

        recyclerView = findViewById(R.id.recyclerView_vehicle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fuelStationList = new ArrayList<>();
        fuel_stations_adapter = new fuel_stations_adapter(this, fuelStationList);
        fuel_stations_adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(fuel_stations_adapter);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("access_token")) {
            accessToken = intent.getStringExtra("access_token");
            Log.d("TAG", "Access Token: " + accessToken);
            retrieveFuelStations();
        } else {
            Toast.makeText(this, "Access token not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void retrieveFuelStations() {
        String base_url = APIConstants.BASE_URL;
        String URL = base_url + "fuel-stations/";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL)
                .header("Authorization", "Bearer " + accessToken) // Pass the access token in the request header
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Log.e("response body", responseBody);
                    List<Fuel_station> fuelStations = parseFuelStations(responseBody);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fuelStationList.clear();
                            fuelStationList.addAll(fuelStations);
                            fuel_stations_adapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(fuel_stations.this, "Failed to fetch data", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(fuel_stations.this, "Failed to fetch data", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private List<Fuel_station> parseFuelStations(String responseBody) {
        List<Fuel_station> fuelStations = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(responseBody);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String station_Id = jsonObject.getString("station_Id");
                String fuel_station_pic = jsonObject.getString("fuel_station_pic");
                String fuel_Station_Name = jsonObject.getString("fuel_Station_Name");
                String location = jsonObject.getString("location");
                String description = jsonObject.getString("description");
                String a_Rations_Diesel = jsonObject.getString("a_Rations_Diesel");
                String a_Rations_Unit_Price = jsonObject.getString("a_Rations_Unit_Price");
                String b_Rations_Petrol = jsonObject.getString("b_Rations_Petrol");
                String b_Rations_Unit_Price = jsonObject.getString("b_Rations_Unit_Price");
                String Total_litres_fuel = jsonObject.getString("Total_litres_fuel");
                String litres_oil = jsonObject.getString("litres_oil");
                String otometer_Reading_Auto_Created = jsonObject.getString("otometer_Reading_Auto_Created");
                String otometer_Reading_By_Officer = jsonObject.getString("otometer_Reading_By_Officer");
                String date_created = jsonObject.getString("date_created");
                String date_updated = jsonObject.getString("date_updated");

                Fuel_station fuelStation = new Fuel_station(station_Id, fuel_station_pic, fuel_Station_Name, location, description, a_Rations_Diesel, a_Rations_Unit_Price, b_Rations_Petrol, b_Rations_Unit_Price, Total_litres_fuel, litres_oil, otometer_Reading_Auto_Created, otometer_Reading_By_Officer, date_created, date_updated);
                fuelStations.add(fuelStation);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return fuelStations;
    }

    @Override
    public void onItemClick(int position) {
        // Open the fuel station profile activity with the fuel station details
        Intent intent = new Intent(this, fuel_station_profile.class);
        intent.putExtra("access_token", accessToken);
        intent.putExtra("fuel_station", fuelStationList.get(position));
        startActivity(intent);
    }
}
