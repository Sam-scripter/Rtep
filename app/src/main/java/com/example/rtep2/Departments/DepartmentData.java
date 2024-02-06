package com.example.rtep2.Departments;

import android.os.Parcel;
import android.os.Parcelable;

public class DepartmentData implements Parcelable {
    private DepartmentClass department;

    public DepartmentData() {

    }

    public DepartmentClass getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentClass department) {
        this.department = department;
    }

    public DepartmentData(DepartmentClass department) {
        this.department = department;
    }

    protected DepartmentData(Parcel in) {
        department = in.readParcelable(DepartmentClass.class.getClassLoader());
    }

    public static final Creator<DepartmentData> CREATOR = new Creator<DepartmentData>() {
        @Override
        public DepartmentData createFromParcel(Parcel in) {
            return new DepartmentData(in);
        }

        @Override
        public DepartmentData[] newArray(int size) {
            return new DepartmentData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(department, flags);
    }

    public static class DepartmentClass implements Parcelable {
        private String department_name;
        private String department_id;
        private String department_full_name;

        public DepartmentClass(String department_name, String department_id, String department_full_name) {
            this.department_name = department_name;
            this.department_id = department_id;
            this.department_full_name = department_full_name;
        }

        protected DepartmentClass(Parcel in) {
            department_name = in.readString();
            department_id = in.readString();
            department_full_name = in.readString();
        }

        public static final Creator<DepartmentClass> CREATOR = new Creator<DepartmentClass>() {
            @Override
            public DepartmentClass createFromParcel(Parcel in) {
                return new DepartmentClass(in);
            }

            @Override
            public DepartmentClass[] newArray(int size) {
                return new DepartmentClass[size];
            }
        };

        public DepartmentClass() {

        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public String getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(String department_id) {
            this.department_id = department_id;
        }

        public String getDepartment_full_name() {
            return department_full_name;
        }

        public void setDepartment_full_name(String department_full_name) {
            this.department_full_name = department_full_name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(department_name);
            dest.writeString(department_id);
            dest.writeString(department_full_name);
        }
    }
}
