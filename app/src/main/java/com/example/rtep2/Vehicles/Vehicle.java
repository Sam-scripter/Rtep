package com.example.rtep2.Vehicles;

import android.os.Parcel;
import android.os.Parcelable;

public class Vehicle implements Parcelable {
    private String vehicle_id;
    private String license_no;
    private String manufacturer;
    private String model;

    private String engine_no;
    private String chassis_no;
    private String driver_id;
    private String transmission;
    private String fuel_type;

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public void setLicense_no(String license_no) {
        this.license_no = license_no;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine_no() {
        return engine_no;
    }

    public void setEngine_no(String engine_no) {
        this.engine_no = engine_no;
    }

    public String getChassis_no() {
        return chassis_no;
    }

    public void setChassis_no(String chassis_no) {
        this.chassis_no = chassis_no;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public void setCurrent_mileage(String current_mileage) {
        this.current_mileage = current_mileage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDistance_covered(String distance_covered) {
        this.distance_covered = distance_covered;
    }

    public void setFuel_consumed(String fuel_consumed) {
        this.fuel_consumed = fuel_consumed;
    }

    public void setFuel_consumed_cost(String fuel_consumed_cost) {
        this.fuel_consumed_cost = fuel_consumed_cost;
    }

    public void setTotal_repairs(String total_repairs) {
        this.total_repairs = total_repairs;
    }

    public void setTotal_repair_cost(String total_repair_cost) {
        this.total_repair_cost = total_repair_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private String vehicle_type;
    private String current_mileage;
    private String description;
    private String distance_covered;
    private String fuel_consumed;
    private String fuel_consumed_cost;
    private String total_repairs;
    private String total_repair_cost;
    private String total_cost;
    private String date_created;
    private String department;

    public Vehicle(String vehicle_id, String license_no, String manufacturer, String model, String vehicle_type, String current_mileage, String description, String distance_covered, String fuel_consumed, String fuel_consumed_cost, String total_repairs, String total_repair_cost, String total_cost, String date_created, String department) {
        this.vehicle_id = vehicle_id;
        this.license_no = license_no;
        this.manufacturer = manufacturer;
        this.model = model;

        this.engine_no = engine_no;
        this.chassis_no = chassis_no;
        this.driver_id = driver_id;
        this.transmission = transmission;
        this.fuel_type = fuel_type;

        this.vehicle_type = vehicle_type;
        this.current_mileage = current_mileage;
        this.description = description;
        this.distance_covered = distance_covered;
        this.fuel_consumed = fuel_consumed;
        this.fuel_consumed_cost = fuel_consumed_cost;
        this.total_repairs = total_repairs;
        this.total_repair_cost = total_repair_cost;
        this.total_cost = total_cost;
        this.date_created = date_created;
        this.department = department;
    }

    protected Vehicle(Parcel in) {
        vehicle_id = in.readString();
        license_no = in.readString();
        manufacturer = in.readString();
        model = in.readString();

        engine_no = in.readString();
        chassis_no = in.readString();
        driver_id = in.readString();
        transmission = in.readString();
        fuel_type = in.readString();

        vehicle_type = in.readString();
        current_mileage = in.readString();
        description = in.readString();
        distance_covered = in.readString();
        fuel_consumed = in.readString();
        fuel_consumed_cost = in.readString();
        total_repairs = in.readString();
        total_repair_cost = in.readString();
        total_cost = in.readString();
        date_created = in.readString();
        department = in.readString();
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public String getVehicle_id() {
        return vehicle_id;
    }

    public String getLicense_no() {
        return license_no;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public String getCurrent_mileage() {
        return current_mileage;
    }

    public String getDescription() {
        return description;
    }

    public String getDistance_covered() {
        return distance_covered;
    }

    public String getFuel_consumed() {
        return fuel_consumed;
    }

    public String getFuel_consumed_cost() {
        return fuel_consumed_cost;
    }

    public String getTotal_repairs() {
        return total_repairs;
    }

    public String getTotal_repair_cost() {
        return total_repair_cost;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vehicle_id);
        dest.writeString(license_no);
        dest.writeString(manufacturer);
        dest.writeString(model);

        dest.writeString(engine_no);
        dest.writeString(chassis_no);
        dest.writeString(driver_id);
        dest.writeString(transmission);
        dest.writeString(fuel_type);

        dest.writeString(vehicle_type);
        dest.writeString(current_mileage);
        dest.writeString(description);
        dest.writeString(distance_covered);
        dest.writeString(fuel_consumed);
        dest.writeString(fuel_consumed_cost);
        dest.writeString(total_repairs);
        dest.writeString(total_repair_cost);
        dest.writeString(total_cost);
        dest.writeString(date_created);
        dest.writeString(department);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}


