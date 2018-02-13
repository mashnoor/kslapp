package com.xtremebd.ksl.services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.PortfolioHelper;
import com.xtremebd.ksl.utils.PriceAlertHelper;

import cz.msebera.android.httpclient.Header;

public class PriceAlertService extends Service {
    private Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;

    public PriceAlertService() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    public void onCreate() {
        Logger.d("Service Created");

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                updateAndNotifyPriceAlert();
                handler.postDelayed(runnable, 60000);
            }
        };

        handler.postDelayed(runnable, 15000);
    }

    private void updateAndNotifyPriceAlert() {
        Logger.d("Checking Update");
        if(PriceAlertHelper.getPriceAlertItems(context).size() == 0)
        {
            Logger.d("returned");
            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppURLS.GET_ALL_ITEMS_UPDATES, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Item[] allItems = Geson.g().fromJson(response, Item[].class);
                for (Item currItem : allItems) {
                    if (PriceAlertHelper.isItemInPriceAlert(context, currItem.getItem())) {
                        Item savedItem = PriceAlertHelper.getItem(context, currItem.getItem());


                        String latestLtp = currItem.getLtp();

                        if (!latestLtp.equals(savedItem.getLastValueForNotification())) {
                            //Decide wheather notification should be shown
                            String currItemHighVal = savedItem.getHighPrice();
                            String currItemLowVal = savedItem.getLowPrice();
                            double currItemHighValDouble = Double.parseDouble(currItemHighVal);
                            double currItemLowValDouble = Double.parseDouble(currItemLowVal);
                            double latestLtpDouble = Double.parseDouble(latestLtp);
                            if (latestLtpDouble > currItemHighValDouble) {
                                sendNotification("HIGH " + currItem.getItem() + "!", currItem.getItem() + " crossed high limit!");
                            } else if (latestLtpDouble < currItemLowValDouble) {
                                sendNotification("LOW " + currItem.getItem() + "!", currItem.getItem() + " crossed low limit!");
                            }
                        }

                        //Update the saved item
                        PriceAlertHelper.deleteItem(context, currItem.getItem());
                        currItem.setLastValueForNotification(currItem.getLtp());
                        currItem.setHighPrice(savedItem.getHighPrice());
                        currItem.setLowPrice(savedItem.getLowPrice());
                        PriceAlertHelper.addItemToPriceAlert(context, currItem);

                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Logger.d("Something went wrong");

            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public void sendNotification(String title, String body) {

        //Get an instance of NotificationManager//

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ksllogo)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setVibrate(new long[]{1000, 1000})
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);


        // Gets an instance of the NotificationManager service//

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than create a new one. In this example, the notification’s ID is 001//


        mNotificationManager.notify(001, mBuilder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
