package com.xtremebd.ksl.utils;

import android.app.Activity;
import android.widget.TextView;

import com.xtremebd.ksl.R;

/**
 * Created by mashnoor on 28/1/18.
 */

public class Sidebar {
    private Sidebar(){}

    public static void attach(Activity activity, String title)
    {
        TextView textView = activity.findViewById(R.id.pageTitle);
        textView.setText(title);
    }
}
