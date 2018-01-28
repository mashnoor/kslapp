package com.xtremebd.ksl.adapters;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Item;

import java.util.List;

/**
 * Created by Mashnoor on 8/15/17.
 */

public class MarketMoversAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    private int position = -1;
    public MarketMoversAdapter(@Nullable List<Item> data, int position) {
        super(R.layout.row_market_movers, data);
        this.position = position;
    }

    @Override
    protected void convert(BaseViewHolder holder, Item item) {
        holder.setText(R.id.tvItemName, item.getItem())
                .setText(R.id.tvLTP, item.getLtp());
        if(position == 0)
        {
            //Show Value
            holder.setText(R.id.tvSubjectData, item.getValue());
        }
        else if(position == 1)
        {
            //Show Value
            holder.setText(R.id.tvSubjectData, item.getVolume());
        }
        else if(position == 2)
        {
            //Show Value
            holder.setText(R.id.tvSubjectData, item.getTotaltrade());
        }



    }
}
