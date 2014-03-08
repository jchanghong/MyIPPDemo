package com.changhong.mscreensynergy.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by changhong on 14-3-7.
 */
public class MyLineLayout extends LinearLayout {
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Lll.l("lin ontouch" + event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Lll.show(this);
        }
        boolean bo = super.onTouchEvent(event);
        Lll.l("return " + bo);
        return bo;

    }

    @Override
    public void computeScroll() {
        Lll.l("lin computescroll");

        super.computeScroll();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Lll.l("lin onsrcollcha");
        Lll.l("l:" + l + "t:" + t + "oldl:" + oldl + "oldt:" + oldt);
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
        Lll.l("lin scrollby x:" + x + "y:" + y);

    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        Lll.l("lin scrollto x:" + x + "y:" + y);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Lll.l("lin onmeasure");
        Lll.l("withmeasure:" + widthMeasureSpec + "heimea:" + heightMeasureSpec);
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);

        Lll.l("lin meaurechind chi:" + child);
        Lll.l("paw" + parentWidthMeasureSpec + "pah" + parentHeightMeasureSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Lll.l("lin oninter ev:");
        Lll.l("" + ev);
        return super.onInterceptTouchEvent(ev);
    }

    public MyLineLayout(Context context) {
        super(context);
    }

    public MyLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLineLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
