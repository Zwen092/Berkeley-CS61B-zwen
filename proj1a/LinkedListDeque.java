import java.util.*;

/**
 * @author zwen
 * @Description
 * @create 2020-11-25 22:22
 */

public class LinkedListDeque<T> {
    private ListNode sentinel;
    private  int size;

    public class ListNode{
        ListNode prev;
        T item;
        ListNode next;

        public ListNode(T item) {
            this.item = item;
        }
    }

    /* initialize your data structure */
    public LinkedListDeque(){
        size = 0;
        sentinel = new ListNode(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item){
        ListNode succ = sentinel, pred = sentinel.next;
        size++;

        ListNode toAdd = new ListNode(item);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;

    }

    public void addLast(T item){
        ListNode succ = sentinel, pred = sentinel.prev;
        size++;


        ListNode toAdd = new ListNode(item);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;

    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return this.size;
    }

    /* i made a mistake here */
    /* which is i set temp != null as the judging statement */
    public void printDeque(){
        ListNode temp = sentinel;
        while(temp.next != sentinel){
            temp = temp.next;
            System.out.print(temp.item + " ");
        }
        System.out.println();
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        }
        size--;
        ListNode temp = sentinel.next;
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        return temp.item;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
        size--;
        ListNode temp = sentinel.prev;
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        return temp.item;
    }

    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }

        ListNode curr = sentinel;
        if(index + 1 < size - index){
            for(int i = 0; i < index + 1; i++){
                curr = curr.next;
            }
        }else{
            for(int i = 0; i < index + 1; i++){
                curr = curr.prev;
            }
        }
        return curr.item;

    }

    public T getRecursive(int index){
        if(index < 0 || index >= size){
            return null;
        }
        return helper(index, sentinel.next);
    }

    private T helper(int index, ListNode node){
        if(index == 0){
            return node.item;
        }
        return helper(index - 1, node.next);



    }

    public static void main(String[] args) {

        int[] a = new int[3];
        a[0] = new Integer(2);


    }

    public static int singleNumber(int[] nums) {
        Set<Integer> s = new HashSet<>();

        for(int i = 0; i < nums.length; i++){
            if(s.contains(nums[i])){
                s.remove(nums[i]);
            }else{
                s.add(nums[i]);
            }

        }
        Iterator i = s.iterator();
        return (int)i.next();
    }


}