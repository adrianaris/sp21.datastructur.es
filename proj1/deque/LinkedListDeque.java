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

    /**
     *  The get() method iterates from the beginning if the
     *  index is closer to the beginning of the list
     *  and from the end if index is closer to the end.
     */
    public Item get(int index) {
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
        LinkedListDeque<Integer> LD = new LinkedListDeque(1);
        LD.addFirst(0);
        LD.addFirst(-1);
        LD.addLast(2);
        LD.addLast(3);
        LD.addLast(4);
        int shift = LD.shift();
        int pop = LD.pop();
        System.out.println(shift);
        System.out.println(pop);
        System.out.println(LD.size());
    }
}
