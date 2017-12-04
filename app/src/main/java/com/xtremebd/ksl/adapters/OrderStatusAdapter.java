package com.xtremebd.ksl.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.OrderStatus;

import java.util.List;


/**
 * Created by Nowfel Mashnoor on 12/5/2017.
 */

public class OrderStatusAdapter extends BaseQuickAdapter<OrderStatus, BaseViewHolder> {
    public OrderStatusAdapter(@Nullable List<OrderStatus> data) {
        super(R.layout.row_orderstatus, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, OrderStatus item) {
        holder.setText(R.id.tvsettlor, "Settlor: " + item.getSettlor());
        holder.setText(R.id.tvPriceType, "Price Type: " + item.getPricetype());
        holder.setText(R.id.tvexch, "Exch: " + item.getExch());
        holder.setText(R.id.tvmc, "Mc: " + item.getMc());
        holder.setText(R.id.tvminfillqty, "Min Fill Qty: " + item.getMinfillqty());
        holder.setText(R.id.tvexecutedqty, "Executed Qty: " + item.getExecutedqty());
        holder.setText(R.id.tvprice, "Price: " + item.getPrice());
        holder.setText(R.id.tvavgprice, "Avg Price: " + item.getAvgprice());
        holder.setText(R.id.tvtime, "Time: " + item.getTime());
        holder.setText(R.id.tvsymbol, "Symbol: " + item.getSymbol());
        holder.setText(R.id.tvorderno, "Order No: " + item.getOrderno());
        holder.setText(R.id.tvboardtype, "Board Type: " + item.getBoardtype());
        holder.setText(R.id.tvbs, "BS: " + item.getBs());
        holder.setText(R.id.tvstatus, "Status: " + item.getStatus());
        holder.setText(R.id.tvorderqty, "Order Qty: " + item.getOrderqty());
        holder.setText(R.id.tvscripgroup, "Scrip Group: " + item.getScripgroup());
    }


}
