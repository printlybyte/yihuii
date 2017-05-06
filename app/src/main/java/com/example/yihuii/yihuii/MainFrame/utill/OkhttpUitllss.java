package com.example.yihuii.yihuii.MainFrame.utill;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuguodong on 2017/5/6.
 */

public class OkhttpUitllss {

    public static void in(String TOOKEN, String parmes, String parmes2, String parmes3) {
        //step 1: 同样的需要创建一个OkHttpClick对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //step 2: 创建  FormBody.Builder
        FormBody formBody = new FormBody.Builder()
                .add("username", "15524511963")
                .add("password", "123456")
                .build();

        //step 3: 创建请求
        Request request = new Request.Builder().url("http://139.129.242.91/pc/yihui/public/Mobile/" + TOOKEN)
                .post(formBody)
                .build();

        //step 4： 建立联系 创建Call对象
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 17-1-4  请求失败
//                handler.sendEmptyMessage(2);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 17-1-4 请求成功
                String aa = response.body().string();
                Message message = Message.obtain();
                message.what = 1;
                message.obj = response.body().string().toString();
//                handler.sendMessage(message);
                Log.i("qwe", aa);
            }
        });
    }
}
