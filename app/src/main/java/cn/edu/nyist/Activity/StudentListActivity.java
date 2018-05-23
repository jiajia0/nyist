package cn.edu.nyist.Activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;

import java.util.List;

import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.Entity.StudentData;
import cn.edu.nyist.Entity.StudentForClass;
import cn.edu.nyist.HttpHelper.Presenter.TeacherPresenter;
import cn.edu.nyist.HttpHelper.Views.StudentForClassView;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;
import cn.edu.nyist.Widget.ViewHolder;

/**
 * Created by yff on 2018/5/23.
 *
 * 学生列表（老师查看自己对应班级的学生）
 */

public class StudentListActivity extends BaseActivity implements AdapterView.OnItemClickListener,StudentForClassView{

    private TeacherPresenter mTeacherPresenter;
    private List<StudentData> mStudents;

    String[] data = {"张1","张2","张3","张4","张5","张6","张7","张8","张9","张10","张11","张12","张13","张14",
            "张15","张16","张17","张18","张19"};

    // 班级代号
    String index;

    ListView lv_student;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_student_list;
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        mTeacherPresenter = new TeacherPresenter(this);
        mTeacherPresenter.onCreate();
        mTeacherPresenter.attachStudentForClassView(this);

        getStudentInfo();
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        //沉浸式状态栏
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.blue), true);
        lv_student = holder.get(R.id.ac_student_list_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);
        lv_student.setAdapter(adapter);
        lv_student.setOnItemClickListener(this);
        lv_student.setOnItemClickListener(this);
    }

    /**
     * 点击对应的学生
     * @param index 学生的位置
     */
    private void onClickIndex(int index) {
        Toast.makeText(this, "第" + index + "个学生", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        onClickIndex(i);
    }

    /**
     * 从后台获取学生信息
     */
    public void getStudentInfo() {
        mTeacherPresenter.teaGetStudentInfo("169207");
        showProgress(true,"获取信息中...");
    }

    /**
     * 成功从后台获取学生信息回掉
     * @param student
     */
    @Override
    public void onSuccess(StudentForClass student) {
        Logger.d("获取班级中的学生信息 Status:" + student.getStatus());
        if (student.getStatus() == 0) {
            mStudents = student.getData();
            Logger.d("该班级中学生人数：" + mStudents.size());
        }
        showProgress(false,"");
    }

    @Override
    public void onError(String result) {
        showProgress(false,"");
    }
}
