import service.CollectionPerformanceTester;

public class Main {
    private static final int DATA_SIZE = 10_000_000;

    public static void main(String[] args) {
        System.out.println(" Запуск тестирования производительности коллекций");
        System.out.println("Размер тестовых данных: " + DATA_SIZE + " элементов");
        System.out.println("Все результаты выводятся в наносекундах (нс)\n");

        try {
            CollectionPerformanceTester tester = new CollectionPerformanceTester(DATA_SIZE);
            tester.runAllTests();
            System.out.println("\n Тестирование завершено успешно!");
        } catch (OutOfMemoryError e) {
            System.err.println(" Ошибка: недостаточно памяти для тестирования с " + DATA_SIZE + " элементами");
            System.err.println("Попробуйте уменьшить значение DATA_SIZE в классе Main");
        } catch (Exception e) {
            System.err.println(" Произошла ошибка во время тестирования: " + e.getMessage());
            e.printStackTrace();
        }
    }
}