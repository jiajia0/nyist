package cn.edu.nyist.WifiUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Leafage on 2018/5/12 15:32.
 * DESCRIPTION : 监听Wifi广播的变化
 */

public class WifiConnectChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Wifi链接的状态广播接收
        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            // 获取Wifi状态
            NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            // 得到此时的Wifi状态，进行处理
            if (networkInfo != null) {
                networkInfo.getState();
            }
        }
    }
}
