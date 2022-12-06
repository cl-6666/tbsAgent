package com.maxvision.tbs;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.maxvision.tbs.ui.TbsActivity;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;


/**
 * name：cl
 * date：2022/12/5
 * desc：框架初始化
 */
public final class TbsUtils {

    private static final String TAG = "TbsUtils";

    /** Application 对象*/
    private static Application sApplication;

    /** tbs是否加载完成判断*/
    private static boolean tbsLoad = false;

    /** 不允许被外部实例化*/
    private TbsUtils() {
    }

    /**
     * 初始化 TBS，需要在 Application.create 中初始化
     *
     * @param application 应用的上下文
     */
    public static void init(Application application) {
        sApplication = application;
        initTbs();
    }

    private static void initTbs() {
        /* 设置允许移动网络下进行内核下载。默认不下载，会导致部分一直用移动网络的用户无法使用x5内核 */
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.setCoreMinVersion(QbSdk.CORE_VER_ENABLE_202112);
        /* SDK内核初始化周期回调，包括 下载、安装、加载 */
        QbSdk.setTbsListener(new TbsListener() {

            /**
             * @param stateCode 用户可处理错误码请参考{@link com.tencent.smtt.sdk.TbsCommonCode}
             */
            @Override
            public void onDownloadFinish(int stateCode) {
                Log.i(TAG, "onDownloadFinished: " + stateCode);
            }

            /**
             * @param stateCode 用户可处理错误码请参考{@link com.tencent.smtt.sdk.TbsCommonCode}
             */
            @Override
            public void onInstallFinish(int stateCode) {
                Log.i(TAG, "onInstallFinished: " + stateCode);
            }

            /**
             * 首次安装应用，会触发内核下载，此时会有内核下载的进度回调。
             * @param progress 0 - 100
             */
            @Override
            public void onDownloadProgress(int progress) {
                Log.i(TAG, "Core Downloading: " + progress);
                if (progress == 100) {
                    tbsLoad = true;
                }
            }
        });

        /* 此过程包括X5内核的下载、预初始化，接入方不需要接管处理x5的初始化流程，希望无感接入 */
        QbSdk.initX5Environment(sApplication, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                // 内核初始化完成，可能为系统内核，也可能为系统内核
                Log.i(TAG, "TBS内核初始化完成，可能为系统内核，也可能为系统内核");
                tbsLoad = true;
            }

            /**
             * 预初始化结束
             * 由于X5内核体积较大，需要依赖wifi网络下发，所以当内核不存在的时候，默认会回调false，此时将会使用系统内核代替
             * 内核下发请求发起有24小时间隔，卸载重装、调整系统时间24小时后都可重置
             * 调试阶段建议通过 WebView 访问 debugtbs.qq.com -> 安装线上内核 解决
             * @param isX5 是否使用X5内核
             */
            @Override
            public void onViewInitFinished(boolean isX5) {
                Log.i(TAG, "onViewInitFinished: " + isX5);
                // hint: you can use QbSdk.getX5CoreLoadHelp(context) anytime to get help.
            }
        });
    }


    public static void loadFileType(Context context, String localPath){
        if (TextUtils.isEmpty(localPath)){
            Log.e(TAG, "localPath不能为空！");
            return;
        }
        if (!TbsUtils.isInit()) {
            Log.e(TAG, "TBS内部加载失败！");
            return;
        }
        TbsActivity.viewFile(context,localPath);
    }


    /**
     * 判断当前框架是否已经初始化
     */
    public static boolean isInit() {
        return sApplication != null && tbsLoad;
    }

}
