package com.futureapp.mr_cv.fragments.drawerFragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.bumptech.glide.Glide;
import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.adapters.FmMainPagerAdapter;
import com.futureapp.mr_cv.models.ConfigFirebaseModel;
import com.futureapp.mr_cv.util.Global;
import com.futureapp.mr_cv.util.TinyDB;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class DashboardFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    @BindView(R.id.profilePic_CIV)
    CircleImageView profilePicCIV;
    @BindView(R.id.profilePic_Fl)
    FrameLayout profilePicFl;
    @BindView(R.id.projects_Tv)
    TextView projectsTv;
    @BindView(R.id.projects_LL)
    LinearLayout projectsLL;
    @BindView(R.id.experience_Tv)
    TextView experienceTv;
    @BindView(R.id.experience_LL)
    LinearLayout experienceLL;
    @BindView(R.id.company_Tv)
    TextView companyTv;
    @BindView(R.id.company_LL)
    LinearLayout companyLL;
    @BindView(R.id.name_Tv)
    TextView nameTv;
    @BindView(R.id.jobTitle_Tv)
    TextView jobTitleTv;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    public static DashboardFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);

        setViewPager();

        setData();

        return view;
    }

    private void setViewPager() {
        viewPager.setAdapter(new FmMainPagerAdapter(getFragmentManager(), getActivity()));
        tabs.setShouldExpand(true);
        tabs.setAllCaps(false);
        tabs.setIndicatorColor(getResources().getColor(R.color.main_app_color_2));
        tabs.setDividerColor(getResources().getColor(R.color.transparent));
        tabs.setUnderlineColor(getResources().getColor(R.color.black));
        tabs.setUnderlineHeight(1);
        setTextFonts();
        tabs.setViewPager(viewPager);
    }

    private void setTextFonts() {
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/" + "Poppins-Regular.ttf");
        tabs.setTypeface(face, Typeface.BOLD);
        nameTv.setTypeface(face, Typeface.BOLD);
    }

    private void setData() {
        projectsTv.setText(Global.configFirebaseModel.getProjectsModel().size() + "");
        experienceTv.setText(Global.configFirebaseModel.getExperience() + "");
        companyTv.setText(Global.configFirebaseModel.getCompaniesModel().size() + "");
        nameTv.setText(Global.configFirebaseModel.getPersonalInfoModel().getName() + "");
        jobTitleTv.setText(Global.configFirebaseModel.getPersonalInfoModel().getJob_title() + "");

        if (Global.configFirebaseModel.getPersonalInfoModel().getProfile_pic().length() > 0) {

            Glide
                    .with(getActivity())
                    .load(Global.configFirebaseModel.getPersonalInfoModel().getProfile_pic())
                    .placeholder(R.color.gray)
                    .into(profilePicCIV);

        }

    }
}