package minstack;

public class TaskMinStack {
    public static void main(String[] args){
        DataTypeMinStack stack = new DataTypeMinStack();
        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(2);
        stack.push(4);
        System.out.println(stack.getMin()); //2
        stack.pop(); //убрали 4
        System.out.println("Минимум после 1 удаления: " + stack.getMin()); //2
        stack.pop(); //убрали 2
        System.out.println("Минимум после 2 удалений: " + stack.getMin()); //3
    }

}
