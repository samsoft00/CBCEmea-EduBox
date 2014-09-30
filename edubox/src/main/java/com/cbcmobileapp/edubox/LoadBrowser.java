package com.cbcmobileapp.edubox;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created by CBC on 18-Sep-14.
 */
public class LoadBrowser extends Activity {

    private ActionBar actionBar;
    ProgressBar progressbar;
    WebView loadbrowser;
    Bundle url;
    LinearLayout layout;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.cbc_webview);

        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        url = getIntent().getExtras();

        ActionBar bar = getActionBar();
        bar.setTitle((String) url.get("NAME"));

        loadbrowser = (WebView) findViewById(R.id.webview);
        //to load html data
        //loadbrowser.loadData(data, mimeType, encoding);

        //loadAd();

        //loadbrowser.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //loadbrowser.getSettings().setAppCacheMaxSize(Long.MAX_VALUE);
        //loadbrowser.getSettings().setAppCachePath("/data/data/com.cbcmobileapp.edubox/cache");
        loadbrowser.getSettings().setAllowFileAccess(false);
        //loadbrowser.getSettings().setAppCacheEnabled(true);
        //loadbrowser.getSettings().setDomStorageEnabled(true);

        loadbrowser.getSettings().setJavaScriptEnabled(true);//enabling javascript
        loadbrowser.getSettings().setLoadWithOverviewMode(true);//zooming on browser
        loadbrowser.getSettings().setUseWideViewPort(true);//responsive - using viewport

        loadbrowser.setWebViewClient(new ourViewClient());//load all browser activities on the browser
        try{
            loadbrowser.loadUrl((String) url.get("URL"));

        }catch(Exception e){

            e.printStackTrace();
        }

//        EduBoxController.getInstance().getRequestQueue();

    }


    //BBack Navigation
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if((keyCode == KeyEvent.KEYCODE_BACK) && loadbrowser.canGoBack()){
            loadbrowser.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.broswer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_return:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.action_refresh:
                loadbrowser.reload();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //WebViewClient here
    private class ourViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            setProgressBarIndeterminateVisibility(true);
            progressbar.setVisibility(View.VISIBLE);
            LoadBrowser.this.progressbar.setProgress(0);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            setProgressBarIndeterminateVisibility(false);
            progressbar.setVisibility(view.GONE);
            LoadBrowser.this.progressbar.setProgress(100);
            super.onPageFinished(view, url);
        }

        public void setValue(int progress){
            LoadBrowser.this.progressbar.setProgress(progress);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;
        }

    }
}
