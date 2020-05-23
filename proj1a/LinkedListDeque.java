public class LinkedListDeque<T> {
    private class TNode{
        public T item;
        public TNode next;
        public TNode prev;
        public TNode(T i, TNode n, TNode p){
            item = i;
            next = n;
            prev = p;
        }
    }

    private TNode sentinel;
    private int size;

    // constructor
    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        TNode first = new TNode(item, sentinel.next, sentinel);
        first.next = sentinel.next;
        first.prev = sentinel;
        sentinel.next = first;
        size += 1;
    }

    public void addLast(T item){
        TNode last = new TNode(item, sentinel, sentinel.prev);
        last.prev = sentinel.prev;
        last.next = sentinel;
        sentinel.prev = last;
        size += 1;

    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        else{
            TNode fst = sentinel.next;
            TNode second = fst.next;
            second.prev = sentinel;
            sentinel.next = second;
            size -= 1;
            return fst.item;

        }
    }

    public T get(int index){
        if (isEmpty()){
            return null;
        }
        int i = 0;
        TNode returnNode = sentinel.next;
        while(i<index){
            returnNode = returnNode.next;
            i += 1;
        }
        return returnNode.item;
    }
    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        else{
            TNode last = sentinel.prev;
            TNode lastLast = last.prev;
            lastLast.next = sentinel;
            sentinel.prev = lastLast;
            size -= -1;
            return last.item;
        }

    }

    public boolean isEmpty(){
        if (sentinel.next == sentinel){
            return true;
        }
        else return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        TNode current = sentinel;
        int i = 0;
        while(current.next != sentinel){
            System.out.print(current.item);
            System.out.print(" ");
            current = current.next;
        }
    }

}
