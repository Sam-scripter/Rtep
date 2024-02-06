package com.example.rtep2.Repair_orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rtep2.R;

import java.util.List;

public class RepairOrderAdapter extends RecyclerView.Adapter<RepairOrderAdapter.ViewHolder> {

    private Context context;
    private List<RepairOrder> repairOrderList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public RepairOrderAdapter(Context context, List<RepairOrder> repairOrderList) {
        this.context = context;
        this.repairOrderList = repairOrderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_repair_order_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RepairOrder repairOrder = repairOrderList.get(position);

        holder.repairOrderNumber.setText(repairOrder.getRepair_order_number());
        holder.vehicleRegistrationNo.setText(repairOrder.getVehicle_Registration_No());
        holder.initiatedBy.setText(repairOrder.getInitiated_by());
        holder.vehicleId.setText(repairOrder.getDefect_Issues());

    }

    @Override
    public int getItemCount() {
        return repairOrderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView repairOrderNumber;
        TextView vehicleRegistrationNo;
        TextView initiatedBy;
        TextView vehicleId;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            repairOrderNumber = itemView.findViewById(R.id.repair_order_number);
            vehicleRegistrationNo = itemView.findViewById(R.id.repair_vehicle_registration_no);
            initiatedBy = itemView.findViewById(R.id.repair_initiated_by);
            vehicleId = itemView.findViewById(R.id.vehicle_id);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
