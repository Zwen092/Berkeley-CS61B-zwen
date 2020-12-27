public class ArrayDeque<T> {
    private T[] items;
    private int initialLength;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        initialLength = 8;
        items = (T[]) new Object[initialLength];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }
    public void addFirst(T item) {
        //todo there's a resize function
        if (isFull()) {
            resize(size << 1);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(T item) {
        //todo: there's a resize function
        if (isFull()) {
            resize(size << 1);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == items.length;
    }


    public int size() {
        return size;
    }

    //todo: finish this later
    public void printDeque() {
        int currentFirst = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[currentFirst] + " ");
            currentFirst = plusOne(currentFirst);
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T item = items[nextFirst];
        size--;
        if (items.length >= 16) {
            checkUsage();
        }
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T item = items[nextLast];
        size--;
        if (items.length >= 16) {
            checkUsage();
        }
        return item;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int first = plusOne(nextFirst);
        for (int i = 0; i < index; i++) {
            first = plusOne(first);
        }
        return items[first];
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int first = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            temp[i] = items[first];
            first = plusOne(first);
        }
        nextFirst = temp.length - 1;
        nextLast = size;
        items = temp;
    }

    private void checkUsage() {
        if ((double) size / items.length < 0.25) {
            resize(items.length >> 1);
        }
    }

    private int minusOne(int x) {
        x--;
        if (x < 0) {
            x = items.length - 1;
        }
        return x;
    }

    private int plusOne(int x) {
        x++;
        if (x > items.length - 1) {
            x = 0;
        }
        return x;
    }

}
