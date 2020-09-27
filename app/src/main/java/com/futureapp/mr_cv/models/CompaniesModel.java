package com.futureapp.mr_cv.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CompaniesModel implements Parcelable {

    String company_name;
    String description;
    String company_website;
    String year;

    public CompaniesModel() {

    }


    protected CompaniesModel(Parcel in) {
        company_name = in.readString();
        description = in.readString();
        company_website = in.readString();
        year = in.readString();
    }

    public static final Creator<CompaniesModel> CREATOR = new Creator<CompaniesModel>() {
        @Override
        public CompaniesModel createFromParcel(Parcel in) {
            return new CompaniesModel(in);
        }

        @Override
        public CompaniesModel[] newArray(int size) {
            return new CompaniesModel[size];
        }
    };

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany_website() {
        return company_website;
    }

    public void setCompany_website(String company_website) {
        this.company_website = company_website;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(company_name);
        parcel.writeString(description);
        parcel.writeString(company_website);
        parcel.writeString(year);
    }
}
