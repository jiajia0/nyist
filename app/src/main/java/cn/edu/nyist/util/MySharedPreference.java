package cn.edu.nyist.util;

import android.content.Context;
import android.content.SharedPreferences;


public class MySharedPreference {

    private static final String ISLOGIN = "islogin";

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
        return sp.getBoolean(ISLOGIN, false);
    }

    /**
     * 将登录状态写进获取SharedPreferences中的登录状态
     * @param isLogin 状态
     * @return 执行结果
     */
    public boolean setIsLogin(Boolean isLogin) {
        return editor.putBoolean(ISLOGIN, isLogin).commit();
    }

}
