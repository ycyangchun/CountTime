package com.example.yc.counttime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity2 extends AppCompatActivity {
    @BindView(R.id.count_tv)
    TextView count_tv;
    @BindView(R.id.start_bt)
    Button start_bt;
    @BindView(R.id.end_bt)
    Button end_bt;

    ////////////第一种 , 第二种 ，/////////////////
    private int recLen = 11;
    Timer timer = new Timer();
    ////////////第一种 , 第二种 ，/////////////////




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        start_bt.requestFocus();
//        start_bt.setNextFocusRightId(R.id.end_bt);
    }
    // 第一种

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen -- ;
                    if(recLen < 0 ){
                        count_tv.setText(""+recLen);
                        timer.cancel();
                    }
                }
            });
        }
    };

    @OnClick(R.id.start_bt)
    public void start(View v){
        System.out.println("==========start=========>");
        if(timer != null ){
            timer.schedule(timerTask , 1000 , 1000);
        }
    }

    @OnClick(R.id.end_bt)
    public void end(View v){
        System.out.println("==========end=========>");
        if(timer != null ){
            timer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
