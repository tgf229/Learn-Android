package com.tgf.scrollview.viewpage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by 涂高峰 on 2017/6/21.
 */
public class ScrollerLayout extends ViewGroup {
    private static final String TAG = "ScrollerLayout";
    private Scroller mScroller;
    private int mDownX,mMoveX;
    private int leftBorder,rightBorder;
    private int mTouchSlop;
    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        //大于这个距离,系统认为是移动
        mTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i=0; i<count; i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i=0; i<count; i++){
            View child = getChildAt(i);
            child.layout(i*child.getMeasuredWidth(), 0, (i+1)*child.getMeasuredWidth(), child.getMeasuredHeight());
        }
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount()-1).getRight();
        Log.i(TAG, "leftBorder: "+leftBorder);
        Log.i(TAG, "rightBorder: "+rightBorder);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getRawX();
        switch (ev.getAction()){
            case  MotionEvent.ACTION_DOWN:
                mDownX = x;
                mMoveX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                //按下的坐标与当前移动坐标绝对值 大于 系统默认的移动距离
                //拦截此移动事件,不向子view传递,进入自身的onTouchEvent
                if (Math.abs(mDownX - x)>mTouchSlop){
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //如果子控件为Button之类的clickable控件,则会由button消费掉down事件,当viewgroup滑动时,会拦截move事件并处理
                //但是若子控件为TextView之类的非clickable控件,则viewgroup和textview都不会消费掉down事件.
                //由于没有任何view消费down事件,后续事件将由上层消费,而不会往下传递给viewgroup.所以此处需要将down事件消费掉,从而能继续接收后续事件
                return true;
            case MotionEvent.ACTION_MOVE:
                //偏移量
                int offsetX = mMoveX-x;
                //左边界处理
                if (getScrollX()+offsetX < leftBorder){
                    scrollTo(leftBorder,0);
                    return true;
                }
                //右边界处理
                if (getScrollX()+offsetX + getWidth()> rightBorder){
                    scrollTo(rightBorder-getWidth(),0);
                    return true;
                }
                //滑动处理
                scrollBy(offsetX,0);
                mMoveX = x;
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起,判断是哪个子控件的index
                //小于第一个子控件的一半宽度则认为是第一个子控件
                //大于第一个子控件的一半宽度则认为是下一个子控件
                int index = (getScrollX()+getWidth()/2)/getWidth();
                Log.i(TAG, "index: "+index); //结果为  0  1  2
                //根据子空间index计算偏移量
                int dy = index * getWidth() - getScrollX();
                Log.i(TAG, "dy: "+dy);
                mScroller.startScroll(getScrollX(),0,dy,0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }
}
