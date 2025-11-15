package skiplist;

public class TaskSkipList {
    public static void main(String[] args) {
        DataTypeSkipList<Integer> skipList = new DataTypeSkipList<>();

        skipList.insert(5);
        skipList.insert(2);
        skipList.insert(8);
        skipList.insert(1);
        skipList.insert(10);
        skipList.insert(7);

        skipList.print();

        System.out.println("Содержит 7? " + skipList.contains(7));
        System.out.println("Содержит 3? " + skipList.contains(3));

        skipList.delete(5);
        skipList.print();

        System.out.println("Размер: " + skipList.size());
    }
}
