import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Main {
    public static void main(String[] args) {
        int amount = 100000000;
        int finish;
        int sum;
        int[] array = new int[amount];
        MyTimer time = new MyTimer();

        time.start();
        fillArray(array);
        finish = time.finish();
        System.out.println("Time filling array: " + finish);

        final int numberOfProcessors = Runtime.getRuntime().availableProcessors();
        final ForkJoinPool pool = new ForkJoinPool(numberOfProcessors);

        time.start();
        final ForkJoinTask<Integer> result = pool.submit(new SumRecursion(array));
        sum = result.join();
        finish = time.finish();
        System.out.println("Time for SumRecursion: " + finish + ". Sum: " + sum);

        time.start();
        sum = pool.invoke(new SumRecursion(array));
        finish = time.finish();
        System.out.println("Time for SumRecursion2: " + finish + ". Sum: " + sum);

        Recursion recursion = new Recursion(array);
        time.start();
        sum = recursion.sum();
        finish = time.finish();
        System.out.println("Time for Recursion: " + finish + ". Sum: " + sum);

        time.start();
        sum = sum(array);
        finish = time.finish();
        System.out.println("Time for method sum: " + finish + ". Sum: " + sum);
    }

    public static void fillArray(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (count != 10) {
                array[i] = count + 1;
                count++;
            } else {
                count = 1;
                array[i] = count;
            }
        }
    }

    public static int sum(int[] array) {
        int sum = 0;
        for (int j : array) {
            sum += j;
        }
        return sum;
    }
}
