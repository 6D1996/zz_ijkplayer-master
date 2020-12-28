package com.dou361.jjdxm_ijkplayer.remotecontrol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.jjdxm_ijkplayer.R;

public class RemoteControlInitial extends Activity {

    private ImageButton StartRemove;
    private ImageButton imageButton_forward,imageButton_backward;
    private TextView speedTextView ;
    private double wheelAngle=0.0;
    ScalableImageView sImgView ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //一直连接Iothub

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_controlinitial);
        sImgView = findViewById(R.id.steering_wheel);

        CountDownTimer countDownTimer = new CountDownTimer(1000000, 200) {
            @Override
            public void onTick(long millisUntilFinished) {
                wheelAngle=sImgView.getmDegree();
                speedTextView = findViewById(R.id.speed);
                speedTextView.setText(String.valueOf((int) wheelAngle));
            }

            @Override
            public void onFinish() {
            }
        }.start();

        //返回键
        findViewById(R.id.back2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                finish();
            }
        });

        StartRemove= (ImageButton)findViewById(R.id.startRemove);
        StartRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                        //IotHub连接成功弹出对话框，否则提示当前连接车辆失败
                        //对话框
                        View my_view = LayoutInflater.from(RemoteControlInitial.this).inflate(R.layout.my_dialog,null,false);
                        final AlertDialog dialog = new AlertDialog.Builder(RemoteControlInitial.this).setView(my_view).create();
                        TextView Title = my_view.findViewById(R.id.title);
                        TextView Context = my_view.findViewById(R.id.content);
                        Title.setText("开始挪车");
                        Context.setText("挪车功能提供用户通过手机遥控移动车辆的功能请问您要开始挪车吗？");
                        ImageButton Confirm = my_view.findViewById(R.id.confirm);
                        ImageButton cancel = my_view.findViewById(R.id.cancel);
                        Confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(RemoteControlInitial.this, RemoteControl.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });
                            cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(RemoteControlInitial.this, "取消成功",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        dialog.getWindow().setLayout(1000,600);
                    }
                 });
    }
}

