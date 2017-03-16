package com.example.yihuii.yihuii.MainFrame.frament2.huiyou_xiangqing;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_activity_haoyou.haoyou_xiangqing;
import com.example.yihuii.yihuii.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class huiyou_item_xiangqing extends AppCompatActivity {
    private WebView oauthWebview;
    private ProgressDialog dialog;
    private TextView textView, textView2;
    private Button button; private PopupWindow mpopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huiyou_item_xiangqing);
        initView();
    }
    private void initView() {
        textView = (TextView) findViewById(R.id.incloud_other);
        textView.setText("操作");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pupupwindou();
            }
        });
        textView2 = (TextView) findViewById(R.id.incolud_text);
        textView2.setText("名片详情");
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

        oauthWebview = (WebView) findViewById(R.id.web_huiyou_item_xiangqing);
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

    } private void pupupwindou() {
        final View view = getLayoutInflater().inflate(R.layout.popup_layout_haoyou_caozuo, null);
        //总布局控件，长，宽
        mpopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mpopupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.black));
        //得到背景后设置透明度;这句话一定要在上面这句话的后面，否则就会报错
        mpopupWindow.getBackground().setAlpha(100);
        // popupWindow.setTouchable(true);
        // popupWindow.setFocusable(true);
        mpopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        mpopupWindow.setOutsideTouchable(true);//点击空白处消失
        mpopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);//在某某parent布局的下面位置。有其他TOP。RIGHT其他属性
        Button button = (Button) view.findViewById(R.id.pup_jiaohuan);
        Button button2 = (Button) view.findViewById(R.id.pup_tinajia);
        Button button3 = (Button) view.findViewById(R.id.pup_fenxang);
        Button button4 = (Button) view.findViewById(R.id.pup_quxiao);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(huiyou_item_xiangqing.this, "交换", Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(huiyou_item_xiangqing.this, "添加到通讯录", Toast.LENGTH_SHORT).show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopupWindow.dismiss();
            }
        });
    }
    public void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}

