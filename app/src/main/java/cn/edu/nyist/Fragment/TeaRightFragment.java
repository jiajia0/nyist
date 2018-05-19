package cn.edu.nyist.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.githang.statusbar.StatusBarCompat;

import cn.edu.nyist.Activity.LoginActivity;
import cn.edu.nyist.Activity.TeacherInfoActivity;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;
import cn.edu.nyist.util.MySharedPreference;

public class TeaRightFragment extends Fragment implements View.OnClickListener{
    View view;

    LinearLayout ll_myInfo;
    Button btu_exit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == view) {
            view = inflater.inflate(R.layout.fragment_tea_right, container, false);
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
        view.findViewById(R.id.fg_tea_right_ll_personInfo).setOnClickListener(this);
        view.findViewById(R.id.fg_tea_right_btu_exit).setOnClickListener(this);
    }

    private void onClickInfo() {
        startActivity(new Intent(getActivity(), TeacherInfoActivity.class));
    }

    private void onClickExit() {
        MySharedPreference.getSingleInstance(getContext()).setIsLogin(Boolean.FALSE);
        MySharedPreference.getSingleInstance(getContext()).setLoginName(null);
        MySharedPreference.getSingleInstance(getContext()).setLoginRole(0);
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fg_tea_right_ll_personInfo:
                onClickInfo();
                break;
            case R.id.fg_tea_right_btu_exit:
                onClickExit();
                break;
        }
    }
}
