package randoTests;
import static org.junit.Assert.*;

import deque.ArrayDeque;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;

public class ArrayDequeRandoTests {
    @Test
    public static void test0() {
        //randomly call ArrayDeque and ArrayDequeSolution methods.
        ArrayDeque<Integer> sad = new ArrayDeque();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution();

        int N = 50000;
        StringBuilder methodCallsList =
                new StringBuilder("\nSequence of method calls until failure:\n");

        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addFirst
                Integer randValue = StdRandom.uniform(-100, 100);
                sad.addFirst(randValue);
                methodCallsList.append("addFirst(")
                        .append(randValue)
                        .append(")")
                        .append("\n");
                ads.addFirst(randValue);
            } else if (operationNumber == 1) {
                // addLast
                Integer randValue = StdRandom.uniform(-100, 100);
                sad.addLast(randValue);
                methodCallsList.append("addLast(")
                        .append(randValue)
                        .append(")")
                        .append("\n");
                ads.addLast(randValue);
            } else if (operationNumber == 2) {
                // removeFirst
                if (ads.size() > 0) {
                    Integer sadFirst = sad.removeFirst();
                    methodCallsList.append("removeFirst() \n");
                    Integer adsFirst = ads.removeFirst();
                    assertEquals(methodCallsList.toString(), sadFirst, adsFirst);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (ads.size() > 0) {
                    Integer sadLast = sad.removeLast();
                    methodCallsList.append("removeLast() \n");
                    Integer adsLast = ads.removeLast();
                    assertEquals(methodCallsList.toString(), sadLast, adsLast);
                }
            }
        }
    }

    public static void main(String[] args) {
        test0();
    }
}
