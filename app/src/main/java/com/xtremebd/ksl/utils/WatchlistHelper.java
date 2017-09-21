package com.xtremebd.ksl.utils;

import android.app.Activity;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.xtremebd.ksl.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mashnoor on 9/21/17.
 */

public class WatchlistHelper {
    public static boolean isIteminWatchlist(Activity activity, String itemName) {
        List<Item> allItems = getWatchlistItems(activity);
        for (Item currItem : allItems) {
            if (currItem.getItem().equals(itemName)) {
                return true;
            }

        }
        return false;


    }




    public static List<Item> getWatchlistItems(Activity activity) {
        Hawk.init(activity).build();
        return Hawk.get("watchlist", new ArrayList<Item>());
    }




    public static void addIteminWatchList(Activity activity, Item item) {
        Hawk.init(activity).build();
        List<Item> wathclistItems = getWatchlistItems(activity);

        Log.d("---------db helper", item.getItem());
        wathclistItems.add(item);
        Hawk.put("watchlist", wathclistItems);


    }

    public static void deleteItemFromWatchList(Activity activity, String item_name)
    {
        List<Item> watchlistItems = getWatchlistItems(activity);
        for(Item item : watchlistItems)
        {
            if(item.getItem().equals(item_name))
            {
                watchlistItems.remove(item);
                Hawk.put("watchlist", watchlistItems);
                return;
            }
        }
    }

}
