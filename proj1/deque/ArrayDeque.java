package deque;

public class ArrayDeque<Item> implements Deque<Item> {
     private Item[] items;
     private int size;

     public ArrayDeque() {
         items = (Item []) new Object[8];
         size = 0;
     }

     public int size() {
         return size;
     }

    /**
     * Helper method for resizing the array when full or too empty.
     */
     private void resize(int capacity) {
         Item[] newItems = (Item[]) new Object[capacity];
         System.arraycopy(items, 0, newItems, 0, size);
         items = newItems;
     }

     public void addFirst(Item i) {
         Item[] newItems;
         if (size == items.length) {
             newItems = (Item[]) new Object[(int) (size * 1.5)];
         } else {
             newItems = (Item[]) new Object[size];
         }
         newItems[0] = i;
         System.arraycopy(items, 0, newItems, 1, size);
         items = newItems;
         size += 1;
     }

     public void addLast(Item i) {
         if (size == items.length) {
             resize((int) (size * 1.5));
         }
         items[size] = i;
         size += 1;
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
         Item toRemove = items[size - 1];
         items[size - 1] = null;
         size -= 1;

         if (size < items.length / 4 && size > 16) {
             resize((int) (size * 1.5));
         }

         return toRemove;
     }

     public static void main(String[] args) {
         ArrayDeque<Integer> aL = new ArrayDeque();
         aL.addFirst(1);
         aL.addLast(2);
         System.out.println(aL.getLast());
     }
}
