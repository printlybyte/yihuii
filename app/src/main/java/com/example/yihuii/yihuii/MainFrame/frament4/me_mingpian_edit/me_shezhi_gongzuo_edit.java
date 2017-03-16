package com.example.yihuii.yihuii.MainFrame.frament4.me_mingpian_edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;

public class me_shezhi_gongzuo_edit extends AppCompatActivity implements View.OnClickListener {
    private EditText editText, editText2;
    private TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_shezhi_gongzuo_edit);
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.gongzuo_gongsi_edit);
        editText2 = (EditText) findViewById(R.id.gongzuo_zhiwei_edit);
        textView = (TextView) findViewById(R.id.gongzuo_fanhui);
        textView2 = (TextView) findViewById(R.id.gongzuo_baocun);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gongzuo_fanhui:
                finish();
                break;
            case R.id.gongzuo_baocun:
                if (editText.getText().toString().equals("")||editText2.getText().toString().equals("")){
                    Toast.makeText(this, "输入为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent ii = getIntent();
                ii.putExtra("gongsi", editText.getText().toString());
                ii.putExtra("zhiwei", editText2.getText().toString());
                setResult(2, ii);
                finish();
                break;
            default:
                break;
        }
    }
}
