package deque;

import java.util.Comparator;

// we need the iterator so we should first implement that

public class MaxArrayDeque<Item> extends ArrayDeque{
    private Comparator<Item> comparator;
    private Comparator<Item> defaultComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return 0;
        }
    };

    public MaxArrayDeque(Comparator<Item> c) {
        super();
        comparator = c;
    }

    public Item max() {
        if (this.size() == 0) return null;
        return null;
    }

    public Item max(Comparator<Item> c) {
        return null;
    }

    public static void main(String[] args) {
        MaxArrayDeque<String> MD = new MaxArrayDeque<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
    }
}
