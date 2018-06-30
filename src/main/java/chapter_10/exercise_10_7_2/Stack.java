package chapter_10.exercise_10_7_2;

import java.util.LinkedList;

public class Stack<T> {

    private LinkedList<T> list = new LinkedList<>();

    public T pop() {
        if(list.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return list.removeLast();
    }

    public void push(T element) {
        if(element == null) {
            throw new IllegalArgumentException();
        }
        list.add(element);
    }
}
