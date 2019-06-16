package com.shuo747.plane;

/**
 * @Author: shuo747
 * @Date: 2019/6/13 10:08
 */
public class GroupThree extends Group {
    public GroupThree(int rowId, int columnId) {
        super(Group.TYPE_GROUP_THREE);
        this.rowId = rowId;
        this.columnId = columnId;
        seats = new Seat[3];
        for (int i = 0 ; i < seats.length ; ++i)
            seats[i] = new Seat(rowId,columnId+i);
    }


    public String pickOne(int state) {// 只选最左边或者最右边
        Seat select = null;
        if(state == 0){
            select = seats[0];
            seats[0].pick();
            GroupTwo two = new GroupTwo(seats[1].rowId, seats[1].columnId);
            Plane.myPlane.addGroup(two);
            Plane.myPlane.removeGroup(this);
        }else{
            select = seats[2];
            seats[2].pick();
            GroupTwo two = new GroupTwo(seats[0].rowId, seats[0].columnId);
            Plane.myPlane.addGroup(two);
            Plane.myPlane.removeGroup(this);
        }
        return select.name;
    }

    public String[] pickTwo(int state) {
        String[] names = new String[2];
        if(state == 0){
            for (int i = 1; i < seats.length; i++) {
                seats[i].pick();
                names[i-1] = seats[i].name;
            }
            GroupOne one = new GroupOne(seats[0].rowId, seats[0].columnId);
            Plane.myPlane.addGroup(one);
            Plane.myPlane.removeGroup(this);
        }else{
            for (int i = 0; i < seats.length-1; i++) {
                seats[i].pick();
                names[i] = seats[i].name;
            }
            GroupOne one = new GroupOne(seats[2].rowId, seats[2].columnId);
            Plane.myPlane.addGroup(one);
            Plane.myPlane.removeGroup(this);
        }
        return names;
    }

    public String[] pickThree() {
        String[] names = new String[3];
        for (int i = 0; i < names.length; i++) {
            seats[i].pick();
            names[i] = seats[i].name;
        }
        Plane.myPlane.removeGroup(this);
        return names;
    }

}
