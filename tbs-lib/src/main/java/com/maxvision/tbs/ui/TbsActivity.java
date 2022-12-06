package com.maxvision.tbs.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.maxvision.tbs.R;
import com.maxvision.tbs.widget.FileReaderView;

public class TbsActivity extends AppCompatActivity {

    /** 文件路径*/
    public static final String FILE_PATH = "filePath";
    /** 文件阅读器View*/
    private FileReaderView mDocumentReaderView;
    private String filePath;

    public static void viewFile(Context context, String localPath) {
        Intent intent = new Intent(context, TbsActivity.class);
        intent.putExtra(FILE_PATH, localPath);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbs);
        filePath = handleIntent();
        init();
    }

    public void init() {
        mDocumentReaderView = findViewById(R.id.documentReaderView);
        mDocumentReaderView.show(filePath);
    }


    private String handleIntent() {
        if (getIntent() != null) {
            return getIntent().getStringExtra(FILE_PATH);
        }
        return null;
    }

    public void onClickClose(View view) {
       finish();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDocumentReaderView != null) {
            mDocumentReaderView.stop();
        }
    }
}