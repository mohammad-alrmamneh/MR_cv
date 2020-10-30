package com.futureapp.mr_cv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.models.TechnologiesModel;
import com.ramijemli.percentagechartview.PercentageChartView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewTechnologiesAdapter extends
        RecyclerView.Adapter<RecycleViewTechnologiesAdapter.MyViewHolder> {

    private ArrayList<TechnologiesModel> technologiesModels;

    private AdapterView.OnItemClickListener onItemClickListener;

    Context context;

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public RelativeLayout itemRow_Rl;
        public TextView statment_Tv;
        public PercentageChartView view_id;

        public MyViewHolder(View view) {
            super(view);


            itemRow_Rl = (RelativeLayout) view.findViewById(R.id.itemRow_Rl);
            statment_Tv = (TextView) view.findViewById(R.id.statment_Tv);
            view_id = (PercentageChartView) view.findViewById(R.id.view_id);

            itemRow_Rl.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //passing the clicked position to the parent class
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());

        }
    }

    public RecycleViewTechnologiesAdapter(Context context,
                                          ArrayList<TechnologiesModel> technologiesModels,
                                          AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.technologiesModels = technologiesModels;
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        TechnologiesModel technologiesModel = technologiesModels.get(position);

        holder.statment_Tv.setText(technologiesModel.getStatment());

        int rate = 0;

        if (technologiesModel.getRate().length() > 0) {
            rate = Integer.parseInt(technologiesModel.getRate());
        }

//        holder.view_id.setProgress(rate, true);

        int color = R.color.white;

        if (rate > 95) {

            color = R.color.main_app_color_1;

        } else if (rate > 85) {

            color = R.color.green;

        } else if (rate > 75) {

            color = R.color.yellow;

        } else {

            color = R.color.white;
        }

        holder.view_id.textColor(context.getResources().getColor(R.color.main_app_color_1))
                .textShadow(context.getResources().getColor(R.color.white), 2f, 2f, 2f)
                .progressColor(context.getResources().getColor(color))
                .backgroundColor(context.getResources().getColor(R.color.yellow))
                .apply();

    }

    @Override
    public int getItemCount() {
        return technologiesModels.size();
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
                .inflate(R.layout.recycleview_technologies_row_item, parent, false);
        return new MyViewHolder(v);
    }
}
