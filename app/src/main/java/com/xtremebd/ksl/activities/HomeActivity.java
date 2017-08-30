package com.xtremebd.ksl.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Index;
import com.xtremebd.ksl.models.MarketSummary;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

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

    @BindView(R.id.tvValueInTaka) TextView tvValueInTaka;
    @BindView(R.id.tvVolume) TextView tvVolume;
    @BindView(R.id.tvContractNumber) TextView tvContractNumber;
    @BindView(R.id.tvIssuedTraded) TextView tvIssuedTraded;
    @BindView(R.id.tvIssuedAdvanced) TextView tvIssuedAdvanced;
    @BindView(R.id.tvIssuesDeclined) TextView tvIssuesDeclined;
    @BindView(R.id.tvIssuesUnchanged) TextView tvIssuesUnchanged;
    @BindView(R.id.tvIssuedCapital) TextView tvIssuedCapital;
    @BindView(R.id.tvClosingMarketCapitalization) TextView tvClosingMarketCapitalization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        getIndexes();
        getMarketSummary();

    }

    private void getMarketSummary() {
        ApiInterfaceGetter.getStaticInterface().getMarketSummary().enqueue(new Callback<MarketSummary>() {
            @Override
            public void onResponse(Call<MarketSummary> call, Response<MarketSummary> response) {
                MarketSummary summary = response.body();
                tvValueInTaka.setText(summary.getValueInTaka());
                tvVolume.setText(summary.getVolume());
                tvContractNumber.setText(summary.getContractNumber());
                tvIssuedTraded.setText(summary.getIssuesTraded());
                tvIssuedAdvanced.setText(summary.getIssuesAdvanced());
                tvIssuesDeclined.setText(summary.getIssuesDeclined());
                tvIssuesUnchanged.setText(summary.getIssuesUnchanged());
                tvIssuedCapital.setText(summary.getIssuedCapital());
                tvClosingMarketCapitalization.setText(summary.getClosingMarketCapitalization());

            }

            @Override
            public void onFailure(Call<MarketSummary> call, Throwable t) {

            }
        });
    }

    private void getIndexes() {
        Call<Index> index = ApiInterfaceGetter.getStaticInterface().getHomeIndex();
        index.enqueue(new Callback<Index>() {
            @Override
            public void onResponse(Call<Index> call, Response<Index> response) {
                Index idx = response.body();
                tvcse30value.setText(idx.getCse30value());
                tvcse30change.setText(idx.getCse30change());
                tvcse50value.setText(idx.getCse50value());
                tvcse50change.setText(idx.getCse50change());
                tvcaspivalue.setText(idx.getCaspivalue());
                tvcaspichange.setText(idx.getCaspichange());
                tvcscxvalue.setText(idx.getCscxvalue());
                tvcscxchange.setText(idx.getCscxchange());
                tvcsivalue.setText(idx.getCsivalue());
                tvcsichange.setText(idx.getCsichange());
            }

            @Override
            public void onFailure(Call<Index> call, Throwable t) {
                Log.d("--------", t.getMessage());
            }
        });
    }

}
