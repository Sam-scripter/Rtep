package com.example.rtep2.Vendors;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;
import com.example.rtep2.Vendors.Vendor;

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

public class edit_vendor_profile extends AppCompatActivity {

    private EditText editTextVendorName;
    // Add references to other UI elements for editing vendor details

    private Vendor vendor;
    private String accessToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vendor_profile);

//        editTextVendorName = findViewById(R.id.editTextVendorName);
//        // Initialize references to other UI elements for editing vendor details
//
//        // Get the vendor and access token from the intent
//        vendor = getIntent().getParcelableExtra("vendor");
//        accessToken = getIntent().getStringExtra("access_token");
//
//        // Populate the UI elements with the existing vendor details
//        if (vendor != null) {
//            editTextVendorName.setText(vendor.getVendor_name());
//            // Set other UI elements with corresponding vendor details
//        }
//
//        Button buttonSaveChanges = findViewById(R.id.buttonSaveChanges);
//        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveChanges();
//            }
//        });
    }

//    private void saveChanges() {
//        // Get the edited vendor details from the UI elements
//        String editedVendorName = editTextVendorName.getText().toString();
//        // Get other edited vendor details from other UI elements
//
//        // Update the vendor object with the edited details
//        if (vendor != null) {
//            vendor.setVendor_name(editedVendorName);
//            // Update other vendor details in the vendor object
//        }
//
//        // Create a JSON object with the updated vendor details
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("vendor_id", vendor.getVendor_id());
//            jsonObject.put("vendor_name", vendor.getVendor_name());
//            // Put other vendor details in the JSON object
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        // Create the request body using the JSON object
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        RequestBody requestBody = RequestBody.create(jsonObject.toString(), JSON);
//
//        // Make the API call to update the vendor profile
//        OkHttpClient client = new OkHttpClient();
//        String apiUrl = "YOUR_API_URL"; // Replace with your actual API URL
//
//        Request request = new Request.Builder()
//                .url(apiUrl)
//                .header("Authorization", "Bearer " + accessToken) // Add the access token to the request header
//                .put(requestBody)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                // Handle request failure
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(edit_vendor_profile.this, "Failed to update vendor profile", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                // Handle request success
//                if (response.isSuccessful()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(edit_vendor_profile.this, "Vendor profile updated successfully", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } else {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(edit_vendor_profile.this, "Failed to update vendor profile", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        });
//    }
}
