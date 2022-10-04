import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class SumRecursion extends RecursiveTask<Integer> {
    public static final int SEQUENT = 10;
    private final int[] array;

    public SumRecursion(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if (array.length <= SEQUENT) {
            return Arrays.stream(array).sum();
        } else {
            SumRecursion left = new SumRecursion(Arrays.copyOfRange(array, 0, array.length / 2));
            left.fork();
            SumRecursion right = new SumRecursion(Arrays.copyOfRange(array, array.length / 2, array.length));
            int rightAns = right.compute();
            int leftAns = left.join();
            return leftAns + rightAns;
        }
    }
}

