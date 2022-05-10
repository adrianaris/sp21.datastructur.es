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

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            }
        }
    }

    public static void main(String[] args) {
       //testThreeAddThreeRemove();
       randomizedTest();
    }
}
