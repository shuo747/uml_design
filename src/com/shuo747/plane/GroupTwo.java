package com.shuo747.plane;

/**
 * @Author: shuo747
 * @Date: 2019/6/13 10:08
 */
public class GroupTwo extends Group {


    public GroupTwo(int rowId, int columnId) {
        super(Group.TYPE_GROUP_TWO);
        this.rowId = rowId;
        this.columnId = columnId;
        seats = new Seat[2];
        for (int i = 0 ; i < seats.length ; ++i)
            seats[i] = new Seat(rowId,columnId+i);
    }

    public String pickOne(int index) {// 随机选一个
        Seat select = null;
        for (int i = 0; i < seats.length; i++) {
            if(i == index){
                select = seats[i];
                seats[i].pick();
            }else{
                GroupOne one = new GroupOne(seats[i].rowId, seats[i].columnId);
                Plane.myPlane.addGroup(one);
                Plane.myPlane.removeGroup(this);
            }
        }
        return select.name;
    }

    public String[] pickTwo() {
        String[] names = new String[2];
        for (int i = 0; i < names.length; i++) {
            seats[i].pick();
            names[i] = seats[i].name;
        }
        Plane.myPlane.removeGroup(this);
        return names;
    }
}
