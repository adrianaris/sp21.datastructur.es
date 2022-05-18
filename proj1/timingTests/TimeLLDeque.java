package timingTests;
import edu.princeton.cs.algs4.Stopwatch;
import deque.LinkedListDeque;

/**
 * Created by hug.
 */
public class TimeLLDeque {
    private static void printTimingTable(LinkedListDeque<Integer> nS,
                                         LinkedListDeque<Double> times,
                                         LinkedListDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < nS.size(); i += 1) {
            int N = nS.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        LinkedListDeque LLD = new LinkedListDeque();

        LinkedListDeque<Integer> nS = new LinkedListDeque();
        LinkedListDeque<Double> times = new LinkedListDeque();
        LinkedListDeque<Integer> opCounts = new LinkedListDeque();
        int powerOfTwo = 7;
        int M = 10000;
        for (int i = 0; i <= powerOfTwo; i++) {
            int numberOfItems = (int)Math.pow(2, i) * 1000;
            for (int j = 0; j < numberOfItems; j++) {
                LLD.addFirst(j);
            }

            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < M; j++) {
                LLD.get(LLD.size() / 2);
            }
            double time = sw.elapsedTime();
            times.addLast(time);
            nS.addLast(numberOfItems);
            opCounts.addLast(M);
        }

        printTimingTable(nS, times, opCounts);
    }
}
