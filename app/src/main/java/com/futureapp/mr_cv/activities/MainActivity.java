package com.futureapp.mr_cv.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.adapters.DrawerAdapter;
import com.futureapp.mr_cv.drawerContents.DrawerItem;
import com.futureapp.mr_cv.drawerContents.SimpleItem;
import com.futureapp.mr_cv.drawerContents.SpaceItem;
import com.futureapp.mr_cv.fragments.drawerFragments.DashboardFragment;
import com.futureapp.mr_cv.fragments.drawerFragments.EducationFragment;
import com.futureapp.mr_cv.fragments.drawerFragments.PersonalInfoFragment;
import com.futureapp.mr_cv.fragments.drawerFragments.PurposeFragment;
import com.futureapp.mr_cv.util.Global;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    @BindView(R.id.menu_Btn)
    Button menuBtn;
    @BindView(R.id.export_PDF_Btn)
    Button exportPDFBtn;

    public static RelativeLayout main_toolbar_Rl;

    boolean doubleBackToExitPressedOnce = false;

    private static final int Dashboard = 0;
    private static final int Personal_Information = 1;
    private static final int Purpose = 2;
    private static final int Education = 3;
    private static final int Exit = 5;


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

        main_toolbar_Rl = findViewById(R.id.main_toolbar_Rl);

        initialDrawer(savedInstanceState);
    }

    private void initialDrawer(Bundle savedInstanceState) {

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(Dashboard).setChecked(true),
                createItemFor(Personal_Information),
                createItemFor(Purpose),
                createItemFor(Education),
                new SpaceItem(48),
                createItemFor(Exit)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(Dashboard);
    }

    @Override
    public void onItemSelected(int position) {

        slidingRootNav.closeMenu();

        if (position == Dashboard) {

            DashboardFragment dashboardFragment = new DashboardFragment();
            showFragment(dashboardFragment);

        } else if (position == Personal_Information) {

            PersonalInfoFragment personalInfoFragment = new PersonalInfoFragment();
            showFragment(personalInfoFragment);

        } else if (position == Purpose) {

            PurposeFragment purposeFragment = new PurposeFragment();
            showFragment(purposeFragment);

        } else if (position == Education) {

            EducationFragment educationFragment = new EducationFragment();
            showFragment(educationFragment);

        } else if (position == Exit) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }

    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.white))
                .withTextTint(color(R.color.white))
                .withSelectedIconTint(color(R.color.main_app_color_1))
                .withSelectedTextTint(color(R.color.main_app_color_1));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.drawer_title);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.drawer_white_icons_array);
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

    }


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