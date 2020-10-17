package com.futureapp.mr_cv.fragments.dashboardFragmentsSlidingTap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.models.TechnologiesModel;
import com.futureapp.mr_cv.util.Global;

import java.util.ArrayList;
import java.util.Collections;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.TagContainerLayout;


public class TechnologiesFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    @BindView(R.id.tags_TCL)
    TagContainerLayout tagsTCL;

    private int mPage;


    public static TechnologiesFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TechnologiesFragment fragment = new TechnologiesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_technologies, container, false);
        ButterKnife.bind(this, view);

        setData(Global.configFirebaseModel.getTechnologiesModels());


        return view;
    }

    private void setData(ArrayList<TechnologiesModel> technologiesModels) {

        if (technologiesModels != null) {


            Collections.shuffle(technologiesModels);

            ArrayList<String> tags = new ArrayList<>();

            for (int i = 0; i < technologiesModels.size(); i++) {
                tags.add(technologiesModels.get(i).getStatment());
            }


            tagsTCL.setTags(tags);

        }

    }

}