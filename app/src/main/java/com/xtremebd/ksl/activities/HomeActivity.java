package com.xtremebd.ksl.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.clients.ApiClient;
import com.xtremebd.ksl.interfaces.ApiInterface;
import com.xtremebd.ksl.models.Index;

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

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        getIndexes();

    }

    private void getIndexes() {
        Call<Index> index = apiInterface.getHomeIndex();
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
