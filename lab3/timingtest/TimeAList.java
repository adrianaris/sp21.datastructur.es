package timingtest;
import edu.princeton.cs.algs4.Stopwatch;
//import javafx.scene.paint.Stop;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> aList = new AList();

        AList<Integer> Ns = new AList();
        AList<Double> times = new AList();
        AList<Integer> opCounts = new AList();
        int powerOfTwo = 16;
        for (int i = 0; i <= powerOfTwo; i+=1) {
            int numberOfItems = (int)Math.pow(2, i) * 1000;
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < numberOfItems; j+=1) {
                aList.addLast(j);
            }
            double time = sw.elapsedTime();
            times.addLast(time);
            Ns.addLast(numberOfItems);
            opCounts.addLast(numberOfItems);
        }

        printTimingTable(Ns, times, opCounts);
    }
}
