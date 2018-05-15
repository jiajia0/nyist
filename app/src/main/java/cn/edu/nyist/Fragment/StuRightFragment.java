package cn.edu.nyist.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.githang.statusbar.StatusBarCompat;

import cn.edu.nyist.Activity.ModifyPwdActivity;
import cn.edu.nyist.R;

public class StuRightFragment extends Fragment implements View.OnClickListener{

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == view) {
            view = inflater.inflate(R.layout.fragment_stu_right, container, false);
        }
        //沉浸式状态栏
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.blue), true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        view.findViewById(R.id.fg_stu_right_ll_personInfo).setOnClickListener(this);
        view.findViewById(R.id.fg_stu_right_ll_adminInfo).setOnClickListener(this);
        view.findViewById(R.id.fg_stu_right_ll_updatePwd).setOnClickListener(this);
        view.findViewById(R.id.fg_stu_right_btu_exit).setOnClickListener(this);
    }

    /**
     * 点击个人中心
     */
    private void onClickPersonInfo() {

    }

    /**
     * 点击辅导员信息
     */
    private void onClickAdminInfo() {

    }

    /**
     * 点击修改密码
     */
    private void onClickUpdataPwd() {
        startActivity(new Intent(getActivity(), ModifyPwdActivity.class));
    }

    /**
     * 退出登录
     */
    private void onClickExit() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fg_stu_right_ll_personInfo:
                onClickPersonInfo();
                break;
            case R.id.fg_stu_right_ll_adminInfo:
                onClickAdminInfo();
                break;
            case R.id.fg_stu_right_ll_updatePwd:
                onClickUpdataPwd();
                break;
            case R.id.fg_stu_right_btu_exit:
                onClickExit();
                break;
        }
    }
}
