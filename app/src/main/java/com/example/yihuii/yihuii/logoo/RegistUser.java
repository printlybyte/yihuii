package com.example.yihuii.yihuii.logoo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegistUser extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入手机号
     */
    private EditText mRegistEdit;
    /**
     * 请输入密码
     */
    private EditText mRegistEdit2;
    /**
     * 注册
     */
    private Button mRegistBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_user);
        initView();
    }

    private void initView() {
        mRegistEdit = (EditText) findViewById(R.id.regist_edit);
        mRegistEdit2 = (EditText) findViewById(R.id.regist_edit2);
        mRegistBtn = (Button) findViewById(R.id.regist_btn);
        mRegistBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_btn:
                luoji();
                break;
        }
    }

    private void luoji() {
        String aa = mRegistEdit.getText().toString();
        String bb = mRegistEdit2.getText().toString();
        if (aa.equals("") || bb.equals("")) {
            Toast.makeText(this, "请检查账户和密码", Toast.LENGTH_SHORT).show();
            return;
        }
        in(aa,bb);

    }

    private void in(String a,String bb ) {
        //step 1: 同样的需要创建一个OkHttpClick对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //step 2: 创建  FormBody.Builder
        FormBody formBody = new FormBody.Builder()
                .add("phone", a)
                .add("password", bb)
                .build();

        //step 3: 创建请求
        Request request = new Request.Builder().url("http://139.129.242.91/pc/yihui/public/Mobile/register")
                .post(formBody)
                .build();

        //step 4： 建立联系 创建Call对象
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 17-1-4  请求失败
                Toast.makeText(RegistUser.this, "请求失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 17-1-4 请求成功
                String aa = response.body().string().toString();
                Log.i("qwe", aa);
            }
        });
    }
}
