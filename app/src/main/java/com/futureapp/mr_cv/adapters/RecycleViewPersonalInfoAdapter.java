package com.futureapp.mr_cv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.models.PersonalInfoModel;
import com.futureapp.mr_cv.models.PersonalInfo_2_Model;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewPersonalInfoAdapter extends
        RecyclerView.Adapter<RecycleViewPersonalInfoAdapter.MyViewHolder> {

    private ArrayList<PersonalInfo_2_Model> personalInfo_2_models;

    private AdapterView.OnItemClickListener onItemClickListener;

    Context context;

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout itemRow_LL;
        public TextView title_Tv;
        public TextView value_Tv;

        public MyViewHolder(View view) {
            super(view);


            itemRow_LL = (LinearLayout) view.findViewById(R.id.itemRow_LL);
            title_Tv = (TextView) view.findViewById(R.id.title_Tv);
            value_Tv = (TextView) view.findViewById(R.id.value_Tv);

            itemRow_LL.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //passing the clicked position to the parent class
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());

        }
    }

    public RecycleViewPersonalInfoAdapter(Context context,
                                          ArrayList<PersonalInfo_2_Model> personalInfo_2_models,
                                          AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.personalInfo_2_models = personalInfo_2_models;
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        PersonalInfo_2_Model personalInfo_2_model = personalInfo_2_models.get(position);

        holder.title_Tv.setText(personalInfo_2_model.getTitle());
        holder.value_Tv.setText(personalInfo_2_model.getValue());

        if (personalInfo_2_model.getClickable().equalsIgnoreCase("true")) {
            holder.value_Tv.setTextColor(context.getResources().getColor(R.color.main_app_color_1));
        }
    }

    @Override
    public int getItemCount() {
        return personalInfo_2_models.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_personal_info_row_item, parent, false);
        return new MyViewHolder(v);
    }
}
