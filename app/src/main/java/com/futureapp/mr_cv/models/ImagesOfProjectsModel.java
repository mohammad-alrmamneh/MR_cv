package com.futureapp.mr_cv.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ImagesOfProjectsModel implements Parcelable {

    String image;

    public ImagesOfProjectsModel() {

    }

    protected ImagesOfProjectsModel(Parcel in) {
        image = in.readString();
    }

    public static final Creator<ImagesOfProjectsModel> CREATOR = new Creator<ImagesOfProjectsModel>() {
        @Override
        public ImagesOfProjectsModel createFromParcel(Parcel in) {
            return new ImagesOfProjectsModel(in);
        }

        @Override
        public ImagesOfProjectsModel[] newArray(int size) {
            return new ImagesOfProjectsModel[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
    }
}
