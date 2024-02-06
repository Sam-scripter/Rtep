package com.example.rtep2.Vendors;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class vendors extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VendorAdapter vendorAdapter;
    private List<Vendor> vendorList;

    private String BASE_URL = "YOUR_BASE_URL"; // Replace with your Django API base URL
    private String ACCESS_TOKEN = "YOUR_ACCESS_TOKEN"; // Replace with your access token

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors);

//        recyclerView = findViewById(R.id.recyclerView_vendors);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        vendorList = new ArrayList<>();
//
//        // Make the API call to retrieve vendor details
//        getVendorDetails();
//
//        vendorAdapter = new VendorAdapter(this, vendorList);
//        recyclerView.setAdapter(vendorAdapter);
//    }
//
//    private void getVendorDetails() {
//        OkHttpClient client = new OkHttpClient();
//
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/api/vendors/").newBuilder();
//        String url = urlBuilder.build().toString();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .header("Authorization", "Bearer " + ACCESS_TOKEN)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                // Handle failure
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    try {
//                        String responseData = response.body().string();
//                        JSONArray jsonArray = new JSONArray(responseData);
//
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                            String vendorId = jsonObject.getString("vendor_id");
//                            String vendorName = jsonObject.getString("vendor_name");
//                            String vendorContactInfo = jsonObject.getString("vendor_contact_info");
//                            // Add other vendor details as needed
//
//                            Vendor vendor = new Vendor(vendorId, vendorName, vendorContactInfo, /* Add other vendor details */);
//                            vendorList.add(vendor);
//                        }
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                vendorAdapter.notifyDataSetChanged();
//                            }
//                        });
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                response.close();
//            }
//        });
//    }
//}
    }
}