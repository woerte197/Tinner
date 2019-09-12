package com.wangyang.baselibrary.utils.camera;


/*
 * 照相机调用类
 * */

import android.content.Context;
import android.net.Uri;


public class CameraModel {

    public static Uri createCropFile() {
//        return Uri.parse(FileUtil.createFile("crop_image",
//                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
        return null;
    }

    public static void start(Context context) {
//        new CameraHandler(context).beginCameraDialog();
    }
}
