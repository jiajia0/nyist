package cn.edu.nyist.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.githang.statusbar.StatusBarCompat;

import cn.edu.nyist.R;

public class TeaRightFragment extends Fragment {
    View view;

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


}
