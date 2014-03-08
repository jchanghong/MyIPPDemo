package com.changhong.mscreensynergy.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by changhong on 14-3-7.
 */
public class MyScrollView extends ScrollView {
    @Override
    public void computeScroll() {
        Lll.l("scr comtscoll");
        super.computeScroll();
    }
    @Override
    public void scrollTo(int x, int y) {
        Lll.l("scr scrollto x:"+x+"  y:"+y);
        super.scrollTo(x, y);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Lll.l("scr oninter ev:");
        Lll.l(ev+"");
        boolean bo = super.onInterceptTouchEvent(ev);
        Lll.l("retun " + bo);
        return bo;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Lll.l("scr onscollchange ");
        Lll.l("l:" + l + "t:" + t + "oldl:" + oldl + "oldt:" + oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Lll.l("scr onmearsure");
        Lll.l("withmeasure:"+widthMeasureSpec+"heimea:"+heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        Lll.l("scr measurechina chind:"+child);
        Lll.l("paw"+parentWidthMeasureSpec+"pah"+parentHeightMeasureSpec);

        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        Lll.l("scr ontouch x:"+ev.getX()+"rawx:"+ev.getRawX());
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            Lll.show(this);
        }
        boolean bo= super.onTouchEvent(ev);
        Lll.l("return " + bo);
        return bo;
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
