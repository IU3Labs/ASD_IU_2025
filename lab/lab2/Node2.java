package lab2;

public class Node2 {
    int val; // значение
    Node2 neibour; // ссылка на соседний узел по горизонтали
    Node2 child; // ссылка на узел на уровне ниже

    public Node2(int val, Node2 neibour, Node2 child) {
        this.val = val;
        this.neibour = neibour;
        this.child = child;
    }
}