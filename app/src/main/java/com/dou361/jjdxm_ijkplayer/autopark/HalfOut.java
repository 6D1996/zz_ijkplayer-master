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

import butterknife.OnClick;

public class HalfOut extends Activity implements View.OnClickListener{

    private Context mContext;
    private ImageButton leftforward,forwardahead,rightforward;
    private TextView toptext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_half_out);
        this.mContext = this;

        leftforward=findViewById(R.id.Leftforward);
        forwardahead=findViewById(R.id.Forwardahead);
        rightforward=findViewById(R.id.Rightforward);
        leftforward.setOnClickListener(this);
        forwardahead.setOnClickListener(this);
        rightforward.setOnClickListener(this);

        toptext = findViewById(R.id.Toptext);
        toptext.setText("半出车");

        //返回键
        findViewById(R.id.back2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                finish();
            }
        });

    }

    @OnClick({R.id.Leftforward, R.id.Forwardahead,R.id.Rightforward})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Leftforward:
                /**半出车*/
            case R.id.Forwardahead:
                /**完全出车*/
            case R.id.Rightforward:
                /**完全出车*/
                //对话框
                View my_view = LayoutInflater.from(HalfOut.this).inflate(R.layout.my_dialog,null,false);
                final AlertDialog dialog = new AlertDialog.Builder(HalfOut.this).setView(my_view).create();
                TextView Title = my_view.findViewById(R.id.title);
                TextView Context = my_view.findViewById(R.id.content);
                Title.setText("确认出车");
                Context.setText("自动出车是由云端计算机控制车辆自动泊出车位，该功能有一定风险，一切后果将由车主承担泊出车位后，车主应尽快接受车辆以避免影响交通");
                ImageButton Confirm = my_view.findViewById(R.id.confirm);
                ImageButton cancel = my_view.findViewById(R.id.cancel);
                Confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(HalfOut.this,AutoParkingOut.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HalfOut.this, "取消成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(1000,720);
                break;
        }
    }
}