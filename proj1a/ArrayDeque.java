/**
 * in arrayDeque,
 * first element of this array is nextFirst + 1
 * last element is nextLast - 1;
 * @param <Item>
 */
public class ArrayDeque<Item> {

    private Item[] items;
    private int size;
    public int nextFirst;
    public int nextLast;
    // no need for maxIndex: because directly use items.length.
    private static int REFACTOR = 2; // when resizing array

    /** constructor: Creates an empty list. */

    public ArrayDeque(){
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4; // this number is pick up randomly

    }

    /**
     * check if the ArrayDeque is empty.
     * @return
     */
    public boolean isEmpty(){
        return (size == 0) ? (true):(false);
    }



    /** check if arrayDeque is full. */
    public boolean isFull(){
        return (size == items.length) ? (true):(false);

    }

    public int size(){
        return size;
    }

    public void addLast(Item item) {
        if (isFull()) {
            resize(REFACTOR * items.length);
        }
        size += 1;
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
    }

    public void addFirst(Item item){
        if (isFull()) {
            resize(REFACTOR * items.length);
        }
        size += 1;
        items[nextFirst] = item;
        nextFirst = (items.length + nextFirst - 1) % items.length; // via:https://github.com/CurryTang/Palindrome/blob/master/ArrayDeque.java
    }

    /**
     * resize the array to the desired capacity.
     * @param capacity: target capacity
     */
    public void resize(int capacity){
        Item[] resizeArray = (Item[]) new Object[capacity];
        int startPos = (nextFirst + 1) % items.length;
        System.arraycopy(items, startPos,
                resizeArray, capacity - (items.length - startPos), items.length - startPos);

        System.arraycopy(items, 0, resizeArray, 0, nextLast);

        nextFirst = capacity - (items.length - nextFirst); // next last stays the same.
        items = resizeArray;
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public Item removeFirst(){
        if(isEmpty()){
            return null;
        }
        /** via CurryTang
         * the memory that my program uses must be proportional to the number of items.
         * For arrays of length 16 or more, your usage factor should always be at least 25%.
         */
        if (size <= items.length / 4 && items.length > 16){
            resize(items.length / 2);
        }
        int removeIndex = (nextFirst + 1) % items.length;

        Item deleted = items[removeIndex];
        items[removeIndex] = null;
        nextFirst = removeIndex;
        return deleted;

    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public Item removeLast(){
        if (isEmpty()){
            return null;
        }

        // if nextLast = 0, then what is (-1) % items.length
        int removeIndex = (nextLast + items.length - 1) % items.length;
        Item deleted = items[removeIndex];
        items[removeIndex] = null;
        nextLast = removeIndex;

        return deleted;

    }

    /** get the ith item */
    public Item get(int index){
        if (isEmpty()){
            return null;
        }
        return items[(index + nextFirst + 1) % items.length];
    }


    public void printDeque(){
        int start = (nextFirst + 1) % items.length;
        while (start != nextLast){
            System.out.print(items[start]);
            System.out.print(' ');
            start = (start + 1) % items.length;
        }
    }
}
