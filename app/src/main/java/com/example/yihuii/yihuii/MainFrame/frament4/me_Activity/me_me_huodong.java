package com.example.yihuii.yihuii.MainFrame.frament4.me_Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.frament1.Costom_Person;
import com.example.yihuii.yihuii.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class me_me_huodong extends AppCompatActivity implements View.OnClickListener, Callback {
    private Button mBtn_back;
    private TextView mT_zhuanfa;
    private PullToRefreshListView mPullRefreshListView;
    private ListView actualListView;
    private List<Costom_huodong_Person> mList = new ArrayList<>();
    private Costom_huodong_Person pp;
    private me_huodong_Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_me_huodong);

        // mT_zhuanfa = (TextView)findViewById(R.id.me_huodong_zhuanfa);
        initView();

    }

    private void initView() {
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list_two);
        actualListView = mPullRefreshListView.getRefreshableView();
        mBtn_back = (Button) findViewById(R.id.mBtn_incloud_back);
        mBtn_back.setOnClickListener(this);
        //  mT_zhuanfa = (TextView) findViewById(R.id.me_huodong_zhuanfa);
        // mT_zhuanfa.setOnClickListener(this);
        actualListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(me_me_huodong.this,com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_huodong_xiangqing.class);
                startActivity(intent);
            }
        });


        setmPullRefreshListView();//调用刷新的发法
    }


    private void setmPullRefreshListView() {

        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                mPullRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRefreshListView.onRefreshComplete();
                        pp.title_huodong = "刷新出来的数据";
                        mList.add(mList.size(), pp);
                        mAdapter.notifyDataSetChanged();

                    }
                }, 2000);

            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(getApplicationContext(), "上拉刷新", Toast.LENGTH_SHORT).show();
                mPullRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRefreshListView.onRefreshComplete();
                    }
                }, 2000);

            }
        });
        // 列表到底，即看到最后一个元素。
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                Toast.makeText(getApplication(), "已经到底！", Toast.LENGTH_SHORT).show();
            }
        });

        pp = new Costom_huodong_Person();
        pp.title_huodong = "北京白马寺";
        mList.add(pp);
        mAdapter = new me_huodong_Adapter(me_me_huodong.this, mList, this);
        actualListView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtn_incloud_back:
                finish();
                break;
//            case R.id.me_huodong_zhuanfa:
//                Toast.makeText(this, "AAAAAA", Toast.LENGTH_SHORT).show();
//                showShare();
//                break;
            default:
                break;
        }
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


    @Override
    public void onchick(View view) {
        if (view.getTag().toString().equals("1")) {
        Intent  ii=new Intent(this,com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_activity_erweima.erweima_xiangqing.class);
            startActivity(ii);
        } else if (view.getTag().toString().equals("2")) {
            showShare();
        } else if (view.getTag().toString().equals("3")) {
            Intent ii = new Intent(this, com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_activity_haoyou.haoyou_activity.class);
            startActivity(ii);
            Toast.makeText(this, "好友被点击了", Toast.LENGTH_SHORT).show();
        } else if (view.getTag().toString().equals("4")) {
            Intent iiw = new Intent(this,   com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_activity_pinglun.pinglun_xiangqing.class);
            startActivity(iiw);

            Toast.makeText(this, "评论被点击了", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "其他的被点击了", Toast.LENGTH_SHORT).show();
        }
    }
}
