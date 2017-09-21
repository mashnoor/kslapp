package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.NewsAdapter;
import com.xtremebd.ksl.adapters.NotificationAdapter;
import com.xtremebd.ksl.models.Notification;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.DBHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsActivity extends AppCompatActivity {

    @BindView(R.id.rvNotifications)
    RecyclerView rvNotifications;
    NotificationAdapter adapter;
    SpotsDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        rvNotifications.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);
        dialog.show();
        showNotifications();
    }

    private void showNotifications()
    {
        ApiInterfaceGetter.getDynamicInterface().getNotifications(DBHelper.getMasterAccount(this).getMasterId()).enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                dialog.dismiss();
                List<Notification> notifcations = response.body();
                adapter = new NotificationAdapter(notifcations);
                rvNotifications.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {

            }
        });
    }


}
