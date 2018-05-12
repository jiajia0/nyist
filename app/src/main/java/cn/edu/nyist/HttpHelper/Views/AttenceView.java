package cn.edu.nyist.HttpHelper.Views;

import cn.edu.nyist.Entity.AttenceRecord;

/**
 * Created by Leafage on 2018/5/12 15:11.
 * DESCRIPTION :
 */

public interface AttenceView {
    void onSuccess(AttenceRecord attenceRecord);
    void onError(String result);
}
