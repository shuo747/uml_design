package com.shuo747.main;

import com.shuo747.plane.Plane;
import com.shuo747.role.Passenger;

import java.util.Random;

/**
 * @Author: shuo747
 * @Date: 2019/6/13 10:03
 */
public class Main {
    static Plane plane;
    static Passenger passenger;
    static String[] names = {
            "郭靖",
            "黄蓉",
            "梅超风",
            "欧阳锋",
            "黄药师",
            "一灯",
            "周伯通",
            "王重阳",
            "洪七公",
            "金轮法王",
    };
    public static void main(String[] args) {
        plane = new Plane(0);
        Random ran = new Random();
        while (plane.tickTotal > 0) {
            passenger = new Passenger( ran.nextLong()+"",names[ran.nextInt(names.length)]);// 生产购票者信息
            int num = 1+ran.nextInt(3);
            passenger.buy(plane, num);// 买一张票
        }


    }
}
