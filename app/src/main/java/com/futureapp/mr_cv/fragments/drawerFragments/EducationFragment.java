package com.futureapp.mr_cv.fragments.drawerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.activities.MainActivity;
import com.futureapp.mr_cv.adapters.RecycleViewEducationAdapter;
import com.futureapp.mr_cv.adapters.RecycleViewPersonalInfoAdapter;
import com.futureapp.mr_cv.models.EducationModel;
import com.futureapp.mr_cv.models.PersonalInfo_2_Model;
import com.futureapp.mr_cv.util.Global;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class EducationFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String ARG_PAGE = "ARG_PAGE";

    RecycleViewEducationAdapter recycleViewEducationAdapter;

    @BindView(R.id.graduate_pic_Iv)
    ImageView graduatePicIv;
    @BindView(R.id.list_RV)
    RecyclerView listRV;

    public static EducationFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        EducationFragment fragment = new EducationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_education, container, false);
        ButterKnife.bind(this, view);

        setData(Global.configFirebaseModel.getEducationModel());

        return view;
    }

    private void setData(ArrayList<EducationModel> educationModels) {

        if (Global.configFirebaseModel.getPersonalInfoModel().getGraduate_pic().length() > 0) {

            Glide
                    .with(getActivity())
                    .load(Global.configFirebaseModel.getPersonalInfoModel().getGraduate_pic())
                    .placeholder(R.drawable.shape_gradient_transparent)
                    .error(R.drawable.shape_gradient_transparent)
                    .into(graduatePicIv);

        }

        recycleViewEducationAdapter = new RecycleViewEducationAdapter(getActivity(), educationModels, this);
        listRV.setAdapter(recycleViewEducationAdapter);

//        DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
//        itemDecorator.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_recycleview_personal_info_divider_hieght));
//
//        listRV.addItemDecoration(itemDecorator);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        listRV.setLayoutManager(layoutManager);
        listRV.setHasFixedSize(true);

    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity.main_toolbar_Rl.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();

        MainActivity.main_toolbar_Rl.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}