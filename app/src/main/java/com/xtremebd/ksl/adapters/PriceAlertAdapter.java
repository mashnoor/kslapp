package com.xtremebd.ksl.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Item;

import java.util.List;

/**
 * Created by Nowfel Mashnoor on 2/12/2018.
 */

public class PriceAlertAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    public PriceAlertAdapter(@Nullable List<Item> priceAlertdata) {
        super(R.layout.row_price_alert, priceAlertdata);
    }

    @Override
    protected void convert(BaseViewHolder holder, Item item) {
        holder.setText(R.id.tvSymbol, item.getItem())
                .setText(R.id.tvLtp, item.getLtp())
                .setText(R.id.tvLow, item.getLowPrice())
                .setText(R.id.tvHigh, item.getHighPrice());
    }
}
