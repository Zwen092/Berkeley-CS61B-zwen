import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author zwen
 * @Description
 * @create 2020-12-11 19:41
 */
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int fisrtPosition;
    private int lastPosition;
    private int length;
    private int refactor;
    private int usageRatio;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        length = items.length;
    }

    public void addFirst(T item){
        //There's an resizing operation to be implement
        resizeHelper(size, length);
        fisrtPosition = (length - size) % length;
        items[fisrtPosition] = item;
        size++;
    }

    public void addLast(T item){
        //There's an resizing operation to be implement
        resizeHelper(size, length);
        lastPosition = size;
        items[lastPosition] = item;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if(!isEmpty()){
            for (int i = 0; i < size; i++) {
                System.out.print(items[i] + " ");
            }
        }
    }

    //something wrong within
    public T removeFirst(){
        resizeHelper(size, length);
        T temp = get(0);
        size--;
        return temp;
    }

    public T removeLast(){
        resizeHelper(size, length);
        T temp = get(size - 1);
        size--;
        return temp;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }
        return items[index];
    }

    public void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, Math.min(length, capacity));
        items = a;
        length = items.length;
    }

    public void resizeHelper(int size, int length){
        usageRatio = size / length;
        if(usageRatio == 1){
            refactor = 4;
            resize(refactor * length);
        }else if(length >= 16 && usageRatio <= 0.25){
            length = length / 2;
            resize(length);
        }
    }


}
