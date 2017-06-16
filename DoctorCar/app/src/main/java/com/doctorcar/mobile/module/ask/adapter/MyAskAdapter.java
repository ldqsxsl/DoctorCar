package com.doctorcar.mobile.module.ask.adapter;

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
import com.doctorcar.mobile.module.ask.bean.ProblemBean;
import com.doctorcar.mobile.utils.TLUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dd on 2017/5/10.
 */

public class MyAskAdapter extends RecyclerView.Adapter<MyAskAdapter.ViewHolder>{

    private Context mContext;
    private List<ProblemBean> mDatas;
    private LayoutInflater mInflater;
    private static OnItemClickViewListener onRecyclerViewListener;


    public MyAskAdapter(Context mContext, List<ProblemBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public MyAskAdapter setDatas(List<ProblemBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
        return this;
    }

    public static void setOnRecyclerViewListener(OnItemClickViewListener onRecyclerViewListener) {
        MyAskAdapter.onRecyclerViewListener = onRecyclerViewListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAskAdapter.ViewHolder(mInflater.inflate(R.layout.my_ask_fg_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ProblemBean problemBean = mDatas.get(position);
        holder.problemTv.setText(problemBean.getProblem_title());
        holder.answerTv.setText(problemBean.getProblem_content());
        holder.statusTv.setText(TimeUtil.formatData(TimeUtil.dateFormatYMDofChinese,Long.parseLong(problemBean.getProblem_time())));


        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecyclerViewListener != null){
                    onRecyclerViewListener.onItemClick(position,problemBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView problemTv;
        public TextView answerTv;
        public TextView praiseTv;
        public TextView statusTv;

        public LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            problemTv = (TextView) itemView.findViewById(R.id.my_ask_fg_item_problem_tv);
            answerTv = (TextView) itemView.findViewById(R.id.my_ask_fg_item_answer_tv);
            praiseTv = (TextView) itemView.findViewById(R.id.my_ask_fg_item_praise_tv);
            statusTv = (TextView) itemView.findViewById(R.id.my_ask_fg_item_status_tv);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
        }
    }
}
