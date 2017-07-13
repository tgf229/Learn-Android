package com.tgf.scrollview.viewdraghelper;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by 涂高峰 on 2017/6/26.
 */
public class VDHDemo extends LinearLayout {
    private static final String TAG = "VDHDemo";
    private ViewDragHelper mDragger;

    private View mDragView;
    private View mAutoBackView;
    private Point mAutoBackOriPos = new Point();

    public VDHDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        //第二个参数为敏感度(sensitivity),敏感度越大mTouchSlop就越小
        //mTouchSlop为系统认为是移动的最小距离,即ViewConfiguration.get(context).getScaledPagingTouchSlop()
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                //返回true表示可以捕获该view,可根据第一个参数决定捕获哪个view
                //如: return xxView == child;
                return mDragView==child || mAutoBackView==child;
//                return true;
            }

            //边界控制
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                final int leftBound = getPaddingLeft(); //左边界为viewgroup的paddingleft
                final int rightBound = getWidth() - leftBound - getPaddingRight() - 200; //200为子view的宽度

                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
                return newLeft;
            }

            //边界控制
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            //手指释放时回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
//                super.onViewReleased(releasedChild, xvel, yvel);
                //若为mAutoBackView,则回到初始位置,调用settleCapturedViewAt()
                //其内部为mScroller.startScroll(),别忘了invalidate和computeScroll
                //注意你拖动的越快，返回的越快
                if (releasedChild == mAutoBackView){
                    mDragger.settleCapturedViewAt(mAutoBackOriPos.x,mAutoBackOriPos.y);
                    invalidate();
                }
            }
            //如果子View不消耗事件，那么整个手势（DOWN-MOVE*-UP）都是直接进入onTouchEvent，
            // 在onTouchEvent的DOWN的时候就确定了captureView

            //如果消耗事件，那么就会先走onInterceptTouchEvent方法，判断是否可以捕获，
            // 而在判断的过程中会去判断另外两个回调的方法：getViewHorizontalDragRange和getViewVerticalDragRange，
            // 只有这两个方法返回大于0的值才能正常的捕获。
            @Override
            public int getViewHorizontalDragRange(View child)
            {
                return getMeasuredWidth()-child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child)
            {
                return getMeasuredHeight()-child.getMeasuredHeight();
            }
        });
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //onLayout结束后将mAutoBackView的返回原点设置为其初始的点
        mAutoBackOriPos.x = mAutoBackView.getLeft();
        mAutoBackOriPos.y = mAutoBackView.getTop();
    }

    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)){
            invalidate();
        }
    }
}
