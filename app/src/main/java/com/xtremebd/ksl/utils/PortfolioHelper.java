package com.xtremebd.ksl.utils;

import android.app.Activity;

import com.orhanobut.hawk.Hawk;
import com.xtremebd.ksl.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mashnoor on 9/21/17.
 */

public class PortfolioHelper {



    public static List<Item> getPortfolioItems(Activity activity) {
        return Hawk.get("portfolio", new ArrayList<Item>());
    }

    public static Item getItem(Activity activity,String itemName)
    {
        List<Item> portfolioItems = getPortfolioItems(activity);
        for (Item currItem : portfolioItems) {
            if (currItem.getItem().equals(itemName)) {
                return currItem;
            }

        }
        return null;
    }

    public static void addIteminPortfolio(Activity activity, Item item)
    {
        Hawk.init(activity).build();

        if(isIteminPortfolio(activity, item.getItem()))
        {
            deleteItemFromPortfolio(activity, item.getItem());
        }
        List<Item> portfolioItems = getPortfolioItems(activity);
        portfolioItems.add(item);
        Hawk.put("portfolio", portfolioItems);
    }
    public static void deleteItemFromPortfolio(Activity activity, String item_name)
    {
        List<Item> portfolioItems = getPortfolioItems(activity);
        for(Item item : portfolioItems)
        {
            if(item.getItem().equals(item_name))
            {
                portfolioItems.remove(item);
                Hawk.put("portfolio", portfolioItems);
                return;
            }
        }
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
}
