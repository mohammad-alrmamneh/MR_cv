package com.futureapp.mr_cv.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SkillsModel implements Parcelable {

    String statment;

    public SkillsModel() {

    }

    protected SkillsModel(Parcel in) {
        statment = in.readString();
    }

    public static final Creator<SkillsModel> CREATOR = new Creator<SkillsModel>() {
        @Override
        public SkillsModel createFromParcel(Parcel in) {
            return new SkillsModel(in);
        }

        @Override
        public SkillsModel[] newArray(int size) {
            return new SkillsModel[size];
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
