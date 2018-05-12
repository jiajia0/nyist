package cn.edu.nyist.HttpHelper.Presenter;

import android.content.Context;

import cn.edu.nyist.Entity.BaseResponse;
import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.HttpHelper.Views.BaseView;
import cn.edu.nyist.HttpHelper.Views.StudentView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Leafage on 2018/5/9 8:36.
 * DESCRIPTION : 学生交互类
 */

public class StudentPresenter extends BasePresenter {
    private Context mContext;
    private CompositeSubscription mCompositeSubscription;
    private StudentView mStudentView;
    private Student mStudent;
    private BaseResponse mBaseResponse;

    public StudentPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(BaseView view) {
        mStudentView = (StudentView) view;
    }

    /**
     * 学生登陆流程处理
     *
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        mCompositeSubscription.add(mDataManager.login(mContext, username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Student>() {
                    @Override
                    public void onCompleted() {
                        if (mStudent != null) {
                            mStudentView.onSuccess(mStudent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mStudentView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(Student student) {
                        mStudent = student;
                    }
                }));
    }

    /**
     * 学生设置手机号码
     *
     * @param username
     * @param phone
     */
    public void stuSetPhone(String username, String phone) {
        mCompositeSubscription.add(mDataManager.stuSetPhone(mContext, username, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onCompleted() {
                        if (mBaseResponse != null) {
                            mStudentView.onSuccess(mBaseResponse);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mStudentView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        mBaseResponse = baseResponse;
                    }
                }));
    }

    /**
     * 学生获取个人信息
     * @param username
     * @param token
     */
    public void stuGetInfo(String username, String token) {
        mCompositeSubscription.add(mDataManager.stuGetInfo(mContext,username,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Student>() {
                    @Override
                    public void onCompleted() {
                        if (mStudent != null) {
                            mStudentView.onSuccess(mStudent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mStudentView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(Student student) {
                        mStudent = student;
                    }
                }));
    }

}
