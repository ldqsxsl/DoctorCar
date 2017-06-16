package com.doctorcar.mobile.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.interf.OnItemClickViewListener;
import com.doctorcar.mobile.utils.TLUtil;

import java.util.List;

/**
 * Created by dd on 2017/5/8.
 */

public class ListDialogFragment extends BaseDialogFragment{

    private ListDialogChoiceListener listDialogChoiceListener;

    public void setListDialogChoiceListener(ListDialogChoiceListener listDialogChoiceListener) {
        this.listDialogChoiceListener = listDialogChoiceListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_dialog,container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_fg_dialog_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.line));
        recyclerView.addItemDecoration(dividerItemDecoration);// 添加分割线。
        String[]arrays = getActivity().getResources().getStringArray(R.array.article_dialog);
        ListDialogAdapter listDialogAdapter = new ListDialogAdapter(getActivity(),arrays);
        listDialogAdapter.setOnRecyclerViewListener(new OnItemClickViewListener<String>() {
            @Override
            public void onItemClick(int position, String object) {
                if (listDialogChoiceListener != null){
                    listDialogChoiceListener.choicePosition(position);
                    dismiss();
                }

            }
        });
        recyclerView.setAdapter(listDialogAdapter);
        return view;
    }


    public interface ListDialogChoiceListener{
        public void choicePosition(int position);
    }



    class  ListDialogAdapter extends RecyclerView.Adapter<ListDialogAdapter.ViewHolder>{
        private Context mContext;
        private String[] mDatas;
        private LayoutInflater mInflater;
        private OnItemClickViewListener onRecyclerViewListener;

        public ListDialogAdapter(Context mContext, String[] mDatas) {
            this.mContext = mContext;
            this.mDatas = mDatas;
            mInflater = LayoutInflater.from(mContext);
        }

        public ListDialogAdapter setDatas(String[] datas) {
            mDatas = datas;
            notifyDataSetChanged();
            return this;
        }

        public void setOnRecyclerViewListener(OnItemClickViewListener onRecyclerViewListener) {
            this.onRecyclerViewListener = onRecyclerViewListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(R.layout.list_fragment_dialog_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final String string = mDatas[position];
            holder.nameTv.setText(string);
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyclerViewListener != null){
                        onRecyclerViewListener.onItemClick(position,string);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas != null ? mDatas.length : 0;
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            public TextView nameTv;
            public LinearLayout ll;

            public ViewHolder(View itemView) {
                super(itemView);
                nameTv = (TextView) itemView.findViewById(R.id.list_fg_dialog_name_tv);
                ll = (LinearLayout) itemView.findViewById(R.id.list_fg_dialog_ll);
            }
        }
    }

}
