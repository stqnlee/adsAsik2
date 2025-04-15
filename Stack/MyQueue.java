package Stack;

import MyLists.MyLinkedList;

public class MyQueue<T extends Comparable<T>> {
    private MyLinkedList<T> queueStorage;

    public MyQueue() {
        queueStorage = new MyLinkedList<>();
    }

    public void enqueue(T element) {
        queueStorage.addLast(element);
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        T firstElement = queueStorage.getFirst();
        queueStorage.removeFirst();
        return firstElement;
    }

    public void front() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.println(queueStorage.getFirst());
    }

    public boolean isEmpty() {
        return queueStorage.size() == 0;
    }

    public int size() {
        return queueStorage.size();
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < queueStorage.size(); i++) {
            System.out.print(" " + queueStorage.get(i));
        }
        System.out.println(" ]");
    }
}