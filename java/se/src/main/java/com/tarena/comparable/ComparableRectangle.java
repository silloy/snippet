package com.tarena.comparable;

import java.awt.*;

/**
 * @author SuShaohua
 * @date 2016/8/23 14:38
 * @description
 */
public class ComparableRectangle extends Rectangle implements Comparable<ComparableRectangle>, Cloneable{
    public ComparableRectangle(int width, int height){
        super(width, height);
    }
    @Override
    public int compareTo(ComparableRectangle o) {
        return 0;
    }

    @Override
    public Object clone(){
        return super.clone();
    }

    ComparableRectangle c =new ComparableRectangle(23,4);


}
