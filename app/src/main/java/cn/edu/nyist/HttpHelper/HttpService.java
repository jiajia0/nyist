package cn.edu.nyist.HttpHelper;

import cn.edu.nyist.Common.Constant;
import cn.edu.nyist.Entity.Student;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Leafage on 2018/5/9 8:34.
 * DESCRIPTION : 定义与后台数据交互的接口
 */

public interface HttpService {

    @FormUrlEncoded
    @POST(Constant.STU_LOGIN)
    Observable<Student> login(@Field("id") String username, @Field("password") String password);
}
