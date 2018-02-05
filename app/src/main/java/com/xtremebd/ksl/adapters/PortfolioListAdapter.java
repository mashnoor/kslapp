package com.xtremebd.ksl.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Item;

import java.util.List;

/**
 * Created by Mashnoor on 9/20/17.
 */

public class PortfolioListAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    public PortfolioListAdapter(List<Item> items) {
        super(R.layout.row_portfolio, items);
    }

    @Override
    protected void convert(BaseViewHolder holder, Item item) {
        holder.setText(R.id.tvItemName, item.getItem())
                .setText(R.id.tvNoStock, item.getNoOfStock())
                .setText(R.id.tvBuyPrice, item.getBuyPrice())
                .setText(R.id.tvLtp, item.getLtp())
                .setText(R.id.tvNetProfit, item.getNetProfit())
                .setText(R.id.tvNetProfitPercentage, item.getNetProfitPercentage())
                .setTextColor(R.id.tvNetProfitPercentage, item.getNetProfitColor())
                .setTextColor(R.id.tvNetProfit, item.getNetProfitColor());

    }

}
