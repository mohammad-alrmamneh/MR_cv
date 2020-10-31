package com.futureapp.mr_cv.fragments.dashboardFragmentsSlidingTap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.adapters.RecycleViewApplicationsAdapter;
import com.futureapp.mr_cv.models.ProjectsModel;
import com.futureapp.mr_cv.util.Global;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ApplicationsFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String ARG_PAGE = "ARG_PAGE";

    RecycleViewApplicationsAdapter recycleViewApplicationsAdapter;

    @BindView(R.id.list_RV)
    RecyclerView listRV;

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

        setData(Global.configFirebaseModel.getProjectsModel());

        return view;
    }

    private void setData(ArrayList<ProjectsModel> projectsModels) {
        recycleViewApplicationsAdapter = new RecycleViewApplicationsAdapter(getActivity(), projectsModels, this);
        listRV.setAdapter(recycleViewApplicationsAdapter);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_recycleview_divider_hieght_for_applications));

        listRV.addItemDecoration(itemDecorator);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        listRV.setLayoutManager(layoutManager);
        listRV.setHasFixedSize(true);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        int goPlaystore_Btn_ID = R.id.goPlaystore_Btn;

        if (view.getId() == goPlaystore_Btn_ID) {

            Global.toast(getActivity(), Global.configFirebaseModel.getProjectsModel().get(position).getPlaystore_link());

        } else {

            Global.toast(getActivity(), Global.configFirebaseModel.getProjectsModel().get(position).getProject_name());
        }


    }
}