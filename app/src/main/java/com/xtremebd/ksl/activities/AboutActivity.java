package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.utils.TopBar;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TopBar.attach(this, "ABOUT APP");
    }
}
