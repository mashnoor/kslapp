package com.xtremebd.ksl.utils;


import android.app.Activity;
import android.util.Log;


import com.orhanobut.hawk.Hawk;

import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.models.MasterAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mashnoor on 8/16/17.
 */

public class DBHelper {

    public static void setMasterAccount(Activity activity, MasterAccount masterAccount) {

        Hawk.init(activity).build();
        Hawk.put("masteraccount", masterAccount);

    }


    public static MasterAccount getMasterAccount(Activity activity) {

        Hawk.init(activity).build();
        return Hawk.get("masteraccount");


    }

    public static void updateAllWatchlistItems(Activity activity, List<Item> items) {
        List<Item> watchlistItesm = getWatchlistItems(activity);
        for (Item item : items) {

        }
    }


    public static boolean isIteminWatchlist(Activity activity, String itemName) {
        List<Item> allItems = getWatchlistItems(activity);
        if (allItems == null) return false;
        for (Item currItem : allItems) {
            if (currItem.getItem().equals(itemName)) {
                return true;
            }

        }
        return false;


    }

    public static boolean isIteminPortfolio(Activity activity, String itemName)
    {
        List<Item> allPortfolioItems = getPortfolioItems(activity);
        for (Item currItem : allPortfolioItems) {
            if (currItem.getItem().equals(itemName)) {
                return true;
            }

        }
        return false;
    }

    public static List<Item> getPortfolioItems(Activity activity) {
        return Hawk.get("portfolio", new ArrayList<Item>());
    }

    public static List<Item> getWatchlistItems(Activity activity) {
        Hawk.init(activity).build();
        return Hawk.get("watchlist", new ArrayList<Item>());
    }

    public static void addIteminPortfolio(Activity activity, Item item)
    {
        Hawk.init(activity).build();
        List<Item> portfolioItems = getPortfolioItems(activity);
        portfolioItems.add(item);
        Hawk.put("portfolio", portfolioItems);
    }

    public static void addIteminWatchList(Activity activity, Item item) {
        Hawk.init(activity).build();
        List<Item> wathclistItems = getWatchlistItems(activity);
        if (wathclistItems == null) {
            wathclistItems = new ArrayList<>();
        }
        Log.d("---------db helper", item.getItem());
        wathclistItems.add(item);
        Hawk.put("watchlist", wathclistItems);


    }

    public static void deleteItemFromWatchList(Activity activity, Item item) {


    }

}
