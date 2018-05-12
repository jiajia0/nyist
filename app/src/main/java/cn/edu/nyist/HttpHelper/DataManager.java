package cn.edu.nyist.HttpHelper;

import android.content.Context;

import cn.edu.nyist.Common.Constant;
import cn.edu.nyist.Entity.AttenceRecord;
import cn.edu.nyist.Entity.BaseResponse;
import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.Entity.Teacher;
import cn.edu.nyist.Entity.TeacherClass;
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

    /**
     * 学生获取个人信息
     * @param context
     * @param username
     * @param token
     * @return
     */
    public Observable<Student> stuGetInfo(Context context, String username, String token) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.stuGetInfo(username, token);
    }

    /**
     * 学生修改密码
     * @param context
     * @param id
     * @param token
     * @param oldPass
     * @param newPass
     * @param confirmPass
     * @return
     */
    public Observable<BaseResponse> stuUpdatePassword(Context context, String id, String token, String oldPass, String newPass, String confirmPass) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.stuUpdatePassword(id, token, oldPass, newPass, confirmPass);
    }

    /**
     * 学生获取自己教师信息
     * @param context
     * @param username
     * @return
     */
    public Observable<Teacher> stuGetTeacherInfo(Context context, String username) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.stuGetTeacherInfo(username);
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

    /**
     * 教师获取个人信息
     * @param context
     * @param username
     * @return
     */
    public Observable<Teacher> teaGetInfo(Context context, String username) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.teaGetInfo(username);
    }

    /**
     * 教师获取管理班级信息
     * @param context
     * @param username
     * @return
     */
    public Observable<TeacherClass> teaGetClassInfo(Context context, String username) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.teaGetClassInfo(username);
    }

    /**
     * 教师修改寝室号
     * @param context
     * @param username 学生学号
     * @param token
     * @param dormNum
     * @return
     */
    public Observable<Student> teaSetDormNum(Context context,String username,String token,String dormNum) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.teaSetDormNum(username, token, dormNum);
    }

    public Observable<AttenceRecord> teaGetAttenceRecord(Context context,String username,String week,String classNum) {
        mHttpService = mHttpHelper.setContext(context).getServer();
        return mHttpService.teaGetAttenceRecord(username, week, classNum);
    }

}
