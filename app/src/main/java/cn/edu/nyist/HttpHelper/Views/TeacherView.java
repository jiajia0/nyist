package cn.edu.nyist.HttpHelper.Views;

import cn.edu.nyist.Entity.Teacher;

/**
 * Created by Leafage on 2018/5/9 20:56.
 * DESCRIPTION :
 */

public interface TeacherView extends BaseView {
    void onSuccess(Teacher teacher);
    void onError(String result);
}
