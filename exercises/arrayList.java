/**
 * Array based list.
 */

public class AList<Item> {
  private Item[] items;
  private int size;

  public Alist() {
    items = (Item[]) new Object[100];
    size = 0;
  }

  public void addLast(int x) {
    if (size == items.length) {
      Item[] a = (Item[]) new Object[size * 2];
      System.arraycopy(items, 0, a, 0, size);
      items = a;
    }

    items[size] = x;
    size += 1;
  }

  public int getLast() {
    return items[size - 1]; 
  }

  public int get(int i) {
    return items[i];
  }

  public int size() {
    return size;
  }

  public int removeLast() {
    size -= 1;
    Item x = items[size];
    items[size] = null;
    return x;
  }

  public static String longest(AList<String> list) {
    String longestString = '';
    for (int i = 0; i < list.size(); i += 1) {
      if (longestString.length() < list.get(i).length()) {
        longestString = list.get(i);
      }
    }

    return longestString;
  }
}
