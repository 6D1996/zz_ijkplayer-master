package com.dou361.jjdxm_ijkplayer.autopark;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dou361.jjdxm_ijkplayer.R;

public class AutoParkingOut extends Activity {

    private ImageButton pause , back;
    private int flag = 0 ;
    private TextView outputfield,toptext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_parking_out);

        pause= (ImageButton)findViewById(R.id.Pause);
        outputfield = findViewById(R.id.Outputfield);
        toptext = findViewById(R.id.Toptext);
        toptext.setText("正在泊出车位");
        back =  findViewById(R.id.back2);
        back.setVisibility(View.INVISIBLE);

        outputfield.setMovementMethod(ScrollingMovementMethod.getInstance());
        pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                if(flag==0) {
                    toptext.setText("暂停中");
                    toptext.setTextColor(Color.RED);
                    flag = 1 ;
                    outputfield.setTextColor(Color.BLUE);
                    refreshLogView( "暂停中...\n" );
                }else{
                    pause.setBackgroundColor(Color.WHITE);
                    toptext.setText("正在泊出车位");
                    toptext.setTextColor(Color.BLACK);
                    flag = 0;
                    outputfield.setTextColor(Color.BLUE);
                    refreshLogView( "继续泊出...\n" );
                }
            }
        });

    }

    //更新输出消息框
    private void refreshLogView(String msg){
        outputfield.append(msg);
        int scrollAmount = outputfield.getLayout().getLineTop(outputfield.getLineCount())
                - outputfield.getHeight();
        if (scrollAmount > 0)
            outputfield.scrollTo(0, scrollAmount);
        else
            outputfield.scrollTo(0, 0);
    }
    
}