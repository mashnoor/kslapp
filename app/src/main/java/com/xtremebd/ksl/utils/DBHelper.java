package com.xtremebd.ksl.utils;


import android.app.Activity;
import android.content.Context;

import com.orhanobut.hawk.Hawk;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.models.MasterAccount;

import java.util.List;

/**
 * Created by Mashnoor on 8/16/17.
 */

public class DBHelper {

    public static void setMasterAccount(Activity activity, MasterAccount masterAccount) {

        Hawk.init(activity).build();
        Hawk.put("masteraccount", masterAccount);

    }


    public static MasterAccount getMasterAccount(Activity activity)
    {

        Hawk.init(activity).build();
        return Hawk.get("masteraccount");


    }


    public static boolean isIteminWatchlist(Activity activity, Item item) throws SnappydbException {
        DB snappydb = DBFactory.open(activity);
        List<Item> watchlist_items = snappydb.getObject("watchlist", List.class);
        snappydb.close();
        return watchlist_items.contains(item);

    }

    public static void addIteminWatchList(Activity activity, Item item) throws SnappydbException {
        DB snappydb = DBFactory.open(activity);
        List<Item> watchlist_items = snappydb.getObject("watchlist", List.class);
        watchlist_items.add(item);
        snappydb.put("watchlist", watchlist_items);
        snappydb.close();

    }

    public static void deleteItemFromWatchList(Activity activity, Item item) throws SnappydbException {
        DB snappydb = DBFactory.open(activity);
        List<Item> watchlist_items = snappydb.getObject("watchlist", List.class);
        watchlist_items.remove(item);
        snappydb.put("watchlist", watchlist_items);
        snappydb.close();


    }

}
