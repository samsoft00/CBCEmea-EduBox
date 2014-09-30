package com.cbcmobileapp.edubox;

import android.app.AlertDialog;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by CBC on 18-Sep-14.
 */
public class HomeFragment extends Fragment {

    ImageView visit_site, subsidiary, about_us;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.intro, container, false);

        //Set fonts to fonts
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), "fonts/Edubox.ttf");
        TextView tv = (TextView) rootView.findViewById(R.id.cbc_name);
        tv.setTypeface(tf);

//        EduBoxController.getInstance().getRequestQueue();

        return rootView;

    }


}
