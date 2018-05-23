package cn.edu.nyist.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;

import cn.edu.nyist.Activity.StudentListActivity;
import cn.edu.nyist.R;

public class TeaMidFragment extends Fragment implements AdapterView.OnItemClickListener{
    View view;

    ListView lv_class;

    String[] data = {"移动1","移动2","移动3","移动4","移动5","云计算1","云计算2","云计算3","移动2","移动3","移动4","移动5","云计算1","云计算2","云计算3","移动2","移动3","移动4","移动5","云计算1","云计算2","云计算3"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == view) {
            view = inflater.inflate(R.layout.fragment_tea_mid, container, false);
        }
        //沉浸式状态栏
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.blue), true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        initData();
    }

    private void initView() {
        lv_class = view.findViewById(R.id.fg_tea_mid_lv);
    }

    private void initData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, data);
        lv_class.setAdapter(adapter);
        lv_class.setOnItemClickListener(this);
    }

    /**
     * 点击对应的班级
     * @param index 班级的位置
     */
    private void onClickIndex(int index) {
        Toast.makeText(getContext(), "第" + index + "个班级", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), StudentListActivity.class);
        //把对应班级的标识传递过去
        intent.putExtra("index", index);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       onClickIndex(i);
    }
}
