package com.futureapp.mr_cv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.models.ProjectsModel;
import com.futureapp.mr_cv.util.Global;

import java.util.ArrayList;


public class ApplicationsListAdapter extends BaseAdapter {
    private final Context mContext;
    ArrayList<ProjectsModel> projectsModels;

    public ApplicationsListAdapter(Context context, ArrayList<ProjectsModel> projectsModels) {
        mContext = context;
        this.projectsModels = projectsModels;

    }

    @Override
    public int getCount() {
        return projectsModels.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        final ViewHolder holder;
        if (vi == null) {

            vi = LayoutInflater.from(mContext).inflate(
                    R.layout.listview_apllications_row_item, parent, false);

            holder = new ViewHolder();

            holder.image_Iv = vi
                    .findViewById(R.id.image_Iv);

            holder.info_Tv = vi
                    .findViewById(R.id.info_Tv);

            vi.setTag(holder);

        } else {
            holder = (ViewHolder) vi.getTag();
        }

        holder.position = position;

        if (projectsModels.get(holder.position).getApp_icon().length() > 0) {

            Glide
                    .with(mContext)
                    .load(projectsModels.get(holder.position).getApp_icon())
                    .placeholder(R.color.gray)
                    .error(R.color.gray)
                    .into(holder.image_Iv);

        }

        holder.info_Tv.setText(projectsModels.get(holder.position).getProject_name() + ": " + projectsModels.get(holder.position).getDescription());

        return vi;
    }

    class ViewHolder {

        ImageView image_Iv;
        TextView info_Tv;
        int position;
    }

}