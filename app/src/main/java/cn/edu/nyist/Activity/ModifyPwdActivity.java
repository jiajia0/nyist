package cn.edu.nyist.Activity;

import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;

import org.apache.commons.lang3.StringUtils;


import cn.edu.nyist.App;
import cn.edu.nyist.Entity.BaseResponse;
import cn.edu.nyist.HttpHelper.Presenter.StudentPresenter;
import cn.edu.nyist.HttpHelper.Views.BaseView;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;
import cn.edu.nyist.Widget.ViewHolder;
import cn.edu.nyist.util.GetToken;


/**
 * Created by yff on 2018/5/14.
 * <p>
 * 用户修改密码
 */

public class ModifyPwdActivity extends BaseActivity implements OnClickListener, BaseView {

    TextView tv_cancel;
    TextInputEditText tie_old_pwd;
    TextInputEditText tie_new_pwd;
    TextInputEditText tie_confirm_pwd;
    Button btu_submit;

    private StudentPresenter mStudentPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        //沉浸式状态栏
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.blue), true);
        tv_cancel = holder.get(R.id.ac_modify_pwd_tv_cacle);
        tie_old_pwd = holder.get(R.id.ac_modify_pwd_tie_old_pwd);
        tie_new_pwd = holder.get(R.id.ac_modify_pwd_tie_new_pwd);
        tie_confirm_pwd = holder.get(R.id.ac_modify_pwd_tie_confirm_pwd);
        btu_submit = holder.get(R.id.ac_modify_pwd_btu_submit);
        holder.setOnClickListener(this, R.id.ac_modify_pwd_btu_submit, R.id.ac_modify_pwd_tv_cacle);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        mStudentPresenter = new StudentPresenter(this);
        mStudentPresenter.attachView(this);
    }

    /**
     * 提交修改信息
     */
    private void submitServer() {
        String username = App.LOGIN_USERNAME;
        String token = GetToken.getToken(this, Integer.valueOf(username), "updatePass.salt");
        //mStudentPresenter.stuUpdatePassword(name, token,"123456","666123","666123");
        mStudentPresenter.stuUpdatePassword(username, token, tie_old_pwd.getText().toString(), tie_new_pwd.getText().toString(), tie_confirm_pwd.getText().toString());
        showProgress(true, "修改中...");
    }

    /**
     * 取消
     */
    private void onClcikCancel() {
        this.finish();
    }

    /**
     * 提交修改
     */
    private void onClickSubmit() {
        if (StringUtils.isEmpty(tie_old_pwd.getText().toString()) ||
                StringUtils.isEmpty(tie_new_pwd.getText().toString()) ||
                StringUtils.isEmpty(tie_confirm_pwd.getText().toString())) {
            //格式输入不对
            toastShort("输入有误");
            return;
        }
        submitServer();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_modify_pwd_tv_cacle:
                onClcikCancel();
                break;
            case R.id.ac_modify_pwd_btu_submit:
                onClickSubmit();
                break;
        }
    }

    /**
     * 修改密码回掉方法
     *
     * @param baseResponse
     */
    @Override
    public void onSuccess(BaseResponse baseResponse) {
        showProgress(false, "");
        Logger.d("modifypwd:" + baseResponse.getStatus());
        if (baseResponse.getStatus() == 0) {
            toastLong("密码修改成功！");
        }
        finish();
    }

    @Override
    public void onError(String result) {
        showProgress(false, "");
        finish();
    }
}
