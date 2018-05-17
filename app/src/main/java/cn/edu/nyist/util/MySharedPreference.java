package cn.edu.nyist.util;

import android.content.Context;
import android.content.SharedPreferences;

import cn.edu.nyist.App;


public class MySharedPreference {

    private static String IS_LOGIN = "islogin"; //是否登录
    private static String LOGIN_ROLE = "loginrole"; //登录角色
    private static String LOGIN_NAME = "loginname"; //登录用户的账号

    private static MySharedPreference mySharedPreference;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    public MySharedPreference(Context context) {
        if (null == sp) {
            sp = context.getSharedPreferences("NYIST", Context.MODE_PRIVATE);
        }
        if (null == editor) {
            editor = context.getSharedPreferences("NYIST", Context.MODE_PRIVATE).edit();
        }
    }

    /**
     * @param context 上下文
     * @return 单例
     */
    public static MySharedPreference getSingleInstance(Context context) {
        if (null == sp) {
            mySharedPreference = new MySharedPreference(context);
        }
        if (null == editor) {
            mySharedPreference = new MySharedPreference(context);
        }
        return mySharedPreference;
    }

    /**
     * 获取SharedPreferences中的登录状态
     * @return 状态
     */
    public Boolean getIsLogin() {
        return sp.getBoolean(IS_LOGIN, false);
    }

    /**
     * 将登录状态写进获取SharedPreferences中的登录状态
     * @param isLogin 状态
     * @return MySharedPreference
     */
    public MySharedPreference setIsLogin(Boolean isLogin) {
        editor.putBoolean(IS_LOGIN, isLogin).commit();
        return mySharedPreference;
    }

    /**
     * 获取登录账号的角色
     * @return 用户角色
     */
    public int getLoginRole() {
        return sp.getInt(LOGIN_ROLE, App.ROLE_NULL);
    }

    /**
     * 设置登录用户的角色
     * @param role 角色
     * @return MySharedPreference
     */
    public MySharedPreference setLoginRole(int role) {
        editor.putInt(LOGIN_ROLE, role).commit();
        return mySharedPreference;
    }

    /**
     * 获取登录的账号
     * @return 登录账号
     */
    public String getLoginName() {
        return sp.getString(LOGIN_NAME, "null");
    }

    /**
     * 设置登录的账号
     * @param loginName 登录账号
     * @return MySharedPreference
     */
    public MySharedPreference setLoginName(String loginName) {
        editor.putString(LOGIN_NAME, loginName).commit();
        return mySharedPreference;
    }
}
