package com.dou361.jjdxm_ijkplayer.autopark;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dou361.jjdxm_ijkplayer.R;
import com.dou361.jjdxm_ijkplayer.remotecontrol.RemoteControl;
import com.dou361.jjdxm_ijkplayer.remotecontrol.RemoteControlInitial;

import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.tencent.iot.hub.device.java.core.mqtt.TXAlarmPingSender.TAG;

public class AutoPark extends Activity implements View.OnClickListener{


    public String hostURL="http://vehicleroadcloud.faw.cn:60443/backend/appBackend/";
    private AutoParkingRequest autoParkingRequest;
    private AutoParkingReply autoParkingReply;
    ImageButton parkout,parkin;
    private Context mContext;
    private TextView toptext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autopark);
        this.mContext = this;

        parkin=findViewById(R.id.In);
        parkout=findViewById(R.id.Out);
        parkin.setOnClickListener(this);
        parkout.setOnClickListener(this);

        toptext = findViewById(R.id.Toptext);
        toptext.setText("自动泊车");

        //返回键
        findViewById(R.id.back2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                finish();
            }
        });

    }

    @Override
    @OnClick({R.id.Out, R.id.In})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Out:
                /**出车*/
                Intent intent1 = new Intent(AutoPark.this, AutoParkOut.class);
                startActivity(intent1);
                break;
            case R.id.In:
                /**泊车*/
                Log.d(TAG, "onClick: setParkingType1");

                //对话框
                View my_view = LayoutInflater.from(AutoPark.this).inflate(R.layout.my_dialog,null,false);
                final AlertDialog dialog = new AlertDialog.Builder(AutoPark.this).setView(my_view).create();
                TextView Title = my_view.findViewById(R.id.title);
                TextView Context = my_view.findViewById(R.id.content);
                Title.setText("确认泊车");
                Context.setText("自动泊车是由云端计算机控制车辆自动泊入车位，该功能有一定风险，一切后果将由车主承担");
                ImageButton Confirm = my_view.findViewById(R.id.confirm);
                ImageButton cancel = my_view.findViewById(R.id.cancel);
                Confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestParkIn();
                        Intent intent=new Intent(AutoPark.this, AutoParkingIn.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AutoPark.this, "取消成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(1000,650);
            default:
                break;
        }

    }
    public void requestParkIn(){
        autoParkingRequest=new AutoParkingRequest();
        autoParkingReply=new AutoParkingReply();
        String reply= autoParkingRequest.AutoParkMethod(1,0,0,"");
        autoParkingReply=JSON.parseObject(reply,AutoParkingReply.class);
        Log.d(TAG, "onClick: 函数返回结果"+reply);
    }
}

