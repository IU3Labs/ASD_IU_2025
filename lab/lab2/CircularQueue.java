import java.util.ArrayList;

public class CircularQueue {
    private ArrayList<Integer>data;
    private int length;
    private int deque_ptr;
    private int enque_ptr;

    public CircularQueue(int len) {
        data = new ArrayList<>();
        length = len;
        deque_ptr = -1;
        enque_ptr = -1;
    }

    public boolean IsFull() {
        if (deque_ptr == 0 && enque_ptr == length-1) {
            return true;
        }
        if (deque_ptr == enque_ptr + 1) {
            return true;
        }
        return false;
    }

    public boolean IsEmpty() {
        return (deque_ptr == -1);
    }

    public void EnQue(int element) {
        if (IsFull()) {
            System.out.println("Очередь заполнена");
            return;
        }
        if (deque_ptr == -1) deque_ptr = 0;
        enque_ptr = (enque_ptr + 1) % length;
        data.add(enque_ptr, element);
    }

    public int DeQue() {
        if (IsEmpty()) {
            System.out.println("Очередь пуста");
            return 0;
        }
        int temp;
        temp = data.remove(deque_ptr);
        if (deque_ptr == enque_ptr) {  // Последний элемент очереди
            deque_ptr = -1;
            enque_ptr = -1;
        } else {
            deque_ptr = (deque_ptr + 1) % length;
        }
        return temp;
    }

    public void Display() {
        int i;
        if (IsEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }
        for (i = deque_ptr; i != enque_ptr; i = (i+1) % length) {
            System.out.println(data.get(i));
        }
        System.out.println(data.get(i));
    }

    public int QLength() {
        return (-deque_ptr + enque_ptr + 1);
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
        int[] data = {10, 20, 30, 40, 50};
        System.out.println("Заполнение очереди до упора...");
        for (int i = 0; i < data.length; i++) {
            queue.EnQue(data[i]);
            System.out.printf("Добавлен элемент. Длина очереди: %d\n", queue.QLength());
        }
        System.out.println("\nИ попробуем добавить еще один элемент:");
        queue.EnQue(60);
        int temp;
        temp = queue.DeQue();
        System.out.printf("\nДостанем первый элемент очереди: %d\n", temp);
        System.out.println("\nТеперь попробуем добавить еще один элемент снова...");
        queue.EnQue(60);
        System.out.println("\nВыведем всю очередь:");
        queue.Display();





    }

}


