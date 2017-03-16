package com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_wenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MyOwnQuestionActivity extends AppCompatActivity {
    private TextView textView, textView2;
    private Button button;
    private PullToRefreshListView mPullRefreshListView;
    private ListView actualListView;
    private wenda_person pp;
    private wenda_Adapter mAdapter;
    private List<wenda_person> mList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_own_question);
        initView();
    }

    private void initView() {
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list_wenda);
        actualListView = mPullRefreshListView.getRefreshableView();
        textView = (TextView) findViewById(R.id.incloud_other);
        textView.setText("");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        textView2 = (TextView) findViewById(R.id.incolud_text);
        textView2.setText("我的问答");
        button = (Button) findViewById(R.id.mBtn_incloud_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
           actualListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Intent ii=new Intent(MyOwnQuestionActivity.this,wenda_xiangqing.class);
                   startActivity(ii);
               }
           });
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
                        pp.naem = "刷新出来的数据";
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

        pp = new wenda_person();
        pp.naem = "暂无问答";
        mList.add(pp);
        mAdapter = new wenda_Adapter(MyOwnQuestionActivity.this, mList);
        actualListView.setAdapter(mAdapter);
    }


}
