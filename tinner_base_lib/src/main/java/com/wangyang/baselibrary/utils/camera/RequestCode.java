package com.wangyang.baselibrary.utils.camera;


import com.yalantis.ucrop.UCrop;

/*
* 请求码存储
* 存储一些中间值
* */
public class RequestCode {
    public static final int TAKE_PHOTO = 4;
    public static final int PICK_PHOTO = 5;
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    public static final int CROP_ERROR = UCrop.RESULT_ERROR;
    public static final int SCAN_PHOTO = 7;

}
