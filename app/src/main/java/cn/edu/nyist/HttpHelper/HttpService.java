package cn.edu.nyist.HttpHelper;

import cn.edu.nyist.Common.Constant;
import cn.edu.nyist.Entity.AttenceRecord;
import cn.edu.nyist.Entity.BaseResponse;
import cn.edu.nyist.Entity.Student;
import cn.edu.nyist.Entity.Teacher;
import cn.edu.nyist.Entity.TeacherClass;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Leafage on 2018/5/9 8:34.
 * DESCRIPTION : 定义与后台数据交互的接口
 */

public interface HttpService {

    // 学生接口-----------------------------------
    // 登陆
    @FormUrlEncoded
    @POST(Constant.STU_LOGIN)
    Observable<Student> login(@Field("id") String username, @Field("password") String password);
    // 设置手机号
    @FormUrlEncoded
    @POST(Constant.STU_SET_PHONE)
    Observable<BaseResponse> stuSetPhone(@Field("id") String username,@Field("phone") String phone);
    // 获取个人信息
    @FormUrlEncoded
    @POST(Constant.STU_GET_INFO)
    Observable<Student> stuGetInfo(@Field("id") String username, @Field("token") String token);
    // 查看个人辅导员信息
    @FormUrlEncoded
    @POST(Constant.STU_GET_TEACHER_INFO)
    Observable<Teacher> stuGetTeacherInfo(@Field("id") String username);
    // 修改密码
    @FormUrlEncoded
    @POST(Constant.STU_UPDATE_PASSWORD)
    Observable<BaseResponse> stuUpdatePassword(@Field("id") String username, @Field("token") String token,@Field("oldPass") String oldPass,@Field("newPass") String newPass,@Field("confirmPass") String confirmPass);
    // 学生考勤接口


    // 教师接口------------------------------------
    // 登陆
    @FormUrlEncoded
    @POST(Constant.TEA_LOGIN)
    Observable<Teacher> teaLogin(@Field("id") String username, @Field("password") String password);
    // 设置手机号
    @FormUrlEncoded
    @POST(Constant.TEA_SET_PHONE)
    Observable<Teacher> teaSetPhone(@Field("id") String username,@Field("phone") String phone);
    // 获取个人信息接口
    @FormUrlEncoded
    @POST(Constant.TEA_GET_INFO)
    Observable<Teacher> teaGetInfo(@Field("id") String username);
    // 查看管理班级信息
    @FormUrlEncoded
    @POST(Constant.TEA_GET_CLASS_INFO)
    Observable<TeacherClass> teaGetClassInfo(@Field("id") String username);
    // 修改学生寝室号
    @FormUrlEncoded
    @POST(Constant.TEA_SET_DORMNUM)
    Observable<Student> teaSetDormNum(@Field("id") String username, @Field("token") String token, @Field("dormNum") String dormNum);
    // 辅导员修改获取考勤信息
    @FormUrlEncoded
    @POST(Constant.TEA_GET_ATTENCE_RECORD)
    Observable<AttenceRecord> teaGetAttenceRecord(@Field("id") String username, @Field("week") String week, @Field("classNum") String classNum);
    // 辅导员修改学生照片

}
