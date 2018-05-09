package cn.edu.nyist.util;

import android.content.Context;
import android.content.SharedPreferences;


public class MySharedPreference {

    private static final String ISLOGIN = "islogin";


    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;


    public static void init(Context context) {
        if (null == sp) {
            sp = context.getSharedPreferences("NYIST", Context.MODE_PRIVATE);
        }
        if (null == editor) {
            editor = context.getSharedPreferences("NYIST", Context.MODE_PRIVATE).edit();
        }
    }

    /**
     * 获取SharedPreferences中的登录状态
     * @return 状态
     */
    public static Boolean getIsLogin() {
        return sp.getBoolean(ISLOGIN, false);
    }

    /**
     * 将登录状态写进获取SharedPreferences中的登录状态
     * @param isLogin 状态
     * @return 执行结果
     */
    public static boolean setIsLogin(Boolean isLogin) {
        return editor.putBoolean(ISLOGIN, isLogin).commit();
    }

}
