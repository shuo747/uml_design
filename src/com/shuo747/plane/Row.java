package com.shuo747.plane;

/**
 * @Author: shuo747
 * @Date: 2019/6/13 10:04
 */
public class Row {
    int type;
    int rowId;
    public static final int TYPE_ROW_0 = 0;
    public int [][] typeDatas = {{2,3,2}};
    //列的指针，初始为0，偏移量为2或3
    int index;

    public Row(int type, int rowId) {
        this.type = type;
        this.rowId = rowId;
        initGroup();
    }

    private void initGroup() {
        for (int count : typeDatas[type]){

            if(count == 1){
                Group group = new GroupOne(rowId,index);
                Plane.myPlane.addGroup(group);
            }
            if(count == 2){
                Group group = new GroupTwo(rowId,index);
                Plane.myPlane.addGroup(group);
            }
            if(count == 3){
                Group group = new GroupThree(rowId,index);
                Plane.myPlane.addGroup(group);
            }
            index+=count;

        }

    }

    public Row() {
    }
}
