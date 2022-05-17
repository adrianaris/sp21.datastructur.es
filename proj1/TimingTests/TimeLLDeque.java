package TimingTests;
import edu.princeton.cs.algs4.Stopwatch;
import deque.LinkedListDeque;

/**
 * Created by hug.
 */
public class TimeLLDeque {
    private static void printTimingTable(LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
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

        LinkedListDeque<Integer> Ns = new LinkedListDeque();
        LinkedListDeque<Double> times = new LinkedListDeque();
        LinkedListDeque<Integer> opCounts = new LinkedListDeque();
        int powerOfTwo = 7;
        int M = 10000;
        for (int i = 0; i <= powerOfTwo; i+=1) {
            int numberOfItems = (int)Math.pow(2, i) * 1000;
            for (int j = 0; j < numberOfItems; j+=1) {
                LLD.addFirst(j);
            }

            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < M; j+=1){
                LLD.get(LLD.size() / 2);
            }
            double time = sw.elapsedTime();
            times.addLast(time);
            Ns.addLast(numberOfItems);
            opCounts.addLast(M);
        }

        printTimingTable(Ns, times, opCounts);
    }
}
