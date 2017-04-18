package com.doctorcar.mobile.module.ask.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;
import com.doctorcar.mobile.module.ask.bean.AnswerResult;
import com.doctorcar.mobile.module.ask.contract.AnswerCommentContract;

/**
 * Created by dd on 2017/4/17.
 */

public class AnswerCommentPresenter extends AnswerCommentContract.Presenter{

    @Override
    public void submitAnswerCommentRequest(Integer answer_id, String user_id,  String comment_answer_content) {
        mRxManage.add(mModel.submitAnswerComment(answer_id,user_id,comment_answer_content).subscribe(new RxSubscriber<Object>(mContext){
            @Override
            protected void _onNext(Object o) {
                mView.returnAnswerCommentData(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }

    @Override
    public void getCommentAnswerRequest(Integer answer_id,Integer page, Integer page_size) {
        mRxManage.add(mModel.getAnswerCommentList(answer_id,page, page_size).subscribe(new RxSubscriber<AnswerCommentResult>(mContext){
            @Override
            protected void _onNext(AnswerCommentResult o) {
                mView.returnAnswerCommentListData(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }
}
