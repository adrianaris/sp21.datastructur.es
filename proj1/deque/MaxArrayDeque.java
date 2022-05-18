package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item> {
    private Comparator<Item> madc;

    public MaxArrayDeque(Comparator<Item> c) {
        super();
        madc = c;
    }

    public Item max() {
        if (this.size() == 0) {
            return null;
        }
        if (madc == null) {
            return max(getComparator());
        }
        return max(madc);
    }

    public Item max(Comparator<Item> c) {
        Item result = this.getLast();
        Iterator<Item> iterator = iterator();

        while (iterator.hasNext()) {
            Item i = iterator.next();
            if (c.compare(result, i) < 0) {
                result = i;
            }
        }

        return result;
    }

    private class MaxDequeComparator implements Comparator<Item> {
        public int compare(Item a, Item b) {
            return a.hashCode() - b.hashCode();
        }
    }

    public Comparator<Item> getComparator() {
        return new MaxDequeComparator();
    }
}
