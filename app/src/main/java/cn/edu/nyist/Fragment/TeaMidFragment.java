package cn.edu.nyist.Fragment;

import android.app.ProgressDialog;
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

import java.util.ArrayList;
import java.util.List;

import cn.edu.nyist.Activity.StudentListActivity;
import cn.edu.nyist.App;
import cn.edu.nyist.Entity.ClassData;
import cn.edu.nyist.Entity.TeacherClass;
import cn.edu.nyist.HttpHelper.Presenter.TeacherPresenter;
import cn.edu.nyist.HttpHelper.Views.ClassView;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;

public class TeaMidFragment extends Fragment implements AdapterView.OnItemClickListener,ClassView{
    View view;

    ListView lv_class;

    private TeacherPresenter mTeacherPresenter;
    private ProgressDialog mProgress;

    //String[] data = {"移动1","移动2","移动3","移动4","移动5","云计算1","云计算2","云计算3","移动2","移动3","移动4","移动5","云计算1","云计算2","云计算3","移动2","移动3","移动4","移动5","云计算1","云计算2","云计算3"};
    List<String> classInfo = new ArrayList<>();

    List<ClassData> mClassData = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化Presenter
        mTeacherPresenter = new TeacherPresenter(getContext());
        mTeacherPresenter.onCreate();
        mTeacherPresenter.attachClassView(this);
    }

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
        //initData();
        getClassInfo();
    }

    private void initView() {
        lv_class = view.findViewById(R.id.fg_tea_mid_lv);
        mProgress = new ProgressDialog(getContext());
    }

    private void initData() {
        //getClassInfo();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, classInfo);
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
        //intent.putExtra("index", index);

        // 传入对应的班级代号
        intent.putExtra("index", mClassData.get(index).getId());
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       onClickIndex(i);
    }

    /**
     * 从后台获取班级信息
     */
    private void getClassInfo() {
        showProgress(true);
        mTeacherPresenter.teaGetClassInfo(App.LOGIN_USERNAME);
    }

    /**
     * 成功获取班级信息之后回掉
     * @param teacherClass
     */
    @Override
    public void onSuccess(TeacherClass teacherClass) {
        Logger.d("班级列表 Status：" + teacherClass.getStatus());
        if (teacherClass.getStatus() == 0) {
            mClassData = teacherClass.getData();
            Logger.d("班级总数：" + mClassData.size());
            classInfo.clear();
            for (ClassData classData : mClassData) {
                classInfo.add(classData.getMajor());
            }
        }
        initData();
        showProgress(false);
    }

    @Override
    public void onError(String result) {
        showProgress(false);
    }


    /**
     * 展示或者隐藏进度
     * @param show
     */
    private void showProgress(final boolean show) {
        mProgress.setMessage("获取信息中...");
        mProgress.setCancelable(false);

        if (show) {
            mProgress.show();
        } else {
            mProgress.dismiss();
        }
    }
}
