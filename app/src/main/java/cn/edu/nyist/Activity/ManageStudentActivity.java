package cn.edu.nyist.Activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;

import cn.edu.nyist.Entity.StudentData;
import cn.edu.nyist.R;
import cn.edu.nyist.Widget.ViewHolder;

/**
 * Created by yff on 2018/5/23.
 *
 * 管理学生信息
 */

public class ManageStudentActivity extends BaseActivity implements View.OnClickListener {

    StudentData stu = new StudentData();

    TextView tv_back;
    TextView tv_name;
    TextView tv_id;
    TextView tv_class;
    TextView tv_dorm;
    TextView tv_sex;
    TextView tv_age;
    TextView tv_status;
    LinearLayout ll_change_dorm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_manage_student;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        //沉浸式状态栏
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.blue), true);
        tv_back = holder.get(R.id.ac_manage_stu_tv_back);
        tv_name = holder.get(R.id.ac_manage_stu_tv_name);
        tv_id = holder.get(R.id.ac_manage_stu_tv_id);
        tv_class = holder.get(R.id.ac_manage_stu_tv_class);
        tv_dorm = holder.get(R.id.ac_manage_stu_tv_dorm);
        tv_sex = holder.get(R.id.ac_manage_stu_tv_sex);
        tv_age = holder.get(R.id.ac_manage_stu_tv_age);
        tv_status = holder.get(R.id.ac_manage_stu_tv_status);
        ll_change_dorm = holder.get(R.id.ac_manage_stu_ll_change_drom);
        holder.setOnClickListener(this, R.id.ac_manage_stu_ll_change_drom);
    }


    /**
     * 取消
     */
    private void onClickcancle() {
        this.finish();
    }

    /**
     * 修改学生宿舍
     */
    private void onClickChangeDorm() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_manage_stu_tv_back:
                onClickcancle();
                break;
            case R.id.ac_manage_stu_ll_change_drom:
                onClickChangeDorm();
                break;
        }
    }
}
