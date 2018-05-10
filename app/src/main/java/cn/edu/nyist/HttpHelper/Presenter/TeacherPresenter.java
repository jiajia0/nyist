package cn.edu.nyist.HttpHelper.Presenter;

import android.content.Context;

import cn.edu.nyist.Entity.BaseResponse;
import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.Entity.Teacher;
import cn.edu.nyist.HttpHelper.Views.BaseView;
import cn.edu.nyist.HttpHelper.Views.TeacherView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Leafage on 2018/5/9 20:55.
 * DESCRIPTION : 教师交互类
 */

public class TeacherPresenter extends BasePresenter {
    private Context mContext;
    private CompositeSubscription mCompositeSubscription;
    private Teacher mTeacher;
    private TeacherView mTeacherView;
    private BaseResponse mBaseResponse;

    public TeacherPresenter(Context context) {
        mContext = context;
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
        this.mTeacherView = (TeacherView)view;
    }

    /**
     * 教师登陆接口
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        mCompositeSubscription.add(mDataManager.teaLogin(mContext, username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Teacher>() {
                    @Override
                    public void onCompleted() {
                        if (mTeacher != null) {
                            mTeacherView.onSuccess(mTeacher);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mTeacherView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(Teacher teacher) {
                        mTeacher = teacher;
                    }
                }));
    }

    /**
     * 教师修改手机号码
     * @param username
     * @param phone
     */
    public void teaSetPhone(String username,String phone) {
        mCompositeSubscription.add(mDataManager.teaSetPhone(mContext,username,phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Teacher>() {
                    @Override
                    public void onCompleted() {
                        if (mTeacher != null) {
                            mTeacherView.onSuccess(mTeacher);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mTeacherView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(Teacher teacher) {
                        mTeacher = teacher;
                    }
                }));
    }

}
