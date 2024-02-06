package com.example.rtep2.Detail_orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rtep2.R;

import java.util.List;

public class DetailOrderAdapter extends RecyclerView.Adapter<DetailOrderAdapter.DetailOrderViewHolder> {

    private Context context;
    private List<Detail_order> detailOrderList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, Detail_order detail_order);
    }

    public DetailOrderAdapter(Context context, List<Detail_order> detailOrderList) {
        this.context = context;
        this.detailOrderList = detailOrderList;
    }

    @NonNull
    @Override
    public DetailOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_detail_order_adapter, parent, false);
        return new DetailOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailOrderViewHolder holder, int position) {
        Detail_order detail_order = detailOrderList.get(position);
        holder.bind(detail_order);

        // Set click listener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (itemClickListener != null && clickedPosition != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(clickedPosition, detail_order);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailOrderList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public class DetailOrderViewHolder extends RecyclerView.ViewHolder {
        private TextView detailOrderNoTextView;
        private TextView V_IdTextView;
        private TextView rations;
        private TextView order_status;
        // Add other TextViews as per your layout

        public DetailOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            detailOrderNoTextView = itemView.findViewById(R.id.detail_order_no);
            V_IdTextView = itemView.findViewById(R.id.detail_vehicle_Id);
            rations = itemView.findViewById(R.id.detail_rations);
            order_status = itemView.findViewById(R.id.DetailOrder_status);


        }

        public void bind(Detail_order detail_order) {
            detailOrderNoTextView.setText(detail_order.getDetail_order_no());
            V_IdTextView.setText(detail_order.getVehicle_Id());
            rations.setText(detail_order.getA_Rations_Diesel());
            order_status.setText(detail_order.getOrder_Status());


        }
    }
}
