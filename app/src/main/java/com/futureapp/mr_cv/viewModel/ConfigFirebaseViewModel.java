package com.futureapp.mr_cv.viewModel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.models.CompaniesModel;
import com.futureapp.mr_cv.models.ConfigFirebaseModel;
import com.futureapp.mr_cv.models.EducationModel;
import com.futureapp.mr_cv.models.ImagesOfProjectsModel;
import com.futureapp.mr_cv.models.PersonalInfoModel;
import com.futureapp.mr_cv.models.PersonalInfo_2_Model;
import com.futureapp.mr_cv.models.ProjectsModel;
import com.futureapp.mr_cv.models.SkillsModel;
import com.futureapp.mr_cv.util.Global;
import com.futureapp.mr_cv.util.TinyDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ConfigFirebaseViewModel extends AndroidViewModel {

    public MutableLiveData<ConfigFirebaseModel> configFirebaseModelMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> showProgressDialogMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> showToastMutableLiveData = new MutableLiveData<>();

    FirebaseRemoteConfig mFirebaseRemoteConfig;

    ConfigFirebaseModel configFirebaseModel;

    Activity activity;

    Context context;
    TinyDB tinyDB;

    public ConfigFirebaseViewModel(@NonNull Application application) {
        super(application);

        this.context = application.getApplicationContext();
        tinyDB = new TinyDB(this.context);
    }

    public void getData(Activity activity) {

        if (Global.isNetworkAvailable(context)) {

            showProgressDialogMutableLiveData.setValue(true);

            this.activity = activity;

            configFirebase();


        } else {

            showToastMutableLiveData.setValue(context.getResources().getString(R.string.validationInternetConnection));
        }


    }

    private void configFirebase() {

        // Get Remote Config instance.
        // [START get_remote_config_instance]
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        // [END get_remote_config_instance]

        // Create a Remote Config Setting to enable developer mode, which you can use to increase
        // the number of fetches available per hour during development. Also use Remote Config
        // Setting to set the minimum fetch interval.
        // [START enable_dev_mode]
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        // [END enable_dev_mode]

        // Set default Remote Config parameter values. An app uses the in-app default values, and
        // when you need to adjust those defaults, you set an updated value for only the values you
        // want to change in the Firebase console. See Best Practices in the README for more
        // information.
        // [START set_default_values]
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
        // [END set_default_values]

        setDataFromFirebase();
    }

    private void setDataFromFirebase() {

        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(activity, new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {

                        showProgressDialogMutableLiveData.setValue(false);

                        String cv_app_playstore_url = mFirebaseRemoteConfig.getInstance().getString("cv_app_playstore_url");
                        String experience = mFirebaseRemoteConfig.getInstance().getString("experience");
                        String purpose = mFirebaseRemoteConfig.getInstance().getString("purpose");
                        PersonalInfoModel personalInfoModel = personalInfoParsing(mFirebaseRemoteConfig.getInstance().getString("personal_info"));
                        ArrayList<PersonalInfo_2_Model> personalInfo_2_models = personalInfo_2_Parsing(mFirebaseRemoteConfig.getInstance().getString("personal_info_2"));
                        ArrayList<SkillsModel> skillsModels = skillsParsing(mFirebaseRemoteConfig.getInstance().getString("skills"));
                        ArrayList<EducationModel> educationModel = educationParsing(mFirebaseRemoteConfig.getInstance().getString("education"));
                        ArrayList<CompaniesModel> companiesModel = companiesParsing(mFirebaseRemoteConfig.getInstance().getString("companies"));
                        ArrayList<ProjectsModel> projectsModel = projectsParsing(mFirebaseRemoteConfig.getInstance().getString("projects"));

                        configFirebaseModel = new ConfigFirebaseModel();

                        configFirebaseModel.setCv_app_playstore_url(cv_app_playstore_url);
                        configFirebaseModel.setExperience(experience);
                        configFirebaseModel.setPurpose(purpose);
                        configFirebaseModel.setPersonalInfoModel(personalInfoModel);
                        configFirebaseModel.setPersonalInfo_2_models(personalInfo_2_models);
                        configFirebaseModel.setSkillsModel(skillsModels);
                        configFirebaseModel.setEducationModel(educationModel);
                        configFirebaseModel.setCompaniesModel(companiesModel);
                        configFirebaseModel.setProjectsModel(projectsModel);

                        configFirebaseModelMutableLiveData.setValue(configFirebaseModel);

                    }
                });
    }

    private PersonalInfoModel personalInfoParsing(String response) {
        PersonalInfoModel personalInfoModel = new PersonalInfoModel();

        try {

            JSONObject jsonObject = new JSONObject(response);


            String profile_pic = jsonObject.getString("profile_pic");
            String name = jsonObject.getString("name");
            String job_title = jsonObject.getString("job_title");
            String dath_of_birth = jsonObject.getString("dath_of_birth");
            String mobile = jsonObject.getString("mobile");
            String email = jsonObject.getString("email");

            personalInfoModel.setProfile_pic(profile_pic);
            personalInfoModel.setName(name);
            personalInfoModel.setJob_title(job_title);
            personalInfoModel.setDath_of_birth(dath_of_birth);
            personalInfoModel.setMobile(mobile);
            personalInfoModel.setEmail(email);

            return personalInfoModel;

        } catch (JSONException e) {
            showToastMutableLiveData.setValue(context.getResources().getString(R.string.serverError));
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<PersonalInfo_2_Model> personalInfo_2_Parsing(String response) {
        ArrayList<PersonalInfo_2_Model> personalInfo_2_models = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(response);


            JSONArray info = jsonObject.getJSONArray("info");

            if (info.length() > 0) {

                for (int i = 0; i < info.length(); i++) {

                    JSONObject jsonObjectTemp = info.getJSONObject(i);

                    String title = jsonObjectTemp.getString("title");
                    String value = jsonObjectTemp.getString("value");
                    String tag = jsonObjectTemp.getString("tag");
                    String clickable = jsonObjectTemp.getString("clickable");

                    PersonalInfo_2_Model personalInfo_2_model = new PersonalInfo_2_Model();

                    personalInfo_2_model.setTitle(title);
                    personalInfo_2_model.setValue(value);
                    personalInfo_2_model.setTag(tag);
                    personalInfo_2_model.setClickable(clickable);

                    personalInfo_2_models.add(personalInfo_2_model);


                }

            }

            return personalInfo_2_models;

        } catch (JSONException e) {
            showToastMutableLiveData.setValue(context.getResources().getString(R.string.serverError));
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<SkillsModel> skillsParsing(String response) {
        ArrayList<SkillsModel> skillsModels = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(response);


            JSONArray skills = jsonObject.getJSONArray("skills");

            if (skills.length() > 0) {

                for (int i = 0; i < skills.length(); i++) {

                    JSONObject jsonObjectTemp = skills.getJSONObject(i);

                    String statment = jsonObjectTemp.getString("statment");

                    SkillsModel skillsModel = new SkillsModel();

                    skillsModel.setStatment(statment);

                    skillsModels.add(skillsModel);


                }

            }

            return skillsModels;

        } catch (JSONException e) {
            showToastMutableLiveData.setValue(context.getResources().getString(R.string.serverError));
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<EducationModel> educationParsing(String response) {
        ArrayList<EducationModel> educationModels = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(response);


            JSONArray education = jsonObject.getJSONArray("education");

            if (education.length() > 0) {

                for (int i = 0; i < education.length(); i++) {

                    JSONObject jsonObjectTemp = education.getJSONObject(i);

                    String statment = jsonObjectTemp.getString("statment");

                    EducationModel educationModel = new EducationModel();

                    educationModel.setStatment(statment);

                    educationModels.add(educationModel);


                }

            }

            return educationModels;

        } catch (JSONException e) {
            showToastMutableLiveData.setValue(context.getResources().getString(R.string.serverError));
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<CompaniesModel> companiesParsing(String response) {
        ArrayList<CompaniesModel> companiesModels = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(response);


            JSONArray companiesJsonObject = jsonObject.getJSONArray("companies");

            if (companiesJsonObject.length() > 0) {

                for (int i = 0; i < companiesJsonObject.length(); i++) {

                    JSONObject jsonObjectTemp = companiesJsonObject.getJSONObject(i);

                    String company_name = jsonObjectTemp.getString("company_name");
                    String description = jsonObjectTemp.getString("description");
                    String company_website = jsonObjectTemp.getString("company_website");
                    String year = jsonObjectTemp.getString("year");

                    CompaniesModel companiesModel = new CompaniesModel();

                    companiesModel.setCompany_name(company_name);
                    companiesModel.setDescription(description);
                    companiesModel.setCompany_website(company_website);
                    companiesModel.setYear(year);

                    companiesModels.add(companiesModel);


                }

            }

            return companiesModels;

        } catch (JSONException e) {
            showToastMutableLiveData.setValue(context.getResources().getString(R.string.serverError));
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<ProjectsModel> projectsParsing(String response) {
        ArrayList<ProjectsModel> projectsModels = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(response);


            JSONArray projects = jsonObject.getJSONArray("projects");

            if (projects.length() > 0) {

                for (int i = 0; i < projects.length(); i++) {

                    JSONObject jsonObjectTemp = projects.getJSONObject(i);

                    String project_name = jsonObjectTemp.getString("project_name");
                    String description = jsonObjectTemp.getString("description");
                    String playstore_link = jsonObjectTemp.getString("playstore_link");
                    String app_icon = jsonObjectTemp.getString("app_icon");

                    ProjectsModel projectsModel = new ProjectsModel();

                    projectsModel.setProject_name(project_name);
                    projectsModel.setDescription(description);
                    projectsModel.setPlaystore_link(playstore_link);
                    projectsModel.setApp_icon(app_icon);


                    JSONArray images = jsonObjectTemp.getJSONArray("images");

                    ArrayList<ImagesOfProjectsModel> imagesOfProjectsModels = new ArrayList<>();

                    if (images.length() > 0) {

                        for (int y = 0; y < images.length(); y++) {

                            JSONObject imagesJSONObject = images.getJSONObject(y);

                            String image = imagesJSONObject.getString("image");

                            ImagesOfProjectsModel imagesOfProjectsModel = new ImagesOfProjectsModel();

                            imagesOfProjectsModel.setImage(image);

                            imagesOfProjectsModels.add(imagesOfProjectsModel);


                        }

                        projectsModel.setImagesOfProjectsModel(imagesOfProjectsModels);


                    }

                    projectsModels.add(projectsModel);

                }

            }

            return projectsModels;

        } catch (JSONException e) {
            showToastMutableLiveData.setValue(context.getResources().getString(R.string.serverError));
            e.printStackTrace();
            return null;
        }
    }
}
