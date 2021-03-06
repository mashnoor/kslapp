package com.xtremebd.ksl.adapters;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Item;

import java.util.List;

/**
 * Created by Mashnoor on 8/10/17.
 */

public class SelectItemAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {

    public SelectItemAdapter(List<Item> items) {
        super(R.layout.row_item, items);
    }


    @Override
    protected void convert(BaseViewHolder holder, Item item) {
        holder.setText(R.id.txtItem, item.getItem())
                .setText(R.id.txtChange, item.getChangepercentage())
                .setText(R.id.txtLTP, item.getLtp())
                .setText(R.id.txtVolume, item.getVolume())
                .setTextColor(R.id.txtChange, item.getColor());

    }

}
