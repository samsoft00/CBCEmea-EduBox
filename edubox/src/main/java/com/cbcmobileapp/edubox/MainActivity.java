package com.cbcmobileapp.edubox;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cbcmobileapp.edubox.utils.Utility;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    //nav dawer title
    private CharSequence mDrawerTitle;

    //used to store app title
    private CharSequence mTitle;

    private String[] siteNames;

    private String[] navMenuTitles;
    private String[] navMenuUrls;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove navigation bar
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Load bitmap programmically from drawable folder
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.main);

        //siteNames = getResources().getStringArray(R.array.web_name);

        mTitle = mDrawerTitle = getTitle();
        //Load slide menu from string resources
        navMenuTitles = getResources().getStringArray(R.array.web_name);
        //url
        navMenuUrls = getResources().getStringArray(R.array.website);
        //icons
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        if(!Utility.isConnectionAvailable(this))
            Toast.makeText(this, "Wireless Connection not Available, Please Connect your Tab", Toast.LENGTH_LONG).show();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        View header = getLayoutInflater().inflate(R.layout.cbc_list_header_image, null);
        mDrawerList.addHeaderView(header);
        //mDrawerList.addFooterView(header);

        navDrawerItems =  new ArrayList<NavDrawerItem>();

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));

        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        adapter =  new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        //enabling action bar app icon and behaviour (Up Navigation)
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle =  new ActionBarDrawerToggle(this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name
        ){

            @Override
            public void onDrawerClosed(View drawerView) {
                // TODO Auto-generated method stub
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            };

        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if(savedInstanceState == null){
            displayView(0);
        }

        //   EduBoxController.getInstance().getRequestQueue();
    }


    private void displayView(int position) {
        // TODO Auto-generated method stub
        android.app.Fragment fragment = null;
        Intent intent = new Intent(MainActivity.this, LoadBrowser.class);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;

            case 1:
                intent.putExtra("URL", navMenuUrls[position - 1]);
                //intent.putExtra("NAME", siteNames[position - 1]);
                startActivity(intent);
                break;

            case 2:
                intent.putExtra("URL", navMenuUrls[position - 1]);
                //intent.putExtra("NAME", siteNames[position - 1]);
                startActivity(intent);
                break;

            case 4:
                intent.putExtra("URL", navMenuUrls[position - 1]);
                //intent.putExtra("NAME", siteNames[position - 1]);
                startActivity(intent);
                break;

            case 5:
                intent.putExtra("URL", navMenuUrls[position - 1]);
                //intent.putExtra("NAME", siteNames[position - 1]);
                startActivity(intent);
                break;

            case 6:
                intent.putExtra("URL", navMenuUrls[position - 1]);
                //intent.putExtra("NAME", siteNames[position - 1]);
                startActivity(intent);
                break;

            case 7:
                intent.putExtra("URL", navMenuUrls[position - 6]);
                //intent.putExtra("NAME", siteNames[position - 1]);
                startActivity(intent);
                break;

            default:
                break;
        }

        if(fragment != null){
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        }else{
            Log.e("Main", "Error in creating fragment");
        }
    }


    private class SlideMenuClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // TODO Auto-generated method stub
            displayView(position);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        switch (item.getItemId()){
            case R.id.action_request:
                reqestApp();
                return true;
            case R.id.action_about:
                Intent i = new Intent(this, LoadBrowser.class);
                i.putExtra("URL", "http://www.cbcemea.com");
                i.putExtra("NAME", "CBC Emea");
                startActivity(i);

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_request).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            //landscape - setContentView(R.layout.yourxmlinlayout-land);
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //portrait
        }
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void setTitle(CharSequence title) {
        // TODO Auto-generated method stub
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    private void reqestApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater boxInflater = getLayoutInflater();

        builder.setView(boxInflater.inflate(R.layout.layout_request, null))

                .setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Thanks", Toast.LENGTH_LONG).show();
                    }
                })

                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
