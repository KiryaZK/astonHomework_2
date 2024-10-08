import list.MyArrayList;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>() {{
            add("Vasya");
            add("Bob");
            add("Vito");
            add("Geralt");
            add("Diego");
        }};

        list.add(1, "Buba");
        System.out.println("last elem: " + list.get(list.size() - 1));
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "Ann", "Kirill", "Dasha", "Alex", "Morty");
        list.addAll(list2);
        System.out.println(list);
        list.remove(0);
        list.remove("Buba");
        list.sort();
        list.add("Ceasar");
        MyArrayList.sort(list);
        System.out.println(list);
    }
}
