package com.dou361.jjdxm_ijkplayer.autopark;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.jjdxm_ijkplayer.R;
import com.dou361.jjdxm_ijkplayer.remotecontrol.RemoteControl;
import com.dou361.jjdxm_ijkplayer.remotecontrol.RemoteControlInitial;

import butterknife.OnClick;

public class FullOut extends Activity implements View.OnClickListener{
    private Context mContext;
    private ImageButton left,right;
    private TextView toptext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_out);
        this.mContext = this;

        left=findViewById(R.id.Left);
        right=findViewById(R.id.Right);
        left.setOnClickListener(this);
        right.setOnClickListener(this);

        toptext = findViewById(R.id.Toptext);
        toptext.setText("完全出车");

        //返回键
        findViewById(R.id.back2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                finish();
            }
        });

    }

    @OnClick({R.id.Left, R.id.Right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Left:
            case R.id.Right:
                /**完全出车*/
                /**半出车*/
                //对话框
                View my_view = LayoutInflater.from(FullOut.this).inflate(R.layout.my_dialog,null,false);
                final AlertDialog dialog = new AlertDialog.Builder(FullOut.this).setView(my_view).create();
                TextView Title = my_view.findViewById(R.id.title);
                TextView Context = my_view.findViewById(R.id.content);
                Title.setText("确认出车");
                Context.setText("自动出车是由云端计算机控制车辆自动泊出车位，该功能有一定风险，一切后果将由车主承担泊出车位后，车主应尽快接受车辆以避免影响交通");
                ImageButton Confirm = my_view.findViewById(R.id.confirm);
                ImageButton cancel = my_view.findViewById(R.id.cancel);
                Confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(FullOut.this,AutoParkingOut.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FullOut.this, "取消成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(1000,750);
                break;
        }
    }
}