package com.xtremebd.ksl.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.utils.TopBar;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TopBar.attach(this, "ABOUT APP");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
