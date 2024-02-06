package com.example.rtep2.Employees;

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

public class Profile extends AppCompatActivity {

    TextView name;
    TextView userEmail;
    TextView mobile_no;
    TextView DOB;
    TextView gender;
    TextView user_department;
    TextView public_service_no;
    TextView DOH;
    TextView position;
    TextView user_role;
    TextView date_created;
    Button btnEdit;
    Button btnDelete;
    private Employee employee;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        userEmail = findViewById(R.id.Email);
        mobile_no = findViewById(R.id.Mobile_no);
        DOB = findViewById(R.id.userDateOfBirth);
        gender = findViewById(R.id.g_text);
        user_department = findViewById(R.id.department_text);
        public_service_no = findViewById(R.id.userPublic_service_no);
        DOH = findViewById(R.id.DOHtext);
        position = findViewById(R.id.p_text);
        user_role = findViewById(R.id.role);
        date_created = findViewById(R.id.date_created);
        btnEdit = findViewById(R.id.Btn_editProfile);
        btnDelete = findViewById(R.id.Btn_delete);

        // Retrieve the intent extras
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("employee")) {
            // Assign the intent extra to the instance variable 'employee'
            employee = intent.getParcelableExtra("employee");

            // Retrieve the access token if passed from the previous activity (Overview)
            if (intent.hasExtra("access_token")) {
                accessToken = intent.getStringExtra("access_token");
                Log.d("TAG", "Access Token: " + accessToken);
            } else {
                Toast.makeText(this, "Access token not found", Toast.LENGTH_SHORT).show();
            }

            // Update the TextViews with the corresponding information
            name.setText(employee.getFirstName() + " " + employee.getLastName());
            userEmail.setText(employee.getEmail());
            mobile_no.setText(employee.getMobileNumber());
            DOB.setText(employee.getDateOfBirth());
            gender.setText(employee.getGender());
            user_department.setText(employee.getDepartment());
            public_service_no.setText(employee.getPublicServiceNumber());
            DOH.setText(employee.getDateOfHire());
            position.setText(employee.getDesignation());
            user_role.setText(employee.getRole());
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, edit_profile.class);
                intent.putExtra("employee", employee);
                intent.putExtra("access_token", accessToken);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });
    }

    private void deleteEmployee() {
        // Retrieve the employee details from the intent extras
        if (employee != null) {
            String publicServiceNumber = employee.getPublicServiceNumber();

            String base_url = APIConstants.BASE_URL;
            String url = base_url + "employees/";

            // Send a DELETE request to delete the employee
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url + publicServiceNumber + "/")
                    .delete()
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Profile.this, "Failed to delete employee: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Profile.this, "Employee deleted successfully", Toast.LENGTH_SHORT).show();
                                // Finish the activity and go back to the previous activity
                                finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Profile.this, "Failed to delete employee", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
    }
}
