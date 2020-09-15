package com.futureapp.mr_cv.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.adapters.FmMainPagerAdapter;
import com.futureapp.mr_cv.adapters.ListViewDrawerAdapter;
import com.futureapp.mr_cv.models.DrawerItemsModel;
import com.futureapp.mr_cv.util.Global;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.menu_Btn)
    Button menuBtn;
    @BindView(R.id.export_PDF_Btn)
    Button exportPDFBtn;
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
    @BindView(R.id.left_drawer)
    ListView leftDrawer;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ListViewDrawerAdapter listViewDrawerAdapter;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setViewPager();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setDrawer();

    }

    private void setViewPager() {
        viewPager.setAdapter(new FmMainPagerAdapter(getSupportFragmentManager(), this));
        tabs.setShouldExpand(true);
        tabs.setAllCaps(false);
//        tabs.setTextColor(getResources().getColor(R.color.black));
        tabs.setIndicatorColor(getResources().getColor(R.color.main_app_color_2));
        tabs.setDividerColor(getResources().getColor(R.color.transparent));
        tabs.setUnderlineColor(getResources().getColor(R.color.black));
        tabs.setUnderlineHeight(1);
        setTextFonts();
        tabs.setViewPager(viewPager);
    }

    private void setTextFonts() {
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/" + "Poppins-Regular.ttf");
        tabs.setTypeface(face, Typeface.BOLD);
        nameTv.setTypeface(face, Typeface.BOLD);
    }

    private void setDrawer() {

        String[] titles = {getResources().getString(R.string.applications),
                getResources().getString(R.string.applications),
                getResources().getString(R.string.applications),
                getResources().getString(R.string.applications),
                getResources().getString(R.string.applications),
                getResources().getString(R.string.applications)};

        int[] icons = {R.drawable.menu_icon,
                R.drawable.menu_icon,
                R.drawable.menu_icon,
                R.drawable.menu_icon,
                R.drawable.menu_icon,
                R.drawable.menu_icon};

        ArrayList<DrawerItemsModel> drawerListArray = new ArrayList<>();

        for (int i = 0; i < titles.length; i++) {

            DrawerItemsModel drawerItemsModel = new DrawerItemsModel();
            drawerItemsModel.setDrawerText(titles[i]);
            drawerItemsModel.setDrawerIcon(icons[i]);

            drawerListArray.add(drawerItemsModel);
        }

        listViewDrawerAdapter = new ListViewDrawerAdapter(this, drawerListArray);

        if (leftDrawer.getHeaderViewsCount() > 0) {

        } else {
            LayoutInflater inflater = getLayoutInflater();
            ViewGroup header = (ViewGroup) inflater.inflate(R.layout.drawer_header, leftDrawer, false);
            leftDrawer.addHeaderView(header);

//            LayoutInflater inflaterFooter = getLayoutInflater();
//            ViewGroup footer = (ViewGroup) inflaterFooter.inflate(R.layout.drawer_footer, leftDrawer, false);
//            leftDrawer.addFooterView(footer);
        }

        leftDrawer.setAdapter(listViewDrawerAdapter);

        leftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }


//                if (position == 0) {
//
//                    //drawer header
//
//                } else if (position == 1) {
//
//                    Intent i = new Intent(MainScreenDriversActivity.this, MyAccountActivity.class);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.enter, R.anim.exit);
//
//                } else if (position == 2) {
//
//                    Intent i = new Intent(MainScreenDriversActivity.this, TermsConditionsActivity.class);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.enter, R.anim.exit);
//
//                } else if (position == 3) {
//
//                    Intent i = new Intent(MainScreenDriversActivity.this, PrivacyPolicyActivity.class);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.enter, R.anim.exit);
//
//                } else if (position == 4) {
//
//                    Intent i = new Intent(MainScreenDriversActivity.this, ContactUsActivity.class);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.enter, R.anim.exit);
//
//                } else if (position == 5) {
//
//                    Intent i = new Intent(MainScreenDriversActivity.this, ChooseAppLanguageInnerActivity.class);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
//
//                } else if (position == 6) {
//
//                    Intent i = new Intent(MainScreenDriversActivity.this, LogoutActivity.class);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
//
//                }
            }
        });

    }

    @OnClick(R.id.menu_Btn)
    public void onMenuBtnClicked() {

        drawerLayout.openDrawer(GravityCompat.START);
    }

    @OnClick(R.id.export_PDF_Btn)
    public void onExportPDFBtnClicked() {
    }

    @OnClick(R.id.profilePic_Fl)
    public void onProfilePicFlClicked() {
    }

    @OnClick(R.id.projects_LL)
    public void onProjectsLLClicked() {
    }

    @OnClick(R.id.experience_LL)
    public void onExperienceLLClicked() {
    }

    @OnClick(R.id.company_LL)
    public void onCompanyLLClicked() {
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;

            Global.toast(this, getResources().getString(R.string.clickAgainToExit));

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }

    }
}