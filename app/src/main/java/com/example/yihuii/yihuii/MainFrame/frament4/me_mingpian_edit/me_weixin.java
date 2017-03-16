package com.example.yihuii.yihuii.MainFrame.frament4.me_mingpian_edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;

public class me_weixin extends AppCompatActivity implements View.OnClickListener {
    private TextView mT_title, mT_pubic_fanhui, mT_public_baocun;
    private EditText editText;
    private String qufen = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_weixin);
        initView();
        qubiee();
    }

    private void initView() {
        mT_title = (TextView) findViewById(R.id.public_textView);
        editText = (EditText) findViewById(R.id.public_edit);
        mT_pubic_fanhui = (TextView) findViewById(R.id.public_fanhui);
        mT_public_baocun = (TextView) findViewById(R.id.public_baocun);
        mT_pubic_fanhui.setOnClickListener(this);
        mT_public_baocun.setOnClickListener(this);
    }

    private void qubiee() {
        Intent intent = getIntent();
        String qq = intent.getStringExtra("qubie");
        if (intent == null) {
            Toast.makeText(this, "intent为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (qq.equals("weixin")) {
            mT_title.setText("请输入微信");
            editText.setHint("请输入微信");
            qufen = "3";
        }
        if (qq.equals("qq")) {
            mT_title.setText("请输入qq");
            editText.setHint("请输入qq");
            qufen = "4";

        }
        if (qq.equals("youxiang")) {
            mT_title.setText("请输入邮箱");
            editText.setHint("请输入邮箱");
            qufen = "5";

        }
        if (qq.equals("shouji")) {
            mT_title.setText("请输入手机号");
            editText.setHint("请输入手机号");
            qufen = "6";

        }  if (qq.equals("lianxi")) {
            mT_title.setText("请输入联系方式");
            editText.setHint("请输入联系方式");
            qufen = "7";

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.public_fanhui:
                finish();
                break;
            case R.id.public_baocun:
                if (editText.getText().toString().equals("")) {
                    Toast.makeText(this, "输入为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (qufen.equals("3")) {
                    Intent intent = new Intent();
                    intent.putExtra("weixin", editText.getText().toString());
                    setResult(33, intent);
                }
                if (qufen.equals("4")) {
                    Intent intent = new Intent();
                    intent.putExtra("qq", editText.getText().toString());
                    setResult(44, intent);
                }
                if (qufen.equals("5")) {
                    Intent intent = new Intent();
                    intent.putExtra("youxiang", editText.getText().toString());
                    setResult(55, intent);
                }
                if (qufen.equals("6")) {
                    Intent intent = new Intent();
                    intent.putExtra("shouji", editText.getText().toString());
                    setResult(66, intent);
                }if (qufen.equals("7")) {
                    Intent intent = new Intent();
                    intent.putExtra("lianxi", editText.getText().toString());
                    setResult(77, intent);
                }
                finish();
                break;
            default:
                break;
        }
    }
}
