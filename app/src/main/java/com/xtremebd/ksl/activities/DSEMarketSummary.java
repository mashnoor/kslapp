package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.DSEData;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DSEMarketSummary extends AppCompatActivity {

    @BindView(R.id.tvDsexData1)
    TextView tvDsexData1;
    @BindView(R.id.tvDsexData2)
    TextView tvDsexData2;
    @BindView(R.id.tvDsexData3)
    TextView tvDsexData3;

    @BindView(R.id.tvDsesData1)
    TextView tvDsesData1;
    @BindView(R.id.tvDsesData2)
    TextView tvDsesData2;
    @BindView(R.id.tvDsesData3)
    TextView tvDsesData3;

    @BindView(R.id.tvDs30Data1)
    TextView tvDs30Data1;
    @BindView(R.id.tvDs30Data2)
    TextView tvDs30Data2;
    @BindView(R.id.tvDs30Data3)
    TextView tvDs30Data3;

    @BindView(R.id.tvTotalTrade)
    TextView tvTotalTrade;
    @BindView(R.id.tvtotalVolume)
    TextView tvTotalVolume;
    @BindView(R.id.tvTotalValue)
    TextView tvTotalValue;
    @BindView(R.id.tvIssuedAdvanced)
    TextView tvIssueAdvanced;
    @BindView(R.id.tvIssuesDeclined)
    TextView tvIssueDeclined;
    @BindView(R.id.tvIssuesUnchanged)
    TextView tvIssuesUnchanged;
    @BindView(R.id.tvDate)
    TextView tvDate;


    ProgressDialog dialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dse_market_summary);
        ButterKnife.bind(this);
        TopBar.attach(this, "DSE MARKET SUMMARY");

        dialog = new ProgressDialog(this);
        dialog.setMessage("Getting Data. Please Wait...");
        dialog.setCancelable(false);
        getDseMarketSummary();
    }

    private void getDseMarketSummary() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppURLS.GET_DSE_MARKET_SUMMARY, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                DSEData dseData = Geson.g().fromJson(response, DSEData.class);

                //DSEX Data
                tvDsexData1.setText(dseData.getDsexData1());
                tvDsexData2.setText(dseData.getDsexData2());
                tvDsexData3.setText(dseData.getDsexData3());

                //DSES Data
                tvDsesData1.setText(dseData.getDsesData1());
                tvDsesData2.setText(dseData.getDsesData2());
                tvDsesData3.setText(dseData.getDsesData3());

                //DS30 Data
                tvDs30Data1.setText(dseData.getDs30Data1());
                tvDs30Data2.setText(dseData.getDsesData2());
                tvDs30Data3.setText(dseData.getDs30Data3());

                //Other Informations
                tvTotalTrade.setText(dseData.getTotalTrade());
                tvTotalVolume.setText(dseData.getTotalVolume());
                tvTotalValue.setText(dseData.getTotalValue());
                tvIssueAdvanced.setText(dseData.getIssuesAdvanced());
                tvIssueDeclined.setText(dseData.getIssuesDeclined());
                tvIssuesUnchanged.setText(dseData.getIssuesUnchanged());

                //Date
                tvDate.setText(dseData.getDate());

                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(DSEMarketSummary.this, "Something went wrong", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                finish();

            }
        });
    }
}
