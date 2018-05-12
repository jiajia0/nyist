package cn.edu.nyist.HttpHelper.Views;

import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.Entity.Teacher;

/**
 * Created by Leafage on 2018/5/9 19:56.
 * DESCRIPTION :
 */

public interface StudentView extends BaseView {
    void onSuccess(Student student);
    void onError(String result);
}
