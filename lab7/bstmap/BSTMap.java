package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Calls containsKey() with a null key.");
        }
        return containsKey(key, root);
    }

    private boolean containsKey(K key, BSTNode node) {
        if (node == null) {
            return false;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return containsKey(key, node.left);
        } else if (compare > 0) {
            return containsKey(key, node.right);
        } else {
            return true;
        }
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
        if (compare < 0) {
            return get(key, map.left);
        } else if (compare > 0) {
            return get(key, map.right);
        } else {
            return map.value;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode node) {
        if (node == null) {
            return 0;
        }

        return node.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Calls put with null key");
        }

        root = put(key, value, root);
    }

    private BSTNode put(K key, V value, BSTNode node) {
        if (node == null) {
            return new BSTNode(key, value, 1);
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            node.value = value;
        } else if (compare < 0) {
            node.left = put(key, value, node.left);
        } else if (compare > 0) {
            node.right = put(key, value, node.right);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }
    private class BSTMapIterator implements Iterator<K> {
        Set<K> set;
        Iterator<K> iterator;
        BSTMapIterator() {
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

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySet(root, set);
        return set;
    }

    private void keySet(BSTNode node, Set<K> set) {
        if (node != null) {
            set.add(node.key);
            if (node.size > 1) {
                keySet(node.right, set);
                keySet(node.left, set);
            }
        }
    }

    @Override
    public V remove(K key) {
        V result = get(key);
        root = removeNode(key, root);
        return result;
    }

    @Override
    public V remove(K key, V value) {
        V sameValue = get(key);
        if (value == sameValue) {
            root = removeNode(key, root);
            return sameValue;
        }
        return null;
    }

    private BSTNode removeNode(K key, BSTNode node) {
        if (node == null) {
            return null;
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = removeNode(key, node.left);
        } else if (compare > 0 ) {
            node.right = removeNode(key, node.right);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            node.right = successor(node.right, node);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    /**
     * Helper method, always finds successor and replaces the root with it;
     */
    private BSTNode successor(BSTNode L, BSTNode R) {
        if (L.left == null) {
            R.value = L.value;
            R.key = L.key;
            R.size = 1 + size(R.left) + size(R.right);
            return L.right;
        } else {
            L.left = successor(L.left, R);
            return L;
        }
    }

    public static void main(String[] args) {
        BSTMap map = new BSTMap();

        //for (int i = 0; i < 10; i++) {
        //    map.put((int) (Math.random() * 100), "x");
        //}

        map.put("B", 1);
        map.put("A", 2);
        map.put("C", 3);
        map.put("b", 4);
        map.put("a", 4);
        map.put("c", 5);
        System.out.println(map.keySet());
        System.out.println(map.remove("B"));
        map.remove("A", 2);
        map.remove("C", 3);
        map.remove("b", 4);
        map.remove("a", 4);
        map.remove("c", 5);
        System.out.println(map.keySet());
    }
}
