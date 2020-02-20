package main.java.yandex.quest;

import main.java.yandex.quest.City.Builder;
import main.java.yandex.quest.City.City;

public class Program {
    public static void main(String[] args) {
        City city = new Builder("/home/alexey/NetBeansProjects/Yandex_3.2/test.txt").getCity();
        System.out.println(city + "\n");
        System.out.println(city.FullResult());
    }
}
