package com.xtremebd.ksl.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Item;

import java.util.List;

/**
 * Created by mashnoor on 22/1/18.
 */

public class TopGainerLoserAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {

    public TopGainerLoserAdapter(List<Item> data) {
        super(R.layout.row_top_gainer_loser, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Item item) {
        holder.setText(R.id.tvSymbol, item.getItem())
                .setText(R.id.tvChange, item.getChange())
                .setText(R.id.tvLTP, item.getLtp())
                .setText(R.id.tvChange, item.getChange())
                .setText(R.id.tvChangePercentage, item.getChangepercentage());
    }
}
