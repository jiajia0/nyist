package cn.edu.nyist.Widget;

import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.edu.nyist.LogUtil.Config;
import cn.edu.nyist.LogUtil.Logger;

/**
 * Created by Leafage on 2018/5/8 16:18.
 * DESCRIPTION : 操作View的各种设置
 */

public class ViewHolder {
    private SparseArray<View> mViews;
    private View mRootView;

    public ViewHolder(LayoutInflater inflater, ViewGroup parent, int layoutId) {
        Logger.init().setLevel(Config.LEVEL_DEBUG);
        this.mViews = new SparseArray<>();
        mRootView = inflater.inflate(layoutId, parent, false);
    }

    /**
     * 通过View的id来获取子View
     *
     * @param resId view的id
     * @param <T>   泛型
     * @return 子View
     */
    public <T extends View> T get(int resId) {
        View view = mViews.get(resId);
        //如果该View没有缓存过，则查找View并缓存
        if (view == null) {
            view = mRootView.findViewById(resId);
            mViews.put(resId, view);
        }
        if (view == null){
            Logger.d("View == null");
        }
        return (T) view;
    }

    /**
     * 获取布局View
     *
     * @return 布局View
     */
    public View getRootView() {
        return mRootView;
    }

    /**
     * 设置文本
     *
     * @param res_id view 的 id
     * @param text   文本内容
     * @return 是否成功
     */
    public boolean setText(CharSequence text, @NonNull int res_id) {
        try {
            TextView textView = get(res_id);
            textView.setText(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setText(@NonNull int res_id, CharSequence text) {
        return setText(text, res_id);
    }

    /**
     * 设置监听器
     *
     * @param l   监听器
     * @param ids view 的 id
     */
    public void setOnClickListener(View.OnClickListener l, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(l);
        }
    }

}
