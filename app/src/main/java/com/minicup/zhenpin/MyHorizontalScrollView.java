package com.minicup.zhenpin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Austin on 2017-05-18.
 */

public class MyHorizontalScrollView extends HorizontalScrollView {
    private List<ZhenPinView> mViewPos = new ArrayList<>();
    private Paint backgroundPaint;
    private LinearLayout mContainer;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private float startX;
    private int screenWidth;
    private int pageIndex = 0;
    private int itemWidth;
    private int totalWidth;

    public MyHorizontalScrollView(Context context) {
        this(context, null);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {
        this.context = context;
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(Color.parseColor("#f1f1f1"));
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        View childAt = getChildAt(0);
        totalWidth = childAt.getMeasuredWidth();
        itemWidth = childAt.getMeasuredWidth() / mViewPos.size();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(backgroundPaint);
    }

    public void addZhenPin(LinearLayout mContainer, List<ZhenPinBean> zhenPin) {
        for (int i = 0; i < zhenPin.size(); i++) {
            final ZhenPinView zhenPinView = new ZhenPinView(context, onItemClickListener, i);
            mContainer.addView(zhenPinView);
            mViewPos.add(zhenPinView);
        }
    }

    private int mBaseScrollX;
    private int mScrollX = 200;//滑动多长距离翻页

    private int getBaseScrollX() {
        return getScrollX() - mBaseScrollX;
    }

    private void baseSmoothScrollTo(int x) {
        smoothScrollTo(x + mBaseScrollX, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                int scrollX = getBaseScrollX();
                if (scrollX > mScrollX) {
                    if(mBaseScrollX<0){
                        mBaseScrollX = 0;
                    }
                    if(mBaseScrollX<=0) {
                        baseSmoothScrollTo(getOffSet());
                        mBaseScrollX += getOffSet();
                    }else{
                        baseSmoothScrollTo(itemWidth);
                        mBaseScrollX += itemWidth;
                    }
                }
                else if (scrollX > 0) {
                    baseSmoothScrollTo(0);
                }
                else if(scrollX > -mScrollX) {
                    baseSmoothScrollTo(0);
                }
                else {
                    if(mBaseScrollX>totalWidth-itemWidth){
                        mBaseScrollX = totalWidth-itemWidth;
                    }
                    if (mBaseScrollX >= totalWidth - itemWidth) {
                        baseSmoothScrollTo(-getOffSet());
                        mBaseScrollX -= getOffSet();
                    }else{
                        baseSmoothScrollTo(-itemWidth);
                        mBaseScrollX -= itemWidth;
                    }
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    private int getOffSet() {
        return itemWidth - (screenWidth - itemWidth) / 2;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, int index);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
