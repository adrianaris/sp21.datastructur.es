package hashmap;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;

    private static final int INIT_SIZE = 16;
    private static final double INIT_LOAD_FACTOR = 0.75;

    private int initialSize; // size of buckets collection.
    private int size; //number of elements.
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        this(INIT_SIZE);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, INIT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.loadFactor = maxLoad;
        buckets = createTable(initialSize);
        size = 0;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new HashSet<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] collection = (Collection<Node>[]) new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            collection[i] = createBucket();
        }
        return collection;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    private int hash(K key) {
        int hash = key.hashCode();
        return Math.floorMod(hash, initialSize);
    }

    @Override
    public void clear() {
        buckets = createTable(initialSize);
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Calls containsKey() with a null key.");
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Calls get() with a null key.");
        }
        Collection<Node> bucket = buckets[hash(key)];
        for (Node n : bucket) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (((double) (size + 1) / initialSize) >= loadFactor) {
            initialSize = initialSize * 2;
            resize(initialSize);
        }

        int hash = hash(key);
        Node node = createNode(key, value);

        for (Node n : buckets[hash]) {
            if (n.key.equals(key)) {
                n.value = value;
            }
        }

        buckets[hash].add(node);
        size++;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            if (!bucket.isEmpty()) {
                for (Node n : bucket) {
                    set.add(n.key);
                }
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {
        Collection<Node> bucket = buckets[hash(key)];
        for (Node n : bucket) {
            if (n.key.equals(key)) {
                V removed = n.value;
                bucket.remove(n);
                size--;
                return removed;
            }
        }

        return null;
    }

    @Override
    public V remove(K key, V value) {
        Collection<Node> bucket = buckets[hash(key)];
        for (Node n : bucket) {
            if (n.key.equals(key) && n.value.equals(value)) {
                bucket.remove(n);
                size--;
                return value;
            }
        }

        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K> {
        Set<K> set;
        Iterator<K> iterator;

        MyHashMapIterator() {
            set = keySet();
            iterator = set.iterator();
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public K next() {
            return iterator.next();
        }
    }

    private void resize(int size) {
        Collection<Node>[] newTable = createTable(size);
        for (Collection<Node> bucket : buckets) {
            for (Node n : bucket) {
                newTable[hash(n.key)].add(n);
            }
        }

        buckets = newTable;
    }
}
