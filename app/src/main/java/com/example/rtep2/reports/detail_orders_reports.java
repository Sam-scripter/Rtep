package com.example.rtep2.reports;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.rtep2.R;

public class detail_orders_reports extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_orders_reports);

        CardView dailyReportButton = findViewById(R.id.detail_daily_reports);
        CardView weeklyReportButton = findViewById(R.id.detail_weekly_reports);
        CardView monthlyReportButton = findViewById(R.id.detail_monthly_reports);

        dailyReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to generate the daily report
                generateReport("daily");
            }
        });

        weeklyReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to generate the weekly report
                generateReport("weekly");
            }
        });

        monthlyReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to generate the monthly report
                generateReport("monthly");
            }
        });
    }

    private void generateReport(String timePeriod) {
        // ... (To be implemented - Generate the report based on the specified time period)
        // You can call the generateWordDocument() method from DetailOrdersActivity here
        // and pass the timePeriod as an argument to generate the appropriate report.
        // Implement the logic to display the report based on your requirements.
        // For example, you can use a WebView or another appropriate view to display the report.
        // The report content should be obtained from the generated Word document.
        // You can also add logic to save the generated report to a specific location if needed.
        // Remember to handle permissions for saving files if you plan to save the reports.
        // You may also use a library for displaying Word documents in your app.
        // The specific implementation will depend on your application's requirements and preferences.
    }
}