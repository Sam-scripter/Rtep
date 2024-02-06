package com.example.rtep2.Fuel_Stations;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Fuel_station implements Parcelable {
    private String station_Id;
    private String fuel_station_pic;
    private String fuel_Station_Name;
    private String location;
    private String description;
    private String a_Rations_Diesel;
    private String a_Rations_Unit_Price;
    private String b_Rations_Petrol;
    private String b_Rations_Unit_Price;
    private String Total_litres_fuel;
    private String litres_oil;
    private String otometer_Reading_Auto_Created;
    private String otometer_Reading_By_Officer;
    private String date_created;
    private String date_updated;

    public String getStation_Id() {
        return station_Id;
    }

    public void setStation_Id(String station_Id) {
        this.station_Id = station_Id;
    }

    public String getFuel_station_pic() {
        return fuel_station_pic;
    }

    public void setFuel_station_pic(String fuel_station_pic) {
        this.fuel_station_pic = fuel_station_pic;
    }

    public String getFuel_Station_Name() {
        return fuel_Station_Name;
    }

    public void setFuel_Station_Name(String fuel_Station_Name) {
        this.fuel_Station_Name = fuel_Station_Name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getA_Rations_Diesel() {
        return a_Rations_Diesel;
    }

    public void setA_Rations_Diesel(String a_Rations_Diesel) {
        this.a_Rations_Diesel = a_Rations_Diesel;
    }

    public String getA_Rations_Unit_Price() {
        return a_Rations_Unit_Price;
    }

    public void setA_Rations_Unit_Price(String a_Rations_Unit_Price) {
        this.a_Rations_Unit_Price = a_Rations_Unit_Price;
    }

    public String getB_Rations_Petrol() {
        return b_Rations_Petrol;
    }

    public void setB_Rations_Petrol(String b_Rations_Petrol) {
        this.b_Rations_Petrol = b_Rations_Petrol;
    }

    public String getB_Rations_Unit_Price() {
        return b_Rations_Unit_Price;
    }

    public void setB_Rations_Unit_Price(String b_Rations_Unit_Price) {
        this.b_Rations_Unit_Price = b_Rations_Unit_Price;
    }

    public String getTotal_litres_fuel() {
        return Total_litres_fuel;
    }

    public void setTotal_litres_fuel(String total_litres_fuel) {
        Total_litres_fuel = total_litres_fuel;
    }

    public String getLitres_oil() {
        return litres_oil;
    }

    public void setLitres_oil(String litres_oil) {
        this.litres_oil = litres_oil;
    }

    public String getOtometer_Reading_Auto_Created() {
        return otometer_Reading_Auto_Created;
    }

    public void setOtometer_Reading_Auto_Created(String otometer_Reading_Auto_Created) {
        this.otometer_Reading_Auto_Created = otometer_Reading_Auto_Created;
    }

    public String getOtometer_Reading_By_Officer() {
        return otometer_Reading_By_Officer;
    }

    public void setOtometer_Reading_By_Officer(String otometer_Reading_By_Officer) {
        this.otometer_Reading_By_Officer = otometer_Reading_By_Officer;
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

    public Fuel_station(String station_Id, String fuel_station_pic, String fuel_Station_Name, String location, String description, String a_Rations_Diesel, String a_Rations_Unit_Price, String b_Rations_Petrol, String b_Rations_Unit_Price, String total_litres_fuel, String litres_oil, String otometer_Reading_Auto_Created, String otometer_Reading_By_Officer, String date_created, String date_updated) {
        this.station_Id = station_Id;
        this.fuel_station_pic = fuel_station_pic;
        this.fuel_Station_Name = fuel_Station_Name;
        this.location = location;
        this.description = description;
        this.a_Rations_Diesel = a_Rations_Diesel;
        this.a_Rations_Unit_Price = a_Rations_Unit_Price;
        this.b_Rations_Petrol = b_Rations_Petrol;
        this.b_Rations_Unit_Price = b_Rations_Unit_Price;
        this.Total_litres_fuel = total_litres_fuel;
        this.litres_oil = litres_oil;
        this.otometer_Reading_Auto_Created = otometer_Reading_Auto_Created;
        this.otometer_Reading_By_Officer = otometer_Reading_By_Officer;
        this.date_created = date_created;
        this.date_updated = date_updated;
    }

    protected Fuel_station(Parcel in) {
        station_Id = in.readString();
        fuel_station_pic = in.readString();
        fuel_Station_Name = in.readString();
        location = in.readString();
        description = in.readString();
        a_Rations_Diesel = in.readString();
        a_Rations_Unit_Price = in.readString();
        b_Rations_Petrol = in.readString();
        b_Rations_Unit_Price = in.readString();
        Total_litres_fuel = in.readString();
        litres_oil = in.readString();
        otometer_Reading_Auto_Created = in.readString();
        otometer_Reading_By_Officer = in.readString();
        date_created = in.readString();
        date_updated = in.readString();

    }

    public static final Creator<Fuel_station> CREATOR = new Creator<Fuel_station>() {
        @Override
        public Fuel_station createFromParcel(Parcel in) {
            return new Fuel_station(in);
        }

        @Override
        public Fuel_station[] newArray(int size) {
            return new Fuel_station[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(station_Id);
        dest.writeString(fuel_station_pic);
        dest.writeString(fuel_Station_Name);
        dest.writeString(location);
        dest.writeString(description);
        dest.writeString(a_Rations_Diesel);
        dest.writeString(a_Rations_Unit_Price);
        dest.writeString(b_Rations_Petrol);
        dest.writeString(b_Rations_Unit_Price);
        dest.writeString(Total_litres_fuel);
        dest.writeString(litres_oil);
        dest.writeString(otometer_Reading_Auto_Created);
        dest.writeString(otometer_Reading_By_Officer);
        dest.writeString(date_created);
        dest.writeString(date_updated);
    }
}
