package cn.edu.nyist.Activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;

import cn.edu.nyist.App;
import cn.edu.nyist.Entity.BaseResponse;
import cn.edu.nyist.Entity.Teacher;
import cn.edu.nyist.Entity.TeacherData;
import cn.edu.nyist.HttpHelper.Presenter.StudentPresenter;
import cn.edu.nyist.HttpHelper.Views.TeacherView;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;
import cn.edu.nyist.Widget.ViewHolder;
import cn.edu.nyist.util.MySharedPreference;

/**
 * Created by yff on 2018/5/14.
 *
 * 辅导员信息显示
 */

public class TeacherInfoActivity extends BaseActivity implements View.OnClickListener,TeacherView{

    TeacherData tea = new TeacherData();

    private StudentPresenter mStudentPresenter;

    TextView tv_back;
    TextView tv_name;
    TextView tv_phone;
    //学校和部门只在老师端显示
    TextView tv_scholl;
    TextView tv_department;
    LinearLayout ll_school;
    LinearLayout ll_department;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_info;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {

        Logger.d("teacherinfo initViews-----------------");

        //沉浸式状态栏
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.blue), true);
        tv_back = holder.get(R.id.ac_tea_inf_tv_back);
        tv_name = holder.get(R.id.ac_tea_inf_tv_name);
        tv_phone = holder.get(R.id.ac_tea_inf_tv_phone);
        /**
         * 如果是教师角色，就显示全部信息
         */
        if (MySharedPreference.getSingleInstance(this).getLoginRole() == App.ROLE_TEACHER) {
            ll_school = holder.get(R.id.ac_tea_info_ll_school);
            ll_department = holder.get(R.id.ac_tea_info_ll_department);
            tv_scholl = holder.get(R.id.ac_tea_inf_tv_school);
            tv_department = holder.get(R.id.ac_tea_inf_tv_department);
        }
        holder.setOnClickListener(this, R.id.ac_tea_inf_tv_back);

    }

    @Override
    protected void initDatas() {
        super.initDatas();
        Logger.d("teacherinfo initdatas-----------------");
        mStudentPresenter = new StudentPresenter(this);
        mStudentPresenter.onCreate();
        mStudentPresenter.attachTeacherView(this);
        mStudentPresenter.stuGetTeacherInfo(App.LOGIN_USERNAME);
    }

    /**
     * 设置教师数据
     */
    private void setData() {
        tv_name.setText(tea.getName());
        tv_phone.setText(tea.getPhone());
        /**
         * 如果是教师角色，就显示全部信息
         */
        if (MySharedPreference.getSingleInstance(this).getLoginRole() == App.ROLE_TEACHER) {
            tv_scholl.setText(tea.getSchool());
            tv_department.setText(tea.getDepartment());
        }
    }

    /**
     * 返回
     */
    private void onCliclBack() {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_tea_inf_tv_back:
                onCliclBack();
                finish();
        }
    }

    @Override
    public void onSuccess(BaseResponse baseResponse) {

    }

    /**
     * 成功获得教师信息
     * @param teacher
     */
    @Override
    public void onSuccess(Teacher teacher) {
        Logger.d("tea:" + teacher.getStatus());
        if (teacher.getStatus() == 0) {
            tea = teacher.getData();
            Logger.d("tea:" + tea.getName());
            Logger.d("tea:" + tea.getPhone());
            setData();
        }
    }

    @Override
    public void onError(String result) {
        Logger.d("teacherinfo---error:" + result);
    }
}
