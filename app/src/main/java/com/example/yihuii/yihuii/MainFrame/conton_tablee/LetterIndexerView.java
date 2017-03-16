package com.example.yihuii.yihuii.MainFrame.conton_tablee;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/19.
 */

public class LetterIndexerView extends View {
    private String[] arrLetters = new String[] { "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z", "#" };
    private int choosedPosition = -1;//用来保存当前被选中下标
    private TextView textView_dialog;//弹出的
    private OnLetterClickedListener listener = null;

    public LetterIndexerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    //初始化弹窗的TextView
    public void initTextView(TextView textView_dialog) {
        this.textView_dialog = textView_dialog;
    }
    /**
     * d
     * */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        // 获取当前控件的宽度和高度
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        // 获取每个文字所占据空间的高度
        int singleTextHeight = viewHeight / arrLetters.length;

        for (int i = 0; i < arrLetters.length; i++) {
            if (choosedPosition == i) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.BLACK);
            }
            int textWidth = (int) paint.measureText(arrLetters[i]);
            // 参数1：画的文字内容；参数2：内容的起始x坐标；参数3：内容的起始y坐标；参数4：画笔；
           // canvas.drawText(arrLetters[i], (viewWidth - textWidth)/2 ,singleTextHeight * (i + 1), paint);
            canvas.drawText(arrLetters[i], (viewWidth - textWidth)/2 ,singleTextHeight * (i + 1), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        // 手指点击处的y轴的坐标
        float y = event.getY();
        // 手指点击处所对应的首字母的索引下标
        int position = (int) (y / (getHeight() / arrLetters.length));

        switch (event.getAction()) {
            // 手指抬起
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.TRANSPARENT);
                if (textView_dialog != null) {
                    textView_dialog.setVisibility(View.GONE);
                }
                // choosedPosition = -1;
                invalidate();
                break;
            // 手指按下及滑动
            default:
                setBackgroundColor(Color.parseColor("#CCCCCC"));
                if (position >= 0 && position < arrLetters.length) {
                    // 触发自定义的监听器
                    if (listener != null) {
                        listener.onLetterClicked(arrLetters[position]);
                    }
                    // 让屏幕中间的textView显示并加载首字母
                    if (textView_dialog != null) {
                        textView_dialog.setVisibility(View.VISIBLE);
                        textView_dialog.setText(arrLetters[position]);
                    }
                    choosedPosition = position;

                    // 重新绘制view
                    invalidate();
                }
                break;
        }
        return true;
    }

    // 设置内部监听器接口
    public interface OnLetterClickedListener {
        public void onLetterClicked(String letter);
    }

    // 设置调用该内部接口对象的方法
    public void setOnLetterClickedListener(OnLetterClickedListener listener) {
        this.listener = listener;
    }
}