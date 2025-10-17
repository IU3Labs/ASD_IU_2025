package MainTask;

// Основное задание
public class MainTask {
    public static void main(String[] args) {
        System.out.println("Тестирование производительности с " +
                DataStructureTester.getDataSize() + " элементов");

        DataStructureTester tester = new DataStructureTester();

        tester.runAllTests();
    }
}