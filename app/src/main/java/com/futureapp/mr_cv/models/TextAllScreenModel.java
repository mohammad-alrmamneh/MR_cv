package com.futureapp.mr_cv.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TextAllScreenModel implements Parcelable {

    String image;
    String title;
    String description;

    public TextAllScreenModel() {

    }

    protected TextAllScreenModel(Parcel in) {
        image = in.readString();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<TextAllScreenModel> CREATOR = new Creator<TextAllScreenModel>() {
        @Override
        public TextAllScreenModel createFromParcel(Parcel in) {
            return new TextAllScreenModel(in);
        }

        @Override
        public TextAllScreenModel[] newArray(int size) {
            return new TextAllScreenModel[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(title);
        parcel.writeString(description);
    }
}
