package com.futureapp.mr_cv.fragments.dashboardFragmentsSlidingTap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureapp.mr_cv.R;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;


public class SkillsFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    public static SkillsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        SkillsFragment fragment = new SkillsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skills, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

}