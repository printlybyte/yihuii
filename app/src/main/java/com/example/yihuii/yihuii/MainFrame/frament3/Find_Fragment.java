package com.example.yihuii.yihuii.MainFrame.frament3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.OnItemClickListener;
import com.example.yihuii.yihuii.MainFrame.frament1.ListViewDecoration;
import com.example.yihuii.yihuii.MainFrame.frament2.networking;
import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.Costom_huodong_Person;
import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_huodong_Adapter;
import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_me_huodong;
import com.example.yihuii.yihuii.R;
import com.example.yihuii.yihuii.logoo.Costom_dialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class Find_Fragment extends Fragment implements Callback_faxian {
    private List<Costom_Person_guangcahng> mList = new ArrayList<>();
    private View view;
    private String PATH_URL = "http://123.56.237.2:8080/open.php?c=square&m=getlist";
    private Context context;
    private PullToRefreshListView mPullRefreshListView;
    private ListView actualListView;
    private Frient_Adapter mAdapter;
    private LinearLayout mNet_state;
    private Dialog dialog = null;
    private Button tt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find_, container, false);
        initView();//点击监听
        return view;
    }

    //监听网络状态
    private void networing_state() {
        if (networking.isNetworkAvailable(getActivity()) == true) {
            mNet_state.setVisibility(View.GONE);//可用隐藏
        } else {
            mNet_state.setVisibility(View.VISIBLE);//不可用展示

        }
    }

    private void initView() {
        tt= (Button) view.findViewById(R.id.faxian_onchick);
        mNet_state = (LinearLayout) view.findViewById(R.id.net_working_faxian);
        mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list_two);
        actualListView = mPullRefreshListView.getRefreshableView();
        context = getActivity();
        postData_activityy();//请求网络
        setmPullRefreshListView();///上下拉
        networing_state();//网络状态
        actualListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), com.example.yihuii.yihuii.MainFrame.frament3.other.faxian_huodong_xaingqing.class);
                startActivity(intent);
            }
        });

        //未加载点击加载
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (networking.isNetworkAvailable(getActivity()) == true) {
                    mNet_state.setVisibility(View.GONE);//可用隐藏
                    dialog = new Dialog(getActivity(), R.style.dialog);
                    dialog.setTitle("正在加载中");
                    dialog.setCancelable(false);
                    dialog.show();
                    postData_activityy();
                } else {
                    mNet_state.setVisibility(View.VISIBLE);//不可用展示
                    Toast.makeText(context, "请检查网络后再试", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                        if (networking.isNetworkAvailable(getActivity()) == false) {
                            Toast.makeText(context, "请检查您的网络连接", Toast.LENGTH_SHORT).show();
                        } else {
                            networing_state();
                            mList.clear();
                            postData_activityy();
                            Toast.makeText(context, "数据刷新成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 2000);

            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                mPullRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRefreshListView.onRefreshComplete();
                        if (networking.isNetworkAvailable(getActivity()) == false) {
                            Toast.makeText(context, "请检查您的网络连接", Toast.LENGTH_SHORT).show();
                        } else {
                            networing_state();//判断网络状态
                            postData_activityy();//请求数据，换成指定页面id
                            Toast.makeText(context, "数据刷新成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 2000);

            }
        });
        // 列表到底，即看到最后一个元素。
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                Toast.makeText(getActivity(), "已经到底！", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void showShare() {
        ShareSDK.initSDK(getActivity());
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
        oks.show(getActivity());

    }

    public void Aleart_costom(String title, String message) {
        AlertDialog.Builder bb = new AlertDialog.Builder(getContext());
        bb.create();
        bb.setTitle(title);
        bb.setMessage(message);
        bb.setPositiveButton("取消", null);
        bb.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        bb.show();
    }


    /*
    * 从网络请求数据赋值到活动列表
    * */
    public void postData_activityy() {

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, PATH_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result.toString();
                try {
                    JSONObject obj = new JSONObject(result);
                    JSONArray array = obj.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj2 = array.getJSONObject(i);
                        Costom_Person_guangcahng person = new Costom_Person_guangcahng();
                        person.Title_guangchang = obj2.getString("item_title");
                        person.image_url_guangcahng = obj2.getString("poster_img");
                        person.riqi_guangchang = obj2.getString("item_time");
                        person.laiyuayu_guangchang = obj2.getString("source_from");
                        mList.add(person);

                    }

                    mAdapter = new Frient_Adapter(getContext(), mList, Find_Fragment.this);
                    actualListView.setAdapter(mAdapter);
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onchick(View view) {
        String biaoshi = view.getTag().toString();
        if (biaoshi != null) {

            if (biaoshi.equals("2")) {
                Aleart_costom("删除提示", "你确认删除此条活动吗？");
            } else if (biaoshi.equals("3")) {
                Intent intent = new Intent(getActivity(), com.example.yihuii.yihuii.MainFrame.frament3.other.pinglun_xiangqing.class);
                startActivity(intent);
            } else if (biaoshi.equals("4")) {

                Toast.makeText(getActivity(), "点赞被点击了", Toast.LENGTH_SHORT).show();
            } else if (biaoshi.equals("a")) {
                showShare();
            } else {
                Intent intent = new Intent(getActivity(), com.example.yihuii.yihuii.MainFrame.frament3.other.faxian_huodong_xaingqing.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(getContext(), "标示为null", Toast.LENGTH_SHORT).show();
        }
    }
}
