package com.example.yihuii.yihuii.MainFrame.frament4.me_Activity;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.frament4.ziactivity.CardActivity;
import com.example.yihuii.yihuii.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2017/2/13.
 */

public class me_huodong_Adapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<Costom_huodong_Person> mList = new ArrayList<>();
    private Callback callback;

    public me_huodong_Adapter(Context context, List<Costom_huodong_Person> mList, Callback callback) {
        this.context = context;
        this.mList = mList;
        this.callback = callback;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodle viewHodle;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.me_huodong_item, null);
            viewHodle = new ViewHodle();
            viewHodle.imageView = (ImageView) convertView.findViewById(R.id.me_huodong_touxaing);
            viewHodle.textView = (TextView) convertView.findViewById(R.id.me_huodong_biaoti);
             viewHodle.mT_fenxiang = (TextView) convertView.findViewById(R.id.me_huodong_zhuanfa);
             viewHodle.mT_pinglun = (TextView) convertView.findViewById(R.id.me_huodong_pinglun);
             viewHodle.mT_erweima = (TextView) convertView.findViewById(R.id.me_huodong_erweima);
             viewHodle.mT_haoyou = (TextView) convertView.findViewById(R.id.me_huodong_huiyou);
            convertView.setTag(viewHodle);
        } else {
            viewHodle = (ViewHodle) convertView.getTag();
        }
        BitmapUtils bitmapUtils = new BitmapUtils(context);
        bitmapUtils.display(viewHodle.imageView, "http://litchiapi.jstv.com" + mList.get(position).image_url);
        viewHodle.textView.setText(mList.get(position).title_huodong);
        viewHodle.mT_fenxiang.setOnClickListener(this);
        viewHodle.mT_pinglun.setOnClickListener(this);
        viewHodle.mT_erweima.setOnClickListener(this);
        viewHodle.mT_haoyou.setOnClickListener(this);
        viewHodle.mT_pinglun.setTag("4");
        viewHodle.mT_fenxiang.setTag("2");
        viewHodle.mT_erweima.setTag("1");
        viewHodle.mT_haoyou.setTag("3");
        return convertView;
    }



    @Override
    public void onClick(View v) {
        callback.onchick(v);
    }
//此处是解决不响应某条点击事件
//    public boolean areAllItemsEnabled() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled(int position) {
//        if (position != 2) {
//            return true;
//        }
//        return false;
//    }

    class ViewHodle {
        ImageView imageView;
        TextView textView;
        TextView mT_fenxiang;
        TextView mT_pinglun;
        TextView mT_erweima;
        TextView mT_haoyou;
    }
}
