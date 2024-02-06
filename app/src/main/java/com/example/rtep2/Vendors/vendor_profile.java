package com.example.rtep2.Vendors;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.*;

public class vendor_profile extends AppCompatActivity {

    private TextView vendorNameTextView;
    private TextView vendorContactTextView;
    private TextView aRationsDieselTextView;
    private TextView aRationsPriceTextView;
    private TextView bRationsPetrolTextView;
    private TextView bRationsPriceTextView;
    private TextView totalFuelTextView;

    private Vendor vendor;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_profile);

//        vendorNameTextView = findViewById(R.id.vendor_name);
//        vendorContactTextView = findViewById(R.id.vendor_contact);
//        aRationsDieselTextView = findViewById(R.id.a_rations_diesel);
//        aRationsPriceTextView = findViewById(R.id.a_rations_price);
//        bRationsPetrolTextView = findViewById(R.id.b_rations_petrol);
//        bRationsPriceTextView = findViewById(R.id.b_rations_price);
//        totalFuelTextView = findViewById(R.id.total_fuel);
//
//        Button editButton = findViewById(R.id.edit_button);
//        Button deleteButton = findViewById(R.id.delete_button);

//        // Get the vendor data and access token from the intent
//        if (getIntent() != null && getIntent().hasExtra("vendor") && getIntent().hasExtra("access_token")) {
//            vendor = getIntent().getParcelableExtra("vendor");
//            accessToken = getIntent().getStringExtra("access_token");
//            if (vendor != null) {
//                updateUIWithVendorData();
//            }
//        }

//        // Edit button click listener
//        editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigateToEditVendorActivity();
//            }
//        });
//
//        // Delete button click listener
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteVendorProfile();
//            }
//        });
    }

//    private void updateUIWithVendorData() {
//        vendorNameTextView.setText(vendor.getVendor_name());
//        vendorContactTextView.setText(vendor.getVendor_contact_info());
//        aRationsDieselTextView.setText(vendor.getA_rations_diesel_units());
//        aRationsPriceTextView.setText(vendor.getA_Rations_Unit_price());
//        bRationsPetrolTextView.setText(vendor.getB_rations_petrol_units());
//        bRationsPriceTextView.setText(vendor.getB_Rations_Unit_Price());
//        totalFuelTextView.setText(vendor.getTotal_fuel_supplied());
//    }
//
//    private void navigateToEditVendorActivity() {
//        // Create an intent to navigate to EditVendorActivity
//        Intent intent = new Intent(this, edit_vendor_profile.class);
//        // Pass the vendor details and access token to the EditVendorActivity
//        intent.putExtra("vendor", vendor);
//        intent.putExtra("access_token", accessToken);
//        startActivity(intent);
//    }
//
//    // Method to delete the vendor profile using the access token
//    private void deleteVendorProfile() {
//        // Replace BASE_URL with your API endpoint URL
//        String BASE_URL = "https://example.com/api/v1/";
//
//        // Create an instance of OkHttpClient
//        OkHttpClient client = new OkHttpClient();
//
//        // Build the URL for the DELETE request
//        String deleteUrl = BASE_URL + "vendors/" + vendor.getVendor_id() + "/";
//
//        // Create a request with the access token in the Authorization header
//        Request request = new Request.Builder()
//                .url(deleteUrl)
//                .addHeader("Authorization", "Bearer " + accessToken)
//                .delete()
//                .build();
//
//        // Make the API call asynchronously
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                // Handle the API call failure (e.g., network error)
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(vendor_profile.this, "Failed to delete profile", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    // Handle the successful API call (e.g., profile deleted)
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(vendor_profile.this, "Profile deleted successfully", Toast.LENGTH_SHORT).show();
//                            // Finish the activity or redirect to another screen as needed
//                            finish();
//                        }
//                    });
//                } else {
//                    // Handle the API call failure (e.g., server error)
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(vendor_profile.this, "Failed to delete profile", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        });
//    }
}
