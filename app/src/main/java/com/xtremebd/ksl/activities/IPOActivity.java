package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class IPOActivity extends AppCompatActivity {

    @BindView(R.id.wvIpo)
    WebView wvIPO;
    private FirebaseAnalytics mFirebaseAnalytics;

    ProgressDialog dialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipo);
        ButterKnife.bind(this);
        TopBar.attach(this, "IPO");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please Wait...");
        dialog.setCancelable(false);
        getIpo();
    }

    private void getIpo() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppURLS.IPO_URL, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                wvIPO.loadData(response, "text/html", "UTF-8");
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                finish();
                dialog.dismiss();
                Toast.makeText(IPOActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();

            }
        });
    }
}
