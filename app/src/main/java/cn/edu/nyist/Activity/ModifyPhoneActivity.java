package cn.edu.nyist.Activity;

import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;

import org.apache.commons.lang3.StringUtils;

import cn.edu.nyist.R;
import cn.edu.nyist.Widget.ViewHolder;

/**
 * Created by yff on 2018/5/18.
 */

public class ModifyPhoneActivity extends BaseActivity implements View.OnClickListener {

    TextView tv_cancle;
    TextInputEditText tie_phone;
    Button btu_submit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_phone;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        //沉浸式状态栏
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.blue), true);
        tv_cancle = holder.get(R.id.ac_modify_phone_tv_cacle);
        tie_phone = holder.get(R.id.ac_modify_phone_tie_phone);
        btu_submit = holder.get(R.id.ac_modify_phone_btu_submit);
        holder.setOnClickListener(this, R.id.ac_modify_phone_tv_cacle, R.id.ac_modify_phone_btu_submit);
    }

    /**
     * 点击取消
     */
    private void onClickCancle() {
       this.finish();
    }

    /**
     * 提交修改
     */
    private void onCliclSubmit() {
        if (StringUtils.isEmpty(tie_phone.getText().toString())) {
            toastShort("请检查数量的电话号码");
            return;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_modify_phone_tv_cacle:
                onClickCancle();
                break;
            case R.id.ac_modify_phone_btu_submit:
                onCliclSubmit();
                break;
        }
    }
}
