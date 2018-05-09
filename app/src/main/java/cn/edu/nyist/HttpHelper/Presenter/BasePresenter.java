package cn.edu.nyist.HttpHelper.Presenter;


import cn.edu.nyist.HttpHelper.DataManager;
import cn.edu.nyist.HttpHelper.Views.BaseView;

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

    public void attachView(BaseView view) {

    }

    public void onStop() {

    }

}
