package com.example.rtep2.Employees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class edit_profile extends AppCompatActivity {

    EditText publicServiceNumber;
    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextMobileNumber;
    EditText editDOB;
    EditText editDOH;
    EditText editTextDepartmentId;
    EditText editTextLocationAddress;
    EditText editTextEmailAddress;
    EditText editTextPosition;
    EditText editTextGender;
    EditText editTextRole;
    EditText workStation;

    Button buttonUpdate;
    Employee employee;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        publicServiceNumber = findViewById(R.id.edit_public_service_no);
        editTextFirstName = findViewById(R.id.edit_first_name);
        editTextLastName = findViewById(R.id.edit_last_name);
        editTextMobileNumber = findViewById(R.id.edit_mobile_number);
        editDOB = findViewById(R.id.edit_DOB);
        editDOH = findViewById(R.id.edit_DOH);
        editTextDepartmentId = findViewById(R.id.edit_department_id);
        editTextLocationAddress = findViewById(R.id.edit_LocationAddress);
        editTextEmailAddress = findViewById(R.id.edit_email_address);
        editTextPosition = findViewById(R.id.edit_position);
        editTextGender = findViewById(R.id.edit_gender);
        editTextRole = findViewById(R.id.edit_role);
        workStation = findViewById(R.id.edit_workStation);

        buttonUpdate = findViewById(R.id.Btn_update);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("employee")) {
            employee = intent.getParcelableExtra("employee");
            populateFieldsWithEmployeeData();

            // Retrieve the access token if passed from the previous activity (Profile)
            if (intent.hasExtra("access_token")) {
                accessToken = intent.getStringExtra("access_token");
                Toast.makeText(this, "Access token has been found", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Access token not found", Toast.LENGTH_SHORT).show();
            }
        }

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    try {
                        updateEmployeeProfile();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        Toast.makeText(edit_profile.this, "Failed to update employee profile: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void populateFieldsWithEmployeeData() {
        publicServiceNumber.setText(employee.getPublicServiceNumber());
        editTextFirstName.setText(employee.getFirstName());
        editTextLastName.setText(employee.getLastName());
        editTextMobileNumber.setText(employee.getMobileNumber());
        editDOB.setText(employee.getDateOfBirth());
        editDOH.setText(employee.getDateOfHire());
        editTextDepartmentId.setText(employee.getDepartment());
        editTextLocationAddress.setText(employee.getLocation());
        editTextEmailAddress.setText(employee.getEmail());
        editTextPosition.setText(employee.getDesignation());
        editTextGender.setText(employee.getGender());
        editTextRole.setText(employee.getRole());
        workStation.setText(employee.getWorkStation());
    }

    private boolean validateInput() {
        // Validate input fields here
        // Add your validation logic based on your requirements
        // Return true if input is valid, false otherwise

        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String mobileNumber = editTextMobileNumber.getText().toString().trim();
        String dateOfBirth = editDOB.getText().toString().trim();
        String dateOfHire = editDOH.getText().toString().trim();
        String department = editTextDepartmentId.getText().toString().trim();
        String location = editTextLocationAddress.getText().toString().trim();
        String email = editTextEmailAddress.getText().toString().trim();
        String position = editTextPosition.getText().toString().trim();
        String gender = editTextGender.getText().toString().trim();
        String role = editTextRole.getText().toString().trim();
        String work_station = workStation.getText().toString().trim();

        if (TextUtils.isEmpty(firstName)) {
            editTextFirstName.setError("First name is required");
            return false;
        }

        if (TextUtils.isEmpty(lastName)) {
            editTextLastName.setError("Last name is required");
            return false;
        }

        if (TextUtils.isEmpty(mobileNumber)) {
            editTextMobileNumber.setError("Mobile number is required");
            return false;
        }

        if (TextUtils.isEmpty(dateOfBirth)) {
            editDOB.setError("Date of birth is required");
            return false;
        }

        if (TextUtils.isEmpty(dateOfHire)) {
            editDOH.setError("Date of hire is required");
            return false;
        }

        if (TextUtils.isEmpty(department)) {
            editTextDepartmentId.setError("Department is required");
            return false;
        }

        if (TextUtils.isEmpty(location)) {
            editTextLocationAddress.setError("Location is required");
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmailAddress.setError("Email address is required");
            return false;
        }

        if (TextUtils.isEmpty(position)) {
            editTextPosition.setError("Position is required");
            return false;
        }

        if (TextUtils.isEmpty(gender)) {
            editTextGender.setError("Gender is required");
            return false;
        }

        if (TextUtils.isEmpty(role)) {
            editTextRole.setError("Role is required");
            return false;
        }

        return true;
    }

    private void updateEmployeeProfile() throws UnsupportedEncodingException {
        String publicServiceNumber = employee.getPublicServiceNumber();
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String mobileNumber = editTextMobileNumber.getText().toString();
        String dateOfBirth = editDOB.getText().toString();
        String dateOfHire = editDOH.getText().toString();
        String department = editTextDepartmentId.getText().toString();
        String location = editTextLocationAddress.getText().toString();
        String email = editTextEmailAddress.getText().toString();
        String position = editTextPosition.getText().toString();
        String gender = editTextGender.getText().toString();
        String role = editTextRole.getText().toString();
        String work_station = workStation.getText().toString();

        // Create a JSON object with the updated employee data
        JSONObject json = new JSONObject();
        try {
            json.put("first_name", firstName);
            json.put("last_name", lastName);
            json.put("mobile_number", mobileNumber);
            json.put("date_of_birth", dateOfBirth);
            json.put("date_of_hire", dateOfHire);
            json.put("department_id", department);
            json.put("home_address", location);
            json.put("email", email);
            json.put("designation", position);
            json.put("gender", gender);
            json.put("role", role);
            json.put("work_Station", work_station);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(edit_profile.this, "Failed to update employee profile: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        // Create an OkHttpClient instance
        OkHttpClient client = new OkHttpClient();
        String base_url = APIConstants.BASE_URL;
        String url = base_url + "employees/";

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json.toString());

        String encodedPublicServiceNumber = URLEncoder.encode(publicServiceNumber, "UTF-8");
        Request request = new Request.Builder()
                .url(url + encodedPublicServiceNumber + "/")
                .patch(requestBody)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        // Send the HTTP request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Show an error message with the specific reason for the failure
                        Toast.makeText(edit_profile.this, "Failed to update employee profile: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Show a success message
                            Toast.makeText(edit_profile.this, "Employee profile updated successfully", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Return to the profile activity
                    Intent intent = new Intent(edit_profile.this, Profile.class);
                    intent.putExtra("employee", employee);
                    intent.putExtra("access_token", accessToken);
                    startActivity(intent);
                    finish();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Show an error message based on the response status code
                            Toast.makeText(edit_profile.this, "Failed to update employee profile: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
