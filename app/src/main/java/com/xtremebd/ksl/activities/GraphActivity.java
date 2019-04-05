package com.xtremebd.ksl.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.DayEndData;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GraphActivity extends AppCompatActivity {


    @BindView(R.id.candleStickChart)
    CandleStickChart candleStickChart;

    @BindView(R.id.volumeChart)
    BarChart volumeChart;



    int which;
    String companyName, fDate = "", tDate = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        ButterKnife.bind(this);

        fDate = getIntent().getStringExtra("fromdate");
        tDate = getIntent().getStringExtra("todate");
        companyName = getIntent().getStringExtra("company");
        viewVolumeGraph();
        viewCandleStickGraph();
    }

    public void viewVolumeGraph() {
        AsyncHttpClient client = new AsyncHttpClient();
        ProgressDialog dialog;


        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading and drawing graph...");

        String[] fromDate = fDate.split("-");
        String[] toDate = tDate.split("-");





        RequestParams params = new RequestParams();
        params.put("fromyear", fromDate[0]);
        params.put("frommonth", fromDate[1]);
        params.put("fromday", fromDate[2]);

        params.put("toyear", toDate[0]);
        params.put("tomonth", toDate[1]);
        params.put("todays", toDate[2]);
        params.put("company", companyName);
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
                for (DayEndData dayEndData : datas) {
                    entries.add(new BarEntry(dayEndData.getDifference(), dayEndData.getVolume()));
                    //Logger.d(dayEndData.getDifference() + " " + dayEndData.getVolume());
                }
                BarDataSet dataSet = new BarDataSet(entries, "Volume");
                dataSet.setColor(Color.RED);


                BarData data = new BarData(dataSet);

                IAxisValueFormatter formatter = new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {

                        for (DayEndData dayEndData : datas) {
                            if (dayEndData.getDifference() == value) {
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

    public void viewCandleStickGraph() {

        AsyncHttpClient client = new AsyncHttpClient();
        ProgressDialog dialog;
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading and drawing graph...");

        String[] fromDate = fDate.split("-");
        String[] toDate = tDate.split("-");

        RequestParams params = new RequestParams();
        params.put("fromyear", fromDate[0]);
        params.put("frommonth", fromDate[1]);
        params.put("fromday", fromDate[2]);

        params.put("toyear", toDate[0]);
        params.put("tomonth", toDate[1]);
        params.put("todays", toDate[2]);
        params.put("company", companyName);
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
                List<CandleEntry> entries = new ArrayList<>();
                int i = 0;

                for (DayEndData dayEndData : datas) {
                    entries.add(new CandleEntry((float) i, dayEndData.getHighPrice(), dayEndData.getLowPrice(), dayEndData.getOpenPrice(), dayEndData.getClosePrice()));
                    Logger.d(dayEndData.getDifference() + " " + dayEndData.getVolume());
                    i++;
                }


                CandleDataSet dataSet = new CandleDataSet(entries, "Candle Stick");
                dataSet.setColor(Color.rgb(80, 80, 80));
                dataSet.setShadowColor(Color.DKGRAY);
                dataSet.setShadowWidth(0.7f);
                dataSet.setDecreasingColor(Color.RED);
                dataSet.setDecreasingPaintStyle(Paint.Style.FILL);
                dataSet.setIncreasingColor(Color.rgb(122, 242, 84));
                dataSet.setIncreasingPaintStyle(Paint.Style.FILL);
                dataSet.setNeutralColor(Color.BLUE);
                dataSet.setValueTextColor(Color.RED);


                CandleData data = new CandleData(dataSet);


                candleStickChart.setData(data);


                candleStickChart.invalidate();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                Logger.d(error.getMessage());

            }
        });
    }


}
