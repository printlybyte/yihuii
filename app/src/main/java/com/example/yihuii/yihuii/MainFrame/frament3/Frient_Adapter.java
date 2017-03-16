package com.example.yihuii.yihuii.MainFrame.frament3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.Callback;
import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.Costom_huodong_Person;
import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_huodong_Adapter;
import com.example.yihuii.yihuii.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class Frient_Adapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private boolean idDianzan = true;
    private List<Costom_Person_guangcahng> mList = new ArrayList<>();
    public Callback_faxian callback;

    public Frient_Adapter(Context context, List<Costom_Person_guangcahng> mList, Callback_faxian callback) {
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
        final ViewHodle viewHodle;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_item2, null);
            viewHodle = new ViewHodle();
            viewHodle.text_title = (TextView) convertView.findViewById(R.id.guangchang_biaoti);
            viewHodle.text_riqi = (TextView) convertView.findViewById(R.id.guangchang_riqi);
            viewHodle.text_laiyuan = (TextView) convertView.findViewById(R.id.guangchang_laiyuanyu);
            viewHodle.image_touxiang = (ImageView) convertView.findViewById(R.id.guangchang_touxiang);


            viewHodle.pinglun = (TextView) convertView.findViewById(R.id.guangchang_pinglun);
            viewHodle.dianzan = (TextView) convertView.findViewById(R.id.guangchang_dianzan);
            viewHodle.shanchu = (TextView) convertView.findViewById(R.id.guangchang_shanchu);
            viewHodle.fenxiang = (TextView) convertView.findViewById(R.id.guangchang_fenxiang);
            final View finalConvertView = convertView;
            viewHodle.dianzan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (idDianzan == true) {
                        Drawable drawable = finalConvertView.getResources().getDrawable(R.mipmap.dianzan_yes);
                        /// 这一步必须要做,否则不会显示.
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        viewHodle.dianzan.setCompoundDrawables(drawable, null, null, null);
                        idDianzan = false;
                    } else {
                        Toast.makeText(context, "SHA", Toast.LENGTH_SHORT).show();
                        Drawable drawable = finalConvertView.getResources().getDrawable(R.mipmap.guangchang_dianzan);
                        /// 这一步必须要做,否则不会显示.
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        viewHodle.dianzan.setCompoundDrawables(drawable, null, null, null);
                        idDianzan = true;
                    }
                }
            });

            convertView.setTag(viewHodle);
        } else {
            viewHodle = (ViewHodle) convertView.getTag();
        }
        BitmapUtils utils = new BitmapUtils(context);
        utils.display(viewHodle.image_touxiang, mList.get(position).image_url_guangcahng);
        viewHodle.text_title.setText(mList.get(position).Title_guangchang);
        viewHodle.text_riqi.setText(mList.get(position).riqi_guangchang);
        viewHodle.text_laiyuan.setText(mList.get(position).laiyuayu_guangchang);

        viewHodle.shanchu.setOnClickListener(this);
        // viewHodle.dianzan.setOnClickListener(this);
        viewHodle.pinglun.setOnClickListener(this);
        viewHodle.fenxiang.setOnClickListener(this);
        viewHodle.shanchu.setTag("2");
        viewHodle.pinglun.setTag("3");
        viewHodle.dianzan.setTag("4");
        viewHodle.fenxiang.setTag("a");
        return convertView;
    }


    @Override
    public void onClick(View v) {
        callback.onchick(v);
    }


    class ViewHodle {
        TextView text_title;
        TextView text_riqi;
        TextView text_laiyuan;
        TextView shanchu;
        TextView pinglun;
        TextView dianzan;
        TextView fenxiang;
        ImageView image_touxiang;
    }


}
