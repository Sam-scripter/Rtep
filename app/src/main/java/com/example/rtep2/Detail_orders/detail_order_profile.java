package com.example.rtep2.Detail_orders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class detail_order_profile extends AppCompatActivity {

    private TextView detailOrderNoTextView;
    private TextView toTextView;
    private TextView orderStatusTextView;
    private TextView orderForTextView;
    private TextView orderDescriptionTextView;
    private TextView lPOCurrentMonthTextView;
    private TextView vehicleIdTextView;
    private TextView licenseNoTextView;
    private TextView aRationsDieselTextView;
    private TextView bRationsPetrolTextView;
    private TextView litresFuelTextView;
    private TextView litresOilTextView;
    private TextView datePostedTextView;
    private TextView dateUpdatedTextView;
    private Button editButton;
    private Button deleteButton;

    private Detail_order detailOrder;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_profile);

        detailOrderNoTextView = findViewById(R.id.P_detail_order_no);
        toTextView = findViewById(R.id.P_detail_to);
        orderStatusTextView = findViewById(R.id.P_detail_order_status);
        orderForTextView = findViewById(R.id.P_detail_order_for);
        orderDescriptionTextView = findViewById(R.id.detail_order_description);
        lPOCurrentMonthTextView = findViewById(R.id.P_detail_order_LPO_no);
        vehicleIdTextView = findViewById(R.id.P_detail_order_vehicle_id);
        licenseNoTextView = findViewById(R.id.P_detail_order_license_no);
        aRationsDieselTextView = findViewById(R.id.P_detail_order_rations_fuel);
        bRationsPetrolTextView = findViewById(R.id.P_detail_order_rations_fuel);
        litresFuelTextView = findViewById(R.id.P_detail_order_litres_fuel);
        litresOilTextView = findViewById(R.id.P_detail_order_litres_oil);
        datePostedTextView = findViewById(R.id.P_detail_order_date_posted);
        dateUpdatedTextView = findViewById(R.id.P_detail_order_date_updated);
        editButton = findViewById(R.id.profile_BtnEdit);
        deleteButton = findViewById(R.id.P_detail_order_BtnDelete);

        // Retrieve the Detail_order object and access token from the intent
        detailOrder = getIntent().getParcelableExtra("detail_order");
        accessToken = getIntent().getStringExtra("access_token");

        if (detailOrder != null) {
            // Populate the TextViews with the detail order details
            detailOrderNoTextView.setText(detailOrder.getDetail_order_no());
            toTextView.setText(detailOrder.getTo());
            orderStatusTextView.setText(detailOrder.getOrder_Status());
            orderForTextView.setText(detailOrder.getOrder_For());
            orderDescriptionTextView.setText(detailOrder.getOrder_Description());
            lPOCurrentMonthTextView.setText(detailOrder.getlPO_No_Current_Month());
            vehicleIdTextView.setText(detailOrder.getVehicle_Id());
            licenseNoTextView.setText(detailOrder.getLicense_No());
            aRationsDieselTextView.setText(detailOrder.getA_Rations_Diesel());
            bRationsPetrolTextView.setText(detailOrder.getB_Rations_Petrol());
            litresFuelTextView.setText(detailOrder.getLitres_fuel());
            litresOilTextView.setText(detailOrder.getLitres_oil());
            datePostedTextView.setText(detailOrder.getDate_posted());
            dateUpdatedTextView.setText(detailOrder.getDate_updated());
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the activity to edit the detail order profile
                Intent intent = new Intent(detail_order_profile.this, edit_detail_order_profile.class);
                intent.putExtra("detail_order", detailOrder);
                intent.putExtra("access_token", accessToken);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform delete operation using the access token
                deleteDetailOrder();
            }
        });
    }

    private void deleteDetailOrder() {
        // Construct the URL for the delete request
        String baseURL = APIConstants.BASE_URL;
        String detailOrderNo = detailOrder.getDetail_order_no();
        String deleteURL = baseURL + "detail_orders/" + detailOrderNo;

        // Create an OkHttpClient instance
        OkHttpClient client = new OkHttpClient();

        // Create the request with the access token in the header
        Request request = new Request.Builder()
                .url(deleteURL)
                .header("Authorization", "Bearer " + accessToken)
                .delete()
                .build();

        // Make the API call asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(detail_order_profile.this, "Failed to delete detail order", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(detail_order_profile.this, "Detail order deleted", Toast.LENGTH_SHORT).show();
                            // Redirect to the detail orders activity
                            Intent intent = new Intent(detail_order_profile.this, detail_orders.class);
                            intent.putExtra("access_token", accessToken);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(detail_order_profile.this, "Failed to delete detail order", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
