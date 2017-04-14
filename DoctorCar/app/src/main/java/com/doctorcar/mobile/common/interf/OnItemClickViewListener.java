package com.doctorcar.mobile.common.interf;

/**
 * RecyclerViewListView  条目 监听接口
 */
public  interface OnItemClickViewListener<T> {
    void onItemClick(int position, T object);
}

