package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Index;
import com.xtremebd.ksl.models.MarketSummary;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CSEMarketSummaryActivity extends AppCompatActivity {

    @BindView(R.id.tvCse30value)
    TextView tvcse30value;
    @BindView(R.id.tvCse30change)
    TextView tvcse30change;
    @BindView(R.id.tvCse50value)
    TextView tvcse50value;
    @BindView(R.id.tvCse50change)
    TextView tvcse50change;
    @BindView(R.id.tvCscxvalue)
    TextView tvcscxvalue;
    @BindView(R.id.tvCscxchange)
    TextView tvcscxchange;
    @BindView(R.id.tvCaspivalue)
    TextView tvcaspivalue;
    @BindView(R.id.tvCaspichange)
    TextView tvcaspichange;
    @BindView(R.id.tvCsivalue)
    TextView tvcsivalue;
    @BindView(R.id.tvCsichange)
    TextView tvcsichange;

    @BindView(R.id.tvValueInTaka)
    TextView tvValueInTaka;
    @BindView(R.id.tvVolume)
    TextView tvVolume;
    @BindView(R.id.tvContractNumber)
    TextView tvContractNumber;
    @BindView(R.id.tvIssuedTraded)
    TextView tvIssuedTraded;

    @BindView(R.id.tvIssuedCapital)
    TextView tvIssuedCapital;
    @BindView(R.id.tvClosingMarketCapitalization)
    TextView tvClosingMarketCapitalization;

    private FirebaseAnalytics mFirebaseAnalytics;

    ProgressDialog dialog;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cse_market_summary);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        TopBar.attach(this, "CSE MARKET SUMMARY");
        Logger.addLogAdapter(new AndroidLogAdapter());
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Getting data from server...");

        getMarketSummary();

    }

    private void getMarketSummary() {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppURLS.GET_MARKET_SUMMARY, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                MarketSummary summary = Geson.g().fromJson(response, MarketSummary.class);
                tvValueInTaka.setText(summary.getValueInTaka());
                tvVolume.setText(summary.getVolume());
                tvContractNumber.setText(summary.getContractNumber());
                tvIssuedTraded.setText(summary.getIssuesTraded());
//                tvIssuedAdvanced.setText(summary.getIssuesAdvanced());
//                tvIssuesDeclined.setText(summary.getIssuesDeclined());
//                tvIssuesUnchanged.setText(summary.getIssuesUnchanged());
                tvIssuedCapital.setText(summary.getIssuedCapital());
                tvClosingMarketCapitalization.setText(summary.getClosingMarketCapitalization());
                getIndexes();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                Toast.makeText(CSEMarketSummaryActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }

    private void getIndexes() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppURLS.GET_INDEX, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Index idx = Geson.g().fromJson(response, Index.class);
                tvcse30value.setText(idx.getCse30value());
                tvcse30change.setText(idx.getCse30change());
                tvcse50value.setText(idx.getCse50value());
                tvcse50change.setText(idx.getCse50change());
                tvcaspivalue.setText(idx.getCaspivalue());
                tvcaspichange.setText(idx.getCaspichange());
                tvcscxvalue.setText(idx.getCscxvalue());
                tvcscxchange.setText(idx.getCscxchange());
                tvcsivalue.setText(idx.getCsivalue());
                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                Toast.makeText(CSEMarketSummaryActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                Logger.d(error.getMessage());

            }
        });


    }


}
