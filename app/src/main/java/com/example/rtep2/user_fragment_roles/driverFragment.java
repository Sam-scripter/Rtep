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

import com.example.rtep2.R;
import com.example.rtep2.Vehicles.vehicle_profile;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link driverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class driverFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public driverFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment driverFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static driverFragment newInstance() {
        return new driverFragment();
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
        View view = inflater.inflate(R.layout.fragment_driver, container, false);

        CardView vehicleCard = view.findViewById(R.id.driver_myVehicle);
        CardView orderFuelCard = view.findViewById(R.id.driver_orderFuel);
        CardView orderRepairCard = view.findViewById(R.id.driver_orderRepair);
        CardView fuelOrdersCard = view.findViewById(R.id.driver_fuelOrders);
        CardView repairOrdersCard = view.findViewById(R.id.driver_repairOrders);
        CardView reportsCard = view.findViewById(R.id.driver_reports);

        vehicleCard.setOnClickListener(this);
        orderFuelCard.setOnClickListener(this);
        orderRepairCard.setOnClickListener(this);
        fuelOrdersCard.setOnClickListener(this);
        repairOrdersCard.setOnClickListener(this);
        reportsCard.setOnClickListener(this);

        return view;

    }
    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.driver_myVehicle:
                // Handle fuel orders click
                intent = new Intent(getActivity(), vehicle_profile.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

            case R.id.driver_orderFuel:
                // Handle repair orders click
//                intent = new Intent(getActivity(), RepairOrdersActivity.class);
//                startActivity(intent);
                break;

            case R.id.driver_orderRepair:
                // Handle employees click
//                Fragment employeesFragment = new adminFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout, employeesFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
                break;

            case R.id.driver_fuelOrders:
                // Handle vehicles click
//                intent = new Intent(getActivity(), VehiclesActivity.class);
//                startActivity(intent);
                break;

            case R.id.driver_repairOrders:
                // Handle departments click
//                intent = new Intent(getActivity(), DepartmentsActivity.class);
//                startActivity(intent);
                break;

            case R.id.driver_reports:
                // Handle reports click
//                intent = new Intent(getActivity(), ReportsActivity.class);
//                startActivity(intent);
                break;

        }
    }

    private FragmentManager getSupportFragmentManager() {
        return requireActivity().getSupportFragmentManager();
    }
}