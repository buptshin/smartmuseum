package com.example.smartmuseum.view.otherview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.smartmuseum.util.DensityUtil;

/**
 * 直线view，按比例显示
 */

public class ScaleView extends View {
    private int mWidth;
    private RectF mRect1;
    private RectF mRect2;
    private Paint mPaint;
    private int [] mColors = new int[]{Color.parseColor("#E9ECF3"),Color.parseColor("#842D29")};
    private static final int BG_COLOR = Color.parseColor("#FFFFFF");
    public ScaleView(Context context) {
        super(context);
        init(context);
    }

    public ScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        setWillNotDraw(false);
        int height = DensityUtil.dip2px(context, 10);
        mWidth = context.getResources().getDisplayMetrics().widthPixels- DensityUtil.dip2px(context, 40);
        mRect1 = new RectF(0,0,0, height);
        mRect2 = new RectF(0,0,0, height);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(BG_COLOR);
        mPaint.setColor(mColors[0]);
        canvas.drawRoundRect(mRect1,15,15,mPaint);
        mPaint.setColor(mColors[1]);
        canvas.drawRoundRect(mRect2,15,15,mPaint);
    }

    /**
     * 给数据赋值
     * @param scales
     */
    public void setScales(double[] scales){
        float scale1 = 0;
        for (int j = 0; j < scales.length - 0; j++) {
            scale1 += scales[j];
        }
        mRect1.right = (int) (mWidth*scale1);
        float scale2 = 0;
        for (int j = 0; j < scales.length - 1; j++) {
            scale2 += scales[j+1];
        }
        mRect2.right = (int) (mWidth*scale2);
        invalidate();
    }
}
