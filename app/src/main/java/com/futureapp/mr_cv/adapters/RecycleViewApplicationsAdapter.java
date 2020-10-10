package com.futureapp.mr_cv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.models.ProjectsModel;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewApplicationsAdapter extends
        RecyclerView.Adapter<RecycleViewApplicationsAdapter.MyViewHolder> {

    private ArrayList<ProjectsModel> projectsModels;

    private AdapterView.OnItemClickListener onItemClickListener;

    Context context;

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public FrameLayout itemRow_Fl;
        public ImageView image_Iv;
        public TextView info_Tv;

        public MyViewHolder(View view) {
            super(view);


            itemRow_Fl = (FrameLayout) view.findViewById(R.id.itemRow_Fl);
            image_Iv = (ImageView) view.findViewById(R.id.image_Iv);
            info_Tv = (TextView) view.findViewById(R.id.info_Tv);

            itemRow_Fl.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //passing the clicked position to the parent class
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());

        }
    }

    public RecycleViewApplicationsAdapter(Context context,
                                          ArrayList<ProjectsModel> projectsModels,
                                          AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.projectsModels = projectsModels;
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ProjectsModel projectsModel = projectsModels.get(position);
        if (projectsModels.get(position).getApp_icon().length() > 0) {

            Glide
                    .with(context)
                    .load(projectsModels.get(position).getApp_icon())
                    .transform(new CenterInside(), new RoundedCorners(15))
                    .placeholder(R.color.gray)
                    .error(R.color.gray)
                    .into(holder.image_Iv);

        }

        holder.info_Tv.setText(projectsModels.get(position).getProject_name() + ": " + projectsModels.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return projectsModels.size();
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
                .inflate(R.layout.listview_apllications_row_item, parent, false);
        return new MyViewHolder(v);
    }
}
