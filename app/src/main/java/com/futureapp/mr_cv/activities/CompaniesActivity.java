package com.futureapp.mr_cv.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.adapters.RecycleViewCompaniesAdapter;
import com.futureapp.mr_cv.databinding.ActivityCompaniesBinding;
import com.futureapp.mr_cv.models.CompaniesModel;
import com.futureapp.mr_cv.models.TextAllScreenModel;
import com.futureapp.mr_cv.util.Constants;
import com.futureapp.mr_cv.util.Global;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class CompaniesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivityCompaniesBinding binding;

    RecycleViewCompaniesAdapter recycleViewCompaniesAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompaniesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setData(Global.configFirebaseModel.getCompaniesModel());

    }

    private void setData(ArrayList<CompaniesModel> companiesModels) {

        ArrayList<CompaniesModel> justCompanies = new ArrayList<>();//add just companies without freelance

        for (int i = 0; i < companiesModels.size(); i++) {
            if (!companiesModels.get(i).getCompany_name().equalsIgnoreCase(getResources().getString(R.string.freelance))) {

                justCompanies.add(companiesModels.get(i));

            }
        }

        binding.toolbar.titleTv.setText(getResources().getString(R.string.companies));

        recycleViewCompaniesAdapter = new RecycleViewCompaniesAdapter(this, justCompanies, this);
        binding.listRv.setAdapter(recycleViewCompaniesAdapter);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recycleview_companies_divider_hieght));

        binding.listRv.addItemDecoration(itemDecorator);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this);
        binding.listRv.setLayoutManager(layoutManager);
        binding.listRv.setHasFixedSize(true);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        CompaniesModel companiesModel = Global.configFirebaseModel.getCompaniesModel().get(position);

        int companyWebsite_Tv_ID = R.id.companyWebsite_Tv;

        if (view.getId() == companyWebsite_Tv_ID) {

            Global.openURL(CompaniesActivity.this, companiesModel.getCompany_website());

        } else {

            TextAllScreenModel textAllScreenModel = new TextAllScreenModel();

            textAllScreenModel.setImage("");
            textAllScreenModel.setTitle(companiesModel.getCompany_name());
            textAllScreenModel.setDescription(companiesModel.getDescription());

            Intent i = new Intent(CompaniesActivity.this, TextAllScreenActivity.class);
            i.putExtra(Constants.PutExtra_Keys.textAllScreen, textAllScreenModel);
            startActivity(i);
        }


    }
}
