package cn.edu.nyist.HttpHelper;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import cn.edu.nyist.Common.Constant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leafage on 2018/5/9 9:09.
 * DESCRIPTION : 设置OkHttp
 */

public class HttpHelper {
    private Context mContext;

    OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build();

    GsonConverterFactory mConverterFactory = GsonConverterFactory.create(new GsonBuilder().create());

    private HttpHelper instance = null;
    private Retrofit mRetrofit = null;

    public HttpHelper() {

    }

    public HttpHelper(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(mConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public HttpService getServer() {
        return mRetrofit.create(HttpService.class);
    }

    public HttpHelper setContext(Context context) {
        return new HttpHelper(context);
    }

}
