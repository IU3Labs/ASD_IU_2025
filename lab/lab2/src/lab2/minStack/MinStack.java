package lab2.minStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    private Integer size;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
        size = 0;
    }

    public void push(int newElement){
        stack.push(newElement);
        if (minStack.empty() || newElement <= minStack.peek()){
            minStack.push(newElement);
        }
        size++;
    }

    public Integer pop(){
        if (size == 0) throw new RuntimeException("MinStack empty");
        if (!minStack.empty() && minStack.peek() == stack.peek()){
            minStack.pop();
        }
        size--;
        return stack.pop();
    }

    public Integer peek(){
        if (size == 0) throw new RuntimeException("MinStack empty");
        return stack.peek();
    }
    public Integer peekMin(){
        if (size == 0) throw new RuntimeException("MinStack empty");
        return minStack.peek();
    }
    public Integer size(){
        return size;
    }
    public void print(){
        ArrayList<Integer> list = new ArrayList<>(stack);
        System.out.println(list.toString());
        System.out.println("min = " + minStack.peek());
    }

    public void printRevers(){
        ArrayList<Integer> list = new ArrayList<>(stack);
        Collections.reverse(list);
        System.out.println(list.toString());
        System.out.println("min = " + minStack.peek());
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(6);
        minStack.push(7);
        minStack.push(4);
        minStack.push(5);

        System.out.println("size "+minStack.size());
//        size 4
        minStack.print();
//        [6, 7, 4, 5]
//        min = 4
        minStack.printRevers();
//        [5, 4, 7, 6]
//        min = 4
        minStack.pop();
        minStack.pop();

        System.out.println("peek "+minStack.peek());
//        peek 7
        System.out.println("peekMin "+minStack.peekMin());
//        peekMin 6
        minStack.pop();
        minStack.pop();
    }
}