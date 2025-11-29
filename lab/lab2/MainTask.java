import java.util.*;

public class MainTask {

    public static class Participant {
        Long identifier;
        String fullName;

        public Participant(Long identifier, String fullName) {
            this.identifier = identifier;
            this.fullName = fullName;
        }
    }

    private static final int ELEMENT_COUNT = 1_000_000;

    public static void main(String[] parameters) {
            HashSet<Participant> thirdCollection = new HashSet<>();
            HashMap<Long, Participant> fourthCollection = new HashMap<>();
            ArrayList<Participant> firstCollection = new ArrayList<>();
            LinkedList<Participant> secondCollection = new LinkedList<>();

        for (long counter = 0; counter < ELEMENT_COUNT; counter++) {
            Participant participant = new Participant(counter, "Participant_" + counter);
            firstCollection.add(participant);
            secondCollection.add(participant);
            thirdCollection.add(participant);
            fourthCollection.put(counter, participant);
        }

        System.out.println("\n HashSet ");
        setRun(thirdCollection);

        System.out.println("\n ArrayList ");
        listRun(firstCollection);

        System.out.println("\n HashMap ");
        mapRun(fourthCollection);

        System.out.println("\n LinkedList ");
        listRun(secondCollection);
    }

    private static long Time(Runnable task) {
        long beginning = System.nanoTime();
        task.run();
        return System.nanoTime() - beginning;
    }

    private static void listRun(List<Participant> dataList) {
        System.out.println("Добавление в конец: " + Time(() ->
                dataList.add(new Participant(1_000_001L, "Additional"))) + " нс");
        System.out.println("Добавление в начало: " + Time(() ->
                dataList.add(0, new Participant(1_000_002L, "Additional"))) + " нс");
        System.out.println("Удаление последнего: " + Time(() ->
                dataList.remove(dataList.size() - 1)) + " нс");
        System.out.println("Удаление первого: " + Time(() ->
                dataList.remove(0)) + " нс");
        System.out.println("Получение среднего: " + Time(() ->
                dataList.get(ELEMENT_COUNT / 2)) + " нс");
        System.out.println("Получение последнего: " + Time(() ->
                dataList.get(ELEMENT_COUNT - 1)) + " нс");
    }

    private static void setRun(Set<Participant> dataSet) {
        Participant firstItem = new Participant(1_000_001L, "ItemA");
        Participant secondItem = new Participant(1_000_002L, "ItemB");

        System.out.println("Добавление элемента: " + Time(() ->
                dataSet.add(firstItem)) + " нс");
        System.out.println("Добавление второго: " + Time(() ->
                dataSet.add(secondItem)) + " нс");
        System.out.println("Удаление элемента: " + Time(() ->
                dataSet.remove(firstItem)) + " нс");
        System.out.println("Удаление второго: " + Time(() ->
                dataSet.remove(secondItem)) + " нс");
        System.out.println("Проверка среднего: " + Time(() ->
                dataSet.contains(firstItem)) + " нс");
        System.out.println("Проверка последнего: " + Time(() ->
                dataSet.contains(secondItem)) + " нс");
    }

    private static void mapRun(Map<Long, Participant> dataMap) {
        System.out.println("Добавление записи: " + Time(() ->
                dataMap.put(1_000_001L, new Participant(1_000_001L, "ItemC"))) + " нс");
        System.out.println("Добавление второй записи: " + Time(() ->
                dataMap.put(1_000_002L, new Participant(1_000_002L, "ItemD"))) + " нс");
        System.out.println("Удаление записи: " + Time(() ->
                dataMap.remove(1_000_001L)) + " нс");
        System.out.println("Удаление второй записи: " + Time(() ->
                dataMap.remove(1_000_002L)) + " нс");
        System.out.println("Получение среднего элемента: " + Time(() ->
                dataMap.get(500_000L)) + " нс");
        System.out.println("Получение последнего элемента: " + Time(() ->
                dataMap.get(999_999L)) + " нс");
    }
}

/*HashSet
Добавление элемента: 11200 нс
Добавление второго: 4300 нс
Удаление элемента: 13100 нс
Удаление второго: 6200 нс
Проверка среднего: 9500 нс
Проверка последнего: 4300 нс

 ArrayList
Добавление в конец: 9300 нс
Добавление в начало: 621200 нс
Удаление последнего: 51300 нс
Удаление первого: 866100 нс
Получение среднего: 10700 нс
Получение последнего: 7200 нс

 HashMap
Добавление записи: 12700 нс
Добавление второй записи: 5600 нс
Удаление записи: 13700 нс
Удаление второй записи: 7000 нс
Получение среднего элемента: 24000 нс
Получение последнего элемента: 5000 нс

 LinkedList
Добавление в конец: 5100 нс
Добавление в начало: 20700 нс
Удаление последнего: 8700 нс
Удаление первого: 2400 нс
Получение среднего: 8198100 нс
Получение последнего: 3800 нс
*/
