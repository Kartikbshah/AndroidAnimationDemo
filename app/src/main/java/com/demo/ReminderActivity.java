package com.demo;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ReminderActivity extends AppCompatActivity
{
    private Button btnOpenPicker;
    private Handler handler = new Handler();
    private String hour1 = "00";
    private String min1 = "00";
    private int hour = 0;
    private int min = 0;
    public static int ampm = 1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reminder);

        btnOpenPicker = (Button)findViewById(R.id.btnTimerPicker);


        btnOpenPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(ReminderActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                TextView btnDone = (TextView) dialog.findViewById(R.id.btnDone);
                TextView btnApply = (TextView) dialog.findViewById(R.id.btnApply);
                LinearLayout layout = (LinearLayout)dialog.findViewById(R.id.ll_dialog);

                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int width = displayMetrics.widthPixels;

                layout.getLayoutParams().width = width - 60;

                CircularSeekBar clock = (CircularSeekBar) dialog.findViewById(R.id.clock);
                final TextView txtTime1 = (TextView) dialog.findViewById(R.id.txtTime1);
                final TextView txtTime2 = (TextView) dialog.findViewById(R.id.txtTime2);

                txtTime1.setText(hour1 + ":" + min1);
                if(ampm == 1)
                    txtTime2.setText("AM");
                else
                    txtTime2.setText("PM");

                clock.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(CircularSeekBar seekBar) {

                    }

                    @Override
                    public void onStartTrackingTouch(CircularSeekBar seekBar) {

                    }

                    @Override
                    public void onProgressChanged(CircularSeekBar circularProgress,
                                                  final int progress1, boolean fromUser) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                hour = progress1 / 60;
                                min = progress1 % 60;

                                if (hour <= 9) {
                                    hour1 = "0" + String.valueOf(hour);
                                } else {
                                    hour1 = String.valueOf(hour);
                                }
                                if (min <= 9) {
                                    min1 = "0" + String.valueOf(min);
                                } else {
                                    min1 = String.valueOf(min);
                                }

                                txtTime1.setText(hour1 + ":" + min1);
                                if(ampm == 1)
                                    txtTime2.setText("AM");
                                else
                                    txtTime2.setText("PM");
                            }
                        });
                    }
                });

                btnApply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {

                        dialog.dismiss();
                    }
                });

                btnDone.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}
