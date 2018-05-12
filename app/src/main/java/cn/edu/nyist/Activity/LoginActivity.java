package cn.edu.nyist.Activity;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.edu.nyist.Entity.BaseResponse;
import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.Entity.Teacher;
import cn.edu.nyist.HttpHelper.Presenter.StudentPresenter;
import cn.edu.nyist.HttpHelper.Presenter.TeacherPresenter;
import cn.edu.nyist.HttpHelper.Views.StudentView;
import cn.edu.nyist.HttpHelper.Views.TeacherView;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;
import cn.edu.nyist.Widget.ViewHolder;
import cn.edu.nyist.util.GetToken;
import cn.edu.nyist.util.MySharedPreference;
import cn.edu.nyist.util.PropertiesUtil;

/**
 * Created by Leafage on 2018/5/8 16:38.
 * DESCRIPTION : 登陆Activity
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, StudentView, TeacherView {

    EditText mUsername;
    EditText mPassword;
    RadioGroup mRadioGroup;
    RadioButton mStudentButton;
    RadioButton mTeacherButton;

    StudentPresenter mStudentPresenter;
    TeacherPresenter mTeacherPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        setTitle("");
        mUsername = holder.get(R.id.username);
        mPassword = holder.get(R.id.password);
        mRadioGroup = holder.get(R.id.stu_or_tea);
        mStudentButton = holder.get(R.id.stu_radio);
        mTeacherButton = holder.get(R.id.tea_radio);

        holder.setOnClickListener(this, R.id.login);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        mStudentPresenter = new StudentPresenter(this);
        mStudentPresenter.onCreate();
        mStudentPresenter.attachView(this);

        mTeacherPresenter = new TeacherPresenter(this);
        mTeacherPresenter.onCreate();
        mTeacherPresenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerKeyboardListener();
        //EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterKeyboardListener();
    }

    private void registerKeyboardListener() {
        final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Logger.e("onGlobalLayout");
                if (isKeyboardShown(rootView)) {
                    Logger.e("软键盘弹起");
                    getViewHolder().get(R.id.span1).setVisibility(View.GONE);
                    getViewHolder().get(R.id.span2).setVisibility(View.GONE);
                } else {
                    Logger.e("软键盘未弹起");
                    getViewHolder().get(R.id.span1).setVisibility(View.INVISIBLE);
                    getViewHolder().get(R.id.span2).setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void unRegisterKeyboardListener() {
        final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(null);
    }

    private boolean isKeyboardShown(View rootView) {
        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                String name = mUsername.getText().toString();
                String pswd = mPassword.getText().toString();
                if (name.isEmpty() || pswd.isEmpty()) {
                    toastShort("学号或密码不能为空");
                    return;
                }
                if (mStudentButton.isChecked()) {
                    //mStudentPresenter.login(name, pswd);
                    //mStudentPresenter.stuSetPhone(name,pswd);
                    //String salt = PropertiesUtil.getProperty(this,"selfInfo.salt","");
                    String token = GetToken.getToken(this,Integer.valueOf(name), "selfInfo.salt");
                    Logger.d("token：" + token);
                    mStudentPresenter.stuGetInfo(name, token);
                }
                if (mTeacherButton.isChecked()) {
                    //mTeacherPresenter.login(name, pswd);
                    //mTeacherPresenter.teaSetPhone(name, pswd);
                    mTeacherPresenter.teaGetInfo(name);
                }
                break;
        }
    }

    /**
     * 学生操作请求成功时回掉方法
     * @param student
     */
    @Override
    public void onSuccess(Student student) {
        Logger.d("student:status:" + student.getStatus());
        if (student.getStatus() == 0) {
            Logger.d("student:" + student.getData().getName());
            MySharedPreference.getSingleInstance(this).setIsLogin(Boolean.TRUE);
        }
    }

    /**
     * 教师操作成功回掉方法
     * @param teacher
     */
    @Override
    public void onSuccess(Teacher teacher) {
        Logger.d("teacher:status:" + teacher.getStatus());
        if (teacher.getStatus() == 0) {
            Logger.d("teacher:" + teacher.getData().getName());
            MySharedPreference.getSingleInstance(this).setIsLogin(Boolean.TRUE);
        }
    }

    /**
     * 只包含基本内容的操作成功回掉方法
     * @param baseResponse
     */
    @Override
    public void onSuccess(BaseResponse baseResponse) {
        Logger.d("baseresponse:status" + baseResponse.getStatus() + baseResponse.getMsg());
    }

    @Override
    public void onError(String result) {
        Logger.d(result);
    }
}
