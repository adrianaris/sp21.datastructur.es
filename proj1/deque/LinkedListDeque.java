package deque;

import java.util.Iterator;

public class LinkedListDeque<Item> implements Deque<Item> {
    private class Node {
        public Node prev;
        public Item item;
        public Node next;

        public Node(Item i) {
            item = i;
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

//    public boolean isEmpty() {
//        return;
//    }
    public int size() {
        return size;
    }
//    public void printDeque() {
//        return;
//    }
//    public Item removeFirst() {
//        return;
//    }
//    public Item removeLast() {
//        return;
//    }
//    public Item get(int index) {
//        return;
//    }
//    public Iterator<Item> iterator() {
//        return;
//    }
//    public boolean equals(Object o) {
//        return;
//    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> LD = new LinkedListDeque(1);
        LD.addFirst(0);
        LD.addFirst(-1);
        LD.addLast(2);
        LD.addLast(3);
        LD.addLast(4);
        System.out.println(LD.size());
    }
}
