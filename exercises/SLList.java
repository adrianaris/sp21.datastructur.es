public class SLList {
  public static class IntNode {
    public int item;
    public IntNode next;
    public IntNode(int i, IntNode n) {
      item = i;
      next = n;
    }
  }

  private IntNode first;

  public SLList(int x) {
    first = new IntNode(x, null);
  }

  public int size = 1;

  public void addFirst(int x) {
    first = new IntNode(x, first);
    size += 1;
  }

  public int getFirst() {
    return first.item;
  }
  
  private static int size(IntNode p) {
    if (p.next == null) return 1;
    return 1 + size(p.next);
  }

  public int size() {
    return size(first);
  }

  public static void main(String[] args) {
    SLList L = new SLList(15);
    L.addFirst(10);
    L.addFirst(5);
    System.out.println(L.size);
  }
}
