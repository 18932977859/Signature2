package com.example.signature;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FirstSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    //SurfaceHolder
    private SurfaceHolder mHolder;
    //用于绘图的Canvas
    private Canvas mCanvas;
    //子线程标志位
    private boolean mIsDrawing;
    //画笔
    private Paint mPaint;
    //路径
    private Path mPath;


    public FirstSurfaceView(Context context) {
        super(context);
        initView();
    }

    public FirstSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FirstSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mHolder = getHolder();

        //添加回调
        mHolder.addCallback(this);

        mPath = new Path();
        //初始化画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);//设置填充样式
        mPaint.setAntiAlias(true);//抗锯齿功能
        mPaint.setStrokeJoin(Paint.Join.ROUND);//设置结合处效果取值1、Join.MITER（结合处为锐角）2、Join.Round(结合处为圆弧) 3、Join.BEVEL(结合处为直线)
        mPaint.setColor(Color.BLACK);//设置画笔颜色
        mPaint.setDither(true);//设置防抖动
        /**
         * setStrokeCap 设置线帽样式，取值有Cap.ROUND(圆形线帽)、Cap.SQUARE(方形线帽)、Paint.Cap.BUTT(无线帽)
         * 线冒，可以理解为一条线两个端点，设置线冒样式使线条两端不看起来不那么死板
         */
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //set the width of stroke.
        mPaint.setStrokeWidth(12);//设置画笔宽度 ，单位px

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (mIsDrawing) {
            try {
                //锁定画布并返回画布对象
                mCanvas = mHolder.lockCanvas();
                //接下去就是在画布上进行一下draw
                drawIt(mCanvas);

            } catch (Exception e) {
            } finally {
                //当画布内容不为空时，才post，避免出现黑屏的情况。
                if (mCanvas != null)
                    mHolder.unlockCanvasAndPost(mCanvas);
            }


            long end = System.currentTimeMillis();
            if (end - start < 100) {
                try {
                    Thread.sleep(100 - end + start);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void drawIt(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    /**
     * 清屏
     *
     * @return true
     */
    public boolean reDraw() {
        mPath.reset();
        return true;
    }

    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawIt(canvas);
        return bitmap;
    }
}