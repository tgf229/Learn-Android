package com.tgf.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by 涂高峰 on 2017/6/20.
 */
public class DragView extends View {
    private static final String TAG = "DragView";
    private int lastX, lastY;
    private Scroller scroller;

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.i(TAG, "y=: "+y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                //方法一
//                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                //方法二
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);
                //方法三
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft()+offsetX;
//                layoutParams.topMargin = getTop()+offsetY;
//                setLayoutParams(layoutParams);
                //方法四
                ((View)getParent()).scrollBy(-offsetX,-offsetY);

                break;
            case MotionEvent.ACTION_UP:
                View view =  (View)getParent();
                Log.i(TAG, "getScrollX: "+view.getScrollX());
                Log.i(TAG, "getScrollY: "+view.getScrollY());
                scroller.startScroll(view.getScrollX(),view.getScrollY(),-view.getScrollX(),-view.getScrollY());
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断Scroller是否执行完毕
        if (scroller.computeScrollOffset()){
            Log.i(TAG, "getCurrX: "+scroller.getCurrX());
            Log.i(TAG, "getCurrY: "+scroller.getCurrY());
            ((ViewGroup)getParent()).scrollTo(scroller.getCurrX(),scroller.getCurrY());
            //通过重绘来不断调用computeScroll
            invalidate();
        }
    }
}
