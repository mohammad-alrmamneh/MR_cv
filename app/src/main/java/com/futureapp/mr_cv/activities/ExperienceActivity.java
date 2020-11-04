package com.futureapp.mr_cv.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.databinding.ActivityExperienceBinding;
import com.futureapp.mr_cv.models.CompaniesModel;
import com.futureapp.mr_cv.models.TechnologiesModel;
import com.futureapp.mr_cv.util.Global;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class ExperienceActivity extends AppCompatActivity {

    private ActivityExperienceBinding binding;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExperienceBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setData(Global.configFirebaseModel.getCompaniesModel(), Global.configFirebaseModel.getTechnologiesModels());

    }

    private void setData(ArrayList<CompaniesModel> companiesModels, ArrayList<TechnologiesModel> technologiesModels) {

        binding.toolbar.titleTv.setText(getResources().getString(R.string.experience));

        String experienceYears = getResources().getString(R.string.experienceFrom) + " " + companiesModels.get(companiesModels.size() - 1).getYear() +
                " " + getResources().getString(R.string.to) + " " + companiesModels.get(0).getYear();

        binding.BarChartViewTitleTv.setText(experienceYears);
        binding.HorizontalBarChartViewTitleTv.setText(getResources().getString(R.string.skillsExperience));

        ArrayList<CompaniesModel> reverseCompanies = new ArrayList<>(companiesModels);

        Collections.reverse(reverseCompanies);

        LinkedHashMap yearsExperience = new LinkedHashMap();

        for (int i = 0; i < reverseCompanies.size(); i++) {

            float percentage = (float) Integer.parseInt(reverseCompanies.get(i).getExperience());

            yearsExperience.put(reverseCompanies.get(i).getYear(), percentage);
        }

        binding.barChart.animate(yearsExperience);

        /////////////////////////////////////////////////////////////

        Collections.shuffle(technologiesModels);

        LinkedHashMap skillsExperience = new LinkedHashMap();

        for (int i = 0; i < technologiesModels.size(); i++) {

            float percentage = (float) Integer.parseInt(technologiesModels.get(i).getRate());

            skillsExperience.put(technologiesModels.get(i).getStatment(), percentage);
        }

        binding.horizontalBarChart.animate(skillsExperience);


    }
}
