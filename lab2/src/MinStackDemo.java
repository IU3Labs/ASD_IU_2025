class MinStackDemo {
    public static void main(String[] args) {
        MinStack stack = new MinStack();

        //проверка добавления
        stack.pushElement(11);
        stack.pushElement(42);
        stack.pushElement(7);
        stack.displayStackState();

        //текущий минимум
        System.out.println("\ncurrent minimum: " + stack.getCurrentMinimum());

        //удаление верхнего элемента
        stack.popElement();

        System.out.println("\nminimum after delete: " + stack.getCurrentMinimum());

        System.out.println("\ntop element: " + stack.getTopElement());

        System.out.println("\nstack size: " + stack.getSize());
    }
}