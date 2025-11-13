package util;

public class TimeMeasurement {
    public static long measureTime(Runnable operation) {
        long sstartTime = System.nanoTime();
        operation.run();
        long endTime = System.nanoTime();
        return endTime - sstartTime;
    }
     public static String formatNanos(long nanos) {
        return String.format("%,d нс", nanos);
     }
}