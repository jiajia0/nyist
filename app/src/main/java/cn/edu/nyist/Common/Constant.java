package cn.edu.nyist.Common;

/**
 * Created by Leafage on 2018/5/7 11:54.
 * DESCRIPTION : 全局变量不可更改
 */

public interface Constant {
    /**
     * URL
     */
    //String BASE_URL = "http://192.168.30.237"; // 基础URL
    String BASE_URL = "http://192.168.56.1";
    //String BASE_URL = "http://192.168.30.233:8080/";

    String STU_LOGIN = BASE_URL + "/student/login.do"; // 学生登陆URL
    String STU_SET_PHONE = BASE_URL + "/student/set_phone.do"; // 学生修改手机URL
    String STU_GET_INFO = BASE_URL + "/student/get_self_info.do"; // 学生获取个人信息
    String STU_GET_TEACHER_INFO = BASE_URL + "/student/get_teacher_info.do"; // 查看教师信息
    String STU_UPDATE_PASSWORD = BASE_URL + "/student/update_password.do"; //修改密码
    String STU_ATTENCE = BASE_URL + "/student/attence.do"; //学生考勤

    String TEA_LOGIN = BASE_URL + "/teacher/login.do"; // 教师登陆URL
    String TEA_SET_PHONE = BASE_URL + "/teacher/set_phone.do"; // 教师修改手机URL
    String TEA_GET_INFO = BASE_URL + "/teacher/get_teacher_info.do"; // 教师获取个人信息
    String TEA_GET_CLASS_INFO = BASE_URL + "/teacher/get_teach_class.do"; //教师查看管理班级信息
    String TEA_SET_DORMNUM = BASE_URL + "/teacher/set_student_dormNum.do"; //教师修改学生寝室号
    String TEA_UPDATE_STUIMG = BASE_URL + "/teacher/update_student_img.do"; //教师修改学生照片
    String TEA_GET_ATTENCE_RECORD = BASE_URL + "/teacher/get_attence_record.do"; //教师得到考勤记录
    String TEA_GET_STUDENT_INFO = BASE_URL + "/teacher/get_students_info.do"; // 教师获得学生信息
}
