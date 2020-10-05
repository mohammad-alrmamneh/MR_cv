package com.futureapp.mr_cv.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.models.ConfigFirebaseModel;
import com.futureapp.mr_cv.util.Constants;
import com.futureapp.mr_cv.util.Global;
import com.futureapp.mr_cv.util.TinyDB;
import com.futureapp.mr_cv.viewModel.ConfigFirebaseViewModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class SplashScreenActivity extends AppCompatActivity {

    ConfigFirebaseViewModel configFirebaseViewModel;

    TinyDB tinydb;
    @BindView(R.id.name_Tv)
    TextView nameTv;
    @BindView(R.id.refresh_here_Tv)
    TextView refreshHereTv;
    @BindView(R.id.internetValidation_Ll)
    LinearLayout internetValidationLl;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        tinydb = new TinyDB(this);

        configFirebaseViewModel = ViewModelProviders.of(this).get(ConfigFirebaseViewModel.class);
        configFirebaseViewModel.showProgressDialogMutableLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if (aBoolean) {
                    Global.progressLoading(SplashScreenActivity.this);
                } else {
                    Global.dismissProgressLoading();
                }

            }
        });

        configFirebaseViewModel.showToastMutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                if (s.equalsIgnoreCase(getResources().getString(R.string.validationInternetConnection))) {
                    internetValidationLl.setVisibility(View.VISIBLE);
                } else {
                    internetValidationLl.setVisibility(View.GONE);
                }

            }
        });

        configFirebaseViewModel.configFirebaseModelMutableLiveData.observe(this, new Observer<ConfigFirebaseModel>() {
            @Override
            public void onChanged(ConfigFirebaseModel configFirebaseModel) {

                if (configFirebaseModel != null) {
                    internetValidationLl.setVisibility(View.GONE);
                    tinydb.putObject(Constants.TinyDB_Keys.configFirebaseList, configFirebaseModel);
                    Global.configFirebaseModel = configFirebaseModel;
                    delay();
                } else {
                    internetValidationLl.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        configFirebaseViewModel.getData(this);
    }

    private void delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {

    }

    @OnClick(R.id.refresh_here_Tv)
    public void onViewClicked() {

        configFirebaseViewModel.getData(this);
    }
}