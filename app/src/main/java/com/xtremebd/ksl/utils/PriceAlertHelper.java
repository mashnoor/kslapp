package com.xtremebd.ksl.utils;


import android.content.Context;

import com.orhanobut.hawk.Hawk;
import com.xtremebd.ksl.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nowfel Mashnoor on 2/12/2018.
 */

public class PriceAlertHelper {

    private final static String DB_NAME = "pricealert";

    public static List<Item> getPriceAlertItems(Context ctx) {
        Hawk.init(ctx).build();
        return Hawk.get(DB_NAME, new ArrayList<Item>());
    }

    public static boolean isItemInPriceAlert(Context ctx, String itemName) {
        List<Item> allPriceAlertItems = getPriceAlertItems(ctx);
        for (Item i : allPriceAlertItems) {
            if (i.getItem().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public static void addItemToPriceAlert(Context ctx, Item item) {
        Hawk.init(ctx).build();
        List<Item> priceAlertItems = getPriceAlertItems(ctx);
        priceAlertItems.add(item);
        Hawk.put(DB_NAME, priceAlertItems);

    }

    public static Item getItem(Context ctx, String item_name) {
        List<Item> priceAlertItems = getPriceAlertItems(ctx);
        for (Item currItem : priceAlertItems) {
            if (currItem.getItem().equals(item_name)) {
                return currItem;
            }
        }
        return null;
    }

    public static void deleteItem(Context ctx, String item_name) {
        List<Item> priceAlertItems = getPriceAlertItems(ctx);
        for (Item item : priceAlertItems) {
            if (item.getItem().equals(item_name)) {
                priceAlertItems.remove(item);
                Hawk.put(DB_NAME, priceAlertItems);
                return;
            }
        }
    }
}
