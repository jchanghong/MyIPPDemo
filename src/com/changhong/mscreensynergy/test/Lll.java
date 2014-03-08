package com.changhong.mscreensynergy.test;

import android.util.Log;
import android.view.View;

/**
 * Created by changhong on 14-3-7.
 */
public class Lll {
    public static final void l(String str) {
        Log.d("jchanghong", str);
    }

    public static final void show(View view) {
        l(""+view+"width:"+view.getWidth()+"heigh："+view.getHeight());
        l("meawidth"+view.getMeasuredHeight()+"meaheigh"+view.getMeasuredHeight());
        l("ScrollX"+view.getScrollX()+"ScrollY"+view.getScrollY());
        l("ScraleX"+view.getScaleX()+"ScraleY"+view.getScaleY());

    }
}
