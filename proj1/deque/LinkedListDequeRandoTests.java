package deque;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;

public class LinkedListDequeRandoTests {
    @Test
    public static void test0() {
        //randomly call LinkedListDeque and ArrayDequeSolution methods.
        LinkedListDeque<Integer> LLD = new LinkedListDeque();
        ArrayDequeSolution<Integer> ADS = new ArrayDequeSolution();

        int N = 5000;
        StringBuilder methodCallsList = new StringBuilder("\nSequence of method calls until failure:\n");

        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addFirst
                Integer randValue = StdRandom.uniform(-100, 100);
                LLD.addFirst(randValue);
                methodCallsList.append("addFirst(")
                        .append(randValue)
                        .append(")")
                        .append("\n");
                ADS.addFirst(randValue);
            } else if (operationNumber == 1) {
                // addLast
                Integer randValue = StdRandom.uniform(-100, 100);
                LLD.addLast(randValue);
                methodCallsList.append("addLast(")
                        .append(randValue)
                        .append(")")
                        .append("\n");
                ADS.addLast(randValue);
            } else if (operationNumber == 2) {
                // removeFirst
                if (ADS.size() > 0) {
                    Integer sadFirst = LLD.removeFirst();
                    methodCallsList.append("removeFirst() \n");
                    Integer adsFirst = ADS.removeFirst();
                    assertEquals(methodCallsList.toString(), sadFirst, adsFirst);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (ADS.size() > 0) {
                    Integer sadLast = LLD.removeLast();
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
