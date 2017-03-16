package com.example.yihuii.yihuii.MainFrame.frament1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class mingpian_camera extends AppCompatActivity {
    String pathUrl = "http://op.juhe.cn/hanvon/bcard/query";
    //申请的KEY
    public static final String APPKEY = "0372a642389651c058e15e1477e40169";
    public static int TAKE_PHOTO_REQUEST_CODE = 1; //拍照
    private Uri imageUri;
    private TextView textView, textView2;
    private Button button;
    private EditText mEd_name, mEd_gongsi, mEd_zhiwei, mEd_youxiang, mEd_shouji, mEd_dinhua, mEd_dizhi, mEd_wangzhi;
     ProgressDialog dd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mingpian__ativity);
        takePhotos();//立即执行
        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.incloud_other);
        textView.setText("保存");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mingpian_camera.this, "一保存完毕", Toast.LENGTH_SHORT).show();
            }
        });
        textView2 = (TextView) findViewById(R.id.incolud_text);
        textView2.setText("名片识别");
        button = (Button) findViewById(R.id.mBtn_incloud_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEd_dinhua = (EditText) findViewById(R.id.mingpian_edit_dianhua);
        mEd_shouji = (EditText) findViewById(R.id.mingpian_edit_shouji);
        mEd_dizhi = (EditText) findViewById(R.id.mingpian_edit_dizhi);
        mEd_name = (EditText) findViewById(R.id.mingpian_edit_name);
        mEd_gongsi = (EditText) findViewById(R.id.mingpian_edit_gongsi);
        mEd_zhiwei = (EditText) findViewById(R.id.mingpian_edit_zhiwei);
        mEd_youxiang = (EditText) findViewById(R.id.mingpian_edit_youxiang);
        mEd_wangzhi = (EditText) findViewById(R.id.mingpian_edit_wangzhi);
    }

    /**
     * 打开相机拍照
     */
    private void takePhotos() {
        imageUri = Uri.fromFile(getImageStoragePath(this));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //指定照片存储路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO_REQUEST_CODE);
    }


    /**
     * 设置图片保存路径
     *
     * @return
     */
    private File getImageStoragePath(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "temp.jpg");
            return file;
        }
        return null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//data为空  不要用来作为判断条件
            if (RESULT_OK == resultCode) {
                if (imageUri != null) {
                    post();//调用转化
                }
            }else {
                finish();
            }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //获取图片url
    private void post() {
        Bitmap bit = getimage(imageUri.getPath().toString());//获得图片地址
        String qqs = Bitmap2StrByBase64(bit);
        start(qqs);
    }


    //64编码执行
    public String Bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public void start(String base_64) {
        Log.i("qweqwe", base_64);
       dd = new ProgressDialog(this);
       dd.setCancelable(false);
        dd.setTitle("正在识别中");
        dd.show();
        HttpUtils utils = new HttpUtils();

        RequestParams pp = new RequestParams();

        if (base_64.equals("")) {
            Toast.makeText(this, "QQKONG", Toast.LENGTH_SHORT).show();
        }
        pp.addBodyParameter("key", APPKEY);
        pp.addBodyParameter("image", base_64);
        utils.send(HttpRequest.HttpMethod.POST, pathUrl, pp, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Toast.makeText(mingpian_camera.this, "成功", Toast.LENGTH_SHORT).show();
                String result = responseInfo.result.toString();
                dd.dismiss();
                try {
                    JSONObject obj = new JSONObject(result);
                    JSONObject obj2 = obj.getJSONObject("result");

                    String name = obj2.getString("name");


                    String zhiwei = obj2.getString("title");
                    String email = obj2.getString("email");
                    String tel = obj2.getString("tel");
                    String comp = obj2.getString("comp");
                    String addr = obj2.getString("addr");
                    String wangzhi = obj2.getString("web");
                    String dianhua = obj2.getString("fax");

                    mEd_name.setText(name);
                    mEd_youxiang.setText(email);
                    mEd_shouji.setText(tel);
                    mEd_dizhi.setText(addr);
                    mEd_zhiwei.setText(zhiwei);
                    mEd_gongsi.setText(comp);
                    mEd_wangzhi.setText(wangzhi);
                    mEd_dinhua.setText(dianhua);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                dd.dismiss();
                AlertDialog.Builder dia = new AlertDialog.Builder(mingpian_camera.this);
                dia.create();
                dia.setTitle("识别失败，请重试");
                dia.setPositiveButton("取消", null);
                dia.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        takePhotos();
                    }
                });
                dia.show();
                Toast.makeText(mingpian_camera.this, "识别失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //质量压缩
    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    //比例压缩
    private Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
