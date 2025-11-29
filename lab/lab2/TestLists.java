public class TestLists {

    public static void main(String[] args) {
        System.out.println("    Тестирование Move-to-Front списка    ");
        testMoveToFront();

        System.out.println("\n    Тестирование Transpose списка    ");
        testTranspose();

        System.out.println("\n    Тестирование Count списка    ");
        testCount();
    }

    private static void testMoveToFront() {
        MoveToFrontList<Integer> list = new MoveToFrontList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.print("Начальный список: ");
        list.print();

        // Тестируем поиск и реорганизацию
        System.out.println("Поиск 3: " + list.find(3));
        System.out.print("Cписок после поиска 3: ");
        list.print();

        System.out.println("Поиск 2: " + list.find(2));
        System.out.print("Cписок после поиска 2: ");
        list.print();

        System.out.println("Поиск 4: " + list.find(4));
        System.out.print("Cписок после поиска 4:  ");
        list.print();

        System.out.println("Поиск 1: " + list.find(1));
        System.out.print("Cписок после поиска 1:  ");
        list.print();

        System.out.println("Поиск 10: " + list.find(10));
        System.out.print("Cписок после поиска 10:  ");
        list.print();

        System.out.println("Размер списка: " + list.size());
    }

    private static void testTranspose() {
        TransposeList<Integer> list = new TransposeList<>();


        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        // Тестируем поиск и реорганизацию
        System.out.print("Начальное состояние: ");
        list.print();

        System.out.println("Поиск 3: " + list.find(3));
        System.out.print("Cписок после поиска 3: ");
        list.print();

        System.out.println("Поиск 2: " + list.find(2));
        System.out.print("Cписок после поиска 2: ");
        list.print();

        System.out.println("Поиск 3: " + list.find(3));
        System.out.print("Cписок после поиска 3: ");
        list.print();

        System.out.println("Поиск 1: " + list.find(1));
        System.out.print("Cписок после поиска 1: ");
        list.print();

        System.out.println("Поиск 10: " + list.find(10));
        System.out.print("Cписок после поиска 10: ");
        list.print();

        System.out.println("Размер списка: " + list.size());
    }

    private static void testCount() {
        CountList<Integer> list = new CountList<>();


        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.print("Начальное состояние: ");
        list.print();

        // Тестируем поиск и реорганизацию
        System.out.println("Поиск 3: " + list.find(3));
        System.out.print("Cписок после поиска 3: ");
        list.print();

        System.out.println("Поиск 2: " + list.find(2));
        System.out.print("Cписок после поиска 2: ");
        list.print();

        System.out.println("Поиск 3: " + list.find(3));
        System.out.print("Cписок после поиска 3: ");
        list.print();

        System.out.println("Поиск 1: " + list.find(1));
        System.out.print("Cписок после поиска 1: ");
        list.print();

        System.out.println("Поиск 4: " + list.find(4));
        System.out.print("Cписок после поиска 4: ");
        list.print();

        System.out.println("Поиск 10: " + list.find(10));
        System.out.print("Cписок после поиска 10: ");
        list.print();

        System.out.println("Размер списка: " + list.size());
    }
}
