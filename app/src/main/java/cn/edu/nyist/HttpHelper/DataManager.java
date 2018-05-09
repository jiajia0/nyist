package cn.edu.nyist.HttpHelper;

import android.content.Context;

import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.Entity.Teacher;
import rx.Observable;

/**
 * Created by Leafage on 2018/5/9 8:32.
 * DESCRIPTION : 处理与后台交互数据方法
 */

public class DataManager {

    private HttpService mHttpService;
    private HttpHelper mHttpHelper;

    private volatile static DataManager sDataManager;

    private DataManager() {
        mHttpHelper = new HttpHelper();
    }

    public static DataManager getSingleInstance() {
        if (null == sDataManager) {
            synchronized (DataManager.class) {
                if (null == sDataManager) {
                    sDataManager = new DataManager();
                }
            }
        }
        return sDataManager;
    }

    /**
     * 学生登陆请求
     *
     * @param username 学号
     * @param password 密码
     * @return
     */
    public Observable<Student> login(Context context, String username, String password) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.login(username, password);
    }

    public Observable<Teacher> teaLogin(Context context, String username, String password){
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.teaLogin(username, password);
    }
}
