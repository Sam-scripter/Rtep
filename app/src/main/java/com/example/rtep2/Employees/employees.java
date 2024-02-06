package com.example.rtep2.Employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;
import com.example.rtep2.Sign_in_up.register;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class employees extends AppCompatActivity implements EmployeeAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;
    private List<Employee> employeeList;
    private String accessToken;
    private FloatingActionButton FaB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        FaB = findViewById(R.id.add_employee);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        employeeList = new ArrayList<>();
        employeeAdapter = new EmployeeAdapter(this, employeeList);
        employeeAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(employeeAdapter);

        // Retrieve the access token if passed from the previous activity (Overview)
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("access_token")) {
            accessToken = intent.getStringExtra("access_token");
            Log.d("TAG", "Access Token: " + accessToken);
            retrieveEmployeeData();
        } else {
            Toast.makeText(this, "Access token not found", Toast.LENGTH_SHORT).show();
        }

        FaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(employees.this, register.class);
                startActivity(intent);
            }
        });
    }

    private void retrieveEmployeeData() {
        String base_url = APIConstants.BASE_URL;
        String url = base_url + "employees/";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + accessToken)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(employees.this, "Failed to fetch employee data first failure", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Log.e("response body", responseBody);
                    List<Employee> employees = parseEmployeeData(responseBody);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            employeeList.clear();
                            employeeList.addAll(employees);
                            employeeAdapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(employees.this, "Failed to fetch employee data second failure", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private List<Employee> parseEmployeeData(String responseBody) {
        List<Employee> employees = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(responseBody);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String publicServiceNumber = jsonObject.getString("public_service_no");
                String firstName = jsonObject.getString("first_name");
                String lastName = jsonObject.getString("last_name");
                String mobileNumber = jsonObject.getString("mobile_number");
                String location = jsonObject.getString("home_address");
                String dateOfBirth = jsonObject.getString("date_of_birth");
                String dateOfHire = jsonObject.getString("date_of_hire");
                String email = jsonObject.getString("email");
                String designation = jsonObject.getString("designation");
                String employmentType = jsonObject.getString("employment_type");
                String password = jsonObject.optString("password", "");
                String gender = jsonObject.getString("gender");
                String role = jsonObject.getString("role");
                String department = jsonObject.getString("department_id");
                String workStation = jsonObject.getString("work_Station");

                Employee employee = new Employee(publicServiceNumber, firstName, lastName, mobileNumber, location, dateOfBirth, dateOfHire, email, designation, employmentType, password, gender, role, department);
                employees.add(employee);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click event if needed
        Employee selectedEmployee = employeeList.get(position);

        Intent intent = new Intent(employees.this, Profile.class);
        intent.putExtra("employee", selectedEmployee);
        intent.putExtra("access_token", accessToken);
        startActivity(intent);
    }
}
