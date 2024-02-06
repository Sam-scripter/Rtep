package com.example.rtep2.user_fragment_roles;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rtep2.Departments.departments;
import com.example.rtep2.Detail_orders.detail_orders;
import com.example.rtep2.Employees.employees;
import com.example.rtep2.Fuel_Stations.fuel_stations;
import com.example.rtep2.R;
import com.example.rtep2.Repair_orders.repair_orders;
import com.example.rtep2.Sign_in_up.register;
import com.example.rtep2.Sign_in_up.register_vehicle;
import com.example.rtep2.Vehicles.vehicles;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link superUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class superUserFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public superUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment superUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static superUserFragment newInstance() {

        return new superUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_super_user, container, false);

        CardView fuelOrdersCard = view.findViewById(R.id.fuel_orders_card);
        CardView repairOrdersCard = view.findViewById(R.id.repair_orders_card);
        CardView employeesCard = view.findViewById(R.id.employees_card);
        CardView vehiclesCard = view.findViewById(R.id.vehicles_card);
        CardView departmentsCard = view.findViewById(R.id.departments_card);
        CardView reportsCard = view.findViewById(R.id.reports_card);
        CardView fuelShipmentsCard = view.findViewById(R.id.fuel_shipments_card);
        CardView fuelStationsCard = view.findViewById(R.id.fuel_stations_card);
        CardView addEmployeeCard = view.findViewById(R.id.add_employee_card);
        CardView addVehicleCard = view.findViewById(R.id.add_vehicle_card);

        fuelOrdersCard.setOnClickListener(this);
        repairOrdersCard.setOnClickListener(this);
        employeesCard.setOnClickListener(this);
        vehiclesCard.setOnClickListener(this);
        departmentsCard.setOnClickListener(this);
        reportsCard.setOnClickListener(this);
        fuelShipmentsCard.setOnClickListener(this);
        fuelStationsCard.setOnClickListener(this);
        addEmployeeCard.setOnClickListener(this);
        addVehicleCard.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.fuel_orders_card:
                // Handle fuel orders click
                intent = new Intent(getActivity(), detail_orders.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

            case R.id.repair_orders_card:
                // Handle repair orders click
                intent = new Intent(getActivity(), repair_orders.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

            case R.id.employees_card:
                // Handle employees click
                intent = new Intent(getActivity(), employees.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

            case R.id.vehicles_card:
                // Handle vehicles click
                intent = new Intent(getActivity(), vehicles.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

                case R.id.departments_card:
                // Handle departments click
                intent = new Intent(getActivity(), departments.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

            case R.id.reports_card:
                // Handle reports click
//                intent = new Intent(getActivity(), ReportsActivity.class);
//                startActivity(intent);
                break;

            case R.id.fuel_shipments_card:
                // Handle fuel shipments click
//                intent = new Intent(getActivity(), FuelShipmentsActivity.class);
//                startActivity(intent);
                break;

            case R.id.fuel_stations_card:
                // Handle fuel stations click
                intent = new Intent(getActivity(), fuel_stations.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

            case R.id.add_employee_card:
                intent = new Intent(getActivity(), register.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

            case R.id.add_vehicle_card:
                intent = new Intent(getActivity(), register_vehicle.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

        }
    }

    private FragmentManager getSupportFragmentManager() {
        return requireActivity().getSupportFragmentManager();
    }

}