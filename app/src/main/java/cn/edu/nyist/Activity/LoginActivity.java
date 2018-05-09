package cn.edu.nyist.Activity;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.HttpHelper.Presenter.LoginPresenter;
import cn.edu.nyist.HttpHelper.Views.LoginView;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;
import cn.edu.nyist.Widget.ViewHolder;

/**
 * Created by Leafage on 2018/5/8 16:38.
 * DESCRIPTION : 登陆Activity
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView {

    EditText mUsername;
    EditText mPassword;

    LoginPresenter mLoginPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        setTitle("");
        mUsername = holder.get(R.id.username);
        mPassword = holder.get(R.id.password);

        holder.setOnClickListener(this, R.id.login);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.onCreate();
        mLoginPresenter.attachView(this);
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
                mLoginPresenter.login(name, pswd);
                break;
        }
    }

    /**
     * 请求成功时回掉方法
     * @param student
     */
    @Override
    public void onSuccess(Student student) {
        Logger.d("status:" + student.getStatus());
        if (student.getStatus() == 0) {
            Logger.d("student:" + student.getData().getName());
        }
    }

    @Override
    public void onError(String result) {
        Logger.d(result);
    }
}
