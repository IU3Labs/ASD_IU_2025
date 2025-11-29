package structures;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private List<ElementFrequency> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    };

    public void add(ElementFrequency element) {
        heap.add(element);
        heapUp(heap.size() - 1);
    };

    public int size() {
        return heap.size();
    };

    public ElementFrequency pollMin() {
        if (heap.isEmpty()) {
            return null;
        };
        ElementFrequency minElement = heap.get(0);
        ElementFrequency lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapDown(0);
        };
        return minElement;
    };

    private void swap(int first, int second) {
        ElementFrequency temp = heap.get(first);
        heap.set(first, heap.get(second));
        heap.set(second, temp);
    };

    private void heapUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap.get(index).frequency < heap.get(parentIndex).frequency) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        };
    };

    private void heapDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;
        if (leftChildIndex < heap.size() && heap.get(leftChildIndex).frequency < heap.get(smallest).frequency) {
            smallest = leftChildIndex;
        };
        if (rightChildIndex < heap.size() && heap.get(rightChildIndex).frequency < heap.get(smallest).frequency) {
            smallest = rightChildIndex;
        };
        if (smallest != index) {
            swap(index, smallest);
            heapDown(smallest);
        };
    };

    public List<Integer> getElements() {
        List<Integer> elements = new ArrayList<>();
        for (ElementFrequency elementFreq : heap) {
            elements.add(elementFreq.element);
        };
        return elements;
    };

};
