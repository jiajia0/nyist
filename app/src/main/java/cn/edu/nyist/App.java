package cn.edu.nyist;

import cn.edu.nyist.Entity.Student;

/**
 * 程序配置
 */
public class App {

    /**
     * 用户是否登录
     */
    public static Boolean IS_LOGIN;
    /**
     * 当前登录用户名
     */
    public static String LOGIN_USERNAME;
    /**
     * 当前登录用户角色
     */
    public static int LOGIN_ROLE;

    /**
     * 拍照过程中必须连接wifi，否则不给予通过
     * 0：失败
     * 1：通过
     */
    public static Boolean IS_PASS = Boolean.FALSE;

    /**
     * 用户角色分类
     */
    public final static int ROLE_NULL = 0; //用户角色位置
    public final static int ROLE_STUDENT = 1; //学生账号
    public final static int ROLE_TEACHER = 2; //老师账号

}
