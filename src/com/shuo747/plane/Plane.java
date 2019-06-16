package com.shuo747.plane;

import com.shuo747.role.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: shuo747
 * @Date: 2019/6/13 10:03
 */
public class Plane {

    public static Plane myPlane = null;
    int type;
    Row[] rows;
    int roeTypeDatas[][] = {{Row.TYPE_ROW_0,Row.TYPE_ROW_0,Row.TYPE_ROW_0,Row.TYPE_ROW_0}};
    List<GroupOne> groupOnes;
    List<GroupTwo> groupTwos;
    List<GroupThree> groupThrees;

    static Random ran = new Random();

    public int tickTotal;
    public int ticketState;// 机票状态
    public static final int STATE_NORMAL = 0;// 正常
    public static final int STATE_NO3 = 1;// 只剩两张
    public static final int STATE_NO2 = 2;// 只剩一张
    public static final int STATE_NULL = 3;// 无票

    public Plane(int type) {
        if(myPlane == null)
            myPlane = this;
        this.type = type;
        init();
        flushState();
    }

    private void init() {
        groupOnes = new ArrayList<>();
        groupTwos = new ArrayList<>();
        groupThrees = new ArrayList<>();

        rows = new Row[roeTypeDatas[type].length];
        for (int i = 0 ; i < rows.length ; ++i)
            rows[i] = new Row(roeTypeDatas[type][i],i);
    }

    /*
    public boolean addGroup(int type){

        switch (type){
            case Group.TYPE_GROUP_ONE:
                groupOnes.add(group);
            case Group.TYPE_GROUP_TWO:
                groupTwos.add(group);
            case Group.TYPE_GROUP_THREE:
                groupThrees.add(group);
        }
        return false;
    }
    */

    public boolean addGroup(Group group){

        switch (group.type){
            case Group.TYPE_GROUP_ONE:
                groupOnes.add((GroupOne) group);
                break;
            case Group.TYPE_GROUP_TWO:
                groupTwos.add((GroupTwo) group);
                break;
            case Group.TYPE_GROUP_THREE:
                groupThrees.add((GroupThree) group);
                break;
        }
        return false;
    }


    public Ticket[] getTickets(int ticketNum) {
        Ticket[] tickets = new Ticket[ticketNum];
        switch (ticketNum) {
            case 1:
                tickets = pickOne();
                break;
            case 2:
                tickets = pickTwo();
                break;
            case 3:
                tickets = pickThree();
                break;
        }
        flushState();
        return tickets;
    }

    //	刷新余票状态
    private void flushState() {
        int ticketNum = 0;
        for (int i = 0; i < groupOnes.size(); i++) {
            ticketNum += 1;
        }
        for (int i = 0; i < groupTwos.size(); i++) {
            ticketNum += 2;
        }
        for (int i = 0; i < groupThrees.size(); i++) {
            ticketNum += 3;
        }

        tickTotal = ticketNum;
        if(ticketNum == 0){
            this.ticketState = STATE_NULL;
        }else if(ticketNum == 1){
            this.ticketState = STATE_NO2;
        }else if(ticketNum == 2){
            this.ticketState = STATE_NO3;
        }else{
            this.ticketState = STATE_NORMAL;
        }
    }

    private Ticket[] pickThree() {
        Ticket[] tickets = new Ticket[3];
        if(groupThrees.size() > 0){
            int index = ran.nextInt(groupThrees.size());
            String[] seatNames = groupThrees.get(index).pickThree();
            for (int i = 0; i < tickets.length; i++) {
                tickets[i] = new Ticket(seatNames[i]);
            }
        }else if(groupTwos.size() > 0){
            int index = ran.nextInt(groupTwos.size());
            String[] seatNames = groupTwos.get(index).pickTwo();
            for (int i = 0; i < seatNames.length; i++) {
                tickets[i] = new Ticket(seatNames[i]);
            }
//			String name = groupOnes.get(0).pickOne(0);
            tickets[2] = pickOne()[0];
        }else{
            String name1 = groupOnes.get(0).pickOne(0);
            tickets[0] = new Ticket(name1);
            String name2 = groupOnes.get(0).pickOne(0);
            tickets[1] = new Ticket(name2);
            String name3 = groupOnes.get(0).pickOne(0);
            tickets[2] = new Ticket(name3);
        }
        return tickets;
    }

    private Ticket[] pickTwo() {
        Ticket[] tickets = new Ticket[2];
        if(groupTwos.size() > 0){
            int index = ran.nextInt(groupTwos.size());
            String[] seatNames = groupTwos.get(index).pickTwo();
            for (int i = 0; i < tickets.length; i++) {
                tickets[i] = new Ticket(seatNames[i]);
            }
        }else if(groupThrees.size() > 0){
            int index = ran.nextInt(groupThrees.size());
            String[] seatNames = groupThrees.get(index).pickTwo(ran.nextInt(2));
            for (int i = 0; i < tickets.length; i++) {
                tickets[i] = new Ticket(seatNames[i]);
            }
        }else{
            String name1 = groupOnes.get(0).pickOne(0);
            tickets[0] = new Ticket(name1);
            String name2 = groupOnes.get(0).pickOne(0);
            tickets[1] = new Ticket(name2);
        }
        return tickets;
    }

    private Ticket[] pickOne() {
        Ticket[] tickets = new Ticket[1];
        if(groupOnes.size() > 0){
            String name = groupOnes.get(0).pickOne(0);
            tickets[0] = new Ticket(name);
        }else if(groupTwos.size() > 0){
            int index = ran.nextInt(groupTwos.size());
            tickets[0] = new Ticket(groupTwos.get(index).pickOne(ran.nextInt(2)));
        }else if(groupThrees.size() > 0){
            int index = ran.nextInt(groupThrees.size());
            tickets[0] = new Ticket(groupThrees.get(index).pickOne(ran.nextInt(2)));
        }
        return tickets;
    }

    public void removeGroup(Group group) {
        for (int i = 0; i < groupOnes.size(); i++) {
            if(group.id == groupOnes.get(i).id){
                groupOnes.remove(i);
                return;
            }
        }
        for (int i = 0; i < groupTwos.size(); i++) {
            if(group.id == groupTwos.get(i).id){
                groupTwos.remove(i);
                return;
            }
        }
        for (int i = 0; i < groupThrees.size(); i++) {
            if(group.id == groupThrees.get(i).id){
                groupThrees.remove(i);
                return;
            }
        }

        flushState();
    }
}
