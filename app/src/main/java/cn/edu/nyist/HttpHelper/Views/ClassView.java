package cn.edu.nyist.HttpHelper.Views;

import cn.edu.nyist.Entity.TeacherClass;

/**
 * Created by Leafage on 2018/5/12 13:48.
 * DESCRIPTION : 班级视图
 */

public interface ClassView {
    void onSuccess(TeacherClass teacherClass);
    void onError(String result);
}
