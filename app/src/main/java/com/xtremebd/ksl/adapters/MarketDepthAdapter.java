package com.xtremebd.ksl.adapters;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.MarketDepth;

import java.util.List;

/**
 * Created by mashnoor on 30/1/18.
 */

public class MarketDepthAdapter extends BaseQuickAdapter<MarketDepth, BaseViewHolder> {
    public MarketDepthAdapter(@Nullable List<MarketDepth> data) {
        super(R.layout.row_market_depth, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, MarketDepth item) {
        holder.setText(R.id.tvPrice, item.getPrice())
                .setText(R.id.tvVolume, item.getVolume());

    }
}
