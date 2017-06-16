package com.doctorcar.mobile.module.ask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.commonutils.TimeUtil;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.bean.AnswerBean;
import com.doctorcar.mobile.utils.TLUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dd on 2017/4/18.
 */

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private List<AnswerBean> mDatas = new ArrayList<AnswerBean>();
    private View mHeaderView;

    public AnswerAdapter(Context mContext, List<AnswerBean> data) {
        this.mContext = mContext;
        this.mDatas = data;
    }
    private static OnItemClickViewListener onRecyclerViewListener;
    private PraiseClickListener praiseClickListener;

    public void setPraiseClickListener(PraiseClickListener praiseClickListener) {
        this.praiseClickListener = praiseClickListener;
    }

    public void setOnRecyclerViewListener(OnItemClickViewListener onRecyclerViewListener) {
        AnswerAdapter.onRecyclerViewListener = onRecyclerViewListener;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getHeaderView() {
        return mHeaderView;
    }
    public void addDatas(List<AnswerBean> datas) {
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
        if(mHeaderView != null && viewType == TYPE_HEADER) return new AnswerAdapter.Holder(mHeaderView);
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item, parent, false);
        return new AnswerAdapter.Holder(layout);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(viewHolder);
        final AnswerBean data = mDatas.get(pos);
        if(viewHolder instanceof AnswerAdapter.Holder) {
            ((AnswerAdapter.Holder) viewHolder).text.setText(data.getAnswer_content());
            ((Holder) viewHolder).praiseNumberTv.setText(data.getAnswer_praise_number()+"");
            ((Holder) viewHolder).answerCommentNumberTv.setText(data.getAnswer_comment_number()+"评论");
            if(data.is_my_praise()){
                ((Holder) viewHolder).praiseIv.setImageResource(R.mipmap.icon_zan);
            }else{
                ((Holder) viewHolder).praiseIv.setImageResource(R.mipmap.icon_zan_red);
            }
            ((Holder) viewHolder).answerTimeTv.setText(TimeUtil.formatData(TimeUtil.dateFormatYMDofChinese,Long.parseLong(data.getAnswer_time())));
            if(onRecyclerViewListener == null) return;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewListener.onItemClick(pos, data);
                }
            });

            ((Holder) viewHolder).praiseRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(praiseClickListener != null){
                        praiseClickListener.praise(v,data);
                    }
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
        TextView praiseNumberTv;
        TextView answerTimeTv;
        TextView answerCommentNumberTv;
        ImageView praiseIv;
        RelativeLayout praiseRl;
        public Holder(View itemView) {
            super(itemView);
            if(itemView == mHeaderView) return;
            answerTimeTv = (TextView) itemView.findViewById(R.id.answer_item_time_tv);
            text = (TextView) itemView.findViewById(R.id.answer_item_content_tv);
            praiseNumberTv = (TextView) itemView.findViewById(R.id.answer_item_praise_number_tv);
            answerCommentNumberTv = (TextView) itemView.findViewById(R.id.answer_item_comment_number_tv);
            praiseIv = (ImageView) itemView.findViewById(R.id.answer_item_praise_number_iv);

            praiseRl = (RelativeLayout) itemView.findViewById(R.id.answer_item_praise_rl);
        }
    }

    public static interface PraiseClickListener{
        void praise(View view,AnswerBean answerBean);
    }

}
