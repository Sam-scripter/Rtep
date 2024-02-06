package com.example.rtep2.Employees;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Employee implements Parcelable {
    private String publicServiceNumber;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String location;
    private String dateOfBirth;
    private String dateOfHire;
    private String email;
    private String designation;
    private String employment_type;
    private String password;
    private String gender;
    private String role;
    private String department;
    private String workStation;

    public String getWorkStation() {
        return workStation;
    }

    public void setWorkStation(String workStation) {
        this.workStation = workStation;
    }

//    public Employee(String publicServiceNumber, String firstName, String lastName, String mobileNumber, String location, String dateOfBirth, String dateOfHire, String email, String designation, String employment_type, String password, String gender, String role, String department, String workStation) {
//        this.publicServiceNumber = publicServiceNumber;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.mobileNumber = mobileNumber;
//        this.location = location;
//        this.dateOfBirth = dateOfBirth;
//        this.dateOfHire = dateOfHire;
//        this.email = email;
//        this.designation = designation;
//        this.employment_type = employment_type;
//        this.password = password;
//        this.gender = gender;
//        this.role = role;
//        this.department = department;
//        this.workStation = workStation;
//    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmployment_type() {
        return employment_type;
    }

    public void setEmployment_type(String employment_type) {
        this.employment_type = employment_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Employee(String publicServiceNumber, String firstName, String lastName, String mobileNumber, String location, String dateOfBirth, String dateOfHire, String email, String designation, String employment_type, String password, String gender, String role, String department) {
        this.publicServiceNumber = publicServiceNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
        this.dateOfHire = dateOfHire;
        this.email = email;
        this.designation = designation;
        this.employment_type = employment_type;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.department = department;
        this.workStation = workStation;
    }

    protected Employee(Parcel in) {
        publicServiceNumber = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        mobileNumber = in.readString();
        location = in.readString();
        dateOfBirth = in.readString();
        dateOfHire = in.readString();
        email = in.readString();
        designation = in.readString();
        employment_type = in.readString();
        password = in.readString();
        gender = in.readString();
        role = in.readString();
        department = in.readString();
        workStation = in.readString();
    }



    public String getPublicServiceNumber() {
        return publicServiceNumber;
    }

    public void setPublicServiceNumber(String publicServiceNumber) {
        this.publicServiceNumber = publicServiceNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String idNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(String dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(publicServiceNumber);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(mobileNumber);
        dest.writeString(location);
        dest.writeString(dateOfBirth);
        dest.writeString(dateOfHire);
        dest.writeString(email);
        dest.writeString(designation);
        dest.writeString(employment_type);
        dest.writeString(password);
        dest.writeString(gender);
        dest.writeString(role);
        dest.writeString(department);
        dest.writeString(workStation);
    }


    ;



}


