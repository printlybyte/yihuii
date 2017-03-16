package com.example.yihuii.yihuii.MainFrame.frament4.ziactivity.xuqiu;

import android.content.Intent;
import android.os.Bundle;

import com.example.yihuii.yihuii.MainFrame.frament2.networking;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.kymjs.kjframe.http.Network;

import java.util.ArrayList;
import java.util.List;

public class MyOwnNeedActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Xuqiu_person> mList = new ArrayList<>();
    private TextView mTx_guanbi, mTx_chuangjian;
    private Need_Adapter adapter;
    private PullToRefreshListView mPullRefreshListView;
    private ListView actualListView;
    private Xuqiu_person pp;
    private Need_Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_own_need);
        Xuqiu_person pp = new Xuqiu_person();
        pp.titlee = "北京公司";
        mList.add(pp);
        initView();
    }

    private void initView() {
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list_xuqiu);
        actualListView = mPullRefreshListView.getRefreshableView();
        mTx_guanbi = (TextView) findViewById(R.id.xuqiu_guanbi);
        mTx_chuangjian = (TextView) findViewById(R.id.xuqiu_chaungjian);
        mTx_guanbi.setOnClickListener(this);
        mTx_chuangjian.setOnClickListener(this);
        adapter = new Need_Adapter(mList, MyOwnNeedActivity.this);
        setmPullRefreshListView();

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
                        if (networking.isNetworkAvailable(MyOwnNeedActivity.this) == false) {
                            Toast.makeText(MyOwnNeedActivity.this, "请检查您的网络是否正常", Toast.LENGTH_SHORT).show();
                        } else {
                            mList.clear();
                            Toast.makeText(MyOwnNeedActivity.this, "数据刷新成功", Toast.LENGTH_SHORT).show();
                        }

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

        pp = new Xuqiu_person();
        pp.titlee = "暂无问答";
        mList.add(pp);
        mAdapter = new Need_Adapter(mList, MyOwnNeedActivity.this);
        actualListView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xuqiu_guanbi:
                finish();
                break;
            case R.id.xuqiu_chaungjian:
                Intent intent = new Intent(MyOwnNeedActivity.this, Need_chuangjian.class);
                startActivityForResult(intent, 1);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //创建返回
        if (requestCode == 1) {
            String title_content = data.getStringExtra("content");
            if ("".equals(title_content)) {
                Toast.makeText(this, "title_content空", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Xuqiu_person pp = new Xuqiu_person();
                pp.titlee = title_content;
                mList.add(0, pp);
                adapter.notifyDataSetChanged();
            }

        }
    }
}
