package com.example.yihuii.yihuii.MainFrame.frament1.shezhi_pak;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.yihuii.yihuii.R;

public class guanyu_xiangqing extends AppCompatActivity {


    private WebView oauthWebview;
    private ProgressDialog dialog;
    private TextView textView, textView2;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanyu_xiangqing);
        initView();
    } private void initView() {


    textView = (TextView) findViewById(R.id.incloud_other);
    textView.setText("");
    textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    textView2 = (TextView) findViewById(R.id.incolud_text);
    textView2.setText("意见反馈");
    button = (Button) findViewById(R.id.mBtn_incloud_back);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

    dialog = new ProgressDialog(this);
    dialog.setMessage("正在加载中");
    dialog.show();

    oauthWebview = (WebView) findViewById(R.id.web_guanyu);
    oauthWebview.setWebViewClient(new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dialog.dismiss();
        }//加载完毕取消dialog
    });

    lodingUrl("http://e.laijiaoliu.com/custom/card/circle?id=3196&ljlfrom=66395665&from=groupmessage");
    //  lodingUrl("http://www.ossrs.net/players/srs_player.html?vhost=hls&port=19351&stream=sea&server=ossrs.net&autostart=true");

}

    /**
     * 加载网址的I定义方法
     */
    private void lodingUrl(String ss) {
        if (ss != null) {
            oauthWebview.loadUrl(ss);
        }

    }
}