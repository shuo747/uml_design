package com.shuo747.plane;

/**
 * @Author: shuo747
 * @Date: 2019/6/13 10:06
 */
public class Seat {
    String name;
    int rowId;
    int columnId;
    boolean picked;
    static String[] names = {"A","B","C","D","E","F","G"};

    public Seat(int rowId, int columnId) {
        this.rowId = rowId;
        this.columnId = columnId;
        name = rowId+"排"+columnId+"坐";
    }

    public String pick(){
        if(!picked){
            picked = true;
            return name;
        }

        return null;
    }
}
