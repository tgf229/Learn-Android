package com.tgf.study.studyui.recyclerview;

import java.util.Random;

/**
 * Created by tugaofeng on 17/3/17.
 */
public class CountryBean {

    private int img;
    private String name;

    public CountryBean(int img, String name) {
        this.img = img;
        this.name = getRandomLengthName(name);
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //随机重复name长度
    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(40)+1;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<length; i++){
            sb.append(name);
        }
        return sb.toString();
    }
}
