package cn.edu.nyist.HttpHelper.Presenter;

import android.content.Intent;

import cn.edu.nyist.HttpHelper.DataManager;

/**
 * Created by Leafage on 2018/5/9 9:03.
 * DESCRIPTION : 基本的Presenter
 */

public class BasePresenter {
    protected DataManager mDataManager;

    public BasePresenter() {
        mDataManager = DataManager.getSingleInstance();
    }

    public void onCreate() {

    }

    public void onStop() {

    }

}
