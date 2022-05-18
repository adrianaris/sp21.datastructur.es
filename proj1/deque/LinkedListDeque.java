package deque;
import java.util.Iterator;

public class LinkedListDeque<Item> implements Deque<Item> {
    /**
     * At the teacher recommendation I decided to write a DLList
     * with one sentinel keeping track of both head and tail.
     */
    private class Node {
        private Node prev;
        private Item item;
        private Node next;

        Node(Item i) {
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

    @Override
    public void addFirst(Item i) {
        Node temp = new Node(i);
        temp.next = sentinel.next;
        temp.prev = sentinel;
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size += 1;
    }

    @Override
    public void addLast(Item i) {
        Node temp = new Node(i);
        temp.next = sentinel;
        temp.prev = sentinel.prev;
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size += 1;
    }

    @Override
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }

        Node removed = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removed.item;
    }

    @Override
    public Item removeLast() {
        if (size == 0) {
            return null;
        }

        Node removed = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removed.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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
    @Override
    public Item get(int index) {
        if (index >= size || index < 0) {
            throw new Error("Index out of bounds!!!");
        }
        if (index < size / 2) {
            Node L = sentinel.next;
            int position = 0;
            while (position != index) {
                position += 1;
                L = L.next;
            }

            return L.item;
        } else {
            Node L = sentinel.prev;
            int position = size - 1;
            while (position != index) {
                position -= 1;
                L = L.prev;
            }
            return L.item;
        }
    }

    public Item getRecursive(int index) {
        return getRecursive(index, sentinel);
    }

    /**
     * This is a helper method for getRecursive main method.
     * It functions in the same vein with the iterative method:
     *     if index is nearer to the start it starts from the start;
     *     if index is nearer to the end it starts from the end.
     * It receives sentinel as argument.
     */
    private Item getRecursive(int index, Node list) {
        if (index == 0) {
            return list.next.item;
        } else if (index == size - 1) {
            return list.prev.item;
        }

        if (index < size / 2) {
            return getRecursive(index - 1, list.next);
        } else {
            return getRecursive(index + 1, list.prev);
        }
    }

    public Iterator<Item> iterator() {
        return new LLDequeIterator();
    }

    private class LLDequeIterator implements Iterator<Item> {
        private Node wizPos;
        LLDequeIterator() {
            wizPos = sentinel.next;
        }
        public boolean hasNext() {
            return wizPos != sentinel;
        }
        public Item next() {
            Item returnItem = wizPos.item;
            wizPos = wizPos.next;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() == this.getClass() && o.hashCode() == this.hashCode()) {
            return true;
        }
        return false;
    }

    /**
     * According to the style sheet I have to implement this
     * because I implement equals() above.
     */
    @Override
    public int hashCode() {
        Node tmp = sentinel;
        int listHash = 0;
        while (tmp.next != sentinel) {
            tmp = tmp.next;
            listHash += tmp.item.hashCode();
        }
        int result = 31;
        result = result + size + listHash;
        return result;
    }
}
