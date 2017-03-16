package com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_activity_haoyou;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.Callback;
import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.Costom_huodong_Person;
import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_huodong_Adapter;
import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_me_huodong;
import com.example.yihuii.yihuii.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class haoyou_activity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private ListView actualListView;
    private PullToRefreshListView mPullRefreshListView;
    private List<haouou_person> mList = new ArrayList<>();
    private haouou_person pp;
    private haoyou_Adapter mAdapter;
    private AutoCompleteTextView mAtText;
    private List<String> mList2=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haoyou_activity);
        initView();
    }

    private void initView() {
        mAtText = (AutoCompleteTextView) findViewById(R.id.autotext_frient_haoyou);
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list_haoyou);
        actualListView = mPullRefreshListView.getRefreshableView();
        textView = (TextView) findViewById(R.id.incolud_text);
        button = (Button) findViewById(R.id.mBtn_incloud_back);
        textView.setText("成员列表");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setmPullRefreshListView();
        mList2.add("1");
        initView_AutoText();

        actualListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(haoyou_activity.this,com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_activity_haoyou.haoyou_xiangqing.class);
                startActivity(intent);
            }
        });
    }
        private  void initView_AutoText(){
            mAtText.setDropDownWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            mAtText.setDropDownHeight(200);

            mAtText.setDropDownBackgroundResource(R.color.white);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mAtText.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mAtText.setText("");
                        Toast.makeText(haoyou_activity.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            ArrayAdapter<String> XX = new ArrayAdapter<String>(haoyou_activity.this, android.R.layout.simple_dropdown_item_1line, mList2);
            mAtText.setAdapter(XX);
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
                        pp.name = "刷新出来的名字";
                        pp.zhiwei = "懂事";
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

        pp = new haouou_person();
        pp.name = "王如意";
        pp.zhiwei = "经理";
        pp.touxiang2 = R.mipmap.ic_launcher;
        mList.add(pp);
        mAdapter = new haoyou_Adapter(haoyou_activity.this, mList);
        actualListView.setAdapter(mAdapter);
    }



}
