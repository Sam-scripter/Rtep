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
import com.example.rtep2.Vehicles.vehicles;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransportFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment TransportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransportFragment newInstance() {

        return new TransportFragment();
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
        View view = inflater.inflate(R.layout.fragment_transport, container, false);

        CardView fuelOrdersCard = view.findViewById(R.id.transport_fuelOrders);
        CardView cashFlowCard = view.findViewById(R.id.transport_cashflow);
        CardView vehiclesCard = view.findViewById(R.id.transport_vehicles);
        CardView reportsCard = view.findViewById(R.id.transport_reports);
        CardView fuelShipmentsCard = view.findViewById(R.id.transport_fuelShipments);
        CardView fuelStationsCard = view.findViewById(R.id.transport_fuelStations);

        fuelOrdersCard.setOnClickListener(this);
        cashFlowCard.setOnClickListener(this);
        vehiclesCard.setOnClickListener(this);
        reportsCard.setOnClickListener(this);
        fuelShipmentsCard.setOnClickListener(this);
        fuelStationsCard.setOnClickListener(this);

        return view;

    }
    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.transport_fuelOrders:
                // Handle fuel orders click
//                intent = new Intent(getActivity(), FuelOrdersActivity.class);
//                startActivity(intent);
                break;

            case R.id.transport_cashflow:
                // Handle repair orders click
//                intent = new Intent(getActivity(), RepairOrdersActivity.class);
//                startActivity(intent);
                break;

            case R.id.transport_vehicles:
                // Handle employees click
                intent = new Intent(getActivity(), vehicles.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

            case R.id.transport_reports:
                // Handle vehicles click
//                intent = new Intent(getActivity(), VehiclesActivity.class);
//                startActivity(intent);
                break;

            case R.id.transport_fuelShipments:
                // Handle departments click
//                intent = new Intent(getActivity(), DepartmentsActivity.class);
//                startActivity(intent);
                break;

            case R.id.transport_fuelStations:
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