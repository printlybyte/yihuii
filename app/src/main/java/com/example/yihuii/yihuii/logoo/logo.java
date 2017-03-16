package com.example.yihuii.yihuii.logoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.yihuii.yihuii.R;

public class logo extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        imageView= (ImageView) findViewById(R.id.image_logo);
        Animation animation=new AlphaAnimation(0.1f,1.0f);
        animation.setDuration(2000);
        imageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(logo.this,LogoinActivity.class);
                startActivity(intent);
                logo.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}