package com.example.rtep2.Vehicles;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rtep2.R;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {
    private Context context;
    private List<Vehicle> vehicleList;
    private OnItemClickListener itemClickListener; // Listener for item click events

    public interface OnItemClickListener {
        void onItemClick(int position, Vehicle vehicle);
    }

    public VehicleAdapter(Context context, List<Vehicle> vehicleList) {
        this.context = context;
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_vehicle_adapter, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);
        holder.bind(vehicle);

        // Set click listener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (itemClickListener != null && clickedPosition != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(clickedPosition, vehicle);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public class VehicleViewHolder extends RecyclerView.ViewHolder {
        private TextView vehiclePlate;
        private TextView vehicleId;
        private TextView vehicleDepartment;

        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            vehiclePlate = itemView.findViewById(R.id.vehicle_plate);
            vehicleId = itemView.findViewById(R.id.vehicle_id);
            vehicleDepartment = itemView.findViewById(R.id.vehicle_department);
        }

        public void bind(Vehicle vehicle) {
            vehiclePlate.setText(vehicle.getLicense_no());
            vehicleId.setText(vehicle.getVehicle_id());
            vehicleDepartment.setText(vehicle.getDepartment());
        }
    }
}
