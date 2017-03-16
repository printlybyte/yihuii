package com.example.yihuii.yihuii.MainFrame.frament4.ziactivity.xuqiu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yihuii.yihuii.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/6.
 */

public class Need_Adapter extends BaseAdapter {
    private Context context;
    private List<Xuqiu_person> mList = new ArrayList<>();

    public Need_Adapter(List<Xuqiu_person> mList, Context context) {
        this.context = context;
        this.mList = mList;
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
        ViewHodle viewHodle = null;
        if (convertView == null) {
          convertView= LayoutInflater.from(context).inflate(R.layout.need_item,null);
            viewHodle=new ViewHodle();
            viewHodle.touxiang= (ImageView) convertView.findViewById(R.id.xuqiu_touxiang);
            viewHodle.textView= (TextView) convertView.findViewById(R.id.xuqiu_title);
            convertView.setTag(viewHodle);
        } else {
            viewHodle= (ViewHodle) convertView.getTag();
        }
       viewHodle.textView.setText(mList.get(position).titlee);
        return convertView;
    }

    class ViewHodle {
        ImageView touxiang;
        TextView textView;
    }
}
