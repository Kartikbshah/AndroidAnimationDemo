package com.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    private TextView tagLine;
    private ImageView hairLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        hairLogo = (ImageView)findViewById(R.id.imageview);
        tagLine = (TextView)findViewById(R.id.tagLine);
        Animation anim3 = AnimationUtils.loadAnimation(this , R.anim.zoomin_with_rotate);
        Animation anim4 = AnimationUtils.loadAnimation(this , R.anim.fadein_text);
        findViewById(R.id.img1).startAnimation(AnimationUtils.loadAnimation(this, R.anim.right_bounce));
        findViewById(R.id.img3).startAnimation(AnimationUtils.loadAnimation(this , R.anim.left_bounce));
        findViewById(R.id.img2).startAnimation(anim3);
        findViewById(R.id.txtResultCaoch).startAnimation(anim4);
        final Animation anim5 = AnimationUtils.loadAnimation(this , R.anim.zoomin);

        anim3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tagLine.setVisibility(View.VISIBLE);
                hairLogo.setVisibility(View.VISIBLE);
                Animation anim4 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
                tagLine.startAnimation(anim4);
                hairLogo.startAnimation(anim5);
            }
        });

        anim5.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splash();
            }
        });
    }

    protected void splash()
    {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(MainActivity.this , ReminderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_right, R.anim.slide_right_out);
            }
        };
        timer.schedule(timerTask, 500);
    }

}
