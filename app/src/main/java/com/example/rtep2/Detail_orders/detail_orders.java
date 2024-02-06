package com.example.rtep2.Detail_orders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rtep2.APIConstants;
import com.example.rtep2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class detail_orders extends AppCompatActivity implements DetailOrderAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    DetailOrderAdapter detailOrderAdapter;
    List<Detail_order> detailOrderList;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_orders);

        recyclerView = findViewById(R.id.recyclerView_detail_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        detailOrderList = new ArrayList<>();
        detailOrderAdapter = new DetailOrderAdapter(this, detailOrderList);
        detailOrderAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(detailOrderAdapter);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("access_token")) {
            accessToken = intent.getStringExtra("access_token");
            Log.d("TAG", "Access Token: " + accessToken);
            retrieveDetailOrders();
        } else {
            Toast.makeText(this, "Access token not found", Toast.LENGTH_SHORT).show();
        }

        // Schedule the report generation tasks
        scheduleDailyReportGeneration();
        scheduleWeeklyReportGeneration();
        scheduleMonthlyReportGeneration();
    }

    private void retrieveDetailOrders() {
        String base_url = APIConstants.BASE_URL;
        String URL = base_url + "detail-orders/";

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
                    List<Detail_order> detailOrders = parseDetailOrders(responseBody);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            detailOrderList.clear();
                            detailOrderList.addAll(detailOrders);
                            detailOrderAdapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(detail_orders.this, "Failed to fetch data", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(detail_orders.this, "Failed to fetch data", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private List<Detail_order> parseDetailOrders(String responseBody) {
        List<Detail_order> detailOrders = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(responseBody);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String detail_order_no = jsonObject.getString("detail_order_no");
                String to = jsonObject.getString("to");
                String order_Status = jsonObject.getString("order_Status");
                String order_For = jsonObject.getString("order_For");
                String order_Description = jsonObject.getString("order_Description");
                String IPO_No_Current_Month = jsonObject.getString("lPO_No_Current_Month"); // Corrected field name
                String vehicle_Id = jsonObject.getString("vehicle_Id");
                String license_No = jsonObject.getString("license_No");
                String a_Rations_Diesel = jsonObject.getString("a_Rations_Diesel");
                String b_Rations_Petrol = jsonObject.getString("b_Rations_Petrol");
                String litres_fuel = jsonObject.getString("litres_fuel");
                String litres_oil = jsonObject.getString("litres_oil");
                String date_posted = jsonObject.getString("date_posted");
                String date_updated = jsonObject.getString("date_updated");

                Detail_order detailOrder = new Detail_order(detail_order_no, to, order_Status, order_For, order_Description, IPO_No_Current_Month, vehicle_Id, license_No, a_Rations_Diesel, b_Rations_Petrol, litres_fuel, litres_oil, date_posted, date_updated);
                detailOrders.add(detailOrder);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return detailOrders;
    }

    @Override
    public void onItemClick(int position, Detail_order detail_order) {
        // Open the detail order profile activity with the detail order details
        Intent intent = new Intent(this, detail_order_profile.class);
        intent.putExtra("access_token", accessToken);
        intent.putExtra("detail_order", detail_order);
        startActivity(intent);
    }

    // Add this method to generate the report based on the specified time period
    private void generateWordDocument(String timePeriod) {
        // ... (To be implemented - Generate the Word document report based on timePeriod)
    }

    private void scheduleDailyReportGeneration() {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Schedule the task to run daily at midnight (00:00:00)
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                generateWordDocument("daily");
            }
        }, calendar.getTime(), 24 * 60 * 60 * 1000); // Repeat every 24 hours
    }

    private void scheduleWeeklyReportGeneration() {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Schedule the task to run weekly on Mondays at midnight (00:00:00)
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                generateWordDocument("weekly");
            }
        }, calendar.getTime(), 7 * 24 * 60 * 60 * 1000); // Repeat every 7 days
    }

    private void scheduleMonthlyReportGeneration() {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Schedule the task to run monthly on the 1st day at midnight (00:00:00)
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                generateWordDocument("monthly");
            }
        }, calendar.getTime(), 30 * 24 * 60 * 60 * 1000); // Repeat every 30 days (approximation)
    }
}
