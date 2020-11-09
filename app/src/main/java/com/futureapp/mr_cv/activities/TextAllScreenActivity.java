package com.futureapp.mr_cv.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.databinding.ActivityTextAllScreenBinding;
import com.futureapp.mr_cv.models.TextAllScreenModel;
import com.futureapp.mr_cv.util.Constants;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class TextAllScreenActivity extends AppCompatActivity {

    private ActivityTextAllScreenBinding binding;

    TextAllScreenModel textAllScreenModel;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextAllScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getExtras();
        setData();

    }

    private void setData() {

        if (textAllScreenModel != null) {

            if (!textAllScreenModel.getImage().equalsIgnoreCase("")) {
                Glide
                        .with(TextAllScreenActivity.this)
                        .load(textAllScreenModel.getImage())
                        .placeholder(R.color.main_app_black_2)
                        .error(R.color.main_app_black_2)
                        .into(binding.imageIv);
            } else {
                binding.imageIv.setVisibility(View.GONE);
            }

            binding.titleTv.setText(textAllScreenModel.getTitle());
            binding.descriptionTv.setText(textAllScreenModel.getDescription());

        }
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            textAllScreenModel = extras.getParcelable(Constants.PutExtra_Keys.textAllScreen);
        }

    }
}
