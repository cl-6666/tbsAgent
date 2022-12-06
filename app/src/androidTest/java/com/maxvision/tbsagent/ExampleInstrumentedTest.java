package com.maxvision.tbsagent;

import android.util.Log;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.io.File;

/**
 * Instrumented test, which will execute on an Android device.
 * 测试用例
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("com.maxvision.tbsagent", appContext.getPackageName());

        Log.i("TAG", "当前文件是否为空：" + fileIsExists("/data/user/0/com.maxvision.tbsagent/files/TestDoc.doc"));

    }


    public boolean fileIsExists(String fileName) {
        try {
            File f = new File(fileName);
            if (f.exists()) {
                Log.i("测试", "有这个文件");
                return true;
            } else {
                Log.i("测试", "没有这个文件");
                return false;
            }
        } catch (Exception e) {
            Log.i("测试", "崩溃");
            return false;
        }
    }
}