package bstmap;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode root;

    private class BSTNode {
        private BSTNode right, left;
        private K key;
        private V value;
        private int size;

        public BSTNode(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
        public BSTNode() {
        }
    }

    @Override
    public void clear() {
        this.root = new BSTNode();
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Calls containsKey() with a null key.");
        }
        return get(key, root) == null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Calls containsKey() with a null key.");
        }
        return get(key, root);
    }

    /**
     * Helper method for containsKey(key) and get(key), takes root and key as args
     * and goes down the branches until null or found.
     */
    private V get(K key, BSTNode map) {
        if (map == null) {
            return null;
        }
        int compare = key.compareTo(map.key);
        if (compare == 0) {
            return map.value;
        } else if (compare < 0) {
            return get(key, map.left);
        } else if (compare > 0) {
            return get(key, map.right);
        }
    }
}
