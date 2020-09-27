package com.futureapp.mr_cv.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ConfigFirebaseModel implements Parcelable {

    String cv_app_playstore_url;
    String purpose;
    String experience;
    PersonalInfoModel personalInfoModel;
    ArrayList<SkillsModel> skillsModel;
    ArrayList<EducationModel> educationModel;
    ArrayList<CompaniesModel> companiesModel;
    ArrayList<ProjectsModel> projectsModel;

    public ConfigFirebaseModel() {

    }


    protected ConfigFirebaseModel(Parcel in) {
        cv_app_playstore_url = in.readString();
        purpose = in.readString();
        experience = in.readString();
        personalInfoModel = in.readParcelable(PersonalInfoModel.class.getClassLoader());
        skillsModel = in.createTypedArrayList(SkillsModel.CREATOR);
        educationModel = in.createTypedArrayList(EducationModel.CREATOR);
        companiesModel = in.createTypedArrayList(CompaniesModel.CREATOR);
        projectsModel = in.createTypedArrayList(ProjectsModel.CREATOR);
    }

    public static final Creator<ConfigFirebaseModel> CREATOR = new Creator<ConfigFirebaseModel>() {
        @Override
        public ConfigFirebaseModel createFromParcel(Parcel in) {
            return new ConfigFirebaseModel(in);
        }

        @Override
        public ConfigFirebaseModel[] newArray(int size) {
            return new ConfigFirebaseModel[size];
        }
    };

    public String getCv_app_playstore_url() {
        return cv_app_playstore_url;
    }

    public void setCv_app_playstore_url(String cv_app_playstore_url) {
        this.cv_app_playstore_url = cv_app_playstore_url;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public PersonalInfoModel getPersonalInfoModel() {
        return personalInfoModel;
    }

    public void setPersonalInfoModel(PersonalInfoModel personalInfoModel) {
        this.personalInfoModel = personalInfoModel;
    }

    public ArrayList<SkillsModel> getSkillsModel() {
        return skillsModel;
    }

    public void setSkillsModel(ArrayList<SkillsModel> skillsModel) {
        this.skillsModel = skillsModel;
    }

    public ArrayList<EducationModel> getEducationModel() {
        return educationModel;
    }

    public void setEducationModel(ArrayList<EducationModel> educationModel) {
        this.educationModel = educationModel;
    }

    public ArrayList<CompaniesModel> getCompaniesModel() {
        return companiesModel;
    }

    public void setCompaniesModel(ArrayList<CompaniesModel> companiesModel) {
        this.companiesModel = companiesModel;
    }

    public ArrayList<ProjectsModel> getProjectsModel() {
        return projectsModel;
    }

    public void setProjectsModel(ArrayList<ProjectsModel> projectsModel) {
        this.projectsModel = projectsModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cv_app_playstore_url);
        parcel.writeString(purpose);
        parcel.writeString(experience);
        parcel.writeParcelable(personalInfoModel, i);
        parcel.writeTypedList(skillsModel);
        parcel.writeTypedList(educationModel);
        parcel.writeTypedList(companiesModel);
        parcel.writeTypedList(projectsModel);
    }
}
