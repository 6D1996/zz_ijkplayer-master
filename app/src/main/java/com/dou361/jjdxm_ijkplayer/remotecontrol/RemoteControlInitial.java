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
import android.widget.Spinner;
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

//        CountDownTimer countDownTimer = new CountDownTimer(1000000, 200) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                wheelAngle=sImgView.getmDegree();
//                speedTextView = findViewById(R.id.speed);
//                speedTextView.setText(String.valueOf((int) wheelAngle));
//            }
//
//            @Override
//            public void onFinish() {
//            }
//        }.start();

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
                        final View speedSetView = LayoutInflater.from(RemoteControlInitial.this).inflate(R.layout.speed_setting_dialog,null,false);
                        final AlertDialog dialog = new AlertDialog.Builder(RemoteControlInitial.this).setView(speedSetView).create();
                        TextView Title = speedSetView.findViewById(R.id.title);
                        final Spinner speedSpinner=speedSetView.findViewById(R.id.speed_spinner);
                        Title.setText("请设置最大车速");
                        ImageButton Confirm = speedSetView.findViewById(R.id.confirm);
                        ImageButton cancel = speedSetView.findViewById(R.id.cancel);
                        Confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(RemoteControlInitial.this, RemoteControlEZPlayer.class);
                                intent.putExtra("speedList",  speedSpinner.getSelectedItemPosition()+"");
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });
                            cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast.makeText(RemoteControlInitial.this, "取消成功"+speedSpinner.getSelectedItemPosition(),Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        dialog.getWindow().setLayout(1000,800);
                    }
                 });
    }
}

