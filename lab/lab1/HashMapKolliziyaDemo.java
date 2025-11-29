package lab1;

import java.util.HashMap;

public class HashMapKolliziyaDemo {
    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Биба");
        map.put(1, "Боба");

        System.out.println(map.get(1));
        System.out.println("Вот коллизия :) [Бибы нет :(((((]");
        
    }
}