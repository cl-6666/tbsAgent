package com.maxvision.tbs.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.maxvision.tbs.R;
import com.maxvision.tbs.TbsUtils;
import com.maxvision.tbs.widget.FileReaderView;
import com.maxvision.tbs.widget.TbsTitleView;

public class TbsActivity extends AppCompatActivity {

    /** 文件路径*/
    public static final String FILE_PATH = "filePath";
    /** 内容标题*/
    public static final String CONTEN_TTITLE = "contentTitle";
    /** 文件阅读器View*/
    private FileReaderView mDocumentReaderView;
    /** 标题View*/
    private TbsTitleView mTbsTitleView;
    private String filePath;
    private String contentTitle;

    public static void viewFile(Context context, String localPath, String contentTitle) {
        Intent intent = new Intent(context, TbsActivity.class);
        intent.putExtra(FILE_PATH, localPath);
        intent.putExtra(CONTEN_TTITLE, contentTitle);
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
        mTbsTitleView = findViewById(R.id.title_main);
        mTbsTitleView.getLeftBackTextTv().setText("" + TbsUtils.getmTitle());
        contentTitle = getContentTitle();
        if (!TextUtils.isEmpty(contentTitle)) {
            mTbsTitleView.getTitleTv().setText(contentTitle);
        }
        mDocumentReaderView.show(filePath);
        mTbsTitleView.getLeftBackTextTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private String handleIntent() {
        if (getIntent() != null) {
            return getIntent().getStringExtra(FILE_PATH);
        }
        return null;
    }

    private String getContentTitle() {
        if (getIntent() != null) {
            return getIntent().getStringExtra(CONTEN_TTITLE);
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