package com.doctorcar.mobile.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.utils.TLUtil;

/**
 * Created by dd on 2017/5/5.
 */

public class EditDialogFragment extends BaseDialogFragment{
    private EditText linkEt;
    private EditText linkNameEt;
    private EditDialogListener editDialogListener;

    public void setEditDialogListener(EditDialogListener editDialogListener) {
        this.editDialogListener = editDialogListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_fragment_dialog, null);
        linkEt = (EditText) view.findViewById(R.id.link_dialog_et);
        linkNameEt = (EditText) view.findViewById(R.id.link_name_dialog_et);
        view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkStr = linkEt.getText().toString().trim();
                String linkNameStr = linkNameEt.getText().toString().trim();
                if(TextUtils.isEmpty(linkStr) || TextUtils.isEmpty(linkNameStr)){
                    TLUtil.showToast("请输入内容！");
                }else{
                    if (editDialogListener != null){
                        editDialogListener.makeSure(linkNameStr,linkStr);
                    }
                }
            }
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        final AlertDialog linkDialog = builder.create();
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.edit_fragment_dialog, null);
//        linkEt = (EditText) view.findViewById(R.id.link_dialog_et);
//        linkNameEt = (EditText) view.findViewById(R.id.link_name_dialog_et);
//        view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String linkStr = linkEt.getText().toString().trim();
//                String linkNameStr = linkNameEt.getText().toString().trim();
//                if(TextUtils.isEmpty(linkStr) || TextUtils.isEmpty(linkNameStr)){
//                    TLUtil.showToast("请输入内容！");
//                }else{
//                    if (editDialogListener != null){
//                        editDialogListener.makeSure(linkNameStr,linkStr);
//                    }
//                }
//            }
//        });
//        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                linkDialog.dismiss();
//            }
//        });
//        linkDialog.setView(view, 0, 0, 0, 0);
//        return linkDialog;
//    }

    public interface EditDialogListener{
        void makeSure(String name,String link);
    }
}
