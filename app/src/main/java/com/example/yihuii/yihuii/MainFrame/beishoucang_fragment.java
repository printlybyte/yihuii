package com.example.yihuii.yihuii.MainFrame;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.yihuii.yihuii.MainFrame.frament2.networking;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.frament2.ContactAdapter;
import com.example.yihuii.yihuii.MainFrame.frament2.HanziToPinyin;
import com.example.yihuii.yihuii.MainFrame.frament2.bean.Contact;
import com.example.yihuii.yihuii.MainFrame.frament2.widget.SideBar;
import com.example.yihuii.yihuii.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;


public class beishoucang_fragment extends Fragment implements SideBar
        .OnTouchingLetterChangedListener, TextWatcher {
    private View view;

    private ListView mListView;
    private Dialog dialog;
    private TextView mFooterView;
    private KJHttp kjh = null;
    private TextView textView;
    private ArrayList<Contact> datas = new ArrayList<>();
    private ContactAdapter mAdapter;
    private LinearLayout linear;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_beishoucang_fragment, container, false);
        mListView = (ListView) view.findViewById(R.id.school_friend_member_);
        mFooterView = (TextView) inflater.inflate(R.layout.item_list_contact_count, null);
        mListView.addFooterView(mFooterView);
        textView = (TextView) view.findViewById(R.id.net_zhengchang);
        linear = (LinearLayout) view.findViewById(R.id.cctv_2);

        initView();
        return view;
    }

    //监听网络状态
    private void networing_state() {
        if (networking.isNetworkAvailable(getActivity()) == true) {
            textView.setVisibility(View.GONE);//可用隐藏
        } else {
            textView.setVisibility(View.VISIBLE);//不可用展示
            linear.setVisibility(View.GONE);
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
        SideBar mSideBar = (SideBar) view.findViewById(R.id.school_friend_sidrbar_);
        TextView mDialog = (TextView) view.findViewById(R.id.school_friend_dialog_);
        EditText mSearchInput = (EditText) view.findViewById(R.id.school_friend_member_search_input_);

        mSideBar.setTextView(mDialog);
        mSideBar.setOnTouchingLetterChangedListener(this);
        mSearchInput.addTextChangedListener(this);
        // 给listView设置adapter
        doHttp();


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (networking.isNetworkAvailable(getActivity()) == true) {
                    textView.setVisibility(View.GONE);//可用隐藏
                    linear.setVisibility(View.VISIBLE);
                    dialog = new Dialog(getActivity(), R.style.dialog);
                    dialog.setTitle("正在加载中");
                    dialog.setCancelable(false);
                    dialog.show();
                    datas.clear();
                    doHttp();
                } else {
                    textView.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "请检查网络后再试", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void doHttp() {

        kjh.get("http://litchiapi.jstv.com/api/GetFeeds?column=0&PageSize=20&pageIndex=12", new HttpCallBack() {
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
            KJLoger.debug("解析异常" + e.getMessage());
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
    }
}