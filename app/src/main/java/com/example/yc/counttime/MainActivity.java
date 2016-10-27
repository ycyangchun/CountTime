package com.example.yc.counttime;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView count_tv;
    Button start_bt;
    Button end_bt;

    ////////////第一种 , 第二种 ，/////////////////
    private int recLen = 11;
    Timer timer = new Timer();
    ////////////第一种 , 第二种 ，/////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count_tv = (TextView) this.findViewById(R.id.count_tv);
        start_bt = (Button) this.findViewById(R.id.start_bt);
        end_bt = (Button) this.findViewById(R.id.end_bt);
        end_bt.setOnClickListener(this);
        start_bt.setOnClickListener(this);

        start_bt.requestFocus();
        start_bt.setNextFocusRightId(R.id.end_bt);

        //第一种  Timer与TimerTask（Java实现）
//        timer.schedule(timerTask, 1000, 1000);

        //第二种  TimerTask与Handler（不用Timer的改进型）
//        timer.schedule(timerTask2, 1000,1000);

        //第三种  Handler与Message（不用TimerTask）
//        Message message3 = handler3.obtainMessage(3);
//        handler3.sendMessageDelayed(message3, 1000);

        //第四种  Handler与Thread（不占用UI线程）
//        new Thread(new MyThread()).start();         // start thread

        //Handler与Runnable（最简单型）
//        handler5.postDelayed(runnable, 1000);

        //CountDownTimer
        timer2.start();

    }
    ////start////// 第一种//////////

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen--;
                    count_tv.setText("==> " + recLen);
                    if (recLen < 1) {
                        timer.cancel();
                    }
                }
            });
        }
    };
    ////end////// 第一种//////////

    ////start////// 第二种//////////

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                count_tv.setText("==> " + recLen);
                if (recLen < 1) {
                    timer.cancel();
                }
            }
        }
    };

    TimerTask timerTask2 = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen--;
                    Message message = Message.obtain();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            });
        }
    };


    ////end////// 第二种//////////

    ////start////// 第三种//////////
    Handler handler3 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 3:
                    recLen--;
                    count_tv.setText("" + recLen);

                    if(recLen > 0){
                        Message message = handler3.obtainMessage(3);
                        handler3.sendMessageDelayed(message, 1000);      // send message
                    }
            }

            super.handleMessage(msg);
        }
    };
    ////end////// 第三种//////////

    ////start////// 第四种//////////
    final Handler handler4 = new Handler(){          // handle
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    recLen++;
                    count_tv.setText("" + recLen);
            }
            super.handleMessage(msg);
        }
    };

    public class MyThread implements Runnable {      // thread
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);     // sleep 1000ms
                    Message message = new Message();
                    message.what = 1;
                    handler4.sendMessage(message);
                } catch (Exception e) {
                }
            }
        }
    }
    ////end//////// 第四种//////////
    ////start////// 第五种//////////
    Handler handler5 = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            recLen++;
            count_tv.setText("" + recLen);
            handler5.postDelayed(this, 1000);
        }
    };
    ////end//////// 第五种//////////


    private CountDownTimer timer2 = new CountDownTimer(1000 * 60 * 10, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {

            count_tv.setText((millisUntilFinished / 1000) + "    "+ format(millisUntilFinished));
        }

        @Override
        public void onFinish() {
            count_tv.setEnabled(true);
            count_tv.setText("获取验证码");
        }
    };

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.start_bt){
            System.out.println("==========start=========>");

        }else if(v.getId() == R.id.end_bt){
            System.out.println("==========end=========>");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static String format(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        String s = sdf.format(new Date(time));
        return s;
    }
}
