package flik;

/** An Integer tester created by Flik Enterprises.
 * @author Josh Hug
 * */
public class Flik {
    /** @param a Value 1
     *  @param b Value 2
     *  @return Whether a and b are the same */
    public static boolean isSameNumber(Integer a, Integer b) {
        // Apparently for values bigger than 127 Integer class returns a new object
        // so, basically we have to different references and that's why "==" doesn't work
        return a.equals(b);
    }
}
