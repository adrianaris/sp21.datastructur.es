package deque;

public class ArrayDeque<Item> implements Deque<Item> {
     private Item[] items;
     private int size;
     private int nextFirst;
     private int nextLast;

     public ArrayDeque() {
         items = (Item []) new Object[8];
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

     public int size() {
         return size;
     }

    /**
     * Helper method for resizing the array when full or too empty.
     */
     private void resize(int capacity) {
         Item[] newItems = (Item[]) new Object[capacity];
         /**
          * Copy from nextFirst until the end of the array
          * and then from the beginning until nextLast.
          */
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
         nextLast = size;
         nextFirst = newItems.length - 1;
         items = newItems;
     }

    public void addFirst(Item i) {
         if (size == items.length) {
             resize(size * 2);
         }
         items[nextFirst] = i;
         if (nextFirst- 1 < 0) {
             nextFirst= items.length - 1;
         } else {
             nextFirst--;
         }
         size++;
     }

     public void addLast(Item i) {
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

     public Item getLast() {
         if (size == 0) throw new Error("List is empty");
         return items[size - 1];
     }

     public Item get(int index) {
         if (index < 0 || index >= size) throw new Error("Index out of bounds");
         return items[index];
     }

     public Item removeLast() {
         if (size - 1 < items.length / 4) {
             resize((int) (size * 1.5));
         }

         Item toRemove = items[nextLast - 1];
         items[nextLast - 1] = null;
         if (nextLast - 1 < 0) {
             nextLast = items.length - 1;
         } else {
             nextLast--;
         }
         size -= 1;

         return toRemove;
     }

     public static void main(String[] args) {
         ArrayDeque<Integer> aL = new ArrayDeque();
         aL.addFirst(1);
         aL.addFirst(0);
         aL.addLast(2);
         aL.addLast(3);
         aL.addFirst(-1);
         aL.addFirst(-2);
         aL.addFirst(-3);
         aL.addLast(4);
         for (int i = -4; i > -14; i--) {
             aL.addFirst(i);
         }
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
         aL.removeLast();
     }
}
