package com.example.yihuii.yihuii.MainFrame.frament1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.OnItemClickListener;
import com.example.yihuii.yihuii.MainFrame.frament2.Frient_list_Adapter;
import com.example.yihuii.yihuii.MainFrame.frament2.Person;
import com.example.yihuii.yihuii.MainFrame.frament2.networking;
import com.example.yihuii.yihuii.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_Fragment extends Fragment {
    private List<Costom_Person> mList = new ArrayList<>();
    private View view;
    private String PATH_URL = "http://123.56.237.2:8080/open.php?c=activity&m=getlist";

    private Activity mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MenuAdapter mMenuAdapter;
    private SwipeMenuRecyclerView mSwipeMenuRecyclerView;
    private Dialog dialog = null;
    private Button button;
    private RelativeLayout mNet_relative, cctv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activity_, null);
        mNet_relative = (RelativeLayout) view.findViewById(R.id.net_working_huodong);
        button = (Button) view.findViewById(R.id.activity_onchick);
        mContext = getActivity();
        cctv = (RelativeLayout) view.findViewById(R.id.cctv_3);
        initView();


        return view;
    }

    private void initView() {
        networing_state();//监听网络状态
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        mSwipeMenuRecyclerView = (SwipeMenuRecyclerView) view.findViewById(R.id.recycler_view);
        mSwipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));// 布局管理器。
        mSwipeMenuRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mSwipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        mSwipeMenuRecyclerView.addItemDecoration(new ListViewDecoration(getContext()));// 添加分割线。
        // 添加滚动监听。
        mSwipeMenuRecyclerView.addOnScrollListener(mOnScrollListener);
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        mSwipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        mSwipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
        mMenuAdapter = new MenuAdapter(mList, getContext());
        mMenuAdapter.setOnItemClickListener(onItemClickListener);
        postData_activityy();//访问网络解析并且setAdapter

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (networking.isNetworkAvailable(getActivity()) == true) {
                    button.setVisibility(View.GONE);//可用隐藏
                    cctv.setVisibility(View.VISIBLE);
                    dialog = new Dialog(getActivity(), R.style.dialog);
                    dialog.setTitle("正在加载中");
                    dialog.setCancelable(false);
                    dialog.show();
                    mList.clear();
                   postData_activityy();
                } else {
                    button.setVisibility(View.VISIBLE);//不可用展示
                    Toast.makeText(getActivity(), "请检查网络后再试", Toast.LENGTH_SHORT).show();
            }}
        });

    }

    //监听网络状态
    private void networing_state() {
        if (networking.isNetworkAvailable(getActivity()) == true) {
            mNet_relative.setVisibility(View.GONE);//可用隐藏

        } else {
            mNet_relative.setVisibility(View.VISIBLE);//不可用展示
            cctv.setVisibility(View.GONE);
        }
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mSwipeMenuRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                    networing_state();
                    if (mList == null) {


                    } else {
                        mList.clear();
                        postData_activityy();
                        mMenuAdapter.notifyDataSetChanged();
                        Toast.makeText(mContext, "数据更新完毕", Toast.LENGTH_SHORT).show();
                    }


                    //  Toast.makeText(mContext, "没有更多数据 了", Toast.LENGTH_SHORT).show();
                }
            }, 2000);
        }
    };

    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                // TODO 这里有个注意的地方，如果你刚进来时没有数据，但是设置了适配器，这个时候就会触发加载更多，需要开发者判断下是否有数据，如果有数据才去加载更多。

                //Toast.makeText(getActivity(), "滑到最底部了，去加载更多吧！", Toast.LENGTH_SHORT).show();
                if (mList == null) {
                    initView();
                    Toast.makeText(mContext, "MLIST+NULL", Toast.LENGTH_SHORT).show();
                } else {
                    mMenuAdapter.notifyDataSetChanged();
                    Toast.makeText(mContext, "MLIST+notifyDataSetChanged", Toast.LENGTH_SHORT).show();
                }

            }
        }
    };


    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            //  {
//                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
//                        .setImage(R.mipmap.ic_action_add) // 图标。
//                        .setWidth(width) // 宽度。
//                        .setHeight(height); // 高度。
//                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。
//
//                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_red)
//                        .setImage(R.mipmap.ic_action_close)
//                        .setWidth(width)
//                        .setHeight(height);
//
//                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
            //  }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.mipmap.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。

//                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_green)
//                        .setText("添加")
//                        .setTextColor(Color.WHITE)
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(addItem);
                // 添加一个按钮到右侧菜单。
            }
        }
    };

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Intent ii = new Intent(getContext(), Html_Activity.class);
            startActivity(ii);
            //Toast.makeText(mContext, "我是第" + position + "条。", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }

            // TODO 推荐调用Adapter.notifyItemRemoved(position)，也可以Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {// 删除按钮被点击。
                //mStrings.remove(adapterPosition);
                mList.remove(adapterPosition);
                mMenuAdapter.notifyItemRemoved(adapterPosition);
            }
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().finish();
        } else if (item.getItemId() == R.id.menu_open_rv_menu) {
            mSwipeMenuRecyclerView.smoothOpenRightMenu(0);
        }
        return true;
    }


    /*
    * 从网络请求数据赋值到活动列表
    * */
    private void postData_activityy() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, PATH_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result.toString();
                try {
                    JSONObject obj = new JSONObject(result);

                    JSONArray array = obj.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj2 = array.getJSONObject(i);

                        Costom_Person person = new Costom_Person();
                        person.Title = obj2.getString("act_title");
                        person.image_url = obj2.getString("poster_img");
                        person.riqi_start = obj2.getString("time_start");
                        person.riqi_end = obj2.getString("time_end");
                        person.dizhi = obj2.getString("act_place");
                        person.canyu = obj2.getString("mine_state");
                        person.zhuangtai = obj2.getString("act_status");
                        mList.add(person);
                    }

                    mSwipeMenuRecyclerView.setAdapter(mMenuAdapter);
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();//如果是展示状态就取消
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mContext, "数据解析失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


}











