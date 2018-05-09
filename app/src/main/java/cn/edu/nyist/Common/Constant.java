package cn.edu.nyist.Common;

/**
 * Created by Leafage on 2018/5/7 11:54.
 * DESCRIPTION : 全局变量不可更改
 */

public interface Constant {
    String BASE_URL = "http://192.168.56.1"; // 基础URL
    String STU_LOGIN = BASE_URL + "/student/login.do"; // 学生登陆URL
    String TEA_LOGIN = BASE_URL + "/teacher/login.do"; // 教师登陆URL
}
