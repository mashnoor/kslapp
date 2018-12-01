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

    @BindView(R.id.fromDate)
    EditText etFromDate;
    @BindView(R.id.toDate)
    EditText etTodate;

    @BindView(R.id.chart)
    CombinedChart graph;

    AsyncHttpClient client;
    ProgressDialog dialog;

    int which;
    String companyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        ButterKnife.bind(this);
        TopBar.attach(this, "GRAPH");
        registerCalenderListener();
        companyName = getIntent().getStringExtra("company");
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading and drawing graph...");
        client = new AsyncHttpClient();
        String fromDate = getIntent().getStringExtra("fromdate");
        String toDate = getIntent().getStringExtra("todate");

        etFromDate.setText(fromDate);
        etTodate.setText(toDate);
    }

    private void registerCalenderListener() {
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                String month = String.valueOf(monthOfYear + 1);
                String day = String.valueOf(dayOfMonth);
                if (month.length() == 1)
                    month = "0" + month;
                // TODO Auto-generated method stub
                if (day.length() == 1)
                    day = "0" + day;
                if (which == 0)
                    etFromDate.setText(year + "-" + month + "-" + day);

                else
                    etTodate.setText(year + "-" + month + "-" + day);
            }

        };

        etFromDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                which = 0;
                new DatePickerDialog(GraphActivity.this, AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        etTodate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                which = 1;
                new DatePickerDialog(GraphActivity.this, AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    public void viewGraph(final View v) {
        if (etFromDate.getText().toString().trim().isEmpty() || etTodate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Enter dates!", Toast.LENGTH_LONG).show();
            return;
        }

        String[] fromDate = etFromDate.getText().toString().split("-");
        String[] toDate = etTodate.getText().toString().split("-");

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

                List<CandleEntry> candleEntries = new ArrayList<>();
                List<BarEntry> barEntries = new ArrayList<>();
                int i = 0;

                for (DayEndData dayEndData : datas) {
                    candleEntries.add(new CandleEntry(i, dayEndData.getHighPrice(), dayEndData.getLowPrice(), dayEndData.getOpenPrice(), dayEndData.getClosePrice()));
                    barEntries.add(new BarEntry(i, dayEndData.getVolume()));
                    //Logger.d(dayEndData.getDifference() + " " + dayEndData.getVolume());
                    i++;
                }


                // ***************** SETTING CANDLE STICK DATA ************************
                CandleDataSet candleDataSet = new CandleDataSet(candleEntries, "Candle Stick");
                candleDataSet.setColor(Color.rgb(80, 80, 80));
                candleDataSet.setShadowColor(Color.DKGRAY);
                candleDataSet.setShadowWidth(0.7f);
                candleDataSet.setDecreasingColor(Color.RED);
                candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL);
                candleDataSet.setIncreasingColor(Color.rgb(122, 242, 84));
                candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL);
                candleDataSet.setNeutralColor(Color.BLUE);
                candleDataSet.setValueTextColor(Color.RED);


                CandleData candleData = new CandleData(candleDataSet);
                //*************** DONE WITH CANDLE DATA ********************/

                //*************** STARTING BAR DATA ************************/

                BarDataSet barDataSet = new BarDataSet(barEntries, "Volume");
                barDataSet.setColor(Color.RED);
                BarData barData = new BarData(barDataSet);
//                IAxisValueFormatter formatter = new IAxisValueFormatter() {
//                    @Override
//                    public String getFormattedValue(float value, AxisBase axis) {
//
//                        for (DayEndData dayEndData : datas) {
//                            if (dayEndData.getDifference() == value) {
//                                return dayEndData.getDate();
//                            }
//                        }
//                        return "0";
//                    }
//                };

                //************* DRAW GRAPH ******************
                graph.setDrawOrder(new CombinedChart.DrawOrder[]{
                        CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.CANDLE
                });

                //graph.getXAxis().setValueFormatter(formatter);
                CombinedData data = new CombinedData();

                data.setData(barData);
                data.setData(candleData);

                graph.setData(data);
                graph.invalidate();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                Logger.d(error.getMessage());

            }
        });
    }
}
