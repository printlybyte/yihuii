package com.example.yihuii.yihuii.MainFrame.frament2;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.conton_tablee.ChineseToPinyinHelper;
import com.example.yihuii.yihuii.MainFrame.conton_tablee.LetterIndexerView;
import com.example.yihuii.yihuii.MainFrame.conton_tablee.MySortAdapter;
import com.example.yihuii.yihuii.MainFrame.conton_tablee.UserModel;
import com.example.yihuii.yihuii.MainFrame.frament2.bean.Contact;
import com.example.yihuii.yihuii.MainFrame.frament2.costom_refrech.RefreshableView;
import com.example.yihuii.yihuii.MainFrame.frament2.widget.SideBar;
import com.example.yihuii.yihuii.R;
import com.example.yihuii.yihuii.logoo.Costom_dialog;
import com.example.yihuii.yihuii.logoo.LogoinActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


/**
 * The type Frient fragment.
 */
public class Frient_Fragment extends Fragment implements SideBar
        .OnTouchingLetterChangedListener, TextWatcher {
    private String PATH_URL = "http://litchiapi.jstv.com/api/GetFeeds?column=0&PageSize=20&pageIndex=11";
    private View view;
    RefreshableView refreshableView;
    private ListView mListView;
    private TextView bb;
    private TextView mFooterView;
    private KJHttp kjh = null;
    private ArrayList<Contact> datas = new ArrayList<>();
    private ContactAdapter mAdapter;
    private LinearLayout mNet_state, cvcv;
    private Dialog dialog = null;

    Handler hh = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                if (networking.isNetworkAvailable(getActivity()) == false) {
                    Toast.makeText(getActivity(), "请检查您的网络连接是否正常", Toast.LENGTH_SHORT).show();
                } else {
                    datas.clear();
                    doHttp();
                }
            }
        }
    };


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_frient_, container, false);
        cvcv = (LinearLayout) view.findViewById(R.id.attention_layout);
        mListView = (ListView) view.findViewById(R.id.school_friend_member);
        refreshableView = (RefreshableView) view.findViewById(R.id.refreshable_view);//自定义listview refresh
        mFooterView = (TextView) inflater.inflate(R.layout.item_list_contact_count, null);
        mNet_state = (LinearLayout) view.findViewById(R.id.networking_linear_huiyou);
        bb = (TextView) view.findViewById(R.id.frient_onchick);
        mListView.addFooterView(mFooterView);
        initView();
        return view;
    }

    //监听网络状态
    private void networing_state() {
        if (networking.isNetworkAvailable(getActivity()) == true) {
            mNet_state.setVisibility(View.GONE);//可用隐藏
        } else {
            mNet_state.setVisibility(View.VISIBLE);//不可用展示
            cvcv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在网络状态不可用的情况下  进行舒心ada加载缓存数据
        if (networking.isNetworkAvailable(getActivity()) == false) {
            // initView();
        }
    }

    private void initView() {
        networing_state();
        HttpConfig config = new HttpConfig();
        HttpConfig.sCookie = "oscid=V" +
                "%2BbmxZFR8UfmpvrBHAcVRKALrd72iPWknXaWDa5Is3veK7WsxTSWY80kRXB1LoH%2F4VJ" +
                "%2F9s7K3Kd9CwCC1CAV%2BnJ7T3ka0blF8vZojozhUdOYkq6D6Laldg%3D%3D; Domain=.oschina" +
                ".net; Path=/; ";
        config.cacheTime = 0;
        kjh = new KJHttp();
        SideBar mSideBar = (SideBar) view.findViewById(R.id.school_friend_sidrbar);
        TextView mDialog = (TextView) view.findViewById(R.id.school_friend_dialog);
        EditText mSearchInput = (EditText) view.findViewById(R.id.school_friend_member_search_input);

        mSideBar.setTextView(mDialog);
        mSideBar.setOnTouchingLetterChangedListener(this);
        mSearchInput.addTextChangedListener(this);
        // 给listView设置adapter
        doHttp();

        //给listview设置监听

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), com.example.yihuii.yihuii.MainFrame.frament2.huiyou_xiangqing.huiyou_item_xiangqing.class);
                startActivity(intent);
            }
        });

        //下拉刷新完成后发送handle message
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
                Message mm = hh.obtainMessage();
                mm.arg1 = 1;
                hh.sendMessage(mm);
            }
        }, 0);


        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (networking.isNetworkAvailable(getActivity()) == true) {
                    mNet_state.setVisibility(View.GONE);//可用隐藏
                    cvcv.setVisibility(View.VISIBLE);
                    dialog = new Dialog(getActivity(), R.style.dialog);
                    dialog.setTitle("正在加载中");
                    dialog.setCancelable(false);
                    dialog.show();
                    datas.clear();
                    doHttp();
                } else {
                    mNet_state.setVisibility(View.VISIBLE);//不可用展示
                    Toast.makeText(getActivity(), "请检查网络后再试", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void doHttp() {
        kjh.get("http://litchiapi.jstv.com/api/GetFeeds?column=0&PageSize=20&pageIndex=11", new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                parser(t);
            }
        });
    }


    private void parser(String json) {
        try {

            JSONObject obj = new JSONObject(json);
            JSONObject obj2 = obj.getJSONObject("paramz");
            JSONArray array = obj2.getJSONArray("feeds");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj3 = array.getJSONObject(i);
                JSONObject obj4 = obj3.getJSONObject("data");
                Contact person = new Contact();
                person.setName(obj4.getString("subject"));
                person.setUrl(obj4.getString("cover"));
                person.setPinyin(HanziToPinyin.getPinYin(person.getName()));
                datas.add(person);

            }

            mFooterView.setText(datas.size() + "位联系人");
            mAdapter = new ContactAdapter(mListView, datas);
            mListView.setAdapter(mAdapter);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();//如果是展示状态就取消
            }
        } catch (JSONException e) {
            KJLoger.debug("网络异常" + e.getMessage());
        }
    }

    @Override
    public void onTouchingLetterChanged(String s) {
        int position = 0;
        // 该字母首次出现的位置
        if (mAdapter != null) {
            position = mAdapter.getPositionForSection(s.charAt(0));
        }
        if (position != -1) {
            mListView.setSelection(position);
        } else if (s.contains("#")) {
            mListView.setSelection(0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        ArrayList<Contact> temp = new ArrayList<>(datas);
        for (Contact data : datas) {
            if (data.getName().contains(s) || data.getPinyin().contains(s)) {
            } else {
                temp.remove(data);
            }
        }
        if (mAdapter != null) {
            mAdapter.refresh(temp);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        Toast.makeText(getActivity(), "ZHSSA", Toast.LENGTH_SHORT).show();
    }

}

