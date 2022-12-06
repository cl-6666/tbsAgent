package com.maxvision.tbsagent;

import android.app.Application;

import com.maxvision.tbs.TbsUtils;

/**
 * name：cl
 * date：2022/12/5
 * desc：类备注
 */
public class APP extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化tbs，默认标题是返回
        TbsUtils.init(this,"我是返回");
    }
}
