package com.shuo747.role;

import com.shuo747.plane.Plane; /**
 * @Author: shuo747
 * @Date: 2019/6/14 9:14
 */
public class Passenger {
    
    String id;
    String name;

    public Ticket[] tickets;

    public Passenger(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void buy(Plane plane, int count) {
        switch (plane.ticketState) {
            case Plane.STATE_NULL:
                System.out.println("抱歉，该航班已满员。");
                //Main.noTicket = true;
                return;
            case Plane.STATE_NO2:
                if(count>1){
                    System.out.println("抱歉"+name+"，该航班已不足两个座位。");
                    return;
                }
            case Plane.STATE_NO3:
                if(count>2){
                    System.out.println("抱歉"+name+"，该航班已不足三个座位。");
                    return;
                }
        }
        tickets = plane.getTickets(count);
        String ticketMessage = "";
        for (int i = 0; i < tickets.length; i++) {
            ticketMessage = ticketMessage+tickets[i].name+"，";
        }

        System.out.println("乘客"+name+"已购买"+ticketMessage+"等"+tickets.length+"个座位的票。");
    }
}
