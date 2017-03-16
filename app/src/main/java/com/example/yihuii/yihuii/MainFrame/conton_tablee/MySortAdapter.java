package com.example.yihuii.yihuii.MainFrame.conton_tablee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.yihuii.yihuii.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */

public class MySortAdapter extends BaseAdapter implements SectionIndexer {
    private List<UserModel> list = new ArrayList<>();
    private Context context ;
    private LayoutInflater inflater = null;

    public MySortAdapter(Context context, List<UserModel> list) {
        this.list = list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.sha,null);
            mHolder.textView_item_firstLetter = (TextView) convertView.findViewById(R.id.shatext);
            mHolder.textView_item_username = (TextView) convertView.findViewById(R.id.shatext2);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.textView_item_username.setText(list.get(position).getUsername());
        // 每个字母分组中的首个position所对应的textView_item_firstLetter控件显示或隐藏
        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            mHolder.textView_item_firstLetter.setVisibility(View.VISIBLE);
            mHolder.textView_item_firstLetter.setText(list.get(position).getFirstLetter());
        } else {
            mHolder.textView_item_firstLetter.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        private TextView textView_item_firstLetter;
        private TextView textView_item_username;
    }

    @Override
    public Object[] getSections() {
        // TODO Auto-generated method stub
        return null;
    }

    // 做字母索引的时候常常会用到SectionIndexer这个接口
    // 1. getSectionForPosition() 通过该item的position找该item在哪个字母分组
    // 2. getPositionForSection() 根据字母分组找这个组中第一个item的position
    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            String firstLetter = list.get(i).getFirstLetter();
            int firstChar = firstLetter.charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getFirstLetter().charAt(0);
    }

}