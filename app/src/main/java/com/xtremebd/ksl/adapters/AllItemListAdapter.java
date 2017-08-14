package com.xtremebd.ksl.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Item;

import java.util.List;

/**
 * Created by Mashnoor on 8/10/17.
 */

public class AllItemListAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {

    public AllItemListAdapter(List<Item> items) {
        super(R.layout.row_item_latest_update, items);
    }


    @Override
    protected void convert(BaseViewHolder holder, Item item) {
        holder.setText(R.id.txtItem, item.getItem())
                .setText(R.id.txtChange, item.getChange())
                .setText(R.id.txtLTP, item.getLtp())
                .setText(R.id.txtVolume, item.getVolume());

    }
}
