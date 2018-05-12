package cn.edu.nyist.HttpHelper;

import android.content.Context;

import cn.edu.nyist.Common.Constant;
import cn.edu.nyist.Entity.BaseResponse;
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

    // 学生接口------------------------------------------------

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

    /**
     * 学生设置手机号码
     * @param context
     * @param username
     * @param phone
     * @return
     */
    public Observable<BaseResponse> stuSetPhone(Context context, String username, String phone) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.stuSetPhone(username, phone);
    }

    public Observable<Student> stuGetInfo(Context context, String username, String token) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.stuGetInfo(username, token);
    }

    // 教师接口------------------------------------------------
    /**
     * 教师登陆
     * @param context
     * @param username
     * @param password
     * @return
     */
    public Observable<Teacher> teaLogin(Context context, String username, String password){
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.teaLogin(username, password);
    }

    /**
     * 教师设置手机号码
     * @param context
     * @param username
     * @param phone
     * @return
     */
    public Observable<Teacher> teaSetPhone(Context context, String username, String phone) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.teaSetPhone(username, phone);
    }

    public Observable<Teacher> teaGetInfo(Context context, String username) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.teaGetInfo(username);
    }
}
