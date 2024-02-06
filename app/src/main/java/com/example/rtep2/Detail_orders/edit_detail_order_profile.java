package com.example.rtep2.Detail_orders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class edit_detail_order_profile extends AppCompatActivity {

    private EditText detailOrderNoEditText;
    private EditText toEditText;
    private EditText orderForEditText;
    private EditText orderDescriptionEditText;
    private EditText lPOCurrentMonthEditText;
    private EditText vehicleIdEditText;
    private EditText licenseNoEditText;
    private EditText aRationsDieselEditText;
    private EditText bRationsPetrolEditText;
    private EditText litresFuelEditText;
    private EditText litresOilEditText;
    private Button saveButton;

    private Detail_order detailOrder;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail_order_profile);

        detailOrderNoEditText = findViewById(R.id.edit_detail_order_no);
        toEditText = findViewById(R.id.edit_to);
        orderForEditText = findViewById(R.id.edit_order_for);
        orderDescriptionEditText = findViewById(R.id.edit_order_description);
        lPOCurrentMonthEditText = findViewById(R.id.edit_lpo_currentMonth);
        vehicleIdEditText = findViewById(R.id.edit_vehicle_id);
        licenseNoEditText = findViewById(R.id.edit_license_no);
        aRationsDieselEditText = findViewById(R.id.edit_a_rations_diesel);
        bRationsPetrolEditText = findViewById(R.id.edit_b_rations_petrol);
        litresFuelEditText = findViewById(R.id.edit_litres_fuel);
        litresOilEditText = findViewById(R.id.edit_litres_oil);
        saveButton = findViewById(R.id.update_detail_order);

        // Retrieve the Detail_order object and access token from the intent
        detailOrder = getIntent().getParcelableExtra("detail_order");
        accessToken = getIntent().getStringExtra("access_token");

        if (detailOrder != null) {
            // Populate the EditTexts with the detail order details
            detailOrderNoEditText.setText(detailOrder.getDetail_order_no());
            toEditText.setText(detailOrder.getTo());
            orderForEditText.setText(detailOrder.getOrder_For());
            orderDescriptionEditText.setText(detailOrder.getOrder_Description());
            lPOCurrentMonthEditText.setText(detailOrder.getlPO_No_Current_Month());
            vehicleIdEditText.setText(detailOrder.getVehicle_Id());
            licenseNoEditText.setText(detailOrder.getLicense_No());
            aRationsDieselEditText.setText(detailOrder.getA_Rations_Diesel());
            bRationsPetrolEditText.setText(detailOrder.getB_Rations_Petrol());
            litresFuelEditText.setText(detailOrder.getLitres_fuel());
            litresOilEditText.setText(detailOrder.getLitres_oil());
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the changes to the detail order profile
                saveDetailOrder();
            }
        });
    }

    private void saveDetailOrder() {
        // Get the updated values from the EditTexts
        String updatedDetailOrderNo = detailOrderNoEditText.getText().toString();
        String updatedTo = toEditText.getText().toString();
        String updatedOrderFor = orderForEditText.getText().toString();
        String updatedOrderDescription = orderDescriptionEditText.getText().toString();
        String updatedIPOCurrentMonth = lPOCurrentMonthEditText.getText().toString();
        String updatedVehicleId = vehicleIdEditText.getText().toString();
        String updatedLicenseNo = licenseNoEditText.getText().toString();
        String updatedARationsDiesel = aRationsDieselEditText.getText().toString();
        String updatedBRationsPetrol = bRationsPetrolEditText.getText().toString();
        String updatedLitresFuel = litresFuelEditText.getText().toString();
        String updatedLitresOil = litresOilEditText.getText().toString();

        // Construct the request body JSON object
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("detail_order_no", updatedDetailOrderNo);
            requestBody.put("to", updatedTo);
            requestBody.put("order_For", updatedOrderFor);
            requestBody.put("order_Description", updatedOrderDescription);
            requestBody.put("IPO_No_Current_Month", updatedIPOCurrentMonth);
            requestBody.put("vehicle_id", updatedVehicleId);
            requestBody.put("license_No", updatedLicenseNo);
            requestBody.put("a_Rations_Diesel", updatedARationsDiesel);
            requestBody.put("b_Rations_Petrol", updatedBRationsPetrol);
            requestBody.put("litres_fuel", updatedLitresFuel);
            requestBody.put("litres_oil", updatedLitresOil);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Construct the URL for the update request
        String baseURL = APIConstants.BASE_URL;
        String detailOrderNo = detailOrder.getDetail_order_no();
        String updateURL = baseURL + "detail-order/" + detailOrderNo + "/";

        // Create an OkHttpClient instance
        OkHttpClient client = new OkHttpClient();

        // Create the request with the access token and request body in the header
        Request request = new Request.Builder()
                .url(updateURL)
                .header("Authorization", "Bearer " + accessToken)
                .put(RequestBody.create(MediaType.parse("application/json"), requestBody.toString()))
                .build();

        // Make the API call asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(edit_detail_order_profile.this, "Failed to save changes", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(edit_detail_order_profile.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
                            // Redirect to the detail order profile activity
                            Intent intent = new Intent(edit_detail_order_profile.this, detail_order_profile.class);
                            intent.putExtra("access_token", accessToken);
                            intent.putExtra("detail_order", detailOrder);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(edit_detail_order_profile.this, "Failed to save changes", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
