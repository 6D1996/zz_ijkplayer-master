package com.dou361.jjdxm_ijkplayer.remotecontrol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.jjdxm_ijkplayer.R;

public class RemoteControlInitial extends Activity {

    private Context mContext;
    private ImageButton StartRemove;
    private ImageButton imageButton_forward,imageButton_backward;
    private TextView speedTextView;
    private double wheelAngle=0.0;
    ScalableImageView sImgView ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //一直连接Iothub

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_controlinitial);
        this.mContext = this;

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
                        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                        builder.setTitle("开始挪车");//设置对话框的标题
                        builder.setMessage("挪车功能提供用户通过手机遥控移动车辆的功能请问您要开始挪车吗？");//设置对话框的内容
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //这个是设置确定按钮
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent=new Intent(RemoteControlInitial.this, RemoteControl.class);
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
}

