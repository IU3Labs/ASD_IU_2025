//Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
//списка и хеш-таблицы. Прокомментировать код.

import java.util.*;
public class LRUCache<K, V> {

    private Node<K, V> start; // Начало кэша; Последний использованный элемент
    private Node<K, V> end; // Конец кэша; Самый ранний по использованию(те все кто перед ним были использованы после него)
    private final int maxSize;  // Предел вместимости
    private final Map<K, Node<K, V>> cache; // Хеш-таблица для доступа к элементам

    private static class Node<K,V> { //Элемент двусвязанного списка
        K key;
        V value;
        Node<K,V> prev;
        Node<K,V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new HashMap<>(maxSize);
        this.start = null;
        this.end = null;
    }

    public void printCache() {
        Node<K, V> current = start;
        System.out.print("Кэш: ");
        while (current != null) {
            if(current != end) {
                System.out.print(current.key + "--" + current.value + ", ");
            }else{
                System.out.print(current.key + "--" + current.value + ".");
            }
            current = current.next;
        }
        System.out.println();
    }

    public V getElem(K key) { // Получение значения из кэша по ключу
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null; // Ключа нет в кэше
        }
        moveToStart(node); // Взаимодействие с элементом -> переносим его в начало списка
        return node.value;
    }

    private V deleteElem(K key){//Удаление элемента по ключу
        if (cache.containsKey(key)) {//Проверка существования ключа в кэше
            Node<K, V> middleNode = cache.get(key);//Ссылка на найденный элемент
            if ((middleNode.next==null)&&(middleNode.prev==null)){//Проверка на единственный элемент
                start=null;
                end=null;
            }else {//Склейка списка
                if (middleNode.prev != null) {
                    middleNode.prev.next = middleNode.next;
                }
                if (middleNode.next != null) {
                    middleNode.next.prev = middleNode.prev;
                }
            }
            return middleNode.value;
        } else {
            return null;
        }
    }

    public void addElem(K key, V value) {// Добавление элемента
        if (cache.containsKey(key)) {//Проверка на существование ключа
            Node<K, V> node = cache.get(key);//Получаем и сохраняем ссылку на существующий элемент
            node.value = value;//Обновляем значение элемента
            moveToStart(node);//Взаимодейтсвие -> переносим в начало
        } else {//Искомого ключа нет
            Node<K, V> newNode = new Node<>(key, value);//Создание нового элемента
            cache.put(key, newNode);//Помещение в кэш
            addToStart(newNode);//Добавляем сразу в начало списка как самый свежий элемент

            if (cache.size() > maxSize) {//Вышли за предел вместимости
                Node<K, V> endNode = deleteEnd();//Удаляем из списка элемент который обновлялся давнее всех
                cache.remove(endNode.key);//Удаляем из кэша
            }
        }
    }

    private void addToStart(Node<K, V> node) {// Добавление нового элемента в начало списка
        node.next = start;//Ставим элемент перед началом
        node.prev = null;

        if (start != null) {
            start.prev = node;//Бывший первый элемент теперь второй
        }
        start = node;//Обновляем начало
        if (end == null) {
            end = start; // Если список был пуст, обновляем end
        }
    }

    private void moveToStart(Node<K, V> node) {//Перенос элемента в начало списка при взаимодейтсвии
        if (node == start) {//Проверяем на нахождение в начале списка
            return;
        }

        if (node.prev != null) {
            node.prev.next = node.next;//Склеиваем список в месте переносимого элемента
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }

        if (node == end) {
            end = node.prev; // При переносе конца обновляем сам конец
        }

        node.next = start;//Ставим элемент перед началом
        node.prev = null;
        if (start != null) {
            start.prev = node;//Бывший первый элемент теперь второй
        }
        start = node;//Обновляем начало

        if (end == null) {
            end = start; // Если список был пуст, обновляем end
        }
    }

    private Node<K, V> deleteEnd() {//Удаление конца списка
        if (end == null) { //Проверка на пустой список
            return null;
        }
        Node<K, V> endNode = end;
        end = end.prev;//Сдвигаем конец списка

        if (end != null) {
            end.next = null;
        } else {
            start = null;//Случай пустого списка
        }
        return endNode;
    }

    public static void main(String[] args) {
        LRUCache<Integer,String> artGroup = new LRUCache<>(10);

        //Состав художественного кружка
        artGroup.addElem(1,"Олеся");
        artGroup.addElem(2,"Аня");
        artGroup.addElem(3,"Кристина");
        artGroup.addElem(4,"Андрей");
        artGroup.addElem(5,"Сережа");
        artGroup.addElem(6,"Юля");
        artGroup.addElem(7,"Ангелина");
        artGroup.addElem(8,"Арсений");
        artGroup.addElem(9,"Вова");
        artGroup.addElem(10,"Дима");

        System.out.println("Список группы в порядке записи в кружок: ");
        artGroup.printCache();

        System.out.println("Посетили следующее занятие(в порядке прибытия): ");
        System.out.println(artGroup.getElem(5));
        System.out.println(artGroup.getElem(10));
        System.out.println(artGroup.getElem(7));
        System.out.println(artGroup.getElem(1));
        System.out.println(artGroup.getElem(3));

        System.out.println("Список группы по посещаемости: ");
        artGroup.printCache();

        System.out.println("Новые заявки в кружок: ");
        artGroup.addElem(11, "Олег");
        artGroup.addElem(12, "Миша");
        artGroup.addElem(2,"Аня");//подтверждает нахождение в группе

        System.out.println("Список группы после приема новых участников: ");
        artGroup.printCache();

        System.out.println("Подали заявку на выход из группы: ");
        System.out.println(artGroup.getElem(4)==null?"Уже отчислен":artGroup.deleteElem(4));
        System.out.println(artGroup.getElem(9)==null?"Уже отчислен":artGroup.deleteElem(9));

        System.out.println("Итоговый список группы: ");
        artGroup.printCache();
    }
}

//Список группы в порядке записи в кружок:
//Кэш: 10--Дима, 9--Вова, 8--Арсений, 7--Ангелина, 6--Юля, 5--Сережа, 4--Андрей, 3--Кристина, 2--Аня, 1--Олеся.
//Посетили следующее занятие(в порядке прибытия):
//Сережа
//Дима
//Ангелина
//Олеся
//Кристина
//Список группы по посещаемости:
//Кэш: 3--Кристина, 1--Олеся, 7--Ангелина, 10--Дима, 5--Сережа, 9--Вова, 8--Арсений, 6--Юля, 4--Андрей, 2--Аня.
//Новые заявки в кружок:
//Список группы после приема новых участников:
//Кэш: 2--Аня, 12--Миша, 11--Олег, 3--Кристина, 1--Олеся, 7--Ангелина, 10--Дима, 5--Сережа, 9--Вова, 8--Арсений.
//Подали заявку на выход из группы:
//Уже отчислен
//Вова
//Итоговый список группы:
//Кэш: 9--Вова, 2--Аня, 12--Миша, 11--Олег, 3--Кристина, 1--Олеся, 7--Ангелина, 10--Дима, 5--Сережа, 8--Арсений.