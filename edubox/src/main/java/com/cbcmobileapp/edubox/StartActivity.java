package com.cbcmobileapp.edubox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends Activity {

    Button conButton;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set Content view to load
        setContentView(R.layout.start_screen);

       //Set fonts to fonts
       Typeface tf = Typeface.createFromAsset(getResources().getAssets(), "fonts/Edubox.ttf");
       TextView tv = (TextView)findViewById(R.id.edu_box);
       tv.setTypeface(tf);

       //ActionBar actionBar = getActionBar();
       //actionBar.hide();

       conButton = (Button) findViewById(R.id.con);
       conButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(StartActivity.this, MainActivity.class);
               startActivity(intent);
           }
       });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
