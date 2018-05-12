package cn.edu.nyist.util;

import android.content.Context;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import cn.edu.nyist.LogUtil.Logger;

/**
 * Created by zhangsiqi
 */
public class PropertiesUtil {

    private static Properties props;

    static {
        String fileName = "config.properties";
        InputStream in = null;
        props = new Properties();
        try {
            //props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
            //InputStream is = context.getAssets().open("test.properties");
            //props.load(is);
            in = new FileInputStream(android.os.Environment.getExternalStorageDirectory() + File.separator
                    + fileName);
            props.load(in);
        } catch (IOException e) {
            Logger.e("配置文件读取异常" + e);
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(Context context,String key,String defaultValue) {
        Properties properties = new Properties();
        try {
            InputStream in=context.getAssets().open("config.properties");
            properties.load(in);
            in.close();
        }catch (IOException e){
            //  ...
            return null;
        }
        String value = properties.getProperty(key);
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }


    /*public static String getProperties(Context context, String key) {
        String value = null;
        Properties properties = new Properties();
        try {
            properties.load(context.getAssets().open("config.properties"));
            value = properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }*/

    public static String getProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }

}
