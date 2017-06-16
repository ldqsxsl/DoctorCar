package com.doctorcar.mobile.module.blog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.commonutils.ImageLoaderUtils;
import com.doctorcar.mobile.common.commonutils.TimeUtil;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.database.Articles;
import com.doctorcar.mobile.module.ask.adapter.AskAlreadyAdapter;
import com.doctorcar.mobile.module.ask.bean.ProblemBean;
import com.doctorcar.mobile.utils.TLUtil;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dd on 2017/5/8.
 */

public class DraftBoxAdapter  extends RecyclerView.Adapter<DraftBoxAdapter.ViewHolder> {

    private Context mContext;
    private List<Articles> mDatas;
    private LayoutInflater mInflater;
    private static OnItemClickViewListener onRecyclerViewListener;

    public DraftBoxAdapter(Context mContext, List<Articles> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public DraftBoxAdapter setDatas(List<Articles> datas) {
        mDatas = datas;
        notifyDataSetChanged();
        return this;
    }

    public static void setOnRecyclerViewListener(OnItemClickViewListener onRecyclerViewListener) {
        DraftBoxAdapter.onRecyclerViewListener = onRecyclerViewListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DraftBoxAdapter.ViewHolder(mInflater.inflate(R.layout.draft_box_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Articles articles = mDatas.get(position);
        holder.titleTv.setText(articles.getTitle());
//        holder.timeTv.setText(TimeUtil.formatData(TimeUtil.dateFormatYMDofChinese,Long.parseLong("")));
        holder.typeTv.setText("");

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecyclerViewListener != null){
                    onRecyclerViewListener.onItemClick(position,articles);
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
            titleTv = (TextView) itemView.findViewById(R.id.draft_box_item_title_tv);
            typeTv = (TextView) itemView.findViewById(R.id.draft_box_item_type_tv);
            timeTv = (TextView) itemView.findViewById(R.id.draft_box_item_time_tv);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
        }
    }

}
