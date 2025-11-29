package org.example.lab2.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Утилита для красивого вывода таблиц и заголовков.
 */
public final class Printers {

    private Printers() { }

    public static void printHeader(String title) {
        System.out.println("\n==== " + title + " ====");
    }

    public static void printTable(Map<String, Long> rows, String unit) {
        int width = rows.keySet().stream().mapToInt(String::length).max().orElse(10) + 2;
        System.out.printf("%-" + width + "s | %s%n", "Operation", "Time (" + unit + ")");
        System.out.println("-".repeat(width + 16));
        rows.forEach((k, v) -> System.out.printf("%-" + width + "s | %d%n", k, v));
    }

    public static Map<String, Long> map() {
        return new LinkedHashMap<>();
    }
}