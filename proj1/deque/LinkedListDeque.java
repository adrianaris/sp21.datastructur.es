package deque;

import java.io.IOException;
import java.util.Iterator;

public class LinkedListDeque<Item> implements Deque<Item> {
    private class Node {
        public Node prev;
        public Item item;
        public Node next;

        public Node(Item i) {
            item = i;
            prev = this;
            next = prev;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node((Item) new Object());
        size = 0;
    }

    public LinkedListDeque(Item i) {
        Node temp = new Node(i);
        sentinel = new Node((Item) new Object());
        temp.next = sentinel;
        temp.prev = sentinel;
        sentinel.next = temp;
        sentinel.prev = temp;
        size = 1;
    }

    public void addFirst(Item i) {
        Node temp = new Node(i);
        temp.next = sentinel.next;
        temp.prev = sentinel;
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size += 1;
    }

    public void addLast(Item i) {
        Node temp = new Node(i);
        temp.next = sentinel;
        temp.prev = sentinel.prev;
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size += 1;
    }

    public Item shift() {
       Node removed = sentinel.next;
       sentinel.next = sentinel.next.next;
       sentinel.next.prev = sentinel;
       size -= 1;
       return removed.item;
    }

    public Item pop() {
        Node removed = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removed.item;
    }

    public boolean isEmpty() {
        if (sentinel.next == null || size == 0) return true;
        return false;
    }

    public int size() {
        return size;
    }
    public void printDeque() {
        Node L = sentinel.next;
        StringBuilder listItems = new StringBuilder(L.item.toString());
        while (L.next != sentinel) {
            L = L.next;
            listItems.append(" ").append(L.item.toString());
        }

        System.out.println(listItems);
        System.out.print("\n");
    }

    /**
     *  The get() method iterates from the beginning if the
     *  index is closer to the beginning of the list
     *  and from the end if index is closer to the end.
     */
    public Item get(int index) {
        if (index >= size || index < 0) throw new Error("Index out of bounds!!!");
        if (index < size / 2) {
            Node L = sentinel.next;
            int pozition = 0;
            while (pozition != index) {
                pozition += 1;
                L = L.next;
            }

            return L.item;
        } else {
            Node L = sentinel.prev;
            int pozition = size - 1;
            while (pozition != index) {
                pozition -= 1;
                L = L.prev;
            }
            return L.item;
        }
    }

//    public Iterator<Item> iterator() {
//        return;
//    }
//    public boolean equals(Object o) {
//        return;
//    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> LD = new LinkedListDeque();
        LD.addFirst(0);
        LD.addFirst(-1);
        LD.addLast(2);
        LD.addLast(3);
        LD.addLast(4);
        int shift = LD.shift();
        int pop = LD.pop();
        int got = LD.get(0);
        System.out.println("get: " + got);
        System.out.println("shift: " + shift);
        System.out.println("pop: " + pop);
        System.out.println("size " + LD.size());
        LD.printDeque();
    }
}
