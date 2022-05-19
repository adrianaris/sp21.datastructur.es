package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> madc;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        madc = c;
    }

    public T max() {
        if (this.size() == 0) {
            return null;
        }
        if (madc == null) {
            return max(getComparator());
        }
        return max(madc);
    }

    public T max(Comparator<T> c) {
        T result = this.getLast();
        Iterator<T> iterator = iterator();

        while (iterator.hasNext()) {
            T i = iterator.next();
            if (c.compare(result, i) < 0) {
                result = i;
            }
        }

        return result;
    }

    private class MaxDequeComparator implements Comparator<T> {
        public int compare(T a, T b) {
            return a.hashCode() - b.hashCode();
        }
    }

    public Comparator<T> getComparator() {
        return new MaxDequeComparator();
    }
}
