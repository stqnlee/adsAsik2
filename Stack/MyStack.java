package Stack;

import MyLists.MyArrayList;

public class MyStack<T extends Comparable<T>> {
    private MyArrayList<T> storage;

    public MyStack() {
        storage = new MyArrayList<>();
    }

    public void push(T item) {
        storage.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        T topItem = storage.get(storage.size() - 1);
        storage.remove(storage.size() - 1);
        return topItem;
    }

    public void peek() {
        System.out.println(storage.get(storage.size() - 1));
    }

    public boolean isEmpty() {
        return storage.size() == 0;
    }

    public int size() {
        return storage.size();
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < storage.size(); i++) {
            System.out.print(" " + storage.get(i));
        }
        System.out.println(" ]");
    }
}