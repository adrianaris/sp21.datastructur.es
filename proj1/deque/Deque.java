package deque;

import java.util.Iterator;

public interface Deque<Item> extends Iterable<Item> {
    public void addFirst(Item i);
    public void addLast(Item i);
    default boolean isEmpty() {
        return this.size() == 0;
    }
    public int size();
    public void printDeque();
    public Item removeFirst();
    public Item removeLast();
    public Item get(int index);
    public Iterator<Item> iterator();
    public boolean equals(Object o);
}
