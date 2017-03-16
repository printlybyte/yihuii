package com.example.yihuii.yihuii.MainFrame.frament1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yihuii.yihuii.MainFrame.frament1.shezhi_pak.guanyu_xiangqing;
import com.example.yihuii.yihuii.MainFrame.frament1.shezhi_pak.yijian_fankui;
import com.example.yihuii.yihuii.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class Setting_activ extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private Button mBtn_tuichu, mBtn_setting;
    private TextView mTx_1, mTx_2, mTx_5;
    private Switch mSw_1, mSw_2;
    /**
     * 原密码
     */
    private EditText mEditYuanmima;
    /**
     * 新密码
     */
    private EditText mEditXinmima;
    /**
     * 显示密码
     */
    private CheckBox mCheckboxXianshi;
    /**
     * 保存
     */
    private Button mXiugaiBaocun;
    /**
     * 取消
     */
    private Button mXiugaiQuxiao;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_activ);
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        mBtn_setting = (Button) findViewById(R.id.mBtn_setting_back);
        mBtn_setting.setOnClickListener(this);
        mBtn_tuichu = (Button) findViewById(R.id.btn_tuichu);
        mBtn_tuichu.setOnClickListener(this);
        mTx_1 = (TextView) findViewById(R.id.txt1);
        mTx_2 = (TextView) findViewById(R.id.txt2);
        mTx_5 = (TextView) findViewById(R.id.txt5);
        mTx_1.setOnClickListener(this);
        mTx_2.setOnClickListener(this);
        mTx_5.setOnClickListener(this);
        mSw_1 = (Switch) findViewById(R.id.swich1);
        mSw_2 = (Switch) findViewById(R.id.swich2);
        mSw_1.setOnCheckedChangeListener(this);
        mSw_2.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tuichu:
                Toast.makeText(this, "Q", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt1:
                //xiugai
                costom_dialog();
                break;
            case R.id.txt2:
                //意见反馈
                Intent ii = new Intent(Setting_activ.this, yijian_fankui.class);
                startActivity(ii);
                break;
            case R.id.txt5:
                //关于
                Intent intent = new Intent(Setting_activ.this, guanyu_xiangqing.class);
                startActivity(intent);
                break;
            case R.id.mBtn_setting_back:
                Setting_activ.this.finish();
                break;
            default:
                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.swich1:
                if (isChecked) {
                    Toast.makeText(this, "A", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "B", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.swich2:
                if (isChecked) {
                    Intent ii = new Intent(Setting_activ.this, qindao_xiangqing.class);
                    startActivity(ii);
                } else {
                }
                break;

            default:
                break;

        }
    }

    private void costom_dialog() {
        AlertDialog.Builder bb = new AlertDialog.Builder(this);

        LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.costom_dialog_xiugaimima, null);
        mEditYuanmima = (EditText) ll.findViewById(R.id.edit_yuanmima);
        mEditXinmima = (EditText) ll.findViewById(R.id.edit_xinmima);
        mCheckboxXianshi = (CheckBox) ll.findViewById(R.id.checkbox_xianshi);
        mXiugaiBaocun = (Button) ll.findViewById(R.id.xiugai_baocun);
        mXiugaiQuxiao = (Button) ll.findViewById(R.id.xiugai_quxiao);
        bb.setView(ll);
        bb.create();
        final Dialog dialog = bb.create();
        //dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        //单机显示密码
        mCheckboxXianshi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(Setting_activ.this, "SA", Toast.LENGTH_SHORT).show();

                    mEditXinmima.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mEditYuanmima.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                } else {

                    mEditXinmima.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mEditYuanmima.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        mXiugaiBaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Setting_activ.this, "以保存", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        mXiugaiQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Setting_activ Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
