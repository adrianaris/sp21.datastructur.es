public class fib{
  public static int fib(int n) {
    int init = 0;
    if (n <= 1) return init + n;

    return fib(n - 1) + fib(n - 2);
  }
  
  public static int fib2(int n, int k, int f0, int f1) {
    if (n < 2) return f0 + f1;

  }

  public static void main(String[] args) {
    int fib = fib(40);
    System.out.println(fib);
  }
}
