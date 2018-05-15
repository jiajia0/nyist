package cn.edu.nyist.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.githang.statusbar.StatusBarCompat;

import java.io.Serializable;

import cn.edu.nyist.App;
import cn.edu.nyist.Fragment.StuLeftFragment;
import cn.edu.nyist.Fragment.StuMidFragment;
import cn.edu.nyist.Fragment.StuRightFragment;
import cn.edu.nyist.Fragment.TeaLeftFragment;
import cn.edu.nyist.Fragment.TeaMidFragment;
import cn.edu.nyist.Fragment.TeaRightFragment;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;
import cn.edu.nyist.Widget.ViewHolder;
import cn.edu.nyist.util.MySharedPreference;
import cn.edu.nyist.util.SystemUtil;

public class MainActivity extends BaseActivity {

    Fragment fg_current;
    Fragment fg_stu_left;
    Fragment fg_stu_mid;
    Fragment fg_stu_right;
    Fragment fg_tea_left;
    Fragment fg_tea_mid;
    Fragment fg_tea_right;

    int index_navigation = 1; //BottomNavigationView指标

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        initApp();
        Logger.init();
        Logger.d("---------------"+MySharedPreference.getSingleInstance(this).getIsLogin());
        Logger.d("---------------"+MySharedPreference.getSingleInstance(this).getLoginName());
        Logger.d("---------------"+MySharedPreference.getSingleInstance(this).getLoginRole());
        /**
         * 登录控制
         */
        if (!MySharedPreference.getSingleInstance(this).getIsLogin()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        if (App.LOGIN_ROLE == App.ROLE_STUDENT) {
            if ( fg_stu_left == null ) {
                fg_stu_left = new StuLeftFragment();
            }
            if ( !fg_stu_left.isAdded() ) {
                getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, fg_stu_left).commit();
                fg_current = fg_stu_left;
            }
        } else  {
            if ( fg_tea_left == null ) {
                fg_tea_left = new TeaLeftFragment();
            }
            if ( !fg_tea_left.isAdded() ) {
                getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, fg_tea_left).commit();
                fg_current = fg_tea_left;
            }
        }
    }

    /**
     * 学生左视图
     */
    private void onSelectStuLeft() {
        Logger.d("onSelectStuLeft");
        if (null == fg_stu_left) {
            fg_stu_left = new StuLeftFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), fg_stu_left);
    }

    /**
     * 学生中间视图
     */
    private void onSelectStuMid() {
        Logger.d("onSelectStuMid");
        if (null == fg_stu_mid) {
            fg_stu_mid = new StuMidFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), fg_stu_mid);
    }

    /**
     * 学生右视图
     */
    private void onSelectStuRight() {
        Logger.d("onSelectStuRight");
        if (null == fg_stu_right) {
            fg_stu_right= new StuRightFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), fg_stu_right);
    }

    /**
     * 老师左视图
     */
    private void onSelectTeaLeft() {
        Logger.d("onSelectTeaLeft");
        if (null == fg_tea_left) {
            fg_tea_left = new TeaLeftFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), fg_tea_left);
    }

    /**
     * 老师中间视图
     */
    private void onSelectTeaMid() {
        Logger.d("onSelectTeaMid");
        if (null == fg_tea_mid) {
            fg_tea_mid = new TeaMidFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), fg_tea_mid);
    }

    /**
     * 老师右视图
     */
    private void onSelectTeaRight() {
        Logger.d("onSelectTeaRight");
        if (null == fg_tea_right) {
            fg_tea_right = new TeaRightFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), fg_tea_right);
    }

    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (fg_current == fragment) {
            return;
        }
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(fg_current).add(R.id.main_fragment, fragment).commit();
        } else {
            transaction.hide(fg_current).show(fragment).commit();
        }
        fg_current = fragment;
    }

    /**
     * 显示Fragment
     */
    private void showViewOnActivity() {
        if (App.LOGIN_ROLE == App.ROLE_STUDENT) {
            switch (index_navigation) {
                case 1:
                    onSelectStuLeft();
                    break;
                case 2:
                    onSelectStuMid();
                    break;
                case 3:
                    onSelectStuRight();
                    break;
            }
        } else if (App.LOGIN_ROLE == App.ROLE_TEACHER){
            switch (index_navigation) {
                case 1:
                    onSelectTeaLeft();
                    break;
                case 2:
                    onSelectTeaMid();
                    break;
                case 3:
                    onSelectTeaRight();
                    break;
            }
        } else {
            Logger.e("空");
            App.LOGIN_ROLE = App.ROLE_NULL;
            Logger.e("LOGIN_ROLE == NULL");
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_left:
                    index_navigation = 1;
                    showViewOnActivity(); //显示对应的Fragment
                    return true;
                case R.id.navigation_mid:
                    index_navigation = 2;
                    showViewOnActivity(); //显示对应的Fragment
                    return true;
                case R.id.navigation_right:
                    index_navigation = 3;
                    showViewOnActivity(); //显示对应的Fragment
                    return true;
            }
            return false;
        }
    };

    /**
     * 初始化APP，因为程序没有启动页面，把一些程序初始化的操作放在了这里
     */
    private void initApp() {

        /**
        MySharedPreference.getSingleInstance(this).
                setIsLogin(Boolean.TRUE).
                setLoginRole(App.ROLE_TEACHER).
                setLoginName("teacher");
*/

        App.IS_LOGIN = MySharedPreference.getSingleInstance(this).getIsLogin();
        App.LOGIN_USERNAME = MySharedPreference.getSingleInstance(this).getLoginName();
        App.LOGIN_ROLE = MySharedPreference.getSingleInstance(this).getLoginRole();
    }
}
