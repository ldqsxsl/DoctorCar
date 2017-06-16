package com.doctorcar.mobile.module.ask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.commonutils.TimeUtil;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.bean.ProblemBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dd on 2017/5/18.
 */

public class WaitSolveAdapter extends RecyclerView.Adapter<WaitSolveAdapter.ViewHolder> {

    private Context mContext;
    private List<ProblemBean> mDatas;
    private LayoutInflater mInflater;
    private static OnItemClickViewListener onRecyclerViewListener;

    public WaitSolveAdapter(Context mContext, List<ProblemBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public static void setOnRecyclerViewListener(OnItemClickViewListener onRecyclerViewListener) {
        WaitSolveAdapter.onRecyclerViewListener = onRecyclerViewListener;
    }
    public WaitSolveAdapter setDatas(List<ProblemBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WaitSolveAdapter.ViewHolder(mInflater.inflate(R.layout.my_focus_problem_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ProblemBean problemBean = mDatas.get(position);
        holder.nameTv.setText(problemBean.getProblem_title());
        holder.contentTv.setText(problemBean.getProblem_content());
        holder.timeTv.setText(TimeUtil.formatData(TimeUtil.dateFormatYMDofChinese,Long.parseLong(problemBean.getProblem_time())));
//        ImageLoaderUtils.display(mContext,holder.headTv,"http://192.168.20.87:8080/images/"+problemBean.getList_image().get(0).getImage_path());


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
        public TextView nameTv;
        public TextView contentTv;
        public TextView timeTv;
        public CircleImageView headTv;
        public LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.my_focus_problem_item_name_tv);
            contentTv = (TextView) itemView.findViewById(R.id.my_focus_problem_item_content_tv);
            headTv = (CircleImageView) itemView.findViewById(R.id.my_focus_problem_item_iv);
            timeTv = (TextView) itemView.findViewById(R.id.my_focus_problem_item_time_tv);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
        }
    }
}
