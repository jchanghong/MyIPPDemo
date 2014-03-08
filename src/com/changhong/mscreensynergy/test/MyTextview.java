package com.changhong.mscreensynergy.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by changhong on 14-3-7.
 */
public class MyTextview extends TextView {
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Lll.l("te ontoucha"+event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Lll.show(this);
        }
        boolean bo= super.onTouchEvent(event);
        Lll.l("return " + bo);
        return bo;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Lll.l("te onmeasure");
        Lll.l("withmeasure:"+widthMeasureSpec+"heimea:"+heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
        Lll.l("te onscroollchange");
        Lll.l("horiz:" + horiz + "vert:" + vert + "oldlho:" + oldHoriz + "oldvert:" + oldVert);
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
    }

    @Override
    public void scrollTo(int x, int y) {
        Lll.l("te scrollto x:"+x+"  y:"+y);

        super.scrollTo(x, y);
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
        Lll.l("te scrollby x:"+x+"  y:"+y);

    }

    public MyTextview(Context context) {
        super(context);
    }

    public MyTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
