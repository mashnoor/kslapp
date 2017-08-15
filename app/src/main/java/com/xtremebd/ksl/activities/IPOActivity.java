package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.AppURLS;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IPOActivity extends AppCompatActivity {

    @BindView(R.id.wvIpo)
    WebView wvIPO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipo);
        ButterKnife.bind(this);
        WebSettings settings = wvIPO.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        getIpo();
    }

    private void getIpo() {
        wvIPO.loadUrl(AppURLS.IPO_URL);
    }
}
