package MainTask;

// Класс для измерения времени выполнения операций
public class PerformanceTimer {
    private long startTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public long stop() {
        return System.nanoTime() - startTime;
    }

    public static void printResult(String operation, long duration) {
        if (duration == -1) {
            System.out.printf("%s: %s%n", operation, "Не применимо");
        } else {
            System.out.printf("%s: %.3f мс%n",
                    operation, duration / 1_000_000.0);
        }
    }
}