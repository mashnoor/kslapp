package com.xtremebd.ksl.adapters;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.models.ITSAccount;
import com.xtremebd.ksl.R;

import java.util.List;

/**
 * Created by rajit on 9/11/17.
 */

public class ITSAccountAdapter extends BaseQuickAdapter<ITSAccount, BaseViewHolder> {
    public ITSAccountAdapter(@Nullable List<ITSAccount> data) {
        super(R.layout.row_its_account, data);
    }


    @Override
    protected void convert(BaseViewHolder holder, ITSAccount account) {
        holder.setText(R.id.tvItsAccountNo, account.getItsAccountNo());
        holder.addOnClickListener(R.id.btnDelete);
        holder.addOnClickListener(R.id.btnEdit);

    }
}
