/*задание А2
* в кольцевом буфере элементы хранятся в массиве фиксированного размера, а указатели "бегают по кругу".*/

import java.util.Scanner;

public class CircularQueue {

    private int[] buffer;
    private int startIndex;
    private int endIndex;
    private int currentSize;
    private int maxCapacity;

    public CircularQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        buffer = new int[maxCapacity];
        startIndex = 0;
        endIndex = -1;
        currentSize = 0;
    }

    public void addElement(int value) {
        if (full()) {
            startIndex = (startIndex + 1) % maxCapacity;
            currentSize--;
            System.out.println("Буфер заполнен — удаляем самый старый элемент");
        }

        endIndex = (endIndex + 1) % maxCapacity;
        buffer[endIndex] = value;
        currentSize++;
        System.out.println("Добавлен элемент: " + value);
    }

    public int removeElement() {
        if (empty()) {
            System.out.println("Буфер пуст — удаление невозможно");
            return -1;
        }
        int removedValue = buffer[startIndex];
        startIndex = (startIndex + 1) % maxCapacity;
        currentSize--;
        System.out.println("Удалён элемент: " + removedValue);
        return removedValue;
    }

    public int getFirstElement() {
        if (empty()) {
            System.out.println("Буфер пуст");
            return -1;
        }
        return buffer[startIndex];
    }

    public boolean empty() {
        return currentSize == 0;
    }

    public boolean full() {
        return currentSize == maxCapacity;
    }

    public void displayBuffer() {
        if (empty()) {
            System.out.println("Буфер пуст");
            return;
        }
        System.out.println("Содержимое буфера:");
        for (int i = 0; i < currentSize; i++) {
            int position = (startIndex + i) % maxCapacity;
            System.out.print(buffer[position] + " ");
        }
        System.out.println("\n");
    }

    public void initializeBuffer() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Введите количество элементов (не больше " + maxCapacity + "): ");
        int count = inputScanner.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.print("Введите элемент " + (i + 1) + ": ");
            addElement(inputScanner.nextInt());
        }
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Укажите размер буфера: ");
        int bufferSize = inputScanner.nextInt();

        CircularQueue circularBuffer = new CircularQueue(bufferSize);

        circularBuffer.initializeBuffer();
        circularBuffer.displayBuffer();

        System.out.println("Удаляем два элемента ");
        circularBuffer.removeElement();
        circularBuffer.removeElement();
        circularBuffer.displayBuffer();

        System.out.println("Добавляем новые элементы: ");
        circularBuffer.addElement(99);
        circularBuffer.addElement(100);
        circularBuffer.displayBuffer();

        System.out.println("Первый элемент: " + circularBuffer.getFirstElement());

        while (true) {
            System.out.print("Введите число для добавления: ");
            int newValue = inputScanner.nextInt();

            if (newValue == -1) {
                System.out.println("Завершение работы программы");
                break;
            }

            circularBuffer.addElement(newValue);
            circularBuffer.displayBuffer();
            System.out.println("Первый элемент: " + circularBuffer.getFirstElement());
        }
    }
}