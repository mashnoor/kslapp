package com.xtremebd.ksl.adapters;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Ledger;

import java.util.List;

/**
 * Created by Nowfel Mashnoor on 12/5/2017.
 */

public class LedgerAdapter extends BaseQuickAdapter<Ledger, BaseViewHolder> {
    public LedgerAdapter(@Nullable List<Ledger> data) {
        super(R.layout.row_ledger, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Ledger item) {
        holder.setText(R.id.tvdocumnet_date, "Date: " + item.getDocumnetDate());
        holder.setText(R.id.tvexplanation, "Explanation: " + item.getExplanation());
        holder.setText(R.id.tvTransactionType, "Transaction Type: "+ item.getTransactionType());
        holder.setText(R.id.tvscrip_quantity, "Scrip QTY: " + item.getScripQuantity());
        holder.setText(R.id.tvscrip_rate, "Rate: " + item.getScripRate());
        holder.setText(R.id.tvdr, "Dr: " + item.getDr());
        holder.setText(R.id.tvcr, "Cr: " + item.getCr());
        holder.setText(R.id.tvbalance, "Balance: " + item.getBalance());

        //holder.setText(R.id.tvdocumnet_number, "Document Number: " + item.getDocumnetNumber());
        //holder.setText(R.id.tvdr_total, "Dr Total: " + item.getDrTotal());
        //holder.setText(R.id.tvbrokerage, "Brokerage: " + item.getBrokerage());
       // holder.setText(R.id.tvcr_total, "Cr Total: " + item.getCrTotal());

    }
}
