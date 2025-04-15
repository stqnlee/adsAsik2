package Stack;

import MyLists.MyArrayList;

public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heapStorage;

    public MyMinHeap() {
        heapStorage = new MyArrayList<>();
    }

    public void insert(T item) {
        heapStorage.add(item);
        bubbleUp(heapStorage.size() - 1);
    }

    public T extractMin() {
        if (isEmpty())
            return null;

        T minValue = heapStorage.get(0);
        T lastValue = heapStorage.get(heapStorage.size() - 1);
        heapStorage.set(0, lastValue);
        heapStorage.remove(heapStorage.size() - 1);
        bubbleDown(0);

        return minValue;
    }

    public T peek() {
        return isEmpty() ? null : heapStorage.get(0);
    }

    public boolean isEmpty() {
        return heapStorage.size() == 0;
    }

    public int size() {
        return heapStorage.size();
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("Heap is empty");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < heapStorage.size(); i++) {
            System.out.print(" " + heapStorage.get(i));
        }
        System.out.println(" ]");
    }

    private void bubbleUp(int currentIndex) {
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            T currentValue = heapStorage.get(currentIndex);
            T parentValue = heapStorage.get(parentIndex);
            if (currentValue.compareTo(parentValue) < 0) {
                heapStorage.set(currentIndex, parentValue);
                heapStorage.set(parentIndex, currentValue);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    private void bubbleDown(int currentIndex) {
        int heapSize = heapStorage.size();
        while (true) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;
            int smallestIndex = currentIndex;

            if (leftChildIndex < heapSize && heapStorage.get(leftChildIndex).compareTo(heapStorage.get(smallestIndex)) < 0) {
                smallestIndex = leftChildIndex;
            }
            if (rightChildIndex < heapSize && heapStorage.get(rightChildIndex).compareTo(heapStorage.get(smallestIndex)) < 0) {
                smallestIndex = rightChildIndex;
            }

            if (smallestIndex != currentIndex) {
                T temp = heapStorage.get(currentIndex);
                heapStorage.set(currentIndex, heapStorage.get(smallestIndex));
                heapStorage.set(smallestIndex, temp);
                currentIndex = smallestIndex;
            } else {
                break;
            }
        }
    }
}