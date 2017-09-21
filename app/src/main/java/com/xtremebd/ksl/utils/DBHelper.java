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
        return Hawk.get("masteraccount", null);


    }







}
