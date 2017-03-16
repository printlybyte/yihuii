package net.ossrs.yasea;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.ossrs.yasea.rtmp.RtmpPublisher;

import com.example.yihuii.yihuii.R;
import com.seu.magicfilter.utils.MagicFilterType;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class zbzbzbzb extends Activity implements View.OnClickListener {
    private static final String TAG = "Yasea";

    //闪光灯
    private CameraManager manager;
    private Camera camera = null;
    private Camera.Parameters parameters = null;
    public static boolean kaiguan = true; // 定义开关状态，状态为false，打开状态，状态为true，关闭状态

    Button btnPublish = null;
    Button btnSwitchCamera = null;
    Button btnRecord = null;
    Button btnSwitchEncoder = null;
    Button btnSwitchFilter = null;
    Button mBtn_luyin, mBtn_jietu, mBtn_light, mBtn_shipin,mBtn_yinpin;
    private String kaishi = "publish";
    private boolean isYes = true;
    private boolean isYes_deng = true;
    private boolean isYes_shipin = true;

    private SharedPreferences sp;
    //  private String rtmpUrl = "rtmp://0.0.0.0/" + getRandomAlphaString(3) + '/' + getRandomAlphaDigitString(5);
    // private String rtmpUrl = "rtmp://123.57.158.44/live/helloworld" + getRandomAlphaString(3) + '/' + getRandomAlphaDigitString(5);
    private String rtmpUrl = "rtmp://123.57.158.44/live/helloworld";
    private String recPath = Environment.getExternalStorageDirectory().getPath() + "/test.mp4";
    private AudioManager audioManager;
    private SrsPublisher mPublisher;
    private    RelativeLayout yinpin_buju,shipin_buju;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_zbzbzbzb);
         yinpin_buju= (RelativeLayout) findViewById(R.id.yinpin_relative);
         shipin_buju= (RelativeLayout) findViewById(R.id.shipin_relative);

        Intent intent = getIntent();
        String ss = intent.getStringExtra("shipin");
        if (ss.equals("1")) {
            yinpin_buju.setVisibility(View.GONE);
            shipin_buju.setVisibility(View.VISIBLE);

        }
        if (ss.equals("2")){
            shipin_buju.setVisibility(View.GONE);
            yinpin_buju.setVisibility(View.VISIBLE);
        }

        mBtn_yinpin= (Button) findViewById(R.id.yinpin_zhibo);
        mBtn_yinpin.setOnClickListener(this);
        mBtn_luyin = (Button) findViewById(R.id.zhibo_luyin);
        mBtn_luyin.setOnClickListener(this);
        mBtn_jietu = (Button) findViewById(R.id.zhibo_jietu);
        mBtn_jietu.setOnClickListener(this);
        mBtn_light = (Button) findViewById(R.id.light_deng);
        mBtn_light.setOnClickListener(this);
        mBtn_shipin = (Button) findViewById(R.id.zhibo_shipin);
        mBtn_shipin.setOnClickListener(this);
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        //设置声音模式
        audioManager.setMode(AudioManager.STREAM_MUSIC);


        //闪光灯
        manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String[] camerList = new String[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                camerList = manager.getCameraIdList();
            }
            for (String str : camerList
                    ) {
                Log.d("List", str);
            }
        } catch (CameraAccessException e) {
            Log.e("error", e.getMessage());
        }


        // response screen rotation event
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

        // restore data.
        sp = getSharedPreferences("Yasea", MODE_PRIVATE);
        rtmpUrl = sp.getString("rtmpUrl", rtmpUrl);

        // initialize url.
        final EditText efu = (EditText) findViewById(R.id.url);

        efu.setText(rtmpUrl);

        btnPublish = (Button) findViewById(R.id.publish);//发起直播
        btnSwitchCamera = (Button) findViewById(R.id.swCam);//切换摄像头
        btnRecord = (Button) findViewById(R.id.record);
        btnSwitchEncoder = (Button) findViewById(R.id.swEnc);
        btnSwitchFilter = (Button) findViewById(R.id.swFilter);
        mPublisher = new SrsPublisher((SrsCameraView) findViewById(R.id.glsurfaceview_camera));
        mPublisher.setPreviewResolution(1280, 720);

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kaishi.contentEquals("publish")) {
                    rtmpUrl = efu.getText().toString();
                    Log.i(TAG, String.format("RTMP URL changed to %s", rtmpUrl));
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("rtmpUrl", rtmpUrl);
                    editor.apply();

                    mPublisher.setOutputResolution(384, 640);
                    mPublisher.setVideoSmoothMode();
                    mPublisher.startPublish(rtmpUrl);

                    if (btnSwitchEncoder.getText().toString().contentEquals("soft encoding")) {
                        //   Toast.makeText(getApplicationContext(), "硬解码", Toast.LENGTH_SHORT).show();
                    } else {
                        //   Toast.makeText(getApplicationContext(), "软解码", Toast.LENGTH_SHORT).show();
                    }

                    btnPublish.setText("");
                    btnPublish.setBackgroundResource(R.mipmap.zhibo_start);
                    btnSwitchEncoder.setEnabled(false);
                    kaishi = "stop";
                } else if (kaishi.contentEquals("stop")) {
                    mPublisher.stopPublish();
                    mPublisher.stopRecord();
                    btnPublish.setText("");//开始直播
                    btnPublish.setBackgroundResource(R.mipmap.zhibo_stop);
                    btnRecord.setText("记录");
                    btnSwitchEncoder.setEnabled(true);
                    kaishi = "publish";
                }
            }
        });

        btnSwitchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Camera.getNumberOfCameras() > 0) {
                    mPublisher.switchCameraFace((mPublisher.getCamraId() + 1) % Camera.getNumberOfCameras());
                }
            }
        });

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnRecord.getText().toString().contentEquals("record")) {
                    mPublisher.startRecord(recPath);
                    btnRecord.setText("pause");
                } else if (btnRecord.getText().toString().contentEquals("pause")) {
                    mPublisher.pauseRecord();
                    btnRecord.setText("resume");
                } else if (btnRecord.getText().toString().contentEquals("resume")) {
                    mPublisher.resumeRecord();
                    btnRecord.setText("pause");
                }
            }
        });

        btnSwitchEncoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnSwitchEncoder.getText().toString().contentEquals("soft encoding")) {
                    mPublisher.swithToSoftEncoder();
                    btnSwitchEncoder.setText("hard encoding");
                } else if (btnSwitchEncoder.getText().toString().contentEquals("hard encoding")) {
                    mPublisher.swithToHardEncoder();
                    btnSwitchEncoder.setText("soft encoding");
                }
            }
        });

        btnSwitchFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnSwitchFilter.getText().toString().contentEquals("beauty")) {
                    if (mPublisher.switchCameraFilter(MagicFilterType.BEAUTY)) {
                        btnSwitchFilter.setText("none");
                    }
                } else if (btnSwitchFilter.getText().toString().contentEquals("none")) {
                    if (mPublisher.switchCameraFilter(MagicFilterType.NONE)) {
                        btnSwitchFilter.setText("beauty");
                    }
                }
            }
        });

        mPublisher.setPublishEventHandler(new RtmpPublisher.EventHandler() {
            @Override
            public void onRtmpConnecting(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onRtmpConnected(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //   Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onRtmpVideoStreaming(final String msg) {
            }

            @Override
            public void onRtmpAudioStreaming(final String msg) {
            }

            @Override
            public void onRtmpStopped(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onRtmpDisconnected(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onRtmpOutputFps(final double fps) {
                Log.i(TAG, String.format("Output Fps: %f", fps));
            }
        });

        mPublisher.setRecordEventHandler(new SrsMp4Muxer.EventHandler() {
            @Override
            public void onRecordPause(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onRecordResume(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onRecordStarted(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  Toast.makeText(getApplicationContext(), "Recording file: " + msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onRecordFinished(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //   Toast.makeText(getApplicationContext(), "MP4 file saved: " + msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mPublisher.setNetworkEventHandler(new SrsEncoder.EventHandler() {
            @Override
            public void onNetworkResume(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNetworkWeak(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //   Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                final String msg = ex.getMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        mPublisher.stopPublish();
                        mPublisher.stopRecord();
                        btnPublish.setText("publish");
                        btnPublish.setBackgroundResource(R.mipmap.zhibo_start);
                        btnRecord.setText("record");
                        btnSwitchEncoder.setEnabled(true);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Button btn = (Button) findViewById(R.id.publish);
        btn.setEnabled(true);
        mPublisher.resumeRecord();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPublisher.pauseRecord();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPublisher.stopPublish();
        mPublisher.stopRecord();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mPublisher.setPreviewRotation(90);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mPublisher.setPreviewRotation(0);
        }
        mPublisher.stopEncode();
        mPublisher.stopRecord();
        btnRecord.setText("record");
        mPublisher.setScreenOrientation(newConfig.orientation);
        if (btnPublish.getText().toString().contentEquals("stop")) {
            mPublisher.startEncode();
        }
    }

    private static String getRandomAlphaString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    private static String getRandomAlphaDigitString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhibo_luyin:
                if (isYes == true) {
                    mBtn_luyin.setBackgroundResource(R.mipmap.zhibo_luyin_n);
                    //关闭麦克风
                    audioManager.setMicrophoneMute(false);
                    isYes = false;
                } else {
                    mBtn_luyin.setBackgroundResource(R.mipmap.zhibo_luyin);
                    // 打开扬声器
                    audioManager.setSpeakerphoneOn(true);
                    isYes = true;
                }
                break;
            case R.id.zhibo_jietu:
                screenshot();
                break;
            case R.id.light_deng:
                if (isYes_deng == true) {
                    mBtn_light.setBackgroundResource(R.mipmap.shanguangdeng_y);
                    if (isLOLLIPOP()) {
                        try {
                            manager.setTorchMode("0", true);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    isYes_deng = false;
                } else {
                    mBtn_light.setBackgroundResource(R.mipmap.shanguangdeng);
                    if (isLOLLIPOP()) {
                        try {
                            manager.setTorchMode("0", false);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    isYes_deng = true;
                }
                break;
            case R.id.zhibo_shipin:
                if (isYes_shipin == true) {
                    mBtn_shipin.setBackgroundResource(R.mipmap.zhibo_shipin_n);
                    isYes_shipin = false;
                } else {
                    mBtn_shipin.setBackgroundResource(R.mipmap.shiping_start);
                    isYes_shipin = true;
                }
                break;
        }
    }

    /**
     * 判断Android系统版本是否 >= LOLLIPOP(API21)
     *
     * @return boolean
     */
    private boolean isLOLLIPOP() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        } else {
            return false;
        }
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