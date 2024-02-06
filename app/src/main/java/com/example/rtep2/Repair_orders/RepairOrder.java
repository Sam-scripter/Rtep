package com.example.rtep2.Repair_orders;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class RepairOrder implements Parcelable {
    private String repair_order_number;
    private String vehicle_id;
    private String vehicle_Registration_No;
    private String initiated_by;
    private String initiator_name;
    private String defect_Issues;
    private String date_Posted;
    private String date_updated;

    public String getRepair_order_number() {
        return repair_order_number;
    }

    public void setRepair_order_number(String repair_order_number) {
        this.repair_order_number = repair_order_number;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_Registration_No() {
        return vehicle_Registration_No;
    }

    public void setVehicle_Registration_No(String vehicle_Registration_No) {
        this.vehicle_Registration_No = vehicle_Registration_No;
    }

    public String getInitiated_by() {
        return initiated_by;
    }

    public void setInitiated_by(String initiated_by) {
        this.initiated_by = initiated_by;
    }

    public String getInitiator_name() {
        return initiator_name;
    }

    public void setInitiator_name(String initiator_name) {
        this.initiator_name = initiator_name;
    }

    public String getDefect_Issues() {
        return defect_Issues;
    }

    public void setDefect_Issues(String defect_Issues) {
        this.defect_Issues = defect_Issues;
    }

    public String getDate_Posted() {
        return date_Posted;
    }

    public void setDate_Posted(String date_Posted) {
        this.date_Posted = date_Posted;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    public RepairOrder(String repair_order_number, String vehicle_id, String vehicle_Registration_No, String initiated_by, String initiator_name, String defect_Issues, String date_Posted, String date_updated) {
        this.repair_order_number = repair_order_number;
        this.vehicle_id = vehicle_id;
        this.vehicle_Registration_No = vehicle_Registration_No;
        this.initiated_by = initiated_by;
        this.initiator_name = initiator_name;
        this.defect_Issues = defect_Issues;
        this.date_Posted = date_Posted;
        this.date_updated = date_updated;
    }

    protected RepairOrder(Parcel in) {
        repair_order_number = in.readString();
        vehicle_id = in.readString();
        vehicle_Registration_No = in.readString();
        initiated_by = in.readString();
        initiator_name = in.readString();
        defect_Issues = in.readString();
        date_Posted = in.readString();
        date_updated = in.readString();

    }

    public static final Creator<RepairOrder> CREATOR = new Creator<RepairOrder>() {
        @Override
        public RepairOrder createFromParcel(Parcel in) {
            return new RepairOrder(in);
        }

        @Override
        public RepairOrder[] newArray(int size) {
            return new RepairOrder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(repair_order_number);
        dest.writeString(vehicle_id);
        dest.writeString(vehicle_Registration_No);
        dest.writeString(initiated_by);
        dest.writeString(initiator_name);
        dest.writeString(defect_Issues);
        dest.writeString(date_Posted);
        dest.writeString(date_updated);
    }
}
