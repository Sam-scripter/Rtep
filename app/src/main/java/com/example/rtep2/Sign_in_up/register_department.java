package com.example.rtep2.Sign_in_up;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class register_department extends AppCompatActivity {

    EditText d_id;
    EditText d_name;
    TextView responses;
    Button b;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_department);

        d_id = findViewById(R.id.d_id);
        d_name = findViewById(R.id.d_name);
//        responses = findViewById(R.id.department_server_response);

        b = findViewById(R.id.Btn_register_department);

        // Retrieve the access token from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("accessToken")) {
            accessToken = extras.getString("accessToken");
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String base_url = APIConstants.BASE_URL;
                String URL = base_url + "departments/";
                int id = Integer.parseInt(d_id.getText().toString());
                String name = d_name.getText().toString();

                OkHttpClient client = new OkHttpClient();

                RequestBody depBody = new FormBody.Builder()
                        .add("department_id", String.valueOf(id))
                        .add("department_name", name)
                        .build();

                Request request = new Request.Builder()
                        .url(URL)
                        .header("Authorization", "Bearer " + accessToken)  // Pass the access token in the request header
                        .post(depBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(register_department.this, "Department Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(register_department.this, "Department Registration successful", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            final String errorMessage = response.body().string(); // Get the error message from the response body
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(register_department.this, "Department Registration Failed" + errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
