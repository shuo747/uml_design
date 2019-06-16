package com.shuo747.plane;

/**
 * @Author: shuo747
 * @Date: 2019/6/13 10:08
 */
public class GroupOne extends Group {

    public GroupOne(int rowID , int columnId) {
        super(Group.TYPE_GROUP_ONE);
        this.rowId = rowId;
        this.columnId = columnId;
        seats = new Seat[1];
        seats[0] = new Seat(rowID,columnId);
    }

    public String pickOne(int index) {
        seats[0].pick();
        Plane.myPlane.removeGroup(this);
        return seats[0].name;
    }
}
