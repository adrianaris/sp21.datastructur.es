package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    public static void testThreeAddThreeRemove() {
        int[] items = {4, 5, 6};
        AListNoResizing<Integer> aList = new AListNoResizing();
        BuggyAList<Integer> bList = new BuggyAList();
        for (int i = 0; i < items.length; i+= 1) {
            aList.addLast(items[i]);
            bList.addLast(items[i]);
        }

        for (int i = items.length - 1; i > 0; i -= 1) {
            assertEquals(Optional.of(items[i]), Optional.of(aList.removeLast()));
            assertEquals(Optional.of(items[i]), Optional.of(bList.removeLast()));
        }
    }

    public static void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 9000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int bSize = B.size();
                assertEquals(size, bSize);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() > 0) {
                    int lastVal = L.getLast();
                    int bLastVal = B.getLast();
                    assertEquals(lastVal, bLastVal);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() > 0) {
                    int lastVal = L.removeLast();
                    int bLastVal = B.removeLast();
                    assertEquals(lastVal, bLastVal);
                }
            }
        }
    }

    public static void main(String[] args) {
       testThreeAddThreeRemove();
       randomizedTest();
    }
}
