package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        /**
         *  Next first is the end of the array and nextLast is the beginning,
         *  so that they converge towards each other and we, at all times,
         *  are able to anticipate the way the array is filled and depleted
         *  making it easier to comprehend and resize it.
         */
        nextFirst = items.length - 1;
        nextLast = 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Helper method for resizing the array when full or too empty.
     */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        /**
         * Copy from nextFirst until the end of the array
         * and then from the beginning until nextLast.
         *     or
         * From nextFirst to nextLast should nextFirst or NextLast
         * has moved past first index or last index respectively in
         * the process of shifting() or poping().
         */
        if (nextFirst < nextLast - 1 || nextFirst == items.length - 1) {
            if (nextFirst == items.length - 1) {
                nextFirst = -1;
            }
            System.arraycopy(items,
                    nextFirst + 1,
                    newItems,
                    0,
                    size);
        } else {
            System.arraycopy(items,
                    nextFirst + 1,
                    newItems,
                    0,
                    items.length - 1 - nextFirst);
            System.arraycopy(items,
                    0,
                    newItems,
                    items.length - 1 - nextFirst,
                    nextLast);
        }
        nextLast = size;
        nextFirst = newItems.length - 1;
        items = newItems;
    }

    @Override
    public void addFirst(T i) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = i;
        if (nextFirst - 1 < 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst--;
        }
        size++;
    }

    @Override
    public void addLast(T i) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = i;
        if (nextLast + 1 == items.length) {
            nextLast = 0;
        } else {
            nextLast++;
        }
        size++;
    }

    public T getLast() {
        if (size == 0) {
            throw new Error("List is empty");
        }
        if (nextLast - 1 >= 0) {
            return items[nextLast - 1];
        } else {
            return items[items.length - 1];
        }

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new Error("Index out of bounds");
        }
        if (nextFirst + 1 + index < items.length) {
            return items[nextFirst + 1 + index];
        } else {
            index = index - items.length + nextFirst + 1;
            return items[index];
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size - 1 < items.length / 4 && items.length > 16) {
            resize((int) (size * 1.5));
        }

        if (nextLast - 1 < 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        T toRemove = items[nextLast];
        items[nextLast] = null;

        size -= 1;

        return toRemove;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size - 1 < items.length / 4 && items.length > 16) {
            resize((int) (size * 1.5));
        }

        if (nextFirst + 1 > items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }

        T toRemove = items[nextFirst];
        items[nextFirst] = null;
        size--;

        return toRemove;
    }

    @Override
    public void printDeque() {
        StringBuilder listTs = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            if (nextFirst + 1 + i < items.length) {
                listTs.append(" ").append(items[nextFirst + 1 + i].toString());
            } else {
                listTs.append(" ").append(items[i - items.length + nextFirst + 1].toString());
            }
        }

        System.out.println(listTs);
        System.out.print("\n");
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        private int iteration;
        ArrayDequeIterator() {
            wizPos = nextFirst + 1 == items.length ? 0 : nextFirst + 1;
            iteration = 0;
        }
        public boolean hasNext() {
            return iteration != size;
        }
        public T next() {
            T returnT = items[wizPos];
            wizPos = wizPos + 1 == items.length ? 0 : wizPos + 1;
            iteration++;
            return returnT;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
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
        int listHash = 0;
        for (int i = 0; i < size; i++) {
            if (nextFirst + 1 + i < items.length) {
                listHash += items[nextFirst + 1 + i].hashCode();
            } else {
                listHash += items[nextFirst + 1 + i - items.length].hashCode();
            }
        }
        int result = 31;
        result = result + size + listHash;
        return result;
    }
}
