package com.futureapp.mr_cv.fragments.dashboardFragmentsSlidingTap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.activities.TextAllScreenActivity;
import com.futureapp.mr_cv.adapters.RecycleViewApplicationsAdapter;
import com.futureapp.mr_cv.models.ProjectsModel;
import com.futureapp.mr_cv.models.TextAllScreenModel;
import com.futureapp.mr_cv.util.Constants;
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

        ProjectsModel projectsModel = Global.configFirebaseModel.getProjectsModel().get(position);

        int goPlaystore_Ll_ID = R.id.goPlaystore_Ll;

        if (view.getId() == goPlaystore_Ll_ID) {

            Global.openPlaystoreAppURL(getActivity(), projectsModel.getPlaystore_link());

        } else {

            TextAllScreenModel textAllScreenModel = new TextAllScreenModel();

            textAllScreenModel.setImage(projectsModel.getApp_icon());
            textAllScreenModel.setTitle(projectsModel.getProject_name());
            textAllScreenModel.setDescription(projectsModel.getDescription());

            Intent i = new Intent(getActivity(), TextAllScreenActivity.class);
            i.putExtra(Constants.PutExtra_Keys.textAllScreen, textAllScreenModel);
            startActivity(i);

        }


    }
}