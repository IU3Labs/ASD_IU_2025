package org.example.lab2.core;

import org.example.lab2.util.Printers;
import java.util.Map;

/**
 * Главный класс для запуска основного задания.
 */
public class App {
    public static void main(String[] args) {
        int n = CollectionsBenchmark.DEFAULT_N;
        CollectionsBenchmark bm = new CollectionsBenchmark();
        bm.fillCollections(n);

        Map<String, Long> results = Printers.map();
        Printers.printHeader("Benchmark (N = " + n + ")");
        results.put("Add to end (ArrayList)", bm.addToEndArrayList());
        results.put("Add to start (LinkedList)", bm.addToStartLinkedList());
        results.put("Remove last (ArrayList)", bm.removeLastArrayList());
        results.put("Remove first (LinkedList)", bm.removeFirstLinkedList());
        results.put("Get middle (ArrayList)", bm.getMiddleFromArrayList());
        results.put("Get last (HashMap)", bm.getLastFromHashMap());
        Printers.printTable(results, "ns");
    }
}