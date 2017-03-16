package com.example.yihuii.yihuii.logoo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.MainActivity;
import com.example.yihuii.yihuii.R;


import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LogoinActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton_regist, mButton_forget, mButton_logoin, mButton_weixin, mButton_qq, mButton_weibo;
    private EditText edit_user, edit_pass;
    private Context context;
    private String USER = "123456";
    private String PASSWORD = "123456";
    private int HANDLER_NUM = 0;
    private Dialog dialog;
//   String  APP_ID="wx74c15f9950ca03b1";
//    IWXAPI api;
//    String text="qwe";
    /*
    * @parms
    * */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HANDLER_NUM) {
                dialog.dismiss();
                Intent intent = new Intent(LogoinActivity.this, MainActivity.class);
                startActivity(intent);

                LogoinActivity.this.finish();

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logoin);

//        initWX();
        context = this;
        initView();

        //mob 短信验证
        SMSSDK.initSDK(this, "193ea9e9f8654", "ef8468f9a1f95779708d4b6fa612b6a3");
    }

    /*
    * @initView  初始化布局控件
    * @  name liuguodong
    * */
    private void initView() {
        mButton_regist = (Button) findViewById(R.id.button_registT);
        mButton_forget = (Button) findViewById(R.id.button_forget);
        mButton_logoin = (Button) findViewById(R.id.button_logoin);
//        mButton_weixin = (Button) findViewById(R.id.btn_weixin);
//        mButton_qq = (Button) findViewById(R.id.btn_qq);
//        mButton_weibo = (Button) findViewById(R.id.btn_weibo);
//        mButton_weixin.setOnClickListener(this);
//        mButton_qq.setOnClickListener(this);
//        mButton_weibo.setOnClickListener(this);
        mButton_regist.setOnClickListener(this);
        mButton_forget.setOnClickListener(this);
        mButton_logoin.setOnClickListener(this);
        edit_user = (EditText) findViewById(R.id.edit_user);
        edit_pass = (EditText) findViewById(R.id.edit_pass);


    }

      /*
    * @onClick  设置点击事件
    * @  name liuguodong
    * */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_registT:
                mobValide();
                break;
            case R.id.button_forget:
                Intent intent = new Intent(LogoinActivity.this, ForgetPwd.class);
                startActivity(intent);
                break;
            case R.id.button_logoin:
                String user = edit_user.getText().toString();
                String pass = edit_pass.getText().toString();
                if ("".equals(user)) {
                    Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(pass)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (user.equals(USER) && pass.equals(PASSWORD)) {
                    dialog = Costom_dialog.createLoadingDialog(LogoinActivity.this, "正在加载中");
                    Message message = new Message();
                    message.what = HANDLER_NUM;
                    handler.sendMessage(message);
                } else {
                    Toast.makeText(context, "账号或者密码错误", Toast.LENGTH_SHORT).show();
                }

                break;
//            case R.id.btn_qq:
//                break;
//            case R.id.btn_weibo:
//                break;
//            case R.id.btn_weixin:
//                break;
            default:
                break;
        }
    }
//    private void initWX() {
//        api = WXAPIFactory.createWXAPI(LogoinActivity.this,APP_ID, true);
//        api.registerApp(APP_ID);
//    }
    private void mobValide() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                    Toast.makeText(LogoinActivity.this, country + phone, Toast.LENGTH_SHORT).show();//登录成功咋在此进行操作

                    // 提交用户信息（此方法可以不调用）
                    //  registerUser(country, phone);
                }
            }
        });
        registerPage.show(LogoinActivity.this);
        //ContactsPage contactsPage = new ContactsPage();
        //contactsPage.show(context)；这两句话是调用手机所有联系人列表的
    }
}
