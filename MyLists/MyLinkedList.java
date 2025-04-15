package MyLists;

import java.util.Iterator;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {

    private class Node {
        T value;
        Node nextNode;
        Node previousNode;

        Node(T value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T item) {
        addLast(item);
    }

    public void addFirst(T item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.nextNode = head;
            head.previousNode = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T item) {
        Node newNode = new Node(item);
        if (tail == null) {
            head = tail = newNode;
        } else {
            newNode.previousNode = tail;
            tail.nextNode = newNode;
            tail = newNode;
        }
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        return getNodeAt(index).value;
    }

    public T getFirst() {
        if (head == null) throw new IllegalStateException("List is empty");
        return head.value;
    }

    public T getLast() {
        if (tail == null) throw new IllegalStateException("List is empty");
        return tail.value;
    }

    public void remove(int index) {
        checkIndex(index);
        Node nodeToRemove = getNodeAt(index);
        removeNode(nodeToRemove);
    }

    public void removeFirst() {
        if (head == null) throw new IllegalStateException("List is empty");
        removeNode(head);
    }

    public void removeLast() {
        if (tail == null) throw new IllegalStateException("List is empty");
        removeNode(tail);
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public void set(int index, T item) {
        checkIndex(index);
        getNodeAt(index).value = item;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size) {
            addLast(element);
            return;
        }
        Node currentNode = getNodeAt(index);
        Node newNode = new Node(element);

        newNode.previousNode = currentNode.previousNode;
        newNode.nextNode = currentNode;
        currentNode.previousNode.nextNode = newNode;
        currentNode.previousNode = newNode;

        size++;
    }

    public int size() {
        return size;
    }

    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    public int indexOf(Object object) {
        int currentIndex = 0;
        for (Node current = head; current != null; current = current.nextNode) {
            if (current.value.equals(object)) return currentIndex;
            currentIndex++;
        }
        return -1;
    }

    public int lastIndexOf(Object object) {
        int currentIndex = size - 1;
        for (Node current = tail; current != null; current = current.previousNode) {
            if (current.value.equals(object)) return currentIndex;
            currentIndex--;
        }
        return -1;
    }

    public Object[] toArray() {
        Object[] resultArray = new Object[size];
        int currentIndex = 0;
        for (Node current = head; current != null; current = current.nextNode) {
            resultArray[currentIndex++] = current.value;
        }
        return resultArray;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node currentNode = head;
            public boolean hasNext() {
                return currentNode != null;
            }
            public T next() {
                T value = currentNode.value;
                currentNode = currentNode.nextNode;
                return value;
            }
        };
    }

    public void sort() {
        if (size <= 1) return;
        Object[] array = toArray();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                Comparable<T> first = (Comparable<T>) array[j];
                T second = (T) array[j + 1];
                if (first.compareTo(second) > 0) {
                    Object temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        Node current = head;
        for (int i = 0; i < size; i++) {
            current.value = (T) array[i];
            current = current.nextNode;
        }
    }

    private Node getNodeAt(int index) {
        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) current = current.nextNode;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) current = current.previousNode;
        }
        return current;
    }

    private void removeNode(Node node) {
        if (node.previousNode != null) node.previousNode.nextNode = node.nextNode;
        else head = node.nextNode;
        if (node.nextNode != null) node.nextNode.previousNode = node.previousNode;
        else tail = node.previousNode;
        size--;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index);
    }
}