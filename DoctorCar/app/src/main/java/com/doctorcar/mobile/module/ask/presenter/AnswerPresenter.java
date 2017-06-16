package com.doctorcar.mobile.module.ask.presenter;

import com.doctorcar.mobile.R;
import com.doctorcar.mobile.common.baserx.RxSubscriber;
import com.doctorcar.mobile.common.commonutils.ToastUitl;
import com.doctorcar.mobile.module.ask.bean.AnswerResult;
import com.doctorcar.mobile.module.ask.contract.AnswerContract;

/**
 * Created by dd on 2017/4/17.
 */

public class AnswerPresenter extends AnswerContract.Presenter{

    @Override
    public void submitAnswerRequest(String user_id, Integer problem_id, String answer_content) {
        mRxManage.add(mModel.submitAnswer(user_id,problem_id,answer_content).subscribe(new RxSubscriber<Object>(mContext){
            @Override
            protected void _onNext(Object o) {
                mView.returnAnswerData(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }

    @Override
    public void getAnswerRequest(String user_id,Integer problem_id,Integer page, Integer page_size) {
        mRxManage.add(mModel.getAnswerList(user_id,problem_id,page, page_size).subscribe(new RxSubscriber<AnswerResult>(mContext){
            @Override
            protected void _onNext(AnswerResult o) {
                mView.returnAnswerListData(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }

    @Override
    public void addProblemFocusRequest(String user_id, Integer problem_id) {
        mRxManage.add(mModel.addProblemFocus(user_id,problem_id).subscribe(new RxSubscriber<Object>(mContext) {
            @Override
            protected void _onNext(Object o) {
                mView.returnAddProblemFocus(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }

    @Override
    public void deleteProblemFocusRequest(String user_id, Integer problem_id) {
        mRxManage.add(mModel.deleteProblemFocus(user_id,problem_id).subscribe(new RxSubscriber<Object>(mContext) {
            @Override
            protected void _onNext(Object o) {
                mView.returnDeleteProblemFocus(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);

            }
        }));
    }


    @Override
    public void addAnswerPraiseRequest(Integer answer_id, String answer_user_id, String praise_user_id, Integer problem_id) {
        mRxManage.add(mModel.addAnswerPraise(answer_id, answer_user_id,praise_user_id,problem_id).subscribe(new RxSubscriber<Object>(mContext) {
            @Override
            protected void _onNext(Object o) {
                mView.returnAddAnswerPraise(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));
    }

    @Override
    public void deleteAnswerPraiseRequest(Integer answer_id,String praise_user_id) {
        mRxManage.add(mModel.deleteAnswerPraise(answer_id,praise_user_id).subscribe(new RxSubscriber<Object>(mContext) {
            @Override
            protected void _onNext(Object o) {
                mView.returnDeleteAnswerPraise(o);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_apps_black_24dp);
            }
        }));

    }
}

