package com.example.yihuii.yihuii.MainFrame.frament1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;

import butterknife.ButterKnife;

/**
 * The type Qindao xiangqing.
 */
public class qindao_xiangqing extends AppCompatActivity implements View.OnClickListener {

    private TextView textView, textView2;
    private Button button;
    /**
     * 暂无设备信息
     */
    private Button mLanyaShebeixinxi;
    /**
     * 扫描二维码
     */
    private Button mLayaSaomiaoErweima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qindao_xiangqing);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        textView = (TextView) findViewById(R.id.incloud_other);
        textView.setText("");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        textView2 = (TextView) findViewById(R.id.incolud_text);
        textView2.setText("签到管理");
        button = (Button) findViewById(R.id.mBtn_incloud_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLanyaShebeixinxi = (Button) findViewById(R.id.lanya_shebeixinxi);
        mLanyaShebeixinxi.setOnClickListener(this);
        mLayaSaomiaoErweima = (Button) findViewById(R.id.laya_saomiao_erweima);
        mLayaSaomiaoErweima.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lanya_shebeixinxi:
                Toast.makeText(this, "暂无设备信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.laya_saomiao_erweima:
                Intent intent=new Intent(qindao_xiangqing.this,QRcode_Activity.class);
                startActivity(intent);
                break;
        }
    }
}
