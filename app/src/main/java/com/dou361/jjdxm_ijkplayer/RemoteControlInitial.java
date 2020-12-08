package com.dou361.jjdxm_ijkplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RemoteControlInitial extends Activity {

    private Context mContext;
    private Button StartRemove;
    private ImageButton imageButton_forward,imageButton_backward;
    private TextView speedTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //一直连接Iothub

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_controlinitial);
        this.mContext = this;
        speedTextView=findViewById(R.id.speed);
        speedTextView.setText("Hello");



//        CompentOnTouch compentOnTouch = new CompentOnTouch();
//
//        imageButton_forward=findViewById(R.id.forward);
//        imageButton_forward.setOnTouchListener(compentOnTouch);
//
//        imageButton_backward=findViewById(R.id.backward);
//        imageButton_backward.setOnTouchListener(compentOnTouch);

        StartRemove= (Button)findViewById(R.id.startRemove);
        StartRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

                        //IotHub连接成功弹出对话框，否则提示当前连接车辆失败
                        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                        builder.setIcon(R.drawable.shangbackground);
                        builder.setTitle("开始挪车");//设置对话框的标题
                        builder.setMessage("挪车功能提供用户通过手机遥控移动车辆的功能请问您要开始挪车吗？");//设置对话框的内容
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //这个是设置确定按钮
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent=new Intent(RemoteControlInitial.this,RemoteControl.class);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //取消按钮

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(RemoteControlInitial.this, "取消成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog b=builder.create();
                        b.show();
                    }
                 });
    }

//    class CompentOnTouch implements View.OnTouchListener {
//
//        public boolean isOnLongClick=false;
//        int i = 0;
//
//
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            switch (view.getId()) {
//// 这是btnMius下的一个层，为了增强易点击性
//                case R.id.forward:
//                    onTouchChange("mius", motionEvent.getAction());
//                    break;
//// 这里也写，是为了增强易点击性
//                case R.id.backward:
//                    onTouchChange("plus", motionEvent.getAction());
//                    break;
//            }
//            return true;
//        }
//
//
//
//        private void onTouchChange(String methodName, int eventAction) {
//// 按下松开分别对应启动停止减线程方法
//            if ("mius".equals(methodName)) {
//                MiusThread miusThread = null;
//                if (eventAction == MotionEvent.ACTION_DOWN) {
//                    miusThread = new MiusThread();
//                    isOnLongClick = true;
//                    miusThread.start();
//                } else if (eventAction == MotionEvent.ACTION_UP) {
//                    if (miusThread != null) {
//                        isOnLongClick = false;
//                    }
//                } else if (eventAction == MotionEvent.ACTION_MOVE) {
//                    if (miusThread != null) {
//                        isOnLongClick = true;
//                    }
//                }
//            }
//// 按下松开分别对应启动停止加线程方法
//            else if ("plus".equals(methodName)) {
//                PlusThread plusThread = null;
//                if (eventAction == MotionEvent.ACTION_DOWN) {
//                    plusThread = new PlusThread();
//                    isOnLongClick = true;
//                    plusThread.start();
//                } else if (eventAction == MotionEvent.ACTION_UP) {
//                    if (plusThread != null) {
//                        isOnLongClick = false;
//                    }
//                } else if (eventAction == MotionEvent.ACTION_MOVE) {
//                    if (plusThread != null) {
//                        isOnLongClick = true;
//                    }
//                }
//            }
//        }
//
//
//        // 减操作
//        class MiusThread extends Thread {
//            @Override
//            public void run() {
//                while (isOnLongClick) {
//                    try {
//                        Thread.sleep(200);
//                        myHandler.sendEmptyMessage(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    super.run();
//                }
//            }
//        }
//
//
//        // 加操作
//        class PlusThread extends Thread {
//            @Override
//            public void run() {
//                while (isOnLongClick) {
//                    try {
//                        Thread.sleep(200);
//                        myHandler.sendEmptyMessage(2);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    super.run();
//                }
//            }
//        }
//
//        Handler myHandler = new Handler() {
//            public void handleMessage(Message msg) {
//
//                switch (msg.what) {
//                    case 1:
//                        speedTextView.setText(i);
//                        Log.d("TAG","Forward:"+i++);
//                        break;
//                    case 2:
//
//                        Log.d("TAG","Backward:"+i--);
//                        break;
//                }
//            };
//        };
//
//
//    }
}

