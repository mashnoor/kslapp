package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.DayEndData;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CandleStickChartActivity extends AppCompatActivity {

    @BindView(R.id.fromDate)
    EditText etFromDate;
    @BindView(R.id.toDate)
    EditText etTodate;

    @BindView(R.id.chart)
    CandleStickChart candlestickChart;

    AsyncHttpClient client;
    ProgressDialog dialog;

    int which;

    String companyName;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candle_stick_chart);
        ButterKnife.bind(this);
        client = new AsyncHttpClient();
        dialog = new ProgressDialog(this);
        companyName = getIntent().getStringExtra("company");
        dialog.setMessage("Loading and drawing graph...");
        Logger.addLogAdapter(new AndroidLogAdapter());
        registerCalenderListener();
        TopBar.attach(this, "CANDLE STICK CHART");
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
                new DatePickerDialog(CandleStickChartActivity.this, AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        etTodate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                which = 1;
                new DatePickerDialog(CandleStickChartActivity.this, AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }
    public void goVolumeGraph(View v)
    {
        Intent intent = new Intent(this, VolumeGraphActivity.class);
        intent.putExtra("company", companyName);

        startActivity(intent);

    }

    public void viewGraph(final View v) {
        if(etFromDate.getText().toString().trim().isEmpty() || etTodate.getText().toString().trim().isEmpty())
        {
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


                candlestickChart.setData(data);


                candlestickChart.invalidate();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                Logger.d(error.getMessage());

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
