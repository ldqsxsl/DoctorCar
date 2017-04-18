package com.doctorcar.mobile.module.ask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.bean.AnswerBean;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dd on 2017/4/18.
 */

public class AnswerCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private List<AnswerCommentBean> mDatas = new ArrayList<AnswerCommentBean>();
    private View mHeaderView;

    public AnswerCommentAdapter(Context mContext, List<AnswerCommentBean> data) {
        this.mContext = mContext;
        this.mDatas = data;
    }
    private static OnItemClickViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnItemClickViewListener onRecyclerViewListener) {
        AnswerCommentAdapter.onRecyclerViewListener = onRecyclerViewListener;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getHeaderView() {
        return mHeaderView;
    }
    public void addDatas(List<AnswerCommentBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null) return TYPE_NORMAL;
        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) return new AnswerCommentAdapter.Holder(mHeaderView);
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_answer_item, parent, false);
        return new AnswerCommentAdapter.Holder(layout);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(viewHolder);
        final AnswerCommentBean data = mDatas.get(pos);
        if(viewHolder instanceof AnswerCommentAdapter.Holder) {
            ((AnswerCommentAdapter.Holder) viewHolder).text.setText(data.getComment_answer_content());
            if(onRecyclerViewListener == null) return;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewListener.onItemClick(pos, data);
                }
            });
        }
    }
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }
    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }
    class Holder extends RecyclerView.ViewHolder {
        TextView text;
        public Holder(View itemView) {
            super(itemView);
            if(itemView == mHeaderView) return;
            text = (TextView) itemView.findViewById(R.id.answer_comment_item_content_tv);
        }
    }
}
