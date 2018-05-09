package cn.edu.nyist.HttpHelper;

import android.content.Context;

import cn.edu.nyist.HttpHelper.Presenter.StudentPresenter;
import cn.edu.nyist.HttpHelper.Presenter.TeacherPresenter;

/**
 * Created by Leafage on 2018/5/9 8:58.
 * DESCRIPTION : 管理各种Presenter
 */

public class PresenterManager {
    private volatile static PresenterManager sPresenterManager;

    private PresenterManager() {
    }

    public static PresenterManager getSingleInstance() {
        if (null == sPresenterManager) {
            synchronized (DataManager.class) {
                if (null == sPresenterManager) {
                    sPresenterManager = new PresenterManager();
                }
            }
        }
        return sPresenterManager;
    }

    public StudentPresenter getStudentPresenter(Context context) {
        return new StudentPresenter(context);
    }

    public TeacherPresenter getTeacherPresenter(Context context) {
        return new TeacherPresenter(context);
    }

}
