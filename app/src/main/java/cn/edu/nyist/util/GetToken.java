package cn.edu.nyist.util;

import android.content.Context;

import java.util.Date;

/**
 * 账号Token生成工具
 *
 * @author zhangsiqi
 * @create 2018-04-24-17:29
 */

public class GetToken {

    public static String getToken(int id, String salt) {
        // 学号 + 日期
        String str = id + DateTimeUtil.getYyyyMmDd();
        return MD5Util.MD5EncodeUtf8(str, salt);
    }

    public static String getEquipToken(int id, String equip, String salt) {
        String str = id + DateTimeUtil.getYyyyMmDd() + equip;
        return MD5Util.MD5EncodeUtf8(str, salt);
    }

    // 添加context
    public static String getToken(Context context,int id, String salt) {
        // 学号 + 日期
        String str = id + DateTimeUtil.getYyyyMmDd();
        return MD5Util.MD5EncodeUtf8(context, str, salt);
    }

    public static String getEquipToken(Context context, int id, String equip, String salt) {
        String str = id + DateTimeUtil.getYyyyMmDd() + equip;
        return MD5Util.MD5EncodeUtf8(context, str, salt);
    }
}
