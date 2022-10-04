public class MyTimer {
    private long startTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public int finish() {
        long endTime = System.currentTimeMillis();
        return (int)(endTime - startTime);
    }
}
