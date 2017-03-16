package com.example.yihuii.yihuii.MainFrame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.MainFrame.frament1.Activity_Fragment;
import com.example.yihuii.yihuii.MainFrame.frament1.QRcode_Activity;
import com.example.yihuii.yihuii.MainFrame.frament1.Setting_activ;
import com.example.yihuii.yihuii.MainFrame.frament1.city_search;
import com.example.yihuii.yihuii.MainFrame.frament1.mingpian_camera;
import com.example.yihuii.yihuii.MainFrame.frament1.mingpian_tuku;
import com.example.yihuii.yihuii.MainFrame.frament2.Frient_Fragment;
import com.example.yihuii.yihuii.MainFrame.frament3.Find_Fragment;
import com.example.yihuii.yihuii.MainFrame.frament4.Me_Fragment;
import com.example.yihuii.yihuii.R;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private Activity_Fragment activity_fragment;
    private LinearLayout layout_to_attention;
    private LinearLayout to_attention;
    private Frient_Fragment frient_fragment;
    private Find_Fragment find_fragment;
    private Me_Fragment me_fragment;
    private FragmentTransaction transaction;
    private FragmentTransaction transaction2;
    private FragmentManager manager = getSupportFragmentManager();
    private TextView text_main_text;
    private PopupWindow mpopupWindow;
    private PopupWindow mpopupWindow2;
    private Button btn_activity, btn_frient, btn_find, btn_me, btn_main_serch, btn_main_setting, btn_main_scaning, btn_attention, btn_to_attention;
    private List<Button> mListButton = new ArrayList<>();
    private   beishoucang_fragment    ff;//被收藏的fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShareSDK.initSDK(getApplicationContext(),"sharesdk的appkey");
        initView();
        changeText_attention();
        startLoding(0);
    }

    /*
    * @param 初始化布局控件
    * */
    private void initView() {


        btn_activity = (Button) findViewById(R.id.btn_activity);
        btn_frient = (Button) findViewById(R.id.btn_frient);
        btn_find = (Button) findViewById(R.id.btn_find);
        btn_me = (Button) findViewById(R.id.btn_me);
        text_main_text = (TextView) findViewById(R.id.text_main_text);
        btn_main_serch = (Button) findViewById(R.id.btn_main_serch);
        btn_main_setting = (Button) findViewById(R.id.btn_main_setting);
        btn_main_scaning = (Button) findViewById(R.id.btn_main_scaning);
        btn_to_attention = (Button) findViewById(R.id.btn_to_attention);
        btn_attention = (Button) findViewById(R.id.btn_attention);

        btn_activity.setOnClickListener(this);
        btn_frient.setOnClickListener(this);
        btn_find.setOnClickListener(this);
        btn_me.setOnClickListener(this);
        mListButton.add(btn_activity);
        mListButton.add(btn_frient);
        mListButton.add(btn_find);
        mListButton.add(btn_me);
        HideVisibilityed();
        btn_onchick();
    }


    /*
    * @param 点击返回不会被销毁
    *
    * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (KeyEvent.KEYCODE_BACK == keyCode) {
//            if (mpopupWindow.isShowing()){
//                mpopupWindow.dismiss();return true;
//            }else {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
    * @param 设置切换Frement的事件点击
    * */
    @Override
    public void onClick(View v) {

        transaction = manager.beginTransaction();
        HideFrament();
        switch (v.getId()) {

            case R.id.btn_activity:
                HideVisibilityed();
                change(0);
                if (activity_fragment == null) {
                    activity_fragment = new Activity_Fragment();
                    transaction.add(R.id.linear_fraement, activity_fragment);
                } else {
                    transaction.show(activity_fragment);
                }
                break;
            case R.id.btn_frient:
                HideVisibilityed();
                change(1);
                if (frient_fragment == null) {
                    frient_fragment = new Frient_Fragment();
                    transaction.add(R.id.linear_fraement, frient_fragment);
                } else {
                    transaction.show(frient_fragment);
                }
                break;
            case R.id.btn_find:
                HideVisibilityed();
                change(2);
                if (find_fragment == null) {
                    find_fragment = new Find_Fragment();
                    transaction.add(R.id.linear_fraement, find_fragment);
                } else {
                    transaction.show(find_fragment);
                }
                break;
            case R.id.btn_me:
                HideVisibilityed();
                change(3);
                if (me_fragment == null) {
                    me_fragment = new Me_Fragment();
                    transaction.add(R.id.linear_fraement, me_fragment);
                } else {
                    transaction.show(me_fragment);
                }
                break;

            default:
                break;
        }
        transaction.commit();
    }

    private void HideFrament() {
        if (activity_fragment != null) {
            transaction.hide(activity_fragment);
        }
        if (frient_fragment != null) {
            transaction.hide(frient_fragment);
        }
        if (find_fragment != null) {
            transaction.hide(find_fragment);
        }
        if (me_fragment != null) {
            transaction.hide(me_fragment);
        }

    }

    /*
    * @param  更换字体颜色和button切图
    * */
    private void change(int positon) {
        if (positon == 0) {
            text_main_text.setVisibility(View.VISIBLE);
            text_main_text.setText("活动");

            text_main_text.setTextColor(Color.WHITE);
            btn_main_serch.setVisibility(View.VISIBLE);
            btn_main_scaning.setVisibility(View.VISIBLE);

        }
        if (positon == 1) {
            btn_to_attention.setVisibility(View.VISIBLE);
            btn_attention.setVisibility(View.VISIBLE);
        }
        if (positon == 2) {
            text_main_text.setVisibility(View.VISIBLE);
            text_main_text.setText("发现");
            text_main_text.setTextColor(Color.WHITE);
            btn_main_scaning.setVisibility(View.VISIBLE);

        }
        if (positon == 3) {
            text_main_text.setVisibility(View.VISIBLE);
            text_main_text.setText("名片");
            btn_main_setting.setVisibility(View.VISIBLE);
        }
        for (Button button : mListButton) {
            if (button.isSelected() || button.isEnabled()) {
                button.setSelected(false);
                button.setTextColor(Color.DKGRAY);
            }
            mListButton.get(positon).setSelected(true);
            mListButton.get(positon).setTextColor(getResources().getColor(R.color.costom_lan_text));
        }
    }


    private void startLoding(int posion) {
        transaction = manager.beginTransaction();
        HideFrament();
        change(posion);
        activity_fragment = new Activity_Fragment();
        transaction.add(R.id.linear_fraement, activity_fragment);
        transaction.commit();
    }

    private void HideVisibilityed() {
        btn_to_attention.setVisibility(View.GONE);
        btn_attention.setVisibility(View.GONE);
        btn_main_setting.setVisibility(View.GONE);
        btn_main_scaning.setVisibility(View.GONE);
        btn_main_serch.setVisibility(View.GONE);
        text_main_text.setVisibility(View.GONE);
    }

    private void btn_onchick() {

        btn_main_serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, city_search.class);
                startActivity(intent);
            }
        });
        btn_main_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Setting_activ.class);
                startActivity(intent);
            }
        });
        //被收藏
        /*
        * @to_attention 未被隐藏
        * @ layout_to_attention  被隐藏的布局
        * */
        btn_to_attention.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                to_attention = (LinearLayout) findViewById(R.id.attention_layout);
                transaction2 = manager.beginTransaction();
                layout_to_attention = (LinearLayout) findViewById(R.id.to_attention_layout);
               //动态设置圆角
                btn_to_attention.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_style_right2));
                btn_attention.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_style_left));
                btn_attention.setTextColor(Color.WHITE);
                btn_to_attention.setTextColor(Color.BLACK);
                to_attention.setVisibility(View.GONE);
                layout_to_attention.setVisibility(View.VISIBLE);


                if (ff == null) {
                    ff = new beishoucang_fragment();
                    transaction2.add(R.id.to_attention_layout, ff);
                } else {
                    transaction2.show(ff);
                }
                transaction2.commit();


            }
        });



        //未收藏
        btn_attention.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ff != null) {
                    transaction2.hide(ff);
                }
                changeText_attention();
                layout_to_attention = (LinearLayout) findViewById(R.id.to_attention_layout);
                to_attention = (LinearLayout) findViewById(R.id.attention_layout);
                layout_to_attention.setVisibility(View.GONE);
                to_attention.setVisibility(View.VISIBLE);
            }
        });
        btn_main_scaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pupupWindow();

            }
        });
    }

    private void pupupWindow() {
        final View view = getLayoutInflater().inflate(R.layout.popup_qrcode, null);
        //总布局控件，长，宽
        mpopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mpopupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.black));
        //得到背景后设置透明度;这句话一定要在上面这句话的后面，否则就会报错
        mpopupWindow.getBackground().setAlpha(200);
        // popupWindow.setTouchable(true);
        // popupWindow.setFocusable(true);
        mpopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        mpopupWindow.setOutsideTouchable(true);//点击空白处消失
        mpopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);//在某某parent布局的下面位置。有其他TOP。RIGHT其他属性

        Button mBtn_cade_scan = (Button) view.findViewById(R.id.pop_card_scan);
        Button mBtn_qrcode_scan = (Button) view.findViewById(R.id.pop_qrcode_scan);
        Button mBtn_pub_Change = (Button) view.findViewById(R.id.pup_change);

        mBtn_cade_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopupWindow.dismiss();
                pupupWindow2();

            }
        });
        mBtn_qrcode_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "二维码扫描", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, QRcode_Activity.class);
                startActivity(intent);
            }
        });
        mBtn_pub_Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopupWindow.dismiss();
            }
        });

    }


    private void pupupWindow2() {
        final View view = getLayoutInflater().inflate(R.layout.popup_qrcode2, null);
        //总布局控件，长，宽
        mpopupWindow2 = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mpopupWindow2.setBackgroundDrawable(getResources().getDrawable(R.color.black));
        //得到背景后设置透明度;这句话一定要在上面这句话的后面，否则就会报错
        mpopupWindow2.getBackground().setAlpha(200);
        // popupWindow.setTouchable(true);
        // popupWindow.setFocusable(true);
        mpopupWindow2.setAnimationStyle(android.R.style.Animation_Dialog);
        mpopupWindow2.setOutsideTouchable(true);//点击空白处消失
        mpopupWindow2.showAtLocation(view, Gravity.BOTTOM, 0, 0);//在某某parent布局的下面位置。有其他TOP。RIGHT其他属性

        Button mBtn_cade_scan = (Button) view.findViewById(R.id.pop_card_scan_2);
        Button mBtn_qrcode_scan = (Button) view.findViewById(R.id.pop_qrcode_scan_2);
        Button mBtn_pub_Change = (Button) view.findViewById(R.id.pup_change_2);

        mBtn_cade_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,mingpian_camera.class);
                startActivity(intent);
                mpopupWindow.dismiss();
                mpopupWindow2.dismiss();

            }
        });
        mBtn_qrcode_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,mingpian_tuku.class);
                startActivity(intent);
                mpopupWindow.dismiss();
                mpopupWindow2.dismiss();
            }
        });
        mBtn_pub_Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopupWindow2.dismiss();
//                mpopupWindow.dismiss();
            }
        });

    }


    /*
    * 设置初始颜色
    * */
    private void changeText_attention() {
        btn_attention.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_style_left2));
        btn_to_attention.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_style_right));
        btn_to_attention.setTextColor(Color.WHITE);
        btn_attention.setTextColor(Color.BLACK);

    }


}
