package com.example.yihuii.yihuii.MainFrame.frament4.ziactivity.xuqiu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;

public class Need_chuangjian extends AppCompatActivity implements View.OnClickListener {
    private Button mBtn_fabu;
    private TextView mTx_guanbi, mSpinner;
    private Spinner mSpinner2;
    private EditText mEdit_txt;
    private String str[] = {"商务", "人才", "信息", "活动", "交友"};
    private String boo[] = {"", ""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_chuangjian);
        initView();
    }

    private void initView() {
        mEdit_txt = (EditText) findViewById(R.id.xuqiu_chuanjian_shuru);
        mSpinner = (TextView) findViewById(R.id.spinner_1);
        //mSpinner2 = (Spinner) findViewById(R.id.spinner_2);
        mBtn_fabu = (Button) findViewById(R.id.xuqiu_chuanjian_fabu);
        mTx_guanbi = (TextView) findViewById(R.id.xuqiu_chuangjian_guanbi);
        mBtn_fabu.setOnClickListener(this);
        mSpinner.setOnClickListener(this);
        // mSpinner2.setOnItemSelectedListener(this);
        mTx_guanbi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xuqiu_chuangjian_guanbi:
                Intent iiq = new Intent();
                iiq.putExtra("content", mEdit_txt.getText().toString());
                setResult(1, iiq);
                finish();
                break;
            case R.id.xuqiu_chuanjian_fabu:
                Intent ii = new Intent();
                ii.putExtra("content", mEdit_txt.getText().toString());
                setResult(0, ii);
                finish();
            case R.id.spinner_1:
                Costom_dialog_item();
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent ii = new Intent();
            ii.putExtra("content", mEdit_txt.getText().toString());
            setResult(0, ii);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    private void Costom_dialog_item() {
        AlertDialog.Builder bb = new AlertDialog.Builder(this);
        bb.create();
        bb.setSingleChoiceItems(str, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSpinner.setText("" + str[which]);
            }
        });
        bb.setNeutralButton("确认", null);

        bb.show();
    }
}
