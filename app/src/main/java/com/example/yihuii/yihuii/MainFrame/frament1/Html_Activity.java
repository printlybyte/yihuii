package com.example.yihuii.yihuii.MainFrame.frament1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.MainActivity;
import com.example.yihuii.yihuii.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * The type Html activity.
 */
public class Html_Activity extends Activity implements View.OnClickListener {
    private WebView oauthWebview;
    private ProgressDialog dialog;
    private Button mBtn_html_back, mBtn_html_other;
    private TextView mText_html_title;
    private PopupWindow mpopupWindow;
    private PopupWindow pp;
    private  static  final String SHIPIN_QUFEN="1";
    private  static  final String YINPIN_QUFEN="2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dasdadas);
        initView();
    }

    /*
    * 初始化
    * */
    private void initView() {
        mBtn_html_back = (Button) findViewById(R.id.mBtn_html_back);
        mBtn_html_other = (Button) findViewById(R.id.mBtn_html_other);
        mText_html_title = (TextView) findViewById(R.id.mText_html_title);
        mBtn_html_back.setOnClickListener(this);
        mBtn_html_other.setOnClickListener(this);


        dialog = new ProgressDialog(this);
        dialog.setMessage("正在加载中");
        dialog.show();

        oauthWebview = (WebView) findViewById(R.id.html_webview);
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

    /*
    * onkeydown方法
    * */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (KeyEvent.KEYCODE_BACK == keyCode) {
//            if (mpopupWindow.isShowing()) {
//                mpopupWindow.dismiss();
//                return true;
//            } else {
//                if (oauthWebview.canGoBack()) {
//                    oauthWebview.goBack();
//                } else {
//                    Html_Activity.this.finish();
//                }
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtn_html_back:
                //点击返回   如果可以则返回上一页面，反之    则退出
                if (oauthWebview.canGoBack()) {
                    oauthWebview.goBack();
                } else {
                    Html_Activity.this.finish();
                }


                break;
            case R.id.mBtn_html_other:
                pupupWindow();

                break;
        }
    }

    private void pupupWindow() {
        final View view = getLayoutInflater().inflate(R.layout.popup_layout, null);
        //总布局控件，长，宽
        mpopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mpopupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.black));
        //得到背景后设置透明度;这句话一定要在上面这句话的后面，否则就会报错
        mpopupWindow.getBackground().setAlpha(200);
        // popupWindow.setTouchable(true);
        // popupWindow.setFocusable(true);
        mpopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        mpopupWindow.setOutsideTouchable(true);//点击空白处消失
        mpopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);//在某某parent布局的下面位置。有其他TOP。RIGHT其他属性
        Button mDel = (Button) view.findViewById(R.id.pop_wid_btn_delele);
        Button mQiandao = (Button) view.findViewById(R.id.pop_wid_btn_qiandao);
        Button mErweima = (Button) view.findViewById(R.id.pop_wid_btn_erweima);
        Button mFenxiang = (Button) view.findViewById(R.id.pop_wid_btn_fenxiang);
        Button mQuxiao = (Button) view.findViewById(R.id.pop_wid_btn_quxiao);
        Button mChangeq = (Button) view.findViewById(R.id.pop_wid_btn_changeq);
        Button mZhibo = (Button) view.findViewById(R.id.pop_wid_btn_zhibo);

        mErweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Html_Activity.this, QRcode_Activity.class);
                startActivity(intent);
                mpopupWindow.dismiss();
            }
        });
        mQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopupWindow.dismiss();
            }
        });
        mFenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
              mpopupWindow.dismiss();
            }
        });
        mChangeq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Html_Activity.this, "修改活动", Toast.LENGTH_SHORT).show();
            }
        });
        mQiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(Html_Activity.this,qindao_xiangqing.class);
                startActivity(intent);
                mpopupWindow.dismiss();
            }
        });
        mZhibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopupWindow.dismiss();
                mpup_zhibo();

            }
        });
        mDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cos_dialog();


            }
        });

    }

    private void mpup_zhibo() {

        final View view = getLayoutInflater().inflate(R.layout.pup_costom, null);
        //总布局控件，长，宽
        pp = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pp.setBackgroundDrawable(getResources().getDrawable(R.color.black));
        //得到背景后设置透明度;这句话一定要在上面这句话的后面，否则就会报错
        pp.getBackground().setAlpha(200);
        // popupWindow.setTouchable(true);
        // popupWindow.setFocusable(true);
        pp.setAnimationStyle(android.R.style.Animation_Dialog);
        pp.setOutsideTouchable(true);//点击空白处消失
        pp.showAtLocation(view, Gravity.BOTTOM, 0, 0);//在某某parent布局的下面位置。有其他TOP。RIGHT其他属性
        Button btn1 = (Button) view.findViewById(R.id.pup_cos_btn1);
        Button btn2 = (Button) view.findViewById(R.id.pup_cos_btn2);
        Button btn3 = (Button) view.findViewById(R.id.pup_cos_btn3);
        btn1.setText("视频直播");
        btn2.setText("音频直播");
        btn3.setText("取消");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Html_Activity.this, net.ossrs.yasea.zbzbzbzb.class);
                intent.putExtra("shipin",SHIPIN_QUFEN);
                startActivity(intent);
                pp.dismiss();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Html_Activity.this, net.ossrs.yasea.zbzbzbzb.class);
                intent.putExtra("shipin",YINPIN_QUFEN);
                startActivity(intent);
                pp.dismiss();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pp.dismiss();
            }
        });


    }


    private void Cos_dialog() {
        final AlertDialog.Builder aa = new AlertDialog.Builder(Html_Activity.this);
        LinearLayout ll = (LinearLayout) getLayoutInflater().inflate(R.layout.costom_dialog, null);
        aa.setView(ll);
        Button mBtn_qux = (Button) ll.findViewById(R.id.cos_dialog_btn1);
        Button mBtn_dele = (Button) ll.findViewById(R.id.cos_dialog_btn2);

        final Dialog dialog = aa.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        mBtn_qux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mpopupWindow.dismiss();
            }
        });
        mBtn_dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Html_Activity.this, "已经删除", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                mpopupWindow.dismiss();
            }
        });

    }

    private void showShare() {
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