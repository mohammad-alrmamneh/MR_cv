package com.futureapp.mr_cv.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ProjectsModel implements Parcelable {

    String project_name;
    String description;
    String playstore_link;
    String app_icon;
    ArrayList<ImagesOfProjectsModel> imagesOfProjectsModel;

    public ProjectsModel() {

    }

    protected ProjectsModel(Parcel in) {
        project_name = in.readString();
        description = in.readString();
        playstore_link = in.readString();
        app_icon = in.readString();
        imagesOfProjectsModel = in.createTypedArrayList(ImagesOfProjectsModel.CREATOR);
    }

    public static final Creator<ProjectsModel> CREATOR = new Creator<ProjectsModel>() {
        @Override
        public ProjectsModel createFromParcel(Parcel in) {
            return new ProjectsModel(in);
        }

        @Override
        public ProjectsModel[] newArray(int size) {
            return new ProjectsModel[size];
        }
    };

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaystore_link() {
        return playstore_link;
    }

    public void setPlaystore_link(String playstore_link) {
        this.playstore_link = playstore_link;
    }

    public String getApp_icon() {
        return app_icon;
    }

    public void setApp_icon(String app_icon) {
        this.app_icon = app_icon;
    }

    public ArrayList<ImagesOfProjectsModel> getImagesOfProjectsModel() {
        return imagesOfProjectsModel;
    }

    public void setImagesOfProjectsModel(ArrayList<ImagesOfProjectsModel> imagesOfProjectsModel) {
        this.imagesOfProjectsModel = imagesOfProjectsModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(project_name);
        parcel.writeString(description);
        parcel.writeString(playstore_link);
        parcel.writeString(app_icon);
        parcel.writeTypedList(imagesOfProjectsModel);
    }
}
