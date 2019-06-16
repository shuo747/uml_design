package com.shuo747.plane;

/**
 * @Author: shuo747
 * @Date: 2019/6/13 10:05
 */
public class Group {
    static int indexId = 0;
    int id;
    int type;
    public int rowId;
    public int columnId;
    Seat[] seats;
    public static final int TYPE_GROUP_ONE = 0;
    public static final int TYPE_GROUP_TWO = 1;
    public static final int TYPE_GROUP_THREE = 2;



    public Group(int type) {
        this.type = type;
        id = indexId++;
    }




}

