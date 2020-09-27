package com.futureapp.mr_cv.models;

import android.os.Parcel;
import android.os.Parcelable;

public class EducationModel implements Parcelable {

    String statment;

    public EducationModel() {

    }

    protected EducationModel(Parcel in) {
        statment = in.readString();
    }

    public static final Creator<EducationModel> CREATOR = new Creator<EducationModel>() {
        @Override
        public EducationModel createFromParcel(Parcel in) {
            return new EducationModel(in);
        }

        @Override
        public EducationModel[] newArray(int size) {
            return new EducationModel[size];
        }
    };

    public String getStatment() {
        return statment;
    }

    public void setStatment(String statment) {
        this.statment = statment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(statment);
    }
}
