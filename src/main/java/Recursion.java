import java.util.Arrays;

public class Recursion {
    public static final int SEQUENT = 10;
    private final int[] array;

    public Recursion(int[] array) {
        this.array = array;
    }
    public int sum() {
        if (array.length <= SEQUENT) {
            return Arrays.stream(array).sum();
        }
        Recursion first = new Recursion(Arrays.copyOfRange(array, 0, array.length / 2));
        Recursion second = new Recursion(Arrays.copyOfRange(array, array.length / 2, array.length));
        int i = first.sum();
        int y = second.sum();
        return i + y;
    }
}
