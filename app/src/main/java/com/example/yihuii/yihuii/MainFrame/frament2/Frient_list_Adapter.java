package com.example.yihuii.yihuii.MainFrame.frament2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yihuii.yihuii.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */

public class Frient_list_Adapter  extends BaseAdapter {
    private List<Person> mList=new ArrayList<>();
    private Context context;
    public Frient_list_Adapter( List<Person> mList,Context context){
        this.context=context;
        this.mList=mList;
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
        ViewHodle viewhodle=null;
        if (convertView==null){
            viewhodle=new ViewHodle();
            convertView= LayoutInflater.from(context).inflate(R.layout.frient_item1,null);
            viewhodle.Icon= (ImageView) convertView.findViewById(R.id.frient_item1_icon);
            viewhodle.Name= (TextView) convertView.findViewById(R.id.frient_item1_name);

            convertView.setTag(viewhodle);

        }else {viewhodle= (ViewHodle) convertView.getTag();

        }
        BitmapUtils bitmapUtils=new BitmapUtils(context);
        bitmapUtils.display( viewhodle.Icon,"http://litchiapi.jstv.com"+mList.get(position).getIcon_adress());
        viewhodle.Name.setText(mList.get(position).getName());
        return convertView;
    }class ViewHodle{
        ImageView Icon;
        TextView Name;
    }
}
