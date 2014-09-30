package com.cbcmobileapp.edubox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CBC on 18-Sep-14.
 */
public class NavDrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;



    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){
            //LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = ((LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.cbc_drawer_list_item, null);
        }

        ImageView imgIcon = (ImageView)convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView)convertView.findViewById(R.id.title);
        TextView txtCount = (TextView)convertView.findViewById(R.id.counter);

        //imgIcon.setImageResource(((NavDrawerItem)this.navDrawerItems.get(position)).getIcon());
        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());

        //check whether it set visible or not
        if(navDrawerItems.get(position).getCounterVisible()){
            txtCount.setText(navDrawerItems.get(position).getCount());
        }else{
            txtCount.setVisibility(View.GONE);
        }

        return convertView;
    }

}
