package com.example.rtep2.user_fragment_roles;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rtep2.Departments.departments;
import com.example.rtep2.Employees.employees;
import com.example.rtep2.R;
import com.example.rtep2.Vehicles.vehicles;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment adminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static adminFragment newInstance() {

        return new adminFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null && args.containsKey("access_token")) {
            String accessToken = args.getString("access_token");
            Log.d("Token in fragment", accessToken);
            // Use the access token as needed in the fragment
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        CardView fuelOrdersCard = view.findViewById(R.id.admin_fuelOrders);
        CardView repairOrdersCard = view.findViewById(R.id.admin_repairOrders);
        CardView employeesCard = view.findViewById(R.id.admin_employees);
        CardView vehiclesCard = view.findViewById(R.id.admin_vehicles);
        CardView departmentsCard = view.findViewById(R.id.admin_departments);
        CardView reportsCard = view.findViewById(R.id.admin_reports);
        CardView fuelShipmentsCard = view.findViewById(R.id.admin_fuelShipments);
        CardView fuelStationsCard = view.findViewById(R.id.admin_fuelStations);

        fuelOrdersCard.setOnClickListener(this);
        repairOrdersCard.setOnClickListener(this);
        employeesCard.setOnClickListener(this);
        vehiclesCard.setOnClickListener(this);
        departmentsCard.setOnClickListener(this);
        reportsCard.setOnClickListener(this);
        fuelShipmentsCard.setOnClickListener(this);
        fuelStationsCard.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.admin_fuelOrders:
                // Handle fuel orders click
//            intent = new Intent(getActivity(), FuelOrdersActivity.class);
//            startActivity(intent);
                break;

            case R.id.admin_repairOrders:
                // Handle repair orders click
//            intent = new Intent(getActivity(), RepairOrdersActivity.class);
//            startActivity(intent);
                break;

            case R.id.admin_employees:
                // Handle employees click
                intent = new Intent(getActivity(), employees.class);
                startActivity(intent);
                break;

            case R.id.admin_vehicles:
                // Handle vehicles click
                intent = new Intent(getActivity(), vehicles.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

            case R.id.admin_departments:
                // Handle departments click
                intent = new Intent(getActivity(), departments.class);
                startActivity(intent);
                break;

            case R.id.admin_reports:
                // Handle reports click
//            intent = new Intent(getActivity(), ReportsActivity.class);
//            startActivity(intent);
                break;

            case R.id.admin_fuelShipments:
                // Handle fuel shipments click
//            intent = new Intent(getActivity(), FuelShipmentsActivity.class);
//            startActivity(intent);
                break;

            case R.id.admin_fuelStations:
                // Handle fuel stations click
//            intent = new Intent(getActivity(), FuelStationsActivity.class);
//            startActivity(intent);
                break;
        }
    }
}