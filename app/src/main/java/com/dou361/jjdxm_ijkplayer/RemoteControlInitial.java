package com.dou361.jjdxm_ijkplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class RemoteControlInitial extends Activity {

    private Context mContext;
    private Button StartRemove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_controlinitial);
        this.mContext = this;

        StartRemove= (Button)findViewById(R.id.startRemove);
        StartRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

                        //对话框
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
}

