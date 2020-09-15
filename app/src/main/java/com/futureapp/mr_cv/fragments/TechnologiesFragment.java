package com.futureapp.mr_cv.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureapp.mr_cv.R;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;


public class TechnologiesFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

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


        return view;
    }
}