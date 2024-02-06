package com.example.rtep2.Detail_orders;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class Detail_order implements Parcelable {
    private String detail_order_no;
    private String to;
    private String order_Status;
    private String order_For;
    private String order_Description;
    private String lPO_No_Current_Month;
    private String vehicle_Id;
    private String license_No;
    private String a_Rations_Diesel;
    private String b_Rations_Petrol;
    private String litres_fuel;
    private String litres_oil;
    private String date_posted;
    private String date_updated;

    public Detail_order(String detail_order_no, String to, String order_Status, String order_For, String order_Description, String IPO_No_Current_Month, String vehicle_Id, String license_No, String a_Rations_Diesel, String b_Rations_Petrol, String litres_fuel, String litres_oil, String date_posted, String date_updated) {
        this.detail_order_no = detail_order_no;
        this.to = to;
        this.order_Status = order_Status;
        this.order_For = order_For;
        this.order_Description = order_Description;
        this.lPO_No_Current_Month = lPO_No_Current_Month;
        this.vehicle_Id = vehicle_Id;
        this.license_No = license_No;
        this.a_Rations_Diesel = a_Rations_Diesel;
        this.b_Rations_Petrol = b_Rations_Petrol;
        this.litres_fuel = litres_fuel;
        this.litres_oil = litres_oil;
        this.date_posted = date_posted;
        this.date_updated = date_updated;
    }

    public String getDetail_order_no() {
        return detail_order_no;
    }

    public void setDetail_order_no(String detail_order_no) {
        this.detail_order_no = detail_order_no;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getOrder_Status() {
        return order_Status;
    }

    public void setOrder_Status(String order_Status) {
        this.order_Status = order_Status;
    }

    public String getOrder_For() {
        return order_For;
    }

    public void setOrder_For(String order_For) {
        this.order_For = order_For;
    }

    public String getOrder_Description() {
        return order_Description;
    }

    public void setOrder_Description(String order_Description) {
        this.order_Description = order_Description;
    }

    public String getlPO_No_Current_Month() {
        return lPO_No_Current_Month;
    }

    public void setlPO_No_Current_Month(String lPO_No_Current_Month) {
        this.lPO_No_Current_Month = lPO_No_Current_Month;
    }

    public String getVehicle_Id() {
        return vehicle_Id;
    }

    public void setVehicle_Id(String vehicle_Id) {
        this.vehicle_Id = vehicle_Id;
    }

    public String getLicense_No() {
        return license_No;
    }

    public void setLicense_No(String license_No) {
        this.license_No = license_No;
    }

    public String getA_Rations_Diesel() {
        return a_Rations_Diesel;
    }

    public void setA_Rations_Diesel(String a_Rations_Diesel) {
        this.a_Rations_Diesel = a_Rations_Diesel;
    }

    public String getB_Rations_Petrol() {
        return b_Rations_Petrol;
    }

    public void setB_Rations_Petrol(String b_Rations_Petrol) {
        this.b_Rations_Petrol = b_Rations_Petrol;
    }

    public String getLitres_fuel() {
        return litres_fuel;
    }

    public void setLitres_fuel(String litres_fuel) {
        this.litres_fuel = litres_fuel;
    }

    public String getLitres_oil() {
        return litres_oil;
    }

    public void setLitres_oil(String litres_oil) {
        this.litres_oil = litres_oil;
    }

    public String getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(String date_posted) {
        this.date_posted = date_posted;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    protected Detail_order(Parcel in) {
        detail_order_no = in.readString();
        to = in.readString();
        order_Status = in.readString();
        order_For = in.readString();
        order_Description = in.readString();
        lPO_No_Current_Month = in.readString();
        vehicle_Id = in.readString();
        license_No = in.readString();
        a_Rations_Diesel = in.readString();
        b_Rations_Petrol = in.readString();
        litres_fuel = in.readString();
        litres_oil = in.readString();
        date_posted = in.readString();
        date_updated = in.readString();
    }

    public static final Creator<Detail_order> CREATOR = new Creator<Detail_order>() {
        @Override
        public Detail_order createFromParcel(Parcel in) {
            return new Detail_order(in);
        }

        @Override
        public Detail_order[] newArray(int size) {
            return new Detail_order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(detail_order_no);
        dest.writeString(to);
        dest.writeString(order_Status);
        dest.writeString(order_For);
        dest.writeString(order_Description);
        dest.writeString(lPO_No_Current_Month);
        dest.writeString(vehicle_Id);
        dest.writeString(license_No);
        dest.writeString(a_Rations_Diesel);
        dest.writeString(b_Rations_Petrol);
        dest.writeString(litres_fuel);
        dest.writeString(litres_oil);
        dest.writeString(date_posted);
        dest.writeString(date_updated);
    }
}
