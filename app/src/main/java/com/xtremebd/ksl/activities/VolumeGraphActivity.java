package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.DayEndData;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class VolumeGraphActivity extends AppCompatActivity {

    @BindView(R.id.fromDate)
    EditText etFromDate;
    @BindView(R.id.toDate)
    EditText etTodate;

    @BindView(R.id.chart)
    BarChart volumeChart;

    AsyncHttpClient client;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_graph);
        ButterKnife.bind(this);
        client = new AsyncHttpClient();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading and drawing graph...");
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public void viewGraph(final View v) {
        String[] fromDate = etFromDate.getText().toString().split("-");
        String[] toDate = etTodate.getText().toString().split("-");

        RequestParams params = new RequestParams();
        params.put("fromyear", fromDate[0]);
        params.put("frommonth", fromDate[1]);
        params.put("fromday", fromDate[2]);

        params.put("toyear", toDate[0]);
        params.put("tomonth", toDate[1]);
        params.put("todays", toDate[2]);
        params.put("company", "ACI");
        client.post(AppURLS.GET_DAY_END_DATA, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Logger.d(response);
                dialog.dismiss();
                final DayEndData[] datas = Geson.g().fromJson(response, DayEndData[].class);
                List<BarEntry> entries = new ArrayList<>();
                for(DayEndData dayEndData : datas)
                {
                    entries.add(new BarEntry(dayEndData.getDifference(), dayEndData.getVolume()));
                    Logger.d(dayEndData.getDifference() + " " + dayEndData.getVolume());
                }
                BarDataSet dataSet = new BarDataSet(entries, "Volume");
                dataSet.setColor(Color.RED);


                BarData data = new BarData(dataSet);

                IAxisValueFormatter formatter = new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {

                        for (DayEndData dayEndData : datas)
                        {
                            if(dayEndData.getDifference() == value )
                            {
                                return dayEndData.getDate();
                            }
                        }
                        return "0";
                    }
                };


                volumeChart.setData(data);
                volumeChart.setScaleEnabled(true);
                volumeChart.setTouchEnabled(true);
                volumeChart.getXAxis().setGranularity(1f);
                volumeChart.getXAxis().setValueFormatter(formatter);
                volumeChart.invalidate();



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                Logger.d(error.getMessage());

            }
        });


    }
}
