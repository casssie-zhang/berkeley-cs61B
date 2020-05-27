import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayDequeTest {

    /**
     * Example test that verifies correctness of the IntList.of static
     * method. The main point of this is to convince you that
     * assertEquals knows how to handle IntLists just fine.
     */

    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> one = new ArrayDeque<>();
        one.addFirst(100);
        assertEquals(one.nextFirst, 2);
        one.addFirst(212);
        assertEquals(one.nextFirst, 1);
        one.addFirst(12);
        assertEquals(one.nextFirst, 0);
        one.addFirst(213);
        assertEquals(one.nextFirst, 7); // add first
        one.addFirst(348);
        assertEquals(one.nextFirst, 6);
        one.addFirst(123);
        assertEquals(one.nextFirst, 5);
        one.addFirst(83); // 7th element
        assertEquals(one.nextFirst, 4);
        one.addFirst(45); // 8th element , add last @ 7
        assertEquals(one.nextFirst, 3); // here, next first = 10
        one.addFirst(10); // 9th element
        assertEquals(one.nextFirst, 10);
        one.printDeque();
    }

}

