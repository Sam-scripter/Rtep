package com.example.rtep2.Departments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class department_profile extends AppCompatActivity {

    private TextView departmentNameTextView;
    private TextView departmentIdTextView;
    private TextView departmentFullName;
    private Button editButton;
    private Button deleteButton;

    private DepartmentData.DepartmentClass department;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_profile);

        departmentNameTextView = findViewById(R.id.profile_department_name);
        departmentIdTextView = findViewById(R.id.profile_department_id);
        departmentFullName = findViewById(R.id.profile_department_full_name);
        editButton = findViewById(R.id.profile_BtnEdit);
        deleteButton = findViewById(R.id.profile_BtnDelete);

        // Retrieve the DepartmentData.DepartmentClass object and access token from the intent
        department = getIntent().getParcelableExtra("department");
        accessToken = getIntent().getStringExtra("access_token");
        Log.d("yes Aceess", accessToken);

        if (department != null) {
            // Display the department's name, ID, and full name
            departmentNameTextView.setText(department.getDepartment_name());
            departmentIdTextView.setText(department.getDepartment_id());
            departmentFullName.setText(department.getDepartment_full_name()); // Set the department full name
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the activity to edit the department profile
                Intent intent = new Intent(department_profile.this, edit_department_profile.class);
                intent.putExtra("department", department);
                intent.putExtra("access_token", accessToken);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform delete operation using the access token
                deleteDepartment();
            }
        });
    }

    private void deleteDepartment() {
        // Construct the URL for the delete request
        String baseURL = APIConstants.BASE_URL;
        String departmentId = department.getDepartment_id();
        String deleteURL = baseURL + "departments/" + departmentId;

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
                        Toast.makeText(department_profile.this, "Failed to delete department", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(department_profile.this, "Department deleted", Toast.LENGTH_SHORT).show();
                            // Redirect to the Departments activity
                            Intent intent = new Intent(department_profile.this, departments.class);
                            intent.putExtra("access_token", accessToken);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(department_profile.this, "Failed to delete department", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
