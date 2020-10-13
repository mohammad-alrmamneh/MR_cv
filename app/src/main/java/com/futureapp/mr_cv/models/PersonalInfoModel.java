package com.futureapp.mr_cv.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonalInfoModel implements Parcelable {

    String profile_pic;
    String graduate_pic;
    String name;
    String job_title;
    String dath_of_birth;
    String mobile;
    String email;

    public PersonalInfoModel() {

    }

    protected PersonalInfoModel(Parcel in) {
        profile_pic = in.readString();
        graduate_pic = in.readString();
        name = in.readString();
        job_title = in.readString();
        dath_of_birth = in.readString();
        mobile = in.readString();
        email = in.readString();
    }

    public static final Creator<PersonalInfoModel> CREATOR = new Creator<PersonalInfoModel>() {
        @Override
        public PersonalInfoModel createFromParcel(Parcel in) {
            return new PersonalInfoModel(in);
        }

        @Override
        public PersonalInfoModel[] newArray(int size) {
            return new PersonalInfoModel[size];
        }
    };

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getGraduate_pic() {
        return graduate_pic;
    }

    public void setGraduate_pic(String graduate_pic) {
        this.graduate_pic = graduate_pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getDath_of_birth() {
        return dath_of_birth;
    }

    public void setDath_of_birth(String dath_of_birth) {
        this.dath_of_birth = dath_of_birth;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(profile_pic);
        parcel.writeString(graduate_pic);
        parcel.writeString(name);
        parcel.writeString(job_title);
        parcel.writeString(dath_of_birth);
        parcel.writeString(mobile);
        parcel.writeString(email);
    }
}
