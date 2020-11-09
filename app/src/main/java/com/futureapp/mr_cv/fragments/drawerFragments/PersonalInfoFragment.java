package com.futureapp.mr_cv.fragments.drawerFragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.activities.ImageAllScreenActivity;
import com.futureapp.mr_cv.adapters.RecycleViewPersonalInfoAdapter;
import com.futureapp.mr_cv.databinding.FragmentPersonalInfoBinding;
import com.futureapp.mr_cv.models.PersonalInfo_2_Model;
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
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.CLIPBOARD_SERVICE;


public class PersonalInfoFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String ARG_PAGE = "ARG_PAGE";

    private FragmentPersonalInfoBinding binding;

    RecycleViewPersonalInfoAdapter recycleViewPersonalInfoAdapter;

    @BindView(R.id.profilePic_CIV)
    CircleImageView profilePicCIV;
    @BindView(R.id.name_Tv)
    TextView nameTv;
    @BindView(R.id.jobTitle_Tv)
    TextView jobTitleTv;
    @BindView(R.id.list_RV)
    RecyclerView listRV;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    public static PersonalInfoFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myClipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPersonalInfoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);

        setData(Global.configFirebaseModel.getPersonalInfo_2_models());

        binding.profilePicFl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), ImageAllScreenActivity.class);
                i.putExtra(Constants.PutExtra_Keys.image, Global.configFirebaseModel.getPersonalInfoModel().getProfile_pic());
                startActivity(i);


            }
        });

        return view;
    }

    private void setData(ArrayList<PersonalInfo_2_Model> personalInfo_2_models) {

        nameTv.setText(Global.configFirebaseModel.getPersonalInfoModel().getName() + "");
        jobTitleTv.setText(Global.configFirebaseModel.getPersonalInfoModel().getJob_title() + "");

        if (Global.configFirebaseModel.getPersonalInfoModel().getProfile_pic().length() > 0) {

            Glide
                    .with(getActivity())
                    .load(Global.configFirebaseModel.getPersonalInfoModel().getProfile_pic())
                    .placeholder(R.color.gray)
                    .error(R.color.gray)
                    .into(profilePicCIV);

        }

        recycleViewPersonalInfoAdapter = new RecycleViewPersonalInfoAdapter(getActivity(), personalInfo_2_models, this);
        listRV.setAdapter(recycleViewPersonalInfoAdapter);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_recycleview_personal_info_divider_hieght));

        listRV.addItemDecoration(itemDecorator);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        listRV.setLayoutManager(layoutManager);
        listRV.setHasFixedSize(true);

    }


    @OnClick(R.id.profilePic_CIV)
    public void onViewClicked() {
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        PersonalInfo_2_Model personalInfo_2_model = Global.configFirebaseModel.getPersonalInfo_2_models().get(position);

        if (personalInfo_2_model.getTag().equalsIgnoreCase(getResources().getString(R.string.emailTag))) {

            myClip = ClipData.newPlainText("text", personalInfo_2_model.getValue());
            myClipboard.setPrimaryClip(myClip);

            Global.toast(getActivity(), getResources().getString(R.string.emailCopied));

        } else if (personalInfo_2_model.getTag().equalsIgnoreCase(getResources().getString(R.string.linkedInTag))) {

            Global.openURL(getActivity(), personalInfo_2_model.getValue());

        } else if (personalInfo_2_model.getTag().equalsIgnoreCase(getResources().getString(R.string.mobileTag))) {

            Global.callPhone(getActivity(), personalInfo_2_model.getValue());

        }

    }
}