package com.example.yihuii.yihuii.MainFrame.frament1;

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
 * Created by Administrator on 2016/12/23.
 */

public class Adapater_list_two extends BaseAdapter {
    private Context context;
    private List<Costom_Person_two> mList = new ArrayList<>();

    public Adapater_list_two(List<Costom_Person_two> mList, Context context) {
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
     ViewHodle viewHodle=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_recyview_item_two, parent, false);





            viewHodle=new ViewHodle();
            viewHodle.text_title = (TextView) convertView.findViewById(R.id.Adapter_recy_title_two);
//            viewHodle.text_a = (TextView) convertView.findViewById(R.id.Adapter_recy__text_a);
//            viewHodle.text_b = (TextView) convertView.findViewById(R.id.Adapter_recy__text_b);
//            viewHodle.text_c = (TextView) convertView.findViewById(R.id.Adapter_recy__text_c);
//            viewHodle.image_icon = (ImageView) convertView.findViewById(R.id.Adapter_recy_icon);
//            viewHodle.image_touxiang = (ImageView) convertView.findViewById(R.id.Adapter_recy_icon_touxiang);



//            viewHodle.image_a = (ImageView) convertView.findViewById(R.id.Adapter_recy_icon_a);
//            viewHodle.image_b = (ImageView) convertView.findViewById(R.id.Adapter_recy_icon_b);
//            viewHodle.image_c = (ImageView) convertView.findViewById(R.id.Adapter_recy_icon_c);
             convertView.setTag(viewHodle);
        }else {
          viewHodle= (ViewHodle) convertView.getTag();
        }
        //BitmapUtils bitmapUtils=new BitmapUtils(context);
        //bitmapUtils.display( viewHodle.image_touxiang,"http://litchiapi.jstv.com"+mList.get(position).image_url);
        viewHodle.text_title.setText(mList.get(position).Title);
        return convertView;
    }

    class ViewHodle {

        TextView text_title;
        TextView text_a;
        TextView text_b;
        TextView text_c;
        ImageView image_icon;
        ImageView image_touxiang;
        ImageView image_a;
        ImageView image_b;
        ImageView image_c;
    }
}
