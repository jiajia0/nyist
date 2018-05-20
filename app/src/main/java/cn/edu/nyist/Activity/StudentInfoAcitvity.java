package cn.edu.nyist.Activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;

import cn.edu.nyist.App;
import cn.edu.nyist.Entity.BaseResponse;
import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.Entity.StudentData;
import cn.edu.nyist.HttpHelper.Presenter.StudentPresenter;
import cn.edu.nyist.HttpHelper.Views.StudentView;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;
import cn.edu.nyist.Widget.ViewHolder;
import cn.edu.nyist.util.GetToken;

/**
 * Created by yff on 2018/5/14.
 *
 * 学生信息显示
 */

public class StudentInfoAcitvity extends BaseActivity implements View.OnClickListener, StudentView{

    private StudentPresenter mStudentPresenter;

    StudentData stu = new StudentData();

    TextView tv_back;
    TextView tv_name;
    TextView tv_id;
    TextView tv_phone;
    TextView tv_school;
    TextView tv_department;
    TextView tv_major;
    TextView tv_class;
    TextView tv_dorm;
    TextView tv_sex;
    TextView tv_age;
    TextView tv_status;
    LinearLayout ll_change_phone;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_student_info;
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        // 从后台获取数据
        mStudentPresenter = new StudentPresenter(this);
        mStudentPresenter.attachStudentView(this);
        Logger.d("leafage" + App.LOGIN_USERNAME);
        String token = GetToken.getToken(this,Integer.valueOf(App.LOGIN_USERNAME), "selfInfo.salt");
        Logger.d("studentinfo:" + token + "___" + App.LOGIN_USERNAME);
        mStudentPresenter.stuGetInfo(App.LOGIN_USERNAME, token);

        // 添加进度条
        showProgress(true, "获取信息中...");
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        //沉浸式状态栏
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.blue), true);
        tv_back = holder.get(R.id.ac_stu_inf_tv_back);
        tv_name = holder.get(R.id.ac_stu_inf_tv_name);
        tv_id = holder.get(R.id.ac_stu_inf_tv_id);
        tv_phone = holder.get(R.id.ac_stu_inf_tv_phone);
        tv_school = holder.get(R.id.ac_stu_inf_tv_school);
        tv_department = holder.get(R.id.ac_stu_inf_tv_department);
        tv_major = holder.get(R.id.ac_stu_inf_tv_major);
        tv_class = holder.get(R.id.ac_stu_inf_tv_class);
        tv_dorm = holder.get(R.id.ac_stu_inf_tv_dorm);
        tv_sex = holder.get(R.id.ac_stu_inf_tv_sex);
        tv_age = holder.get(R.id.ac_stu_inf_tv_age);
        tv_status = holder.get(R.id.ac_stu_inf_tv_status);
        ll_change_phone = holder.get(R.id.ac_stu_inf_ll_change_phone);
        holder.setOnClickListener(this, R.id.ac_stu_inf_tv_back, R.id.ac_stu_inf_ll_change_phone);
        //setData();
    }

    /**
     * 设置学生信息
     */
    private void setData() {
        tv_name.setText(stu.getName());
        tv_id.setText(stu.getId());
        tv_phone.setText(stu.getPhone());
        tv_school.setText(stu.getSchool());
        tv_department.setText(stu.getDepartment());
        tv_major.setText(stu.getMajor());
        tv_class.setText(stu.getClassNum());
        tv_dorm.setText(stu.getDorNum());
        tv_sex.setText(stu.getSex());
        tv_age.setText(stu.getAge());
        tv_status.setText(stu.getStatus());
    }

    /**
     * 返回
     */
    private void onClickBack() {
        finish();
    }

    /**
     * 修改电话号码
     */
    private void onClickPhone() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_stu_inf_tv_back:
                onClickBack();
                break;
            case R.id.ac_stu_inf_ll_change_phone:
                onClickPhone();
                break;
        }
    }

    @Override
    public void onSuccess(BaseResponse baseResponse) {
        showProgress(false, "");
    }

    @Override
    public void onSuccess(Student student) {
        Logger.d("studentInfo:" + student.getStatus());
        if (student.getStatus() == 0) {
            stu = student.getData();
            setData();
        }
        // 取消进度条，提示成功
        showProgress(false, "");
    }

    @Override
    public void onError(String result) {
        // 取消进度条，提示失败
        showProgress(false, "");
    }
}
