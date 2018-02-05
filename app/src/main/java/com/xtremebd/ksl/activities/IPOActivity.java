package com.xtremebd.ksl.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IPOActivity extends AppCompatActivity {

    @BindView(R.id.wvIpo)
    WebView wvIPO;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipo);
        ButterKnife.bind(this);
        TopBar.attach(this, "IPO");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        getIpo();
    }

    private void getIpo() {
        wvIPO.loadUrl(AppURLS.IPO_URL);
    }
}
