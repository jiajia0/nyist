package cn.edu.nyist.HttpHelper.Presenter;

import android.content.Context;

import cn.edu.nyist.Entity.BaseResponse;
import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.Entity.Teacher;
import cn.edu.nyist.HttpHelper.Views.BaseView;
import cn.edu.nyist.HttpHelper.Views.StudentView;
import cn.edu.nyist.HttpHelper.Views.TeacherView;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.util.MySharedPreference;
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
    private TeacherView mTeacherView;
    private BaseView mBaseView;
    private Student mStudent;
    private Teacher mTeacher;
    private BaseResponse mBaseResponse;

    public StudentPresenter(Context context) {
        this.mContext = context;
        mCompositeSubscription = new CompositeSubscription();
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
        mBaseView = view;
    }

    public void attachStudentView(StudentView studentView) {
        mStudentView = studentView;
    }

    /**
     * 学生获取个人信息时需要使用该视图
     */
    public void attachTeacherView(TeacherView teacherView) {
        mTeacherView = teacherView;
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
                            mBaseView.onSuccess(mBaseResponse);
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

    /**
     * 学生获取自己辅导员信息
     * @param username
     */
    public void stuGetTeacherInfo(String username) {
        mCompositeSubscription.add(mDataManager.stuGetTeacherInfo(mContext, username)
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
                        mStudentView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(Teacher teacher) {
                        mTeacher = teacher;
                    }
                }));
    }

    /**
     * 学生修改密码
     * @param username
     * @param token
     * @param oldPass
     * @param newPass
     * @param confirmPass
     */
    public void stuUpdatePassword(String username, String token, String oldPass, String newPass, String confirmPass) {
        mCompositeSubscription.add(mDataManager.stuUpdatePassword(mContext, username, token, oldPass, newPass, confirmPass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onCompleted() {
                        if (mBaseResponse != null) {
                            mBaseView.onSuccess(mBaseResponse);
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

}
