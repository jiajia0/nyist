package cn.edu.nyist.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.nyist.App;
import cn.edu.nyist.Entity.BaseResponse;
import cn.edu.nyist.HttpHelper.Presenter.StudentPresenter;
import cn.edu.nyist.HttpHelper.Views.BaseView;
import cn.edu.nyist.LogUtil.Logger;
import cn.edu.nyist.R;
import cn.edu.nyist.util.GetToken;
import cn.edu.nyist.util.MySharedPreference;

import static android.app.Activity.RESULT_OK;

public class StuMidFragment extends Fragment implements View.OnClickListener,BaseView {

    public static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    public static final int CROP_PHOTO = 2;// 剪裁
    private Button takePhoto;
    private Button attence;
    private ImageView picture;
    private Uri imageUri;
    public static File tempFile;

    private StudentPresenter mStudentPresenter;

    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        takePhoto = (Button) getActivity().findViewById(R.id.take_photo);
        picture = (ImageView) getActivity().findViewById(R.id.picture);

        mStudentPresenter = new StudentPresenter(getContext());
        mStudentPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == view) {
            view = inflater.inflate(R.layout.fragment_stu_mid, container, false);
        }
        initView();
        return view;
    }

    private void initView() {
//        view.findViewById(R.id.take_photo).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Logger.e("ISLOGIN===>" + App.IS_LOGIN );
//                Logger.e("NAME===>" + App.LOGIN_USERNAME );
//                Logger.e("ROLE===>" + App.LOGIN_ROLE );
//                MySharedPreference.getSingleInstance(getActivity()).setIsLogin(Boolean.FALSE);
//                MySharedPreference.getSingleInstance(getActivity()).setLoginRole(App.ROLE_TEACHER);
//                MySharedPreference.getSingleInstance(getActivity()).setLoginName("yangfengfan");
//
//            }
//
//        });
        takePhoto = view.findViewById(R.id.take_photo);
        takePhoto.setOnClickListener(this);
        attence = view.findViewById(R.id.attence);
        attence.setOnClickListener(this);
        picture = view.findViewById(R.id.picture);
//        view.findViewById(R.id.attence).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                App.IS_LOGIN = MySharedPreference.getSingleInstance(getActivity()).getIsLogin();
//                App.LOGIN_ROLE = MySharedPreference.getSingleInstance(getActivity()).getLoginRole();
//                App.LOGIN_USERNAME = MySharedPreference.getSingleInstance(getActivity()).getLoginName();
//                Logger.e("ISLOGIN===>" + App.IS_LOGIN );
//                Logger.e("NAME===>" + App.LOGIN_USERNAME );
//                Logger.e("ROLE===>" + App.LOGIN_ROLE );
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 进行拍照
            case R.id.take_photo:
                openCamera(getActivity());
                //打开系统相册方法
                //openGallery();
                break;
                // 进行考勤
            case R.id.attence:
                if (tempFile == null) {
                    Logger.d("tempFile is Null!");
                    Toast.makeText(getContext(), "图片为空！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // 生成带设备号的token，用来查寝
                    String token = getAttenceToken();
                    mStudentPresenter.stuAttence(App.LOGIN_USERNAME, token, tempFile);
                    Toast.makeText(getContext(), "提交到服务器", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private String getAttenceToken() {
        // 生成带设备号的token，用来查寝
        String token = GetToken.getEquipToken(getContext(),Integer.valueOf(App.LOGIN_USERNAME), "198127398273457","attence.salt");
        Logger.d("带设备号的token：" + token);
        return token;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.d("requestCode:" + requestCode);

        switch (requestCode) {
            case PHOTO_REQUEST_CAREMA:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver()
                                .openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //打开系统相册的回调
            /*case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;*/
        }

    }

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, CROP_PHOTO);
    }

    public void openCamera(Activity activity) {
        //獲取系統版本
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                    "yyyy_MM_dd_HH_mm_ss");
            String filename = timeStampFormat.format(new Date());
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    filename + ".jpg");
            if (currentapiVersion < 24) {
                // 从文件中创建uri
                imageUri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                //兼容android7.0 使用共享文件的形式
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
                //检查是否有存储权限，以免崩溃
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    Toast.makeText(getContext(), "请开启存储权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        //activity.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);

        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    /*
* 判断sdcard是否被挂载
*/
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 考勤成功回掉函数
     * @param baseResponse
     */
    @Override
    public void onSuccess(BaseResponse baseResponse) {
        Logger.d("attence:" + baseResponse.getStatus());
        if (baseResponse.getStatus() == 0) {
            Toast.makeText(getContext(), "考勤成功！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String result) {
        Toast.makeText(getContext(), "未知异常！", Toast.LENGTH_SHORT).show();
    }
}
