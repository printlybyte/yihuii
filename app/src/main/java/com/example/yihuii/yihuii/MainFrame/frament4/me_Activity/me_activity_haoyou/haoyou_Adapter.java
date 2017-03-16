package com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_activity_haoyou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.Callback;
import com.example.yihuii.yihuii.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 */

public class haoyou_Adapter extends BaseAdapter {
    private Context context;
    private List<haouou_person> mList = new ArrayList<>();

    public haoyou_Adapter(Context context, List<haouou_person> mList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.haoyou_item, null);
            viewHodle = new ViewHodle();
            viewHodle.name= (TextView) convertView.findViewById(R.id.haoyou_name);
            viewHodle.zhiwei= (TextView) convertView.findViewById(R.id.haoyou_zhiwei);
            viewHodle.touxiang= (ImageView) convertView.findViewById(R.id.haoyou_touxiang);
            convertView.setTag(viewHodle);
        } else {
            viewHodle= (ViewHodle) convertView.getTag();
        }
        viewHodle.name.setText(mList.get(position).name);
        viewHodle.zhiwei.setText(mList.get(position).zhiwei);
        viewHodle.touxiang.setBackgroundResource(mList.get(position).touxiang2);
        return convertView;
    }

    class ViewHodle {
        TextView name;
        TextView zhiwei;
        ImageView touxiang;
    }
}
