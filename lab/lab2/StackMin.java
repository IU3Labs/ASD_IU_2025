import java.util.ArrayList;

public class StackMin {
    public ArrayList<Integer> mainStack;
    public ArrayList<Integer> minStack;

    public StackMin() {
        this.mainStack = new ArrayList<Integer>();
        this.minStack = new ArrayList<Integer>();
    }

    int length = 0;

    public void Add(int index, int element) {
        if (mainStack.isEmpty()) {
            mainStack.add(element);
            minStack.add(element);
        } else {
            mainStack.add(index, element);

            boolean isAdd = false;
            for (int i = 0; i < length; i++) {
                if (minStack.get(i) >= element) {
                    minStack.add(i, element);
                    isAdd = true;
                    break;
                }
            }
            if (!isAdd) {
                minStack.add(element);
            }
        }
        length++;
    }

    public void Remove() {
        // Это стек: последним пришел - первым вышел
        for (int i = 0; i < length; i++) {
            if (mainStack.get(0) == minStack.get(i)) {
                minStack.remove(i);
                break;
            }
        }
        mainStack.remove(0);
        length--;
    }

    public int Count() {
        return length;
    }


    public void PrintForward() {
        for (int i = 0; i < length; i++) {
            System.out.printf("%d ", mainStack.get(i));
        }
        System.out.println();
    }

    public void PrintBackward() {
        for (int i = length-1; i >= 0; i--) {
            System.out.printf("%d ", mainStack.get(i));
        }
        System.out.println();
    }

    public int GetMin() {
        return minStack.get(0);
    }


    public static void main(String[] args) {
        StackMin stack = new StackMin();
        stack.Add(0, 30);  // Начало
        stack.Add(1, 20);  // Конец
        stack.Add(1, 40);  // Середина
        stack.Add(0, 10);
        stack.Add(0, 50);
        System.out.println("Изначальный стек:");
        stack.PrintForward();
        System.out.printf("Минимальный элемент до удаления: %d\n", stack.GetMin());
        stack.Remove();
        System.out.printf("Минимальный элемент после первого удаления: %d\n", stack.GetMin());
        stack.Remove();
        System.out.printf("Минимальный элемент после второго удаления: %d\n", stack.GetMin());
        System.out.printf("Длина стека: %d\n", stack.Count());
        System.out.println("Вывод стека в прямом порядке: ");
        stack.PrintForward();
        System.out.println("Вывод стека в обратном порядке: ");
        stack.PrintBackward();

    }
}
