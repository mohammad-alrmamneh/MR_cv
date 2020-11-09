package com.futureapp.mr_cv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.futureapp.mr_cv.databinding.RecycleviewCompaniesRowItemBinding;
import com.futureapp.mr_cv.models.CompaniesModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewCompaniesAdapter extends
        RecyclerView.Adapter<RecycleViewCompaniesAdapter.MyViewHolder> {

    private ArrayList<CompaniesModel> companiesModels;

    private AdapterView.OnItemClickListener onItemClickListener;

    Context context;

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public RecycleviewCompaniesRowItemBinding binding;

        public MyViewHolder(RecycleviewCompaniesRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.companyWebsiteTv.setOnClickListener(this);
            binding.itemRowLL.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //passing the clicked position to the parent class
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());

        }
    }

    public RecycleViewCompaniesAdapter(Context context,
                                       ArrayList<CompaniesModel> companiesModels,
                                       AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.companiesModels = companiesModels;
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        CompaniesModel companiesModel = companiesModels.get(position);

        holder.binding.companyNameTv.setText(companiesModel.getCompany_name());
        holder.binding.companyDescriptionTv.setText(companiesModel.getDescription());
        holder.binding.companyWebsiteTv.setText(companiesModel.getCompany_website());
        holder.binding.yearTv.setText(companiesModel.getYear());

    }

    @Override
    public int getItemCount() {
        return companiesModels.size();
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
    public RecycleViewCompaniesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RecycleviewCompaniesRowItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }
}
