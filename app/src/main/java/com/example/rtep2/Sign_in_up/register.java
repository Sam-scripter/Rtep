package com.example.rtep2.Sign_in_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rtep2.Employees.employees;
import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class register extends AppCompatActivity {

    EditText public_service_no;
    EditText first_name;
    EditText last_name;
    EditText mobile_no;
    EditText location_address;
    EditText email_address;
    Spinner position;
    EditText password;
    Spinner gender;
    EditText department_id;
    Spinner role;
    Button register;
    Button dobButton;
    Button dohButton;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        public_service_no = findViewById(R.id.public_service_no);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        mobile_no = findViewById(R.id.mobile_number);
        location_address = findViewById(R.id.LocationAddress);
        email_address = findViewById(R.id.email_address);
        position = findViewById(R.id.position);
        password = findViewById(R.id.password);
        gender = findViewById(R.id.gender);
        department_id = findViewById(R.id.department_id);
        role = findViewById(R.id.role);
        register = findViewById(R.id.Btn_register);
        dobButton = findViewById(R.id.Btn_dob);
        dohButton = findViewById(R.id.Btn_doh);

        dobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(dobButton);
            }
        });

        dohButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(dohButton);
            }
        });

        // Spinner values
        String[] positions = {"Driver", "Transport Officer", "Repair Manager"};
        String[] roles = {"admin", "superuser", "employee"};
        String[] genders = {"male", "female", "other"};

        // Position spinner
        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positions);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        position.setAdapter(positionAdapter);

        // Role spinner
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(roleAdapter);

        // gender spinner
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);

        // Retrieve the access token from the previous activity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("access_token")) {
            accessToken = intent.getStringExtra("access_token");
            Log.d("Yes Access", "Access token was found");

        } else {
            Toast.makeText(this, "Access token not found", Toast.LENGTH_SHORT).show();
            Log.d("No Access", "Access token was not found");
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values from the EditText fields
                String Public_service_no = public_service_no.getText().toString();
                String First_name = first_name.getText().toString();
                String Last_name = last_name.getText().toString();
                String mobile_number = mobile_no.getText().toString();
                String LocationAddress = location_address.getText().toString();
                String EmailAddress = email_address.getText().toString();
                String Position = position.getSelectedItem().toString();
                String Password = password.getText().toString();
                String Gender = gender.getSelectedItem().toString();
                int DepartmentId = Integer.parseInt(department_id.getText().toString());
                String Role = role.getSelectedItem().toString();
                String DOB = dobButton.getText().toString();
                String DOH = dohButton.getText().toString();

                String base_url = APIConstants.BASE_URL;
                String url = base_url + "register/";

                OkHttpClient client = new OkHttpClient();

                RequestBody employee_body = new FormBody.Builder()
                        .add("public_service_no", Public_service_no)
                        .add("first_name", First_name)
                        .add("last_name", Last_name)
                        .add("mobile_number", mobile_number)
                        .add("date_of_birth", DOB)
                        .add("date_of_hire", DOH)
                        .add("home_address", LocationAddress)
                        .add("email", EmailAddress)
                        .add("designation", Position)
                        .add("password", Password)
                        .add("gender", Gender)
                        .add("role", Role)
                        .add("department_id", String.valueOf(DepartmentId))
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .header("Authorization", "Bearer " + accessToken)  // Pass the access token in the request header
                        .post(employee_body)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                Log.d("Unauthorized 1", "No no Registration failed");
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(register.this, employees.class);
                                    intent.putExtra("access_token", accessToken);
                                    startActivity(intent);
                                    // Perform any additional actions upon successful registration
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                    Log.d("Unauthorized", "No no Registration failed");
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void showDatePicker(final Button button) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(register.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String formattedDate = formatDate(year, monthOfYear, dayOfMonth);
                        button.setText(formattedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    private String formatDate(int year, int month, int day) {
        return String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, day);
    }
}
