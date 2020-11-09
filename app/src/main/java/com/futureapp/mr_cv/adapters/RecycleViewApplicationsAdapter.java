package com.futureapp.mr_cv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.databinding.RecycleviewApplicationsRowItemBinding;
import com.futureapp.mr_cv.models.ProjectsModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
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

        public RecycleviewApplicationsRowItemBinding binding;

        public MyViewHolder(RecycleviewApplicationsRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.itemRowLL.setOnClickListener(this);
            binding.goPlaystoreLl.setOnClickListener(this);

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
        if (projectsModel.getApp_icon().length() > 0) {

            int margin = context.getResources().getDimensionPixelSize(R.dimen._5sdp);

            Glide
                    .with(context)
                    .load(projectsModel.getApp_icon())
                    .transform(new CenterInside(), new RoundedCorners(margin))
                    .placeholder(R.color.gray)
                    .error(R.color.gray)
                    .into(holder.binding.appPicCIV);

        }

        holder.binding.projectNameTv.setText(projectsModels.get(position).getProject_name());
        holder.binding.projectDescriptionTv.setText(projectsModels.get(position).getDescription());


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

    @NonNull
    @Override
    public RecycleViewApplicationsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecycleViewApplicationsAdapter.MyViewHolder(RecycleviewApplicationsRowItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }
}
