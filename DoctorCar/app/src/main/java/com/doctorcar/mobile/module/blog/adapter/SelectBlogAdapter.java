package com.doctorcar.mobile.module.blog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.blog.bean.ArticleBean;

import java.util.List;

/**
 * Created by dd on 2017/5/9.
 */

public class SelectBlogAdapter extends RecyclerView.Adapter<SelectBlogAdapter.ViewHolder>{

    private Context mContext;
    private List<ArticleBean> mDatas;
    private LayoutInflater mInflater;
    private static OnItemClickViewListener onRecyclerViewListener;

    public SelectBlogAdapter(Context mContext, List<ArticleBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public SelectBlogAdapter setDatas(List<ArticleBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
        return this;
    }

    public static void setOnRecyclerViewListener(OnItemClickViewListener onRecyclerViewListener) {
        SelectBlogAdapter.onRecyclerViewListener = onRecyclerViewListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectBlogAdapter.ViewHolder(mInflater.inflate(R.layout.mine_article_fg_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ArticleBean articleBean = mDatas.get(position);
        holder.titleTv.setText(articleBean.getArticle_title());
//        holder.timeTv.setText(TimeUtil.formatData(TimeUtil.dateFormatYMDofChinese,Long.parseLong("")));
        holder.typeTv.setText("");

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecyclerViewListener != null){
                    onRecyclerViewListener.onItemClick(position,articleBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTv;
        public TextView typeTv;
        public TextView timeTv;
        public LinearLayout ll;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.mine_article_fg_item_title_tv);
            typeTv = (TextView) itemView.findViewById(R.id.mine_article_fg_item_type_tv);
            timeTv = (TextView) itemView.findViewById(R.id.mine_article_fg_item_time_tv);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
        }
    }
}
