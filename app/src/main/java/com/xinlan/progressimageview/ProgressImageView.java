package com.xinlan.progressimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.widget.ImageViewCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by panyi on 18-5-27.
 * 外部带进度条的ImageView
 *
 */
public class ProgressImageView extends android.support.v7.widget.AppCompatImageView {
    private int mRadius = 20;
    private Paint mBgPaint = new Paint();
    private Paint mPaint = new Paint();
    private RectF mProgressRect = new RectF();

    private boolean mShowProgress = true;
    private boolean mShowBottom = true;

    private int mProgress = 0;

    public ProgressImageView(Context context) {
        super(context);
        initView(context);
    }

    public ProgressImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ProgressImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void setProgress(int progress){
        if(progress < 0 || progress > 100)
            return;
        mProgress = progress;
        invalidate();
    }

//    public ProgressImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        initView(context);
//    }

    private void initView(Context context){
        mBgPaint.setColor(Color.GRAY);
        mBgPaint.setStrokeJoin(Paint.Join.ROUND);
        mBgPaint.setStrokeCap(Paint.Cap.ROUND); //设置圆角
        mBgPaint.setAntiAlias(true);//反锯齿
        mBgPaint.setStyle(Paint.Style.STROKE);//设置空心
        mBgPaint.setStrokeWidth(mRadius);//圆圈宽度设置

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND); //设置圆角
        mPaint.setAntiAlias(true);//反锯齿
        mPaint.setStyle(Paint.Style.STROKE);//设置空心
        mPaint.setStrokeWidth(mRadius);//圆圈宽度设置
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.rotate(-90 , getWidth() >> 1 , getHeight() >> 1);//坐标系旋转
        if(!mShowBottom)
            return;

        int halfRadius = mRadius >> 1;
        mProgressRect.set( halfRadius , halfRadius, getWidth() - halfRadius, getHeight() - halfRadius);
        canvas.drawArc(mProgressRect , 0, 360, false , mBgPaint);

        if(!mShowProgress)
            return;

        int angle = (int)(((float)mProgress / 100) * 360);
        canvas.drawArc(mProgressRect , 0, angle, false , mPaint);
    }
}//end class
