package com.example.rtep2.Vendors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rtep2.R;

import java.util.List;

public class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.ViewHolder> {

    private Context context;
    private List<Vendor> vendorList;

    public VendorAdapter(Context context, List<Vendor> vendorList) {
        this.context = context;
        this.vendorList = vendorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_vendor_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vendor vendor = vendorList.get(position);
        holder.vendorNameTextView.setText(vendor.getVendor_name());
        holder.vendorContactTextView.setText(vendor.getVendor_contact_info());
    }

    @Override
    public int getItemCount() {
        return vendorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vendorNameTextView;
        TextView vendorContactTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vendorNameTextView = itemView.findViewById(R.id.vendor_name);
            vendorContactTextView = itemView.findViewById(R.id.vendor_contact_info);
        }
    }
}
