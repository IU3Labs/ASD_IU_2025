public class Demo {

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.put(5, 50);
        skipList.put(3, 30);
        skipList.put(7, 70);
        skipList.put(1, 10);
        skipList.put(9, 90);
        skipList.print();
        System.out.println("Поиск ключа 3: " + skipList.get(3));
        System.out.println("Поиск ключа 7: " + skipList.get(7));
        skipList.remove(5);
        System.out.println("Удаление элемента с ключом 5:\n");
        skipList.print();
    }
}
