package com.doctorcar.mobile.module.ask.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.ask.bean.AnswerCommentResult;
import com.doctorcar.mobile.module.ask.contract.SearchMyAnswerContract;

/**
 * Created by dd on 2017/5/16.
 */

public class SearchMyAnswerPresenter extends SearchMyAnswerContract.Presenter{

    @Override
    public void getMyCommentAnswerRequest(Integer problem_id, String user_id) {
        mRxManage.add(mModel.getMyAnswerCommentList(problem_id,user_id).subscribe(new RxSubscriber<AnswerCommentResult>(mContext){
            @Override
            protected void _onNext(AnswerCommentResult o) {
                mView.returnMyAnswerCommentListData(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }

    @Override
    public void submitAnswerCommentRequest(Integer answer_id, String user_id, String comment_answer_content) {
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
}
