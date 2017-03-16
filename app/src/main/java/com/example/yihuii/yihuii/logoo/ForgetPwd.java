package com.example.yihuii.yihuii.logoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import static com.example.yihuii.yihuii.R.id.forgret_fanhui;

public class ForgetPwd extends AppCompatActivity {
    private Button mForget_btn_yes;
    private EditText mForget_edit_phonenum;
    private String  PATH_URL="";
    private TextView tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        initView();
    }

    private void initView() {
        tt= (TextView) findViewById(forgret_fanhui);

        mForget_btn_yes = (Button) findViewById(R.id.btn_forget_fasong);
        mForget_edit_phonenum = (EditText) findViewById(R.id.edit_btn_phone);
        mForget_btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForgetPwd.this, "发送", Toast.LENGTH_SHORT).show();
            }
        });
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });
    }
    private void  Postinternet(){
        HttpUtils utils=new HttpUtils();
        RequestParams params=new RequestParams();
        utils.send(HttpRequest.HttpMethod.POST, PATH_URL, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }

}
