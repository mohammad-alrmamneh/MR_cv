package com.futureapp.mr_cv.fragments.drawerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureapp.mr_cv.R;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;


public class EducationFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

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

        return view;
    }

}