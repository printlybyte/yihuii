package com.example.yihuii.yihuii.MainFrame.frament4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yihuii.yihuii.MainFrame.frament4.ziactivity.CardActivity;
import com.example.yihuii.yihuii.MainFrame.frament4.ziactivity.xuqiu.MyOwnNeedActivity;
import com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_wenda.MyOwnQuestionActivity;
import com.example.yihuii.yihuii.R;


public class Me_Fragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {
    private View view;
    private TextView mT_title, mT_huodong, mT_fabu, mT_xuqiu, mT_huida, mT_yuejian;
    private ImageView mI_touxaing;
    private RelativeLayout mRalative, two_ralative, new_ralative;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me_, container, false);
        initView();
        return view;
    }

    private void initView() {

        two_ralative = (RelativeLayout) view.findViewById(R.id.Relative_xinzeng);
        new_ralative = (RelativeLayout) view.findViewById(R.id.relative_second);
        mT_title = (TextView) view.findViewById(R.id.me_me_title);
        mT_huodong = (TextView) view.findViewById(R.id.me_me_huodong);
        mT_fabu = (TextView) view.findViewById(R.id.me_me_fabu);
        mT_xuqiu = (TextView) view.findViewById(R.id.me_me_xuqiu);
        mT_huida = (TextView) view.findViewById(R.id.me_me_huida);
        mRalative = (RelativeLayout) view.findViewById(R.id.relative);
        mRalative.setOnClickListener(this);
        new_ralative.setOnClickListener(this);
        two_ralative.setOnClickListener(this);
        mT_title.setOnClickListener(this);
        mT_huodong.setOnClickListener(this);
        mT_fabu.setOnClickListener(this);
        mT_huida.setOnClickListener(this);
        mT_xuqiu.setOnClickListener(this);
        two_ralative.setVisibility(View.VISIBLE);
        new_ralative.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.me_me_huodong:
//                Cos_dialog();//我的活动弹出自定义dialog

                Intent intent_me_huodong = new Intent(getActivity(), com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_me_huodong.class);
                startActivity(intent_me_huodong);


                break;
            case R.id.me_me_fabu:
//                intent = new Intent(getContext(), FabuActivity.class);
//                startActivity(intent);
                Cos_dialog();
                break;
            case R.id.me_me_xuqiu:
                intent = new Intent(getContext(), MyOwnNeedActivity.class);
                startActivity(intent);
                break;
            case R.id.me_me_huida:
                intent = new Intent(getContext(), MyOwnQuestionActivity.class);
                startActivity(intent);
                // Cos_dialog();
                break;

            case R.id.relative:
                Intent intent1 = new Intent(getActivity(), CardActivity.class);
                startActivity(intent1);
                break;
            case R.id.Relative_xinzeng:
                Intent intent2 = new Intent(getActivity(), CardActivity.class);

                startActivityForResult(intent2, 2);
                break;
            case R.id.relative_second:
                Intent intent3 = new Intent(getActivity(), CardActivity.class);

                startActivity(intent3);
                break;

            default:
                break;

        }
    }

    private void Cos_dialog() {
        final AlertDialog.Builder aa = new AlertDialog.Builder(getActivity());
        LinearLayout ll = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.costom_dialog_me, null);
        aa.setView(ll);
        Button mBtn_qux = (Button) ll.findViewById(R.id.cos_dialog_btn1);

        final Dialog dialog = aa.create();
        //没有背景
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        mBtn_qux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == 2) {
            two_ralative.setVisibility(View.GONE);
            new_ralative.setVisibility(View.VISIBLE);
        }

    }

    //长按删除
    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.relative_second:

                break;
        }
        return false;
    }
}


