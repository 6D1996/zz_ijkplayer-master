package com.dou361.jjdxm_ijkplayer.remotecontrol;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.RotateAnimation;

import com.dou361.jjdxm_ijkplayer.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * Fixed on 2020/9/17,which is a happy day!
 */
public class ScalableImageView extends androidx.appcompat.widget.AppCompatImageView{

    private static final double MAX_DEGREE = 600.0;//方向盘最大转角
    private Matrix matrix;
    private Matrix cacheMatrix;  //缓存的matrix ，同时记录上一次滑动的位置
    private float mPointDistinct = 1f;
    private  double mDegree=0.0;// 旋转的ScalableImageView角度
    double radius0 =0.0;


    private Bitmap bitmap;

    public  Double getmDegree() {
        return mDegree;

    }

    List<Double> angleList= new ArrayList<>();//For story the angle data
    List<PointF> pointFList = new ArrayList<>();
    PointF point2store=new PointF();
    int circle =0;
    double degree0 = 0.0;//按下角度
    double degree = 0.0;//转动角度
    double radiusLast;
    double degreeLast;
    double radiusNow;
    double degreeNow;
    double delta_degree;

    enum Mode {
        NONE, DOWN, MOVE
    }

    private Mode mode; //当前mode
    private Context mContext;

    private PointF mStart = new PointF();

    public ScalableImageView(Context context) {
        this(context, null);
    }

    public ScalableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScalableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        circle = 0;
        degree = 0.0;
        degree0 = 0.0;
        mDegree = 0.0;
        radius0 = 0.0;
        matrix = new Matrix();
        cacheMatrix = new Matrix();
        pointFList.clear();
        mode = Mode.NONE;
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.steering_wheel);  //避免OOM
        setImageBitmap(bitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                cacheMatrix.set(matrix); //先拷贝一份到缓存
                float delta_x0 = (event.getX()- bitmap.getWidth()/2);
                float delta_y0 = (event.getY() - bitmap.getHeight()/ 2);
                radius0 = Math.atan2(delta_y0,delta_x0);
                degree0= (Double) Math.toDegrees(radius0);
                Log.d(TAG, "onTouchEvent: 初始角度"+degree0);
                mode = Mode.DOWN;
                mStart.set(delta_x0, delta_y0);
                pointFList.add(mStart);
                break;

            case MotionEvent.ACTION_MOVE:
                //单点触控的时候
                if (mode == Mode.DOWN) {
                    matrix.set(cacheMatrix);
                    float delta_x = (event.getX()- bitmap.getWidth()/2);
                    float delta_y = (event.getY() - bitmap.getHeight()/ 2);

                    pointFList.add(new PointF(delta_x,delta_y));
                    if(pointFList.size()>5){
                        for(int i=0;i<pointFList.size()-5;i++){
                            pointFList.remove(i);
                        }
                    }
                    if(pointFList.size()>=2){
                        for(int i=0;i<pointFList.size();i++){
                            Log.d(TAG, "onTouchEvent: \n第"+i+"个点："+pointFList.get(i).x+","+pointFList.get(i).y+"\n");
                        }
                         radiusLast = Math.atan2(pointFList.get(pointFList.size()-2).y,pointFList.get(pointFList.size()-2).x);
                         degreeLast = Math.toDegrees(radiusLast);
                         radiusNow = Math.atan2(pointFList.get(pointFList.size()-1).y,pointFList.get(pointFList.size()-1).x);
                         degreeNow = Math.toDegrees(radiusNow);
                         delta_degree = degreeNow - degreeLast;
                        if(delta_degree<=0&&Math.abs(delta_degree)<180){
                            mDegree=mDegree-Math.abs(delta_degree);
                        }else if(delta_degree<=0&&Math.abs(delta_degree)>=180){
                            mDegree= mDegree+360-Math.abs(delta_degree);
                        }else if(delta_degree>0&&Math.abs(delta_degree)<180){
                            mDegree= mDegree+Math.abs(delta_degree);
                        }else if(delta_degree>0&&Math.abs(delta_degree)>=180){
                            mDegree= mDegree-360+Math.abs(delta_degree);
                        }
                        Log.d(TAG, "onTouchEvent: 現在角度"+mDegree);
                    }
                    if(mDegree>MAX_DEGREE) {mDegree = MAX_DEGREE;}
                    if(mDegree<-MAX_DEGREE){mDegree =-MAX_DEGREE;}

                    /*double radius = Math.atan2(delta_y,delta_x);
                    Log.d(TAG, "onTouchEvent: pass弧度"+(radius-radius0));
                    //向量叉乘判斷正負
                    if(event.getY()-mStart.y>0){
                        Log.d(TAG, "onTouchEvent: 順時針"+Math.atan2(1,-1));
//                        degree=degree+360;
                    }else {
                        Log.d(TAG, "onTouchEvent: 逆時針"+Math.atan2(-1,1));
                    }

                    Log.d(TAG, "onTouchEvent: 現在弧度"+radius);
                    Log.d(TAG, "onTouchEvent: 現在角度"+Math.toDegrees(radius));
                    degree= (Double) Math.toDegrees(radius)-degree0;
                    Log.d(TAG, "onTouchEvent: 旋轉角度"+degree);
                    angleList.add(degree);
                    if(angleList.size()>5){
                        for(int i=0;i<angleList.size()-5;i++){
                            angleList.remove(i);
                        }
                    }
                    if(angleList.size()>2) {
                        //
                        Log.d(TAG, "onTouchEvent: \n當前角度"+angleList.get(angleList.size() - 1)+"上次角度"+angleList.get(angleList.size() - 2));
                        if (angleList.get(angleList.size() - 1) - angleList.get(angleList.size() - 2) > 300)
                        {
//                            circle--;
                        Log.d(TAG, "onTouchEvent: 減了一圈"+circle);}
                        if (angleList.get(angleList.size() - 1) - angleList.get(angleList.size() - 2) < -300)
                        {
//                            circle++;
                        Log.d(TAG, "onTouchEvent: 加了一圈"+circle);}
                        degree = degree + 360 * circle;

                        if(degree>MAX_DEGREE) {degree = MAX_DEGREE;}
                        if(degree<-MAX_DEGREE){degree =-MAX_DEGREE;}
                    }
                    Log.d("赋值Degree",mDegree+"");
                    mDegree = degree;*/

//                    final RotateAnimation rotateAnimation = new RotateAnimation(0f, (float)degree, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
                    matrix.postRotate((float) mDegree,bitmap.getWidth()/2, bitmap.getHeight()/ 2);
                }
                break;
            case MotionEvent.ACTION_UP:
                init();
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                mode = Mode.NONE;
                break;
        }
        setImageMatrix(matrix);
        return true;
    }

}