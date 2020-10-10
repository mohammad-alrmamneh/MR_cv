package com.futureapp.mr_cv.fragments.drawerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.util.Global;
import com.hanks.htextview.evaporate.EvaporateTextView;
import com.hanks.htextview.typer.TyperTextView;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;


public class PurposeFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    @BindView(R.id.text_TTV)
    TyperTextView textTTV;
    @BindView(R.id.title_ETV)
    EvaporateTextView titleETV;


    public static PurposeFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PurposeFragment fragment = new PurposeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purpose, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        setData();
    }

    private void setData() {

        StringBuilder sb = new StringBuilder();
        String title = "";
        String[] textSplit = Global.configFirebaseModel.getPurpose().split("::");

        for (int i = 0; i < textSplit.length; i++) {
            if (i == 0) {
                title = textSplit[i];
            } else {
                sb.append(textSplit[i]);
            }
        }

        titleETV.animateText(title);
        textTTV.animateText(sb);

    }

}