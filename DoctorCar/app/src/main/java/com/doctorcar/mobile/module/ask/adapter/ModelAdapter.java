package com.doctorcar.mobile.module.ask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doctorcar.mobile.R;


import com.doctorcar.mobile.common.interf.OnModelItemClickViewListener;
import com.doctorcar.mobile.module.ask.bean.BrandBean;
import com.doctorcar.mobile.module.ask.bean.ModelBean;

import java.util.List;

/**
 * Created by dd on 2017/3/9.
 */

public class ModelAdapter  extends RecyclerView.Adapter<ModelAdapter.ViewHolder>{
    private Context mContext;
    private List<ModelBean> mDatas;
    private LayoutInflater mInflater;

    public ModelAdapter(Context mContext, List<ModelBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public static OnModelItemClickViewListener onModelItemClickViewListener;

    public static void setOnModelItemClickViewListener(OnModelItemClickViewListener onModelItemClickViewListener) {
        ModelAdapter.onModelItemClickViewListener = onModelItemClickViewListener;
    }


    public List<ModelBean> getDatas() {
        return mDatas;
    }

    public ModelAdapter setDatas(List<ModelBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public ModelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ModelAdapter.ViewHolder(mInflater.inflate(R.layout.model_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ModelAdapter.ViewHolder holder, final int position) {
        final ModelBean modelBean = mDatas.get(position);

        holder.modelName.setText(modelBean.getModel_name());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onModelItemClickViewListener != null){
                    onModelItemClickViewListener.onModelItemClick(position,modelBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView modelName;
        public LinearLayout content;

        public ViewHolder(View itemView) {
            super(itemView);
            modelName = (TextView) itemView.findViewById(R.id.model_item_name);

            content = (LinearLayout) itemView.findViewById(R.id.model_item_ll);
        }
    }

}
