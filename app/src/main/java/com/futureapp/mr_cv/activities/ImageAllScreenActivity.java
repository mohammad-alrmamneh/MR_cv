package com.futureapp.mr_cv.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.databinding.ActivityImageAllScreenBinding;
import com.futureapp.mr_cv.util.Constants;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class ImageAllScreenActivity extends AppCompatActivity {

    private ActivityImageAllScreenBinding binding;

    String image = "";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageAllScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getExtras();

        setData();

    }

    private void setData() {
        if (!image.equalsIgnoreCase("")) {
            Glide
                    .with(ImageAllScreenActivity.this)
                    .load(image)
                    .placeholder(R.color.gray)
                    .error(R.color.gray)
                    .into(binding.imageview);
        }
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            image = extras.getString(Constants.PutExtra_Keys.image);
        }

    }
}
