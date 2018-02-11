package com.xtremebd.ksl.adapters;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.News;

import java.util.List;

/**
 * Created by Mashnoor on 8/15/17.
 */

public class NewsAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
    public NewsAdapter(@Nullable List<News> data) {
        super(R.layout.row_news, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, News news) {
        holder.setText(R.id.tvNewsTitle, news.getTitle())
                .setText(R.id.tvNewsBody, news.getBody())
                .setText(R.id.tvDate, news.getDate());
    }
}
