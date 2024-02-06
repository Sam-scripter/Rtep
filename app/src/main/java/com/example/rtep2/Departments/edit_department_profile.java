package com.example.rtep2.Departments;

import androidx.appcompat.app.AppCompatActivity;

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

public class edit_department_profile extends AppCompatActivity {

    private EditText departmentNameEditText;
    private EditText departmentIdEditText;
    private EditText departmentFullEditText;
    private Button saveButton;

    private DepartmentData.DepartmentClass department;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_department_profile);

        departmentNameEditText = findViewById(R.id.edit_department_name);
        departmentIdEditText = findViewById(R.id.edit_department_id);
        departmentFullEditText = findViewById(R.id.edit_department_full_name);
        saveButton = findViewById(R.id.Btn_save);

        // Retrieve the access token and department from the intent
        accessToken = getIntent().getStringExtra("access_token");
        Log.d("access token got", accessToken);
        department = getIntent().getParcelableExtra("department");

        // Populate the EditText fields with the department details
        if (department != null) {
            departmentNameEditText.setText(department.getDepartment_name());
            departmentIdEditText.setText(department.getDepartment_id());
            departmentFullEditText.setText(department.getDepartment_full_name());
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the API call to update the department
                updateDepartment();
            }
        });
    }

    private void updateDepartment() {
        // Get the updated values from the EditText fields
        String updatedDepartmentName = departmentNameEditText.getText().toString().trim();
        String updatedDepartmentId = departmentIdEditText.getText().toString().trim();
        String updatedDepartmentFullName = departmentFullEditText.getText().toString().trim();

        // Create a JSON object with the updated department details
        try {
            JSONObject updatedDepartmentJson = new JSONObject();
            updatedDepartmentJson.put("department_name", updatedDepartmentName);
            updatedDepartmentJson.put("department_id", updatedDepartmentId);
            updatedDepartmentJson.put("department_full_name", updatedDepartmentFullName);


            // Create the request body with the JSON object
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), updatedDepartmentJson.toString());

            // Construct the URL for the update request
            String baseURL = APIConstants.BASE_URL;
            String departmentId = department.getDepartment_id();
            String updateURL = baseURL + "departments/" + departmentId + "/";

            // Create an OkHttpClient instance
            OkHttpClient client = new OkHttpClient();

            // Create the request with the access token in the header and the request body
            Request request = new Request.Builder()
                    .url(updateURL)
                    .header("Authorization", "Bearer " + accessToken)
                    .put(requestBody)
                    .build();

            // Make the API call asynchronously
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(edit_department_profile.this, "Failed to update department", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(edit_department_profile.this, "Department updated", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(edit_department_profile.this, "Failed to update department", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
