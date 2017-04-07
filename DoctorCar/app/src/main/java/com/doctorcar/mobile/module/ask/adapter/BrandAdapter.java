package com.doctorcar.mobile.module.ask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.bean.BrandBean;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    private Context mContext;
    private List<BrandBean> mDatas;
    private LayoutInflater mInflater;
    private Integer pos = 0;
    private View clickView = null;

    public BrandAdapter(Context mContext, List<BrandBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    private static OnItemClickViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnItemClickViewListener onRecyclerViewListener) {
        BrandAdapter.onRecyclerViewListener = onRecyclerViewListener;
    }

    public List<BrandBean> getDatas() {
        return mDatas;
    }

    public BrandAdapter setDatas(List<BrandBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public BrandAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.brand_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BrandBean brandBean = mDatas.get(position);
        holder.brandName.setText(brandBean.getBrand_name());
        if (position == pos){
            holder.content.setBackgroundColor(mContext.getResources().getColor(R.color.chat_tip_color));
        }
        holder.content.setTag(R.id.tag,position);
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = (Integer) v.getTag(R.id.tag);
                if (onRecyclerViewListener != null){
                    onRecyclerViewListener.onItemClick(position,brandBean);
                }
               notifyDataSetChanged();
            }
        });
        holder.brandImg.setImageResource(R.mipmap.car_brand_baoma);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView brandName;
        public ImageView brandImg;
        public LinearLayout content;

        public ViewHolder(View itemView) {
            super(itemView);
            brandName = (TextView) itemView.findViewById(R.id.brand_item_name);
            brandImg = (ImageView) itemView.findViewById(R.id.brand_item_img);
            content = (LinearLayout) itemView.findViewById(R.id.content);
        }
    }
}
