package com.example.rtep2.Repair_orders;

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

public class repair_orders extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RepairOrderAdapter repairOrderAdapter;
    private List<RepairOrder> repairOrderList;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_orders);

        recyclerView = findViewById(R.id.recyclerView_repair_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repairOrderList = new ArrayList<>();

        // Retrieve the access token if passed from the previous activity (Overview)
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("access_token")) {
            accessToken = intent.getStringExtra("access_token");
            Log.d("TAG", "Access Token: " + accessToken);

        } else {
            Toast.makeText(this, "Access token not found", Toast.LENGTH_SHORT).show();
        }


        String base_url = APIConstants.BASE_URL;
        String url = base_url + "repair-orders/";
        // Initialize OkHttp client
        OkHttpClient client = new OkHttpClient();

        // Create a request
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + accessToken)
                .build();

        // Make the API call asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    try {
                        // Parse the JSON response
                        JSONArray jsonArray = new JSONArray(responseBody);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String repairOrderNumber = jsonObject.getString("repair_order_number");
                            String vehicleId = jsonObject.getString("vehicle_id");
                            String vehicleRegistrationNo = jsonObject.getString("vehicle_Registration_No");
                            String initiatedBy = jsonObject.getString("initiated_by");
                            String initiatorName = jsonObject.getString("initiator_name");
                            String defectIssues = jsonObject.getString("defect_Issues");
                            String datePosted = jsonObject.getString("date_Posted");
                            String dateUpdated = jsonObject.getString("date_updated");

                            RepairOrder repairOrder = new RepairOrder(repairOrderNumber, vehicleId, vehicleRegistrationNo,
                                    initiatedBy, initiatorName, defectIssues, datePosted, dateUpdated);
                            repairOrderList.add(repairOrder);
                        }

                        // Update the UI on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                repairOrderAdapter = new RepairOrderAdapter(repair_orders.this, repairOrderList);
                                recyclerView.setAdapter(repairOrderAdapter);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("API Error", "Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }
}
