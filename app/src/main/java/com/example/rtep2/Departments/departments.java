package com.example.rtep2.Departments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rtep2.R;
import com.example.rtep2.APIConstants;

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

public class departments extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DepartmentAdapter departmentAdapter;
    private List<DepartmentData.DepartmentClass> departmentList;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);

        recyclerView = findViewById(R.id.recyclerView_departments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        departmentList = new ArrayList<>();
        departmentAdapter = new DepartmentAdapter(this, departmentList);
        recyclerView.setAdapter(departmentAdapter);

        // Retrieve the access token from the intent
        accessToken = getIntent().getStringExtra("access_token");
        Log.d("yes access", accessToken);

        // Set item click listener for the adapter
        departmentAdapter.setOnItemClickListener(new DepartmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DepartmentData.DepartmentClass department) {
                openDepartmentProfile(department);
            }
        });

        retrieveDepartments();
    }

    private void retrieveDepartments() {
        String URL = APIConstants.BASE_URL + "departments/";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL)
                .header("Authorization", "Bearer " + accessToken)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(departments.this, "Failed to fetch departments", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Log.e("response body", responseBody);

                    new AsyncTask<String, Void, List<DepartmentData.DepartmentClass>>() {
                        @Override
                        protected List<DepartmentData.DepartmentClass> doInBackground(String... strings) {
                            return parseDepartments(strings[0]);
                        }

                        @Override
                        protected void onPostExecute(List<DepartmentData.DepartmentClass> departments) {
                            departmentList.clear();
                            departmentList.addAll(departments);
                            departmentAdapter.notifyDataSetChanged();
                        }
                    }.execute(responseBody);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(departments.this, "Failed to fetch departments", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private List<DepartmentData.DepartmentClass> parseDepartments(String responseBody) {
        List<DepartmentData.DepartmentClass> departments = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(responseBody);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String departmentName = jsonObject.getString("department_name");
                String departmentId = jsonObject.getString("department_id");
                String departmentFullName = jsonObject.getString("department_full_name");

                DepartmentData.DepartmentClass department = new DepartmentData.DepartmentClass(departmentName, departmentId, departmentFullName);
                departments.add(department);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return departments;
    }

    private void openDepartmentProfile(DepartmentData.DepartmentClass department) {
        // Pass the department data and accessToken to the profile activity
        Intent intent = new Intent(this, department_profile.class);
        intent.putExtra("department_name", department.getDepartment_name());
        intent.putExtra("department_id", department.getDepartment_id());
        intent.putExtra("department_full_name", department.getDepartment_full_name());
        intent.putExtra("department", department);
        intent.putExtra("access_token", accessToken);
        startActivity(intent);
    }
}
