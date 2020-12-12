public class LinkedListDeque<T> {
    private ListNode sentinel;
    private  int size;

    private class ListNode {
        ListNode prev;
        T item;
        ListNode next;

        private ListNode(T item, ListNode next, ListNode prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
        private ListNode(T item) {
            this.item = item;
        }
    }

    /* initialize your data structure */
    public LinkedListDeque() {
        size = 0;
        sentinel = new ListNode(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        if (isEmpty()) {
            sentinel.next = new ListNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new ListNode(item, sentinel.next, sentinel);
            sentinel.next.next.prev = sentinel.next;
        }
        size++;
    }

    public void addLast(T item) {
        if (isEmpty()) {
            sentinel.next = new ListNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.prev = new ListNode(item, sentinel, sentinel.prev);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size++;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    /* i made a mistake here */
    /* which is i set temp != null as the judging statement */
    public void printDeque() {
        ListNode temp = sentinel;
        while (temp.next != sentinel) {
            temp = temp.next;
            System.out.print(temp.item + " ");
        }
        System.out.println();
    }


    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        ListNode temp = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return temp.item;

    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        ListNode temp = sentinel.prev;
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        return temp.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        ListNode temp = sentinel;
        while (index >= 0) {
            index--;
            temp = temp.next;
        }
        return temp.item;

    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return helper(index, sentinel.next);
    }

    private T helper(int index, ListNode node) {
        if (index == 0) {
            return node.item;
        }
        return helper(index - 1, node.next);
    }

}
