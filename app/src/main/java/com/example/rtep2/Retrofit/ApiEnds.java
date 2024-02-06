package com.example.rtep2.Retrofit;

import com.example.rtep2.Departments.DepartmentData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public class ApiEnds {

    interface RequestDepartment {
        @GET("/api/departments/")
        Call<List<DepartmentData.DepartmentClass>> getDepartments();
    }


}
