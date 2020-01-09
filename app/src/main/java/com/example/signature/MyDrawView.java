package com.example.signature;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyDrawView extends View {
    private Paint mPaint;
    Context mContext;
    private int colorText;
    //爻的高度
    private int yaoHeight ;
    private int yaoWidth ;
    public MyDrawView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MyTitle);
        colorText = ta.getColor(R.styleable.MyTitle_textColor,Color.BLACK);
        yaoHeight = ta.getDimensionPixelSize(R.styleable.MyTitle_yaoHeight,16);
        yaoWidth = ta.getDimensionPixelSize(R.styleable.MyTitle_yaoWidth,100);
        ta.recycle();
    }

    public MyDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

    }

//    public MyDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        this.mContext = context;
//    }
    public void SetColor(int nColor){
        this.colorText = nColor;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        /*画图*/
//        /*初始化画笔 文字*/
//        Paint pa = new Paint();
//        /*圆*/
//        int we=canvas.getWidth()/2;
//        int he=canvas.getHeight()/2;
//        canvas.drawColor(Color.WHITE);
//        pa.setColor(Color.RED);
//        canvas.drawCircle(we,he,200,pa);
//        pa.setColor(Color.WHITE);
//        canvas.drawCircle(we,he,150,pa);
//        pa.setColor(Color.YELLOW);
//        canvas.drawCircle(we,he,100,pa);
//        pa.setColor(Color.BLUE);
//        canvas.drawText("武晓瑞",we-17,he,pa);
//
//        //-------------------------------------------------------------------
//        // 创建画笔(正方形)
//        Paint p = new Paint();
//        //设置实心
//        p.setStyle(Paint.Style.FILL);
//        // 设置黑色
//        p.setColor(Color.BLACK);
//        // 设置画笔的锯齿效果
//        p.setAntiAlias(true);
//        //绘制
////        canvas.drawRect(50, 100, 300, 300, p);//(左、上、右、下)
//        canvas.drawRect(10,150,300,300,p);
//
////        canvas.drawText("我是你的小可爱",wi-40,hi,pa);
//        //-------------------------------------------------------------------
//        /*三角形*/
//        Paint p1 = new Paint();
//        p1.setColor(Color.BLACK);
//        //实例化路径
//        Path path = new Path();
//        path.moveTo(80, 200);// 此点为多边形的起点
//        path.lineTo(120, 250);
//        path.lineTo(80, 250);
//        path.close(); // 使这些点构成封闭的多边形
//        canvas.drawPath(path, p1);
//        //-------------------------------------------------------------------
//        /*扇形——SectorActivity*/
//        // 创建画笔
//        Paint p2 = new Paint();
//        p2.setColor(Color.RED);
//        RectF rectF = new RectF(60, 100, 200, 240);
//        canvas.drawArc(rectF, 200, 130, true, p2);
//        //-------------------------------------------------------------------
//        /*椭圆——OvalActivity*/
//        // 创建画笔
//        Paint p3 = new Paint();
//        p3.setColor(Color.GREEN);
//        RectF rectF1 = new RectF(60, 100, 200, 240);
//        rectF1.set(210,100,250,130);
//        canvas.drawOval(rectF1, p3);

        Paint p4 = new Paint();
        p4.setColor(colorText);
        //设置实心
        p4.setStyle(Paint.Style.FILL);
        // 设置画笔的锯齿效果
        p4.setAntiAlias(true);
        int top = 150;
        int left = 10;

        canvas.drawRect(left,top,left + yaoWidth,top + yaoHeight,p4);
    }
}
