package cn.edu.nyist.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.nyist.App;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;
import cn.edu.nyist.util.MySharedPreference;

public class StuMidFragment extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == view) {
            view = inflater.inflate(R.layout.fragment_stu_mid, container, false);
        }
        initView();
        return view;
    }

    private void initView() {
        view.findViewById(R.id.set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.e("ISLOGIN===>" + App.IS_LOGIN );
                Logger.e("NAME===>" + App.LOGIN_USERNAME );
                Logger.e("ROLE===>" + App.LOGIN_ROLE );
                MySharedPreference.getSingleInstance(getActivity()).setIsLogin(Boolean.FALSE);
                MySharedPreference.getSingleInstance(getActivity()).setLoginRole(App.ROLE_TEACHER);
                MySharedPreference.getSingleInstance(getActivity()).setLoginName("yangfengfan");

            }

        });

        view.findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.IS_LOGIN = MySharedPreference.getSingleInstance(getActivity()).getIsLogin();
                App.LOGIN_ROLE = MySharedPreference.getSingleInstance(getActivity()).getLoginRole();
                App.LOGIN_USERNAME = MySharedPreference.getSingleInstance(getActivity()).getLoginName();
                Logger.e("ISLOGIN===>" + App.IS_LOGIN );
                Logger.e("NAME===>" + App.LOGIN_USERNAME );
                Logger.e("ROLE===>" + App.LOGIN_ROLE );
            }
        });
    }

}
