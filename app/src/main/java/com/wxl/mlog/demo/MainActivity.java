package com.wxl.mlog.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wxl.mlog.MLog;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MLog.debug("  btn click");
            }
        });

        MLog.debug("print debug");
        MLog.error("print error");

        printOkhttpLogInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MLog.info("print info");
        MLog.verbose("print verbose");
        MLog.warn("print warn");

    }

    private void printOkhttpLogInfo() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://www.baidu.com/")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
                MLog.debug(" response = " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MLog.debug(" response = " + response.message());
            }
        });

    }
}
