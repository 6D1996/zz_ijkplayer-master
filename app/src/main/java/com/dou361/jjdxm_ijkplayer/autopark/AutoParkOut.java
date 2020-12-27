package com.dou361.jjdxm_ijkplayer.autopark;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dou361.jjdxm_ijkplayer.R;

import butterknife.OnClick;

public class AutoParkOut extends Activity implements View.OnClickListener{
    private ImageButton halfout,fullout;
    private TextView toptext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_park_out);

        halfout=findViewById(R.id.Halfout);
        fullout=findViewById(R.id.Fullout);
        halfout.setOnClickListener(this);
        fullout.setOnClickListener(this);

        toptext = findViewById(R.id.Toptext);
        toptext.setText("出车");


        //返回键
        findViewById(R.id.back2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                finish();
            }
        });

    }

    @OnClick({R.id.Halfout, R.id.Fullout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Halfout:
                /**半出车*/
                Intent intent1 = new Intent(AutoParkOut.this, HalfOut.class);
                startActivity(intent1);
                break;
            case R.id.Fullout:
                /**完全出车*/
                Intent intent2 = new Intent(AutoParkOut.this, FullOut.class);
                startActivity(intent2);

        }
    }

}