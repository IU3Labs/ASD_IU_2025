package org.example.lab2.util;

/**
 * Мини-утилита для измерения времени выполнения блока кода.
 */
public final class Stopwatch {
    private long start;

    public void start() {
        start = System.nanoTime();
    }

    public long stop() {
        return System.nanoTime() - start;
    }

    public static long measure(Runnable action) {
        long t0 = System.nanoTime();
        action.run();
        return System.nanoTime() - t0;
    }
}