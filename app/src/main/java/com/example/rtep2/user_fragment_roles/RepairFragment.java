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
 * Use the {@link RepairFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepairFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RepairFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment RepairFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RepairFragment newInstance() {

        return new RepairFragment();
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
        View view = inflater.inflate(R.layout.fragment_repair, container, false);


        CardView repairOrdersCard = view.findViewById(R.id.repair_repairOrders);
        CardView vehiclesCard = view.findViewById(R.id.repair_vehicles);
        CardView reportsCard = view.findViewById(R.id.repair_reports);
        CardView summaryCard = view.findViewById(R.id.repair_summary);

        repairOrdersCard.setOnClickListener(this);
        vehiclesCard.setOnClickListener(this);
        reportsCard.setOnClickListener(this);
        summaryCard.setOnClickListener(this);

        return view;

    }
    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.repair_repairOrders:
                // Handle fuel orders click
//                intent = new Intent(getActivity(), FuelOrdersActivity.class);
//                startActivity(intent);
                break;

            case R.id.repair_vehicles:
                // Handle repair orders click
                intent = new Intent(getActivity(), vehicles.class);
                intent.putExtra("access_token", getArguments().getString("access_token"));
                startActivity(intent);
                break;

            case R.id.repair_reports:
                // Handle employees click
//                Fragment employeesFragment = new adminFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout, employeesFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
                break;

            case R.id.repair_summary:
                // Handle vehicles click
//                intent = new Intent(getActivity(), VehiclesActivity.class);
//                startActivity(intent);
                break;

        }
    }

    private FragmentManager getSupportFragmentManager() {
        return requireActivity().getSupportFragmentManager();
    }
}