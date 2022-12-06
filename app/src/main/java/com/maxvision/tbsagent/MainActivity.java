package com.maxvision.tbsagent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.maxvision.tbs.TbsUtils;
import com.maxvision.tbs.widget.FileReaderView;
import com.permissionx.guolindev.PermissionX;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        copy();
        init();
    }

    private void requestPermissions() {
        String[] perms = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
        };
        PermissionX.init(this)
                .permissions(perms)
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {
                        Toast.makeText(MainActivity.this, "All permissions are granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void init() {
        findViewById(R.id.tv_start).setOnClickListener(view -> {
             //弹出保存图片的对话框
            new AlertDialog.Builder(MainActivity.this)
                    .setItems(new String[]{"加载word文档", "加载excel文件", "加载pdf", "加载ppt文件", "加载txt文件"},
                            (dialog, which) -> {
                                switch (which) {
                                    case 1:
                                        filePath = getFilePath(1);
                                        TbsUtils.loadFileType(this,filePath,"我是标题");
                                        break;
                                    case 2:
                                        filePath = getFilePath(2);
                                        TbsUtils.loadFileType(this,filePath);
                                        break;
                                    case 3:
                                        filePath = getFilePath(3);
                                        TbsUtils.loadFileType(this,filePath);
                                        break;
                                    case 4:
                                        filePath = getFilePath(4);
                                        TbsUtils.loadFileType(this,filePath);
                                        break;
                                    case 5:
                                        filePath = getFilePath(5);
                                        TbsUtils.loadFileType(this,filePath);
                                        break;
                                    default:
                                        filePath = getFilePath(0);
                                        TbsUtils.loadFileType(this,filePath);
                                        break;
                                }
                            })
                    .show();
        });

    }


    private String getFilePath(int position) {
        String path = null;
        switch (position) {
            case 0:
                path = getFilesDir().getAbsolutePath() + File.separator + "TestDoc.doc";
                break;

            case 1:
                path = getFilesDir().getAbsolutePath() + File.separator +"TestExcel.xls";
                break;

            case 2:
                path = getFilesDir().getAbsolutePath() + File.separator +"TestPDF.pdf";
                break;

            case 3:
                path = getFilesDir().getAbsolutePath() + File.separator +"TestPPT.ppt";
                break;
            case 4:
                path = getFilesDir().getAbsolutePath() + File.separator +"TestTXT.txt";
                break;
            default:
                break;
        }
        return path;
    }


    private void copy() {
        // 开始复制
        String path = "test" + File.separator;
        copyAssetsFileToAppFiles(path + "TestDoc.doc", "TestDoc.doc");
        copyAssetsFileToAppFiles(path + "TestExcel.xls", "TestExcel.xls");
        copyAssetsFileToAppFiles(path + "TestPDF.pdf", "TestPDF.pdf");
        copyAssetsFileToAppFiles(path + "TestPPT.ppt", "TestPPT.ppt");
        copyAssetsFileToAppFiles(path + "TestPPT.ppt", "TestTXT.txt");

    }


    /**
     * 从assets目录中复制某文件内容
     *
     * @param assetFileName assets目录下的文件
     * @param newFileName   复制到/data/data/package_name/files/目录下文件名
     */
    private void copyAssetsFileToAppFiles(String assetFileName, String newFileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = this.getAssets().open(assetFileName);
            fos = this.openFileOutput(newFileName, Context.MODE_PRIVATE);
            int byteCount = 0;
            byte[] buffer = new byte[1024];
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}