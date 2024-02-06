package com.example.rtep2.Retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.rtep2.Departments.DepartmentData;
import com.example.rtep2.R;
import com.example.rtep2.Retrofit.ApiEnds.RequestDepartment;

public class RetrofitActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_retrofit);

        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.210.65:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestDepartment requestDepartment = retrofit.create(RequestDepartment.class);
        requestDepartment.getDepartments().enqueue(new Callback<List<DepartmentData.DepartmentClass>>() {
            @Override
            public void onResponse(Call<List<DepartmentData.DepartmentClass>> call, Response<List<DepartmentData.DepartmentClass>> response) {
                List<DepartmentData.DepartmentClass> departments = response.body();
                if (departments != null && !departments.isEmpty()) {
                    DepartmentData.DepartmentClass department = departments.get(1); // Assuming you want the first department in the list
                    textView.setText(department.getDepartment_name());
                } else {
                    textView.setText("No departments found");
                }
            }

            @Override
            public void onFailure(Call<List<DepartmentData.DepartmentClass>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
