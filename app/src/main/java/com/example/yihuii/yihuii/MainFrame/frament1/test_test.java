package com.example.yihuii.yihuii.MainFrame.frament1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yihuii.yihuii.Coston_view_wheel.WheelView;
import com.example.yihuii.yihuii.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class test_test extends Activity {

    final List<String> listss = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_test);


        listss.add("男");
        listss.add("女");


        final WheelView wheelView = (WheelView) findViewById(R.id.wheelView);
        wheelView.lists(listss).fontSize(35).showCount(2).selectTip("").select(0).listener(new WheelView.OnWheelViewItemSelectListener() {
            @Override
            public void onItemSelect(int index) {
                //    Toast.makeText(test_test.this, wheelView.getSelectItem(), Toast.LENGTH_SHORT).show();
            }
        }).build();

    }

}
