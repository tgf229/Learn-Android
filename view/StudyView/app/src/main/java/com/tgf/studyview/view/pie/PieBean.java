package com.tgf.studyview.view.pie;

import android.support.annotation.NonNull;

/**
 * Created by 涂高峰 on 2017/5/16.
 */
public class PieBean {
    public PieBean(@NonNull String name, @NonNull String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 用户关心的数值
     */
    private String name; //名称
    private String value; //值

    /**
     * 程序计算数值
     */
    private float percent; //百分比
    private float angle; //角度
    private int color; //颜色

    @Override
    public String toString() {
        return "name="+getName()+" ,value="+getValue()+" ,percent="+getPercent()+" ,angle="+getAngle()+" ,color="+getColor();
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
