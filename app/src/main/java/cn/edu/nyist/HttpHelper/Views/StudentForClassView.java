package cn.edu.nyist.HttpHelper.Views;

import cn.edu.nyist.Entity.StudentForClass;

/**
 * Created by Leafage on 2018/5/18 14:28.
 * DESCRIPTION : 班级下的学生信息视图
 */

public interface StudentForClassView {
    void onSuccess(StudentForClass student);
    void onError(String result);
}
