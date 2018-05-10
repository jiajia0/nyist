package cn.edu.nyist.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import cn.edu.nyist.App;
import cn.edu.nyist.Fragment.Stu_LeftFragment;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;
import cn.edu.nyist.Widget.ViewHolder;
import cn.edu.nyist.util.MySharedPreference;

public class MainActivity extends BaseActivity implements Stu_LeftFragment.OnFragmentInteractionListener{

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        /**
         * 登录控制
         */
        if (MySharedPreference.getSingleInstance(this).getIsLogin()) {
            Logger.d("isLogin---->"+MySharedPreference.getSingleInstance(this).getIsLogin());
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        Fragment stu_left = new Stu_LeftFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, stu_left).commit();
    }

    /**
     * 学生左视图
     */
    private void onSelectStuLeft() {

    }
    /**
     * 学生中间视图
     */
    private void onSelectStuMid() {

    }

    /**
     * 学生右视图
     */
    private void onSelectStuRight() {

    }

    /**
     * 老师左视图
     */
    private void onSelectTeaLeft() {

    }

    /**
     * 老师中间视图
     */
    private void onSelectTeaMid() {

    }

    /**
     * 老师视图
     */
    private void onSelectTeaRight() {

    }

    /**
     * 初始化APP，因为程序没有启动页面，把一些程序初始化的操作放在了这里
     */
    private void initApp() {
        App.IS_LOGIN = MySharedPreference.getSingleInstance(this).getIsLogin();
        App.LOGIN_USERNAME = MySharedPreference.getSingleInstance(this).getLoginName();
        App.LOGIN_ROLE = MySharedPreference.getSingleInstance(this).getLoginRole();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
