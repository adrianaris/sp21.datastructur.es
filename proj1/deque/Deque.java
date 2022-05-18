package deque;

import java.util.Iterator;

public interface Deque<Item> extends Iterable<Item> {
    void addFirst(Item i);
    void addLast(Item i);
    default boolean isEmpty() {
        return this.size() == 0;
    }
    int size();
    void printDeque();
    Item removeFirst();
    Item removeLast();
    Item get(int index);
    Iterator<Item> iterator();
    boolean equals(Object o);
}
