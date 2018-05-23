package cn.edu.nyist.Activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;

import cn.edu.nyist.R;
import cn.edu.nyist.Widget.ViewHolder;

/**
 * Created by yff on 2018/5/23.
 *
 * 学生列表（老师查看自己对应班级的学生）
 */

public class StudentListActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    String[] data = {"张1","张2","张3","张4","张5","张6","张7","张8","张9","张10","张11","张12","张13","张14",
            "张15","张16","张17","张18","张19"};

    String index;

    ListView lv_student;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_student_list;
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

}
