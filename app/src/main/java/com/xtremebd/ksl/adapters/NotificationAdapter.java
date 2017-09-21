package com.xtremebd.ksl.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.News;
import com.xtremebd.ksl.models.Notification;

import java.util.List;

/**
 * Created by Mashnoor on 9/22/17.
 */

public class NotificationAdapter extends BaseQuickAdapter<Notification, BaseViewHolder>
{
    public NotificationAdapter( @Nullable List<Notification> data) {
        super(R.layout.row_notification, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Notification notification) {
        holder.setText(R.id.tvNotificationTitle, notification.getTitle())
                .setText(R.id.tvNotificationBody, notification.getMessage())
                .setText(R.id.tvNotificationDate, notification.getTime());
    }
}
