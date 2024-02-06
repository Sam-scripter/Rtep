package com.example.rtep2.Fuel_Stations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rtep2.R;

import java.util.List;

public class fuel_stations_adapter extends RecyclerView.Adapter<fuel_stations_adapter.ViewHolder> {

    private Context context;
    private List<Fuel_station> fuelStationList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public fuel_stations_adapter(Context context, List<Fuel_station> fuelStationList) {
        this.context = context;
        this.fuelStationList = fuelStationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_fuel_stations_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fuel_station fuelStation = fuelStationList.get(position);

        holder.fuelStationName.setText(fuelStation.getFuel_Station_Name());
        holder.totalLitres.setText(fuelStation.getTotal_litres_fuel());
        holder.rationsFuel.setText(fuelStation.getA_Rations_Diesel());
    }

    @Override
    public int getItemCount() {
        return fuelStationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fuelStationName;
        TextView totalLitres;
        TextView rationsFuel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fuelStationName = itemView.findViewById(R.id.fuel_Station_Name);
            totalLitres = itemView.findViewById(R.id.total_litres);
            rationsFuel = itemView.findViewById(R.id.rations_fuel);

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
