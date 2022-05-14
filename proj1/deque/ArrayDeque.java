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
          *     or
          * From nextFirst to nextLast should nextFirst or NextLast
          * has moved past first index or last index respectively in
          * the process of shifting() or poping().
          */
         if (nextFirst < nextLast - 1) {
             System.arraycopy(items,
                     nextFirst + 1,
                     newItems,
                     0,
                     nextLast - nextFirst + 1);
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
         if (nextLast - 1 >= 0) {
             return items[nextLast - 1];
         } else {
             return items[items.length - 1];
         }

     }

     public Item get(int index) {
         if (index < 0 || index >= size) throw new Error("Index out of bounds");
         if (nextFirst + 1 + index < items.length) {
             return items[nextFirst + 1 + index];
         } else {
             index = index - items.length + nextFirst + 1;
             return items[index];
         }
     }

     public Item removeLast() {
         if (size - 1 < items.length / 4) {
             resize((int) (size * 1.5));
         }

         if (nextLast - 1 < 0) {
             nextLast = items.length - 1;
         } else {
             nextLast--;
         }
         Item toRemove = items[nextLast];
         items[nextLast] = null;

         size -= 1;

         return toRemove;
     }

     public Item removeFirst() {
         if (size - 1 < items.length / 4) {
             resize((int) (size * 1.5));
         }

         if (nextFirst + 1 > items.length -1) {
             nextFirst = 0;
         } else {
             nextFirst++;
         }

         Item toRemove = items[nextFirst];
         items[nextFirst] = null;
         size--;

         return toRemove;
     }

     public boolean isEmpty() {
         if (size == 0) return true;
         return false;
     }

     public void printDeque() {
         StringBuilder listItems = new StringBuilder(size);
         for (int i = 0; i < size; i++) {
             if (nextFirst + 1 + i < items.length) {
                 listItems.append(" ").append(items[nextFirst + 1 + i].toString());
             } else {
                 listItems.append(" ").append(items[i - items.length + nextFirst + 1].toString());
             }
         }

         System.out.println(listItems);
         System.out.print("\n");
     }

    public boolean equals(Object o) {
        if (o instanceof ArrayDeque && o.hashCode() == this.hashCode()) {
            return true;
        }
        return false;
    }

    /**
     * According to the style sheet I have to implement this
     * because I implement equals() above
     */
    public int hashCode() {
        int ListHash = 0;
        for (int i = 0; i < size; i++) {
            if (nextFirst + 1 + i < items.length) {
                ListHash += items[nextFirst + 1 + i].hashCode();
            } else {
                ListHash += items[nextFirst + 1 + i - items.length].hashCode();
            }
        }
        int result = 37;
        result = result + size + ListHash;
        return result;
    }

     public static void main(String[] args) {
         ArrayDeque<Integer> aL = new ArrayDeque();
         ArrayDeque<Integer> aH = new ArrayDeque();
         aH.addFirst(1);
         System.out.println("size " + aL.size);
         aL.addFirst(1);
         System.out.println(aL.equals(aH));
         aL.addFirst(0);
         aL.addLast(2);
         aL.addLast(3);
         aL.addFirst(-1);
         aL.addFirst(-2);
         System.out.println(aL.get(5));
         aL.addFirst(-3);
         aL.addLast(4);
         for (int i = -4; i > -14; i--) {
             aL.addFirst(i);
         }
         aL.removeLast();
         aL.removeFirst();
         aL.removeFirst();
         aL.removeFirst();
         aL.removeFirst();
         aL.removeFirst();
         aL.printDeque();
         aL.removeFirst();
         aL.removeFirst();
         aL.removeFirst();
         aL.removeFirst();
         aL.removeFirst();
         aL.removeFirst();
         aL.removeFirst();
         aL.removeFirst();
     }
}
