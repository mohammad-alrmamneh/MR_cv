package com.futureapp.mr_cv.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TechnologiesModel implements Parcelable {

    String statment;
    String rate;

    public TechnologiesModel() {

    }

    protected TechnologiesModel(Parcel in) {
        statment = in.readString();
        rate = in.readString();
    }

    public static final Creator<TechnologiesModel> CREATOR = new Creator<TechnologiesModel>() {
        @Override
        public TechnologiesModel createFromParcel(Parcel in) {
            return new TechnologiesModel(in);
        }

        @Override
        public TechnologiesModel[] newArray(int size) {
            return new TechnologiesModel[size];
        }
    };

    public String getStatment() {
        return statment;
    }

    public void setStatment(String statment) {
        this.statment = statment;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(statment);
        parcel.writeString(rate);
    }
}
