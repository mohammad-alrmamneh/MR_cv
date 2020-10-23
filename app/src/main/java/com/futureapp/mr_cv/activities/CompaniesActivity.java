package com.futureapp.mr_cv.activities;

import android.content.Context;
import android.os.Bundle;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.models.CompaniesModel;
import com.futureapp.mr_cv.util.Global;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class CompaniesActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);
        ButterKnife.bind(this);

        setData(Global.configFirebaseModel.getCompaniesModel());

    }

    private void setData(ArrayList<CompaniesModel> companiesModels) {

    }

}
