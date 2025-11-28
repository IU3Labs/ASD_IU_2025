//Класс для представления операции с данными
//Хранит тип операции, элемент и дополнительную информацию

package tasks.task3;

public class Operation<T> {
    public enum Type {
        PUSH, POP,           // Операции для стека
        ADD_FIRST, ADD_LAST, // Операции для deque
        REMOVE_FIRST, REMOVE_LAST
    }

    private Type type;       // Тип операции
    private T element;       // Элемент, связанный с операцией
    private T previousState; // Предыдущее состояние (для отмены)

    public Operation(Type type, T element) {
        this.type = type;
        this.element = element;
    }

    public Operation(Type type, T element, T previousState) {
        this.type = type;
        this.element = element;
        this.previousState = previousState;
    }

    // Геттеры
    public Type getType() { return type; }
    public T getElement() { return element; }
    public T getPreviousState() { return previousState; }

    @Override
    public String toString() {
        return "Operation{type=" + type + ", element=" + element +
                ", previousState=" + previousState + "}";
    }
}