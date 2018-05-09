package cn.edu.nyist.HttpHelper.Presenter;

import android.content.Context;

import cn.edu.nyist.Entity.Student;
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

    public void login(String username, String password) {
        Logger.d("login：" + username + password);
        mCompositeSubscription.add(mDataManager.login(mContext, username, password)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Student>() {
            @Override
            public void onCompleted() {
                Logger.d("login：" + "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("login：" + "onError" + e.getMessage());
            }

            @Override
            public void onNext(Student student) {
                Logger.d("login：" + "onNext");

                Logger.d("login:" + student.getStatus());

                if (student.getStatus() == 0) {
                    Logger.d("login:" + student.getData().getName());
                }
            }
        }));
    }
}
