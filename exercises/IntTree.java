/**
 * We want to define a destructive mergeRight routine that combines the values
 * in two search trees. The trees here are binarry search trees with a sentinel
 * node (whose data field is irrelevant) at their root. The sentinel's left
 * child is the tree containing the data in each case. One of the trees is right-
 * -leaning--essentially an ordered list.
 */
class IntTree {
  public IntTree (int data, IntTree left, IntTree right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }


  public final int data;
  public IntTree left, right;


  /** 
  * Assuming that T and L are binary search trees each with a single
  * sentinel tree node, and that all left children in L aside from
  * the sentinel are empty (L is "right-leaning"), returns (the
  * sentinel of) a binary search tree containing the original elements
  * of T and L. The operation is destructive, and creates no
  * new nodes. In the worst case, it takes time linear in the
  * total number of items in T and L.
  */
  public static IntTree mergeRight (IntTree T, IntTree L) {
    while (L.left != null) {
      T.left = insert(T.left, removeRoot(L.left));
    }
    return T;
  }

  public static IntTree insert(IntTree T, IntTree removedRoot) {
    if (T == null) {
      return removedRoot;
    }
    if (removedRoot.data < T.data) {
      T.left = insert(T.left, removedRoot);
    } else if (removedRoot.data > T.data) {
      T.right = insert(T.right, removedRoot);
    }
    
    return T;
  }

  public static IntTree removeRoot(IntTree L) {
    IntTree rootNoode = L;
    rootNode.right = null;
    rootNode.left = null;

    if (L.left == null) {
      L = L.right;
    } else if (L.right == null) {
      L = L.left;
    } else {
      L.right = successor(L.right, L)
    }

    return rootNode;
  }

  private IntTree successor(IntTree L, IntTree R) {
    if (L.left == null) {
      R.data = L.data;
      return L.right;
    } else {
      L.left = successor(L.left, R);
      return L;
    }
  }
}

