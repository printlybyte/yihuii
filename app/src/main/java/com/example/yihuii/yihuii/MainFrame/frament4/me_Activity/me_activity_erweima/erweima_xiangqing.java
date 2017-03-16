package com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_activity_erweima;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class erweima_xiangqing extends AppCompatActivity {
    private TextView textView, textView2;
    private Button button;
    private ImageView imageView;
    private String qb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erweima_xiangqing);

        Intent intent = getIntent();
        if (intent != null) {
            qb = intent.getStringExtra("qubie");

        }
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.erweima_uri);
        textView = (TextView) findViewById(R.id.incloud_other);
        textView.setText("保存");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenshot();
            }
        });
        textView2 = (TextView) findViewById(R.id.incolud_text);
        textView2.setText("活动二维码");
        button = (Button) findViewById(R.id.mBtn_incloud_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        BitmapUtils utils = new BitmapUtils(this);

        if (qb != null) {
            if (qb.equals("2")) {
                textView2.setText("名片二维码");
                utils.display(imageView, "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2151853798,1611784908&fm=58");
            } else {
                utils.display(imageView, "https://img.alicdn.com/tps/TB1o.j9PpXXXXcTXFXXXXXXXXXX-180-140.jpg");
            }
        } else {
            utils.display(imageView, "https://img.alicdn.com/tps/TB1o.j9PpXXXXcTXFXXXXXXXXXX-180-140.jpg");
        }
    }

    private void SaveImageToSysAlbum() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            BitmapDrawable bmpDrawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bmp = bmpDrawable.getBitmap();
            if (bmp != null) {
                try {
                    ContentResolver cr = getContentResolver();
                    String url = MediaStore.Images.Media.insertImage(cr, bmp,
                            String.valueOf(System.currentTimeMillis()), "");
                    Toast.makeText(this, getString(R.string.save_succ) + url, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
        }
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
    }


    private void screenshot() {
        // 获取屏幕
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bmp = dView.getDrawingCache();
        if (bmp != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                String filePath = sdCardPath + File.separator + "screenshot.png";

                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
                Toast.makeText(this, "图片保存成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
            }
        } else {
            Toast.makeText(this, "bmp=null", Toast.LENGTH_SHORT).show();
        }
    }
}
