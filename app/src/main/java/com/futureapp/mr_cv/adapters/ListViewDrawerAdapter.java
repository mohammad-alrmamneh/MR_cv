package com.futureapp.mr_cv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.futureapp.mr_cv.R;
import com.futureapp.mr_cv.models.DrawerItemsModel;

import java.util.ArrayList;


public class ListViewDrawerAdapter extends BaseAdapter {
    private final Context mContext;
    ArrayList<DrawerItemsModel> fields;

    public ListViewDrawerAdapter(Context context, ArrayList<DrawerItemsModel> fields) {
        mContext = context;
        this.fields = fields;

    }

    @Override
    public int getCount() {
        return fields.size();
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
                    R.layout.listview_drawer_item, parent, false);

            holder = new ViewHolder();

            holder.drawerText_Tv = vi
                    .findViewById(R.id.drawerText_Tv);

            holder.drawerIcon_Iv = vi
                    .findViewById(R.id.drawerIcon_Iv);

            vi.setTag(holder);

        } else {
            holder = (ViewHolder) vi.getTag();
        }

        holder.position = position;

        holder.drawerText_Tv.setText(fields.get(holder.position).getDrawerText());
        holder.drawerIcon_Iv.setBackgroundResource(fields.get(holder.position).getDrawerIcon());

        return vi;
    }

    class ViewHolder {

        TextView drawerText_Tv;
        ImageView drawerIcon_Iv;
        int position;
    }

}