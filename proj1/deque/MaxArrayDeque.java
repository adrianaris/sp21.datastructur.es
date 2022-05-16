package deque;

import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque{
    public MaxArrayDeque(Comparator<Item> c) {
        System.out.println("comparator: " + c);
    }
}
