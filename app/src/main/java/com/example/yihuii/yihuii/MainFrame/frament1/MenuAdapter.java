package com.example.yihuii.yihuii.MainFrame.frament1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yihuii.yihuii.MainFrame.OnItemClickListener;
import com.example.yihuii.yihuii.R;
import com.lidroid.xutils.BitmapUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YOLANDA on 2016/7/22.
 */
public class MenuAdapter extends SwipeMenuAdapter<MenuAdapter.DefaultViewHolder> {
    public Context mcontext;
    private List<Costom_Person> mList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public MenuAdapter(List<Costom_Person> mList, Context context) {
        this.mcontext = context;
        this.mList = mList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recyview_item, parent, false);
    }

    @Override
    public MenuAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.DefaultViewHolder holder, int position) {
        holder.setData(mList.get(position).Title);
        holder.setData2(mList.get(position).image_url);
        holder.setDataa(mList.get(position).riqi_start + "至" + mList.get(position).riqi_end);
        holder.setDatab(mList.get(position).dizhi);
        holder.setDatac(mList.get(position).canyu);
        holder.setDatad(mList.get(position).zhuangtai);
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        TextView text_title;
        TextView text_a;
        TextView text_b;
        TextView text_c;
        ImageView image_icon;
        ImageView image_icon2;
        ImageView image_touxiang;
        ImageView image_a;
        ImageView image_b;
        ImageView image_c;
        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //   tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            text_title = (TextView) itemView.findViewById(R.id.Adapter_recy_title);
            text_a = (TextView) itemView.findViewById(R.id.Adapter_recy__text_a);
            text_b = (TextView) itemView.findViewById(R.id.Adapter_recy__text_b);
            text_c = (TextView) itemView.findViewById(R.id.Adapter_recy__text_c);
            image_icon = (ImageView) itemView.findViewById(R.id.Adapter_recy_icon);
            image_icon2 = (ImageView) itemView.findViewById(R.id.Adapter_recy_icon2);
            image_touxiang = (ImageView) itemView.findViewById(R.id.Adapter_recy_icon_touxiang);
        }


        public void setData(String title) {
            this.text_title.setText(title);

        }

        public void setData2(String title) {
            BitmapUtils utils = new BitmapUtils(itemView.getContext());
            utils.display(image_touxiang, title);


        }

        public void setDataa(String title) {
            this.text_a.setText(title);

        }

        public void setDatab(String title) {
            this.text_b.setText(title);

        }

        public void setDatac(String title) {
            this.text_c.setText(title);

        }

        public void setDatad(String titlew) {
            if (titlew.equals("已经开始")) {
                image_icon.setVisibility(View.VISIBLE);
                image_icon2.setVisibility(View.GONE);

            } else {
                image_icon.setVisibility(View.GONE);
                image_icon2.setVisibility(View.VISIBLE);
            }
        }


        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }


        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
