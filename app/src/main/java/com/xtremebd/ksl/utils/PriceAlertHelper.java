package com.xtremebd.ksl.utils;

import android.app.Activity;

import com.orhanobut.hawk.Hawk;
import com.xtremebd.ksl.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nowfel Mashnoor on 2/12/2018.
 */

public class PriceAlertHelper {

    private final static String DB_NAME = "pricealert";

    public static List<Item> getPriceAlertItems(Activity activity) {
        Hawk.init(activity).build();
        return Hawk.get(DB_NAME, new ArrayList<Item>());
    }

    public static boolean isItemInPriceAlert(Activity activity, String itemName) {
        List<Item> allPriceAlertItems = getPriceAlertItems(activity);
        for (Item i : allPriceAlertItems) {
            if (i.getItem().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public static void addItemToPriceAlert(Activity activity, Item item) {
        Hawk.init(activity).build();
        List<Item> priceAlertItems = getPriceAlertItems(activity);
        priceAlertItems.add(item);
        Hawk.put(DB_NAME, priceAlertItems);

    }
    public static void deleteItem(Activity activity, String item_name)
    {
        List<Item> priceAlertItems = getPriceAlertItems(activity);
        for(Item item : priceAlertItems)
        {
            if(item.getItem().equals(item_name))
            {
                priceAlertItems.remove(item);
                Hawk.put("watchlist", priceAlertItems);
                return;
            }
        }
    }
}
