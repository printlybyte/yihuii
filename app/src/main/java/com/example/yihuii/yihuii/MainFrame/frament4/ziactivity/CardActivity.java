package com.example.yihuii.yihuii.MainFrame.frament4.ziactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.Coston_view_wheel.WheelView;
import com.example.yihuii.yihuii.MainFrame.frament1.city_search;
import com.example.yihuii.yihuii.MainFrame.frament4.me_mingpian_edit.me_bianji_jiaoyujingli;
import com.example.yihuii.yihuii.MainFrame.frament4.me_mingpian_edit.me_shezhi_gongzuo_edit;
import com.example.yihuii.yihuii.MainFrame.frament4.me_mingpian_edit.me_weixin;
import com.example.yihuii.yihuii.uituls.ysuo;
import com.example.yihuii.yihuii.R;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linear;
    private LinearLayout linear2;
    final List<String> listss = new ArrayList<>();
    private RelativeLayout r;
    private static int PHOTO_REQUEST_GALLERY = 3; //相册
    private Uri imageUri;
    private RelativeLayout r2;
    public static int TAKE_PHOTO_REQUEST_CODE = 1; //拍照
    public static int PHOTO_REQUEST_CUT = 2; //裁切
    TextView textView, textView2;
    private TextView back_text, mT_gongsi, mT_zhiwei, mT_ruxueshijian, mT_xuexiao, mT_zhaunye, mT_qq_bianji, mT_weixin_bianji, mT_youxiang_bianji, mT_shouji_bianji;
    private LinearLayout mT_lainxifansghi_linear, mShezhi_touxaing, mTsex_buju, mTxt_gongzuo, mTxt_xueli, mT_weixin, mT_qq, mT_youxiang, mT_shouji, mT_city;
    private PopupWindow mpopup;
    private PopupWindow mpopup2;
    private ImageView touciang;
    private Button mBtn_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        listss.add("男");
        listss.add("女");
        loding();
        loding2();
        initView();

    }

    private void initView() {
        mBtn_other = (Button) findViewById(R.id.mBtn_gengduo);
        mBtn_other.setOnClickListener(this);
        back_text = (TextView) findViewById(R.id.card_activity_back);
        back_text.setOnClickListener(this);
        mShezhi_touxaing = (LinearLayout) findViewById(R.id.shezhi_touxiang);
        mShezhi_touxaing.setOnClickListener(this);
        touciang = (ImageView) findViewById(R.id.shezhi_touxiang_image);
        mTsex_buju = (LinearLayout) findViewById(R.id.checkbox_one_buju);
        mTsex_buju.setOnClickListener(this);
        mTxt_gongzuo = (LinearLayout) findViewById(R.id.checkbox_two_gongzuo);
        mTxt_gongzuo.setOnClickListener(this);
        mTxt_xueli = (LinearLayout) findViewById(R.id.checkbox_there_xueli);
        mTxt_xueli.setOnClickListener(this);
        mT_gongsi = (TextView) findViewById(R.id.gongsi);
        mT_zhiwei = (TextView) findViewById(R.id.zhiwei);
        mT_ruxueshijian = (TextView) findViewById(R.id.me_ruxuenian);
        mT_xuexiao = (TextView) findViewById(R.id.me_xuexiao);
        mT_zhaunye = (TextView) findViewById(R.id.me_zhuanye);
        mT_weixin = (LinearLayout) findViewById(R.id.me_weixin_linear);
        mT_weixin.setOnClickListener(this);
        mT_qq = (LinearLayout) findViewById(R.id.me_qq_linear);
        mT_qq.setOnClickListener(this);
        mT_qq_bianji = (TextView) findViewById(R.id.me_qq);
        mT_weixin_bianji = (TextView) findViewById(R.id.me_weixin);
        mT_youxiang = (LinearLayout) findViewById(R.id.me_youxiang_lianear);
        mT_youxiang.setOnClickListener(this);
        mT_shouji = (LinearLayout) findViewById(R.id.me_shouji_linear);
        mT_shouji.setOnClickListener(this);
        mT_youxiang_bianji = (TextView) findViewById(R.id.me_youxiang);
        mT_shouji_bianji = (TextView) findViewById(R.id.me_shouji);
        mT_city = (LinearLayout) findViewById(R.id.me_suozaidi);
        mT_city.setOnClickListener(this);
        mT_lainxifansghi_linear = (LinearLayout) findViewById(R.id.me_lianxifangshi_lianear);
        mT_lainxifansghi_linear.setOnClickListener(this);
    }


    private void loding() {
        linear = (LinearLayout) findViewById(R.id.xxxxv);
        textView = new TextView(this);
        textView.setText("增加");
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setHeight(80);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        linear.addView(textView);
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r = (RelativeLayout) LayoutInflater.from(CardActivity.this).inflate(R.layout.test, null);

                linear.addView(r);

            }
        });
    }

    private void loding2() {
        linear2 = (LinearLayout) findViewById(R.id.xxxxv2);
        textView2 = new TextView(this);
        textView2.setText("增加");
        textView2.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView2.setHeight(80);
        textView2.setGravity(Gravity.CENTER);
        textView2.setTextColor(Color.BLACK);
        linear2.addView(textView2);
        linear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r2 = (RelativeLayout) LayoutInflater.from(CardActivity.this).inflate(R.layout.test2, null);

                linear2.addView(r2);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtn_gengduo:
                AlertDialog.Builder bb = new AlertDialog.Builder(this);
                Dialog dialog = bb.create();
                LinearLayout ll = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_coston_mingpian_gengduo, null);
                Button button = (Button) ll.findViewById(R.id.mingpian_xiangqing_gengduo_btn);
                button.setText("查看二维码");
                Button button2 = (Button) ll.findViewById(R.id.mingpian_xiangqing_gengduo_btn2);
                button2.setText("分享名片");
                Button button3 = (Button) ll.findViewById(R.id.mingpian_xiangqing_gengduo_btn3);
                button3.setText("预览名片");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CardActivity.this, com.example.yihuii.yihuii.MainFrame.frament4.me_Activity.me_activity_erweima.erweima_xiangqing.class);
                        intent.putExtra("qubie", "2");
                        startActivity(intent);

                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showShare();
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CardActivity.this, gerenmingpian_yulan.class);
                        startActivity(intent);
                    }
                });

                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                bb.setView(ll);
                bb.show();


                break;
            case R.id.card_activity_back:


                setResult(2);
                finish();
                break;
            case R.id.shezhi_touxiang:
                pupupWindow2();
                break;

            case R.id.checkbox_one_buju:
                pupup_gunlun();
                break;
            case R.id.checkbox_two_gongzuo:
                Intent intent = new Intent(CardActivity.this, me_shezhi_gongzuo_edit.class);
                startActivityForResult(intent, 11);
                break;
            case R.id.checkbox_there_xueli:
                Intent intent2 = new Intent(CardActivity.this, me_bianji_jiaoyujingli.class);
                startActivityForResult(intent2, 22);
                break;
            case R.id.me_weixin_linear:
                Intent intent3 = new Intent(CardActivity.this, me_weixin.class);
                intent3.putExtra("qubie", "weixin");
                startActivityForResult(intent3, 33);
                break;
            case R.id.me_qq_linear:
                Intent intent4 = new Intent(CardActivity.this, me_weixin.class);
                intent4.putExtra("qubie", "qq");
                startActivityForResult(intent4, 44);
                break;
            case R.id.me_youxiang_lianear:
                Intent intent5 = new Intent(CardActivity.this, me_weixin.class);
                intent5.putExtra("qubie", "youxiang");
                startActivityForResult(intent5, 55);
                break;
            case R.id.me_shouji_linear:
                Intent intent6 = new Intent(CardActivity.this, me_weixin.class);
                intent6.putExtra("qubie", "shouji");
                startActivityForResult(intent6, 66);
                break;
            case R.id.me_lianxifangshi_lianear:
                Intent intent7 = new Intent(CardActivity.this, me_weixin.class);
                intent7.putExtra("qubie", "lianxi");
                startActivityForResult(intent7, 77);
                break;

            case R.id.me_suozaidi:
                city_search();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == 0) {

                final String picturePath = BGAPhotoPickerActivity.getSelectedImages(data).get(0);

                File file = new File(picturePath);
                if (file.exists()) {
                    //bb = BitmapFactory.decodeFile(picturePath);//得到图片路径
                    Bitmap bitmap = ysuo.getimage(picturePath);//第一次压缩
                    touciang.setImageBitmap(bitmap);
                    mpopup.dismiss();
                    //   aa.setImageBitmap(aaq);//显示图片

                }
            }
        }

        if (requestCode == TAKE_PHOTO_REQUEST_CODE) {
            if (imageUri != null) {
                startPhotoZoom(imageUri);
                Bitmap bitmap = decodeUriBitmap(imageUri);
                touciang.setImageBitmap(bitmap);
                mpopup.dismiss();

            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            if (imageUri != null) {
                Bitmap bitmap = decodeUriBitmap(imageUri);
                touciang.setImageBitmap(bitmap);
                mpopup.dismiss();
            }
        } else if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                imageUri = data.getData();
                Bitmap bitmap = decodeUriBitmap(imageUri);
                touciang.setImageBitmap(bitmap);
                mpopup.dismiss();
            }
        }
        if (requestCode == 11) {
            if (data == null) {
                return;
            }
            String a = mT_gongsi.getText().toString();
            String b = data.getStringExtra("gongsi");
            mT_gongsi.setText(a + " :" + b);
            String aa = mT_zhiwei.getText().toString();
            String bb = data.getStringExtra("zhiwei");
            mT_zhiwei.setText(aa + " :" + bb);

        }
        if (requestCode == 22) {
            if (data == null) {
                Toast.makeText(this, "data为空", Toast.LENGTH_SHORT).show();
                return;
            }
            String a = mT_xuexiao.getText().toString();
            String b = data.getStringExtra("gongsi1");
            mT_xuexiao.setText(a + " :" + b);
            String aa = mT_zhaunye.getText().toString();
            String bb = data.getStringExtra("zhiwei1");
            mT_zhaunye.setText(aa + " :" + bb);
            String aaa = mT_ruxueshijian.getText().toString();
            String bbb = data.getStringExtra("jiaoyushijian");
            mT_ruxueshijian.setText(aaa + " :" + bbb);

        }
        if (requestCode == 33) {
            if (data != null) {

                mT_weixin_bianji.setText(mT_weixin_bianji.getText().toString() + " :" + data.getStringExtra("weixin"));
            }
        }
        if (requestCode == 44) {
            if (data != null) {
                mT_qq_bianji.setText(mT_qq_bianji.getText().toString() + " :" + data.getStringExtra("qq"));
            }
        }
        if (requestCode == 55) {
            if (data != null) {
                mT_youxiang_bianji.setText(mT_youxiang_bianji.getText().toString() + " :" + data.getStringExtra("youxiang"));
            }
        }
        if (requestCode == 66) {
            if (data != null) {
                mT_shouji_bianji.setText(mT_shouji_bianji.getText().toString() + " :" + data.getStringExtra("shouji"));
            }

        }
    }

    private void pupupWindow2() {
        final View view = getLayoutInflater().inflate(R.layout.popup_qrcode2, null);
        //总布局控件，长，宽
        mpopup = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mpopup.setBackgroundDrawable(getResources().getDrawable(R.color.gray));
        //得到背景后设置透明度;这句话一定要在上面这句话的后面，否则就会报错
        mpopup.getBackground().setAlpha(255);
        // popupWindow.setTouchable(true);
        // popupWindow.setFocusable(true);
        mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
        mpopup.setOutsideTouchable(true);//点击空白处消失
        mpopup.showAtLocation(view, Gravity.BOTTOM, 0, 0);//在某某parent布局的下面位置。有其他TOP。RIGHT其他属性

        Button mBtn_cade_scan = (Button) view.findViewById(R.id.pop_card_scan_2);
        Button mBtn_qrcode_scan = (Button) view.findViewById(R.id.pop_qrcode_scan_2);
        Button mBtn_pub_Change = (Button) view.findViewById(R.id.pup_change_2);

        mBtn_cade_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhotos();

            }
        });
        mBtn_qrcode_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(BGAPhotoPickerActivity.newIntent(CardActivity.this, null, 1, null, false), 0);
            }
        });
        mBtn_pub_Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopup.dismiss();
            }
        });

    }


    private void pupup_gunlun() {
        final View view = getLayoutInflater().inflate(R.layout.pupup_gunlun_layout, null);
        final WheelView wheelView = (WheelView) view.findViewById(R.id.wheelView);
        wheelView.lists(listss).fontSize(35).showCount(2).selectTip("").select(0).listener(new WheelView.OnWheelViewItemSelectListener() {
            @Override
            public void onItemSelect(int index) {
                //    Toast.makeText(test_test.this, wheelView.getSelectItem(), Toast.LENGTH_SHORT).show();
            }
        }).build();
        //总布局控件，长，宽
        mpopup2 = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mpopup2.setBackgroundDrawable(getResources().getDrawable(R.color.white));
        //得到背景后设置透明度;这句话一定要在上面这句话的后面，否则就会报错
        mpopup2.getBackground().setAlpha(255);
        // popupWindow.setTouchable(true);
        // popupWindow.setFocusable(true);
        mpopup2.setAnimationStyle(android.R.style.Animation_Dialog);
        mpopup2.setOutsideTouchable(true);//点击空白处消失
        mpopup2.showAtLocation(view, Gravity.BOTTOM, 0, 0);//在某某parent布局的下面位置。有其他TOP。RIGHT其他属性

        Button mBtn_cade_scan = (Button) view.findViewById(R.id.pupup_gunlun_queciao);
        Button mBtn_qrcode_scan = (Button) view.findViewById(R.id.pupup_gunlun_yes);


        mBtn_cade_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopup2.dismiss();

            }
        });
        mBtn_qrcode_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CardActivity.this, "AAA", Toast.LENGTH_SHORT).show();


            }
        });


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

    //裁剪  相册
    private Bitmap decodeUriBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * 调用系统裁剪
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        //  intent.putExtra("crop", "true");
        //  intent.putExtra("scale", true);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);

        //设置了true的话直接返回bitmap，可能会很占内存
        intent.putExtra("return-data", false);
        //设置输出的格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //设置输出的地址
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //不启用人脸识别
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private void city_search() {
        CityPicker cityPicker = new CityPicker.Builder(CardActivity.this).textSize(20)
                .title("我是老大")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#234Dfa")
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .build();

        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
//                tvResult.setText("选择结果：\n省：" + citySelected[0] + "\n市：" + citySelected[1] + "\n区："
//                        + citySelected[2] + "\n邮编：" + citySelected[3]);
            }
        });
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}