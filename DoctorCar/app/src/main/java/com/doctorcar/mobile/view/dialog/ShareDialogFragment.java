package com.doctorcar.mobile.view.dialog;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.utils.ViewUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by dd on 2017/6/14.
 */

public class ShareDialogFragment extends BaseDialogFragment implements View.OnClickListener{
    private IWXAPI api;
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.share_fragment_dialog,null);
        ViewUtil.getView(view,R.id.share_wx_rl).setOnClickListener(this);
        ViewUtil.getView(view,R.id.share_wx_friends_rl).setOnClickListener(this);
        ViewUtil.getView(view,R.id.share_qq_rl).setOnClickListener(this);
        ViewUtil.getView(view,R.id.share_qq_space_rl).setOnClickListener(this);

        api = WXAPIFactory.createWXAPI(getActivity(), "wxd930ea5d5a258f4f");
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_wx_rl:
                WXTextObject wxTextObject = new WXTextObject();
                wxTextObject.text = "我要";

                WXMediaMessage wxMediaMessage = new WXMediaMessage();
                wxMediaMessage.mediaObject = wxTextObject;
                wxMediaMessage.description = "fdfssd";

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.message = wxMediaMessage;
                req.transaction = buildTransaction("text");
                req.scene = mTargetScene;
                api.sendReq(req);
                break;
            case R.id.share_wx_friends_rl:break;
            case R.id.share_qq_rl:break;
            case R.id.share_qq_space_rl:break;
        }
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
