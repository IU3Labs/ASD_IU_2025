
public class SelfOrganizedList {
    private SinglyNode head; //указатель на начало списка

    public SelfOrganizedList() {
        this.head = null;
    }

    //метод поиска элемента с автоматической реорганизацией Move-to-Front
    public boolean search(int value) {
        if (head == null) {
            return false;
        }

        //если искомый элемент первый
        if (head.data == value) {
            return true;
        }

        //ищем элемент, запоминая предыдущий узел
        SinglyNode current = head;
        SinglyNode previous = null;

        while (current != null && current.data != value) {
            previous = current;
            current = current.next;
        }

        if (current == null) { //элемент не найден
            return false;
        }

        //выполняем Move-to-Front т.е. перемещаем найденный узел в начало
        previous.next = current.next;  //отвязываем узел
        current.next = head; //ставим его в начало
        head = current; //обновляем голову

        return true;
    }

    public void add(int value) { //добавление эл-та в конец
        if (search(value)) {
            return; //если уже существует то не добавляем
        }

        SinglyNode newNode = new SinglyNode(value);
        if (head == null) {
            head = newNode;
        } else {
            SinglyNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void display() {
        if (head == null) {
            System.out.println("Список пуст");
            return;
        }

        System.out.print("Список: ");
        SinglyNode current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        SelfOrganizedList list = new SelfOrganizedList();

        list.display();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.display(); // 10 -> 20 -> 30 -> 40

        list.search(30);
        list.display(); //30 -> 10 -> 20 -> 40

        list.search(20);
        list.display(); // 20 -> 30 -> 10 -> 40

        list.search(10);
        list.display(); // 10 -> 20 -> 30 -> 40


        boolean found = list.search(99);
        System.out.println("Найден 99? " + found); // false
        list.display(); // порядок не изменился

        list.search(20);
        list.search(20);
        list.display(); //20 будет в начале
    }
}