package cn.edu.nyist.HttpHelper.Presenter;

import android.content.Context;
import android.view.View;

import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.HttpHelper.Views.BaseView;
import cn.edu.nyist.HttpHelper.Views.LoginView;
import cn.edu.nyist.LogUtil.Logger;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Leafage on 2018/5/9 8:36.
 * DESCRIPTION : 处理Login流程
 */

public class LoginPresenter extends BasePresenter {
    private Context mContext;
    private CompositeSubscription mCompositeSubscription;
    private LoginView mLoginView;
    private Student mStudent;

    public LoginPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(BaseView view) {
        mLoginView = (LoginView)view;
    }

    public void login(String username, String password) {
        mCompositeSubscription.add(mDataManager.login(mContext, username, password)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Student>() {
            @Override
            public void onCompleted() {
                if (mStudent != null) {
                    mLoginView.onSuccess(mStudent);
                }
            }

            @Override
            public void onError(Throwable e) {
                mLoginView.onError(e.getMessage());
            }

            @Override
            public void onNext(Student student) {
                mStudent = student;
            }
        }));
    }
}
