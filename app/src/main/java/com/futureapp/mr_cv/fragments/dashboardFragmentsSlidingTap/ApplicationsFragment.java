package com.futureapp.mr_cv.fragments.dashboardFragmentsSlidingTap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.adapters.ApplicationsListAdapter;
import com.futureapp.mr_cv.util.Global;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ApplicationsFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    @BindView(R.id.listView)
    AsymmetricGridView listView;

    ApplicationsListAdapter applicationsListAdapter;

    private int mPage;


    public static ApplicationsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ApplicationsFragment fragment = new ApplicationsFragment();
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
        View view = inflater.inflate(R.layout.fragment_applications, container, false);
        ButterKnife.bind(this, view);

        setData();

        return view;
    }

    private void setData() {
        // Choose your own preferred column width
        listView.setRequestedColumnWidth(Utils.dpToPx(getActivity(), 120));
        final List<AsymmetricItem> items = new ArrayList<>();

        // initialize your items array
        applicationsListAdapter = new ApplicationsListAdapter(getActivity(), Global.configFirebaseModel.getProjectsModel());
        AsymmetricGridViewAdapter asymmetricAdapter =
                new AsymmetricGridViewAdapter<>(getActivity(), listView, applicationsListAdapter);
        listView.setAdapter(asymmetricAdapter);
    }
}