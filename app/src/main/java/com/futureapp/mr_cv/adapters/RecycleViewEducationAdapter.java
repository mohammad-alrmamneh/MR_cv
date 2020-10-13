package com.futureapp.mr_cv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.models.EducationModel;
import com.futureapp.mr_cv.models.PersonalInfo_2_Model;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewEducationAdapter extends
        RecyclerView.Adapter<RecycleViewEducationAdapter.MyViewHolder> {

    private ArrayList<EducationModel> educationModels;

    private AdapterView.OnItemClickListener onItemClickListener;

    Context context;

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout itemRow_LL;
        public TextView statment_Tv;

        public MyViewHolder(View view) {
            super(view);


            itemRow_LL = (LinearLayout) view.findViewById(R.id.itemRow_LL);
            statment_Tv = (TextView) view.findViewById(R.id.statment_Tv);

            itemRow_LL.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //passing the clicked position to the parent class
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());

        }
    }

    public RecycleViewEducationAdapter(Context context,
                                       ArrayList<EducationModel> educationModels,
                                       AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.educationModels = educationModels;
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        EducationModel educationModel = educationModels.get(position);

        holder.statment_Tv.setText(educationModel.getStatment());

    }

    @Override
    public int getItemCount() {
        return educationModels.size();
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
                .inflate(R.layout.recycleview_education_row_item, parent, false);
        return new MyViewHolder(v);
    }
}
