package com.futureapp.mr_cv.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonalInfo_2_Model implements Parcelable {

    String title;
    String value;
    String tag;
    String clickable;

    public PersonalInfo_2_Model() {

    }

    protected PersonalInfo_2_Model(Parcel in) {
        title = in.readString();
        value = in.readString();
        tag = in.readString();
        clickable = in.readString();
    }

    public static final Creator<PersonalInfo_2_Model> CREATOR = new Creator<PersonalInfo_2_Model>() {
        @Override
        public PersonalInfo_2_Model createFromParcel(Parcel in) {
            return new PersonalInfo_2_Model(in);
        }

        @Override
        public PersonalInfo_2_Model[] newArray(int size) {
            return new PersonalInfo_2_Model[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getClickable() {
        return clickable;
    }

    public void setClickable(String clickable) {
        this.clickable = clickable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(value);
        parcel.writeString(tag);
        parcel.writeString(clickable);
    }
}
