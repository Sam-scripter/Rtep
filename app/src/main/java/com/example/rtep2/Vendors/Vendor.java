package com.example.rtep2.Vendors;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Vendor implements Parcelable {
    private String vendor_id;
    private String vendor_name;
    private String vendor_contact_info;
    private String a_rations_diesel_units;
    private String a_Rations_Unit_price;
    private String b_rations_petrol_units;
    private String b_Rations_Unit_Price;
    private String total_fuel_supplied;
    private String date_created;
    private String date_updated;

    public static final Creator<Vendor> CREATOR = new Creator<Vendor>() {
        @Override
        public Vendor createFromParcel(Parcel in) {
            return new Vendor(in);
        }

        @Override
        public Vendor[] newArray(int size) {
            return new Vendor[size];
        }
    };

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_contact_info() {
        return vendor_contact_info;
    }

    public void setVendor_contact_info(String vendor_contact_info) {
        this.vendor_contact_info = vendor_contact_info;
    }

    public String getA_rations_diesel_units() {
        return a_rations_diesel_units;
    }

    public void setA_rations_diesel_units(String a_rations_diesel_units) {
        this.a_rations_diesel_units = a_rations_diesel_units;
    }

    public String getA_Rations_Unit_price() {
        return a_Rations_Unit_price;
    }

    public void setA_Rations_Unit_price(String a_Rations_Unit_price) {
        this.a_Rations_Unit_price = a_Rations_Unit_price;
    }

    public String getB_rations_petrol_units() {
        return b_rations_petrol_units;
    }

    public void setB_rations_petrol_units(String b_rations_petrol_units) {
        this.b_rations_petrol_units = b_rations_petrol_units;
    }

    public String getB_Rations_Unit_Price() {
        return b_Rations_Unit_Price;
    }

    public void setB_Rations_Unit_Price(String b_Rations_Unit_Price) {
        this.b_Rations_Unit_Price = b_Rations_Unit_Price;
    }

    public String getTotal_fuel_supplied() {
        return total_fuel_supplied;
    }

    public void setTotal_fuel_supplied(String total_fuel_supplied) {
        this.total_fuel_supplied = total_fuel_supplied;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }


    public Vendor(String vendor_id, String vendor_name, String vendor_contact_info, String a_rations_diesel_units, String a_Rations_Unit_price, String b_rations_petrol_units, String b_Rations_Unit_Price, String total_fuel_supplied, String date_created, String date_updated) {
        this.vendor_id = vendor_id;
        this.vendor_name = vendor_name;
        this.vendor_contact_info = vendor_contact_info;
        this.a_rations_diesel_units = a_rations_diesel_units;
        this.a_Rations_Unit_price = a_Rations_Unit_price;
        this.b_rations_petrol_units = b_rations_petrol_units;
        this.b_Rations_Unit_Price = b_Rations_Unit_Price;
        this.total_fuel_supplied = total_fuel_supplied;
        this.date_created = date_created;
        this.date_updated = date_updated;


    }

    protected Vendor(Parcel in) {
        vendor_name = in.readString();
        vendor_contact_info = in.readString();
        a_rations_diesel_units = in.readString();
        a_Rations_Unit_price = in.readString();
        b_rations_petrol_units = in.readString();
        b_Rations_Unit_Price = in.readString();
        total_fuel_supplied = in.readString();
        date_created = in.readString();
        date_updated = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(vendor_id);
        dest.writeString(vendor_name);
        dest.writeString(vendor_contact_info);
        dest.writeString(a_rations_diesel_units);
        dest.writeString(a_Rations_Unit_price);
        dest.writeString(b_rations_petrol_units);
        dest.writeString(b_Rations_Unit_Price);
        dest.writeString(total_fuel_supplied);
        dest.writeString(date_created);
        dest.writeString(date_updated);
    }
}
