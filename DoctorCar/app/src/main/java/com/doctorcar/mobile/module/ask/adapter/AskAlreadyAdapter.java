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
import com.doctorcar.mobile.common.commonutils.ImageLoaderUtils;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.module.ask.bean.BrandBean;
import com.doctorcar.mobile.module.ask.bean.ProblemBean;
import com.doctorcar.mobile.module.ask.bean.ProblemResult;
import com.doctorcar.mobile.utils.TLUtil;

import java.util.List;

public class AskAlreadyAdapter extends RecyclerView.Adapter<AskAlreadyAdapter.ViewHolder> {

    private Context mContext;
    private List<ProblemBean> mDatas;
    private LayoutInflater mInflater;


    public AskAlreadyAdapter(Context mContext, List<ProblemBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    private static OnItemClickViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnItemClickViewListener onRecyclerViewListener) {
        AskAlreadyAdapter.onRecyclerViewListener = onRecyclerViewListener;
    }

    public List<ProblemBean> getDatas() {
        return mDatas;
    }

    public AskAlreadyAdapter setDatas(List<ProblemBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public AskAlreadyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.ask_already_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ProblemBean problemBean = mDatas.get(position);
        holder.brandName.setText(problemBean.getProblem_content());
        holder.content.setTag(R.id.tag,position);
        ImageLoaderUtils.display(mContext,holder.brandImg,"http://192.168.20.87:8080/images/"+problemBean.getList_image().get(0).getImage_path());
        TLUtil.showLog("http://192.168.20.87:8080/images/"+problemBean.getList_image().get(0));
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecyclerViewListener != null){
                    onRecyclerViewListener.onItemClick(position,problemBean);
                }
            }
        });
//        holder.brandImg.setImageResource(R.mipmap.car_brand_baoma);
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
