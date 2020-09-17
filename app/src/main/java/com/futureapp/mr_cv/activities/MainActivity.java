package com.futureapp.mr_cv.activities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.adapters.DrawerAdapter;
import com.futureapp.mr_cv.adapters.FmMainPagerAdapter;
import com.futureapp.mr_cv.drawerContents.DrawerItem;
import com.futureapp.mr_cv.drawerContents.SimpleItem;
import com.futureapp.mr_cv.drawerContents.SpaceItem;
import com.futureapp.mr_cv.util.Global;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

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

    boolean doubleBackToExitPressedOnce = false;

    private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_MESSAGES = 2;
    private static final int POS_CART = 3;
    private static final int POS_LOGOUT = 5;
    @BindView(R.id.menu_Btn)
    Button menuBtn;
    @BindView(R.id.export_PDF_Btn)
    Button exportPDFBtn;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

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

        initialDrawer(savedInstanceState);
    }

    private void initialDrawer(Bundle savedInstanceState) {

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_MESSAGES),
                createItemFor(POS_CART),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);
    }

    @Override
    public void onItemSelected(int position) {

        slidingRootNav.closeMenu();

//        if (position == POS_LOGOUT) {
//
//        }

    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.black))
                .withTextTint(color(R.color.black))
                .withSelectedIconTint(color(R.color.main_app_color_2))
                .withSelectedTextTint(color(R.color.main_app_color_2));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        setDrawer();

    }

    private void setViewPager() {
        viewPager.setAdapter(new FmMainPagerAdapter(getSupportFragmentManager(), this));
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
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/" + "Poppins-Regular.ttf");
        tabs.setTypeface(face, Typeface.BOLD);
        nameTv.setTypeface(face, Typeface.BOLD);
    }

//    private void setDrawer() {
//
//        String[] titles = {getResources().getString(R.string.applications),
//                getResources().getString(R.string.applications),
//                getResources().getString(R.string.applications),
//                getResources().getString(R.string.applications),
//                getResources().getString(R.string.applications),
//                getResources().getString(R.string.applications)};
//
//        int[] icons = {R.drawable.menu_icon,
//                R.drawable.menu_icon,
//                R.drawable.menu_icon,
//                R.drawable.menu_icon,
//                R.drawable.menu_icon,
//                R.drawable.menu_icon};
//
//        ArrayList<DrawerItemsModel> drawerListArray = new ArrayList<>();
//
//        for (int i = 0; i < titles.length; i++) {
//
//            DrawerItemsModel drawerItemsModel = new DrawerItemsModel();
//            drawerItemsModel.setDrawerText(titles[i]);
//            drawerItemsModel.setDrawerIcon(icons[i]);
//
//            drawerListArray.add(drawerItemsModel);
//        }
//
//        listViewDrawerAdapter = new ListViewDrawerAdapter(this, drawerListArray);
//
//        if (leftDrawer.getHeaderViewsCount() > 0) {
//
//        } else {
//            LayoutInflater inflater = getLayoutInflater();
//            ViewGroup header = (ViewGroup) inflater.inflate(R.layout.drawer_header, leftDrawer, false);
//            leftDrawer.addHeaderView(header);
//
////            LayoutInflater inflaterFooter = getLayoutInflater();
////            ViewGroup footer = (ViewGroup) inflaterFooter.inflate(R.layout.drawer_footer, leftDrawer, false);
////            leftDrawer.addFooterView(footer);
//        }
//
//        leftDrawer.setAdapter(listViewDrawerAdapter);
//
//        leftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                    drawerLayout.closeDrawer(GravityCompat.START);
//                }
//
//
////                if (position == 0) {
////
////                    //drawer header
////
////                } else if (position == 1) {
////
////                    Intent i = new Intent(MainScreenDriversActivity.this, MyAccountActivity.class);
////                    startActivity(i);
////                    overridePendingTransition(R.anim.enter, R.anim.exit);
////
////                } else if (position == 2) {
////
////                    Intent i = new Intent(MainScreenDriversActivity.this, TermsConditionsActivity.class);
////                    startActivity(i);
////                    overridePendingTransition(R.anim.enter, R.anim.exit);
////
////                } else if (position == 3) {
////
////                    Intent i = new Intent(MainScreenDriversActivity.this, PrivacyPolicyActivity.class);
////                    startActivity(i);
////                    overridePendingTransition(R.anim.enter, R.anim.exit);
////
////                } else if (position == 4) {
////
////                    Intent i = new Intent(MainScreenDriversActivity.this, ContactUsActivity.class);
////                    startActivity(i);
////                    overridePendingTransition(R.anim.enter, R.anim.exit);
////
////                } else if (position == 5) {
////
////                    Intent i = new Intent(MainScreenDriversActivity.this, ChooseAppLanguageInnerActivity.class);
////                    startActivity(i);
////                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
////
////                } else if (position == 6) {
////
////                    Intent i = new Intent(MainScreenDriversActivity.this, LogoutActivity.class);
////                    startActivity(i);
////                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
////
////                }
//            }
//        });
//
//    }

    @OnClick(R.id.menu_Btn)
    public void onMenuBtnClicked() {

        if (slidingRootNav.isMenuClosed()) {
            slidingRootNav.openMenu();
        } else if (slidingRootNav.isMenuOpened()) {
            slidingRootNav.closeMenu();
        }
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
        if (slidingRootNav.isMenuOpened()) {
            slidingRootNav.closeMenu();
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