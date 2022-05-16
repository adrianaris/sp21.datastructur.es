package deque;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;

public class ArrayDequeRandoTests {
    @Test
    public static void test0() {
        //randomly call ArrayDeque and ArrayDequeSolution methods.
        ArrayDeque<Integer> SAD = new ArrayDeque();
        ArrayDequeSolution<Integer> ADS = new ArrayDequeSolution();

        int N = 5000;
        StringBuilder methodCallsList = new StringBuilder("\nSequence of method calls until failure:\n");

        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addFirst
                Integer randValue = StdRandom.uniform(-100, 100);
                SAD.addFirst(randValue);
                methodCallsList.append("addFirst(")
                        .append(randValue)
                        .append(")")
                        .append("\n");
                ADS.addFirst(randValue);
            } else if (operationNumber == 1) {
                // addLast
                Integer randValue = StdRandom.uniform(-100, 100);
                SAD.addLast(randValue);
                methodCallsList.append("addLast(")
                        .append(randValue)
                        .append(")")
                        .append("\n");
                ADS.addLast(randValue);
            } else if (operationNumber == 2) {
                // removeFirst
                if (ADS.size() > 0) {
                    Integer sadFirst = SAD.removeFirst();
                    methodCallsList.append("removeFirst() \n");
                    Integer adsFirst = ADS.removeFirst();
                    assertEquals(methodCallsList.toString(), sadFirst, adsFirst);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (ADS.size() > 0) {
                    Integer sadLast = SAD.removeLast();
                    methodCallsList.append("removeLast() \n");
                    Integer adsLast = ADS.removeLast();
                    assertEquals(methodCallsList.toString(), sadLast, adsLast);
                }
            }
        }
    }

    public static void main(String[] args) {
        test0();
    }
}
