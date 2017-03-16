package com.example.yihuii.yihuii.MainFrame.frament4.me_mingpian_edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;


public class me_bianji_jiaoyujingli extends AppCompatActivity implements View.OnClickListener {
    private EditText editText, editText2, editText3;
    private TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_bianji_jiaoyujingli);
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.gongzuo_gongsi_edit_jiaoyu);

        editText2 = (EditText) findViewById(R.id.gongzuo_zhiwei_edit_jiaoyu);
        editText3 = (EditText) findViewById(R.id.gongzuo_ruxue_time_edit);
        textView = (TextView) findViewById(R.id.gongzuo_fanhui_jiaoyu);
        textView2 = (TextView) findViewById(R.id.gongzuo_baocun_jiaoyu);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);



//        editText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editText.setHint("");
//                editText2.setHint("请输入专业");
//                editText3.setHint("请输入入学时间");
//
//            }
//        });
//        editText2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editText.setHint("请输入专业");
//                editText2.setHint("");
//                editText3.setHint("请输入入学时间");
//
//            }
//        });
//        editText3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editText.setHint("请输入学历");
//                editText2.setHint("请输入专业");
//                editText3.setHint("");
//            }
//        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gongzuo_fanhui_jiaoyu:
                finish();
                break;
            case R.id.gongzuo_baocun_jiaoyu:
                if (editText.getText().toString().equals("") || editText2.getText().toString().equals("") || editText3.getText().toString().equals("")) {
                    Toast.makeText(this, "输入为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent ii = getIntent();
                ii.putExtra("gongsi1", editText.getText().toString());
                ii.putExtra("zhiwei1", editText2.getText().toString());
                ii.putExtra("jiaoyushijian", editText3.getText().toString());
                setResult(22, ii);
                finish();
                break;
            default:
                break;
        }
    }
}

