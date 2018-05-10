package cn.edu.nyist.HttpHelper.Views;

import cn.edu.nyist.Entity.BaseResponse;

/**
 * Created by Leafage on 2018/5/9 19:56.
 * DESCRIPTION : 基础视图
 */

public interface BaseView {
    void onSuccess(BaseResponse baseResponse);
    void onError(String result);
}
