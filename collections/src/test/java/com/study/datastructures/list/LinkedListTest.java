package com.study.datastructures.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    LinkedList linkedList;

    @Before
    public void setUp() throws Exception {
        linkedList = new LinkedList();
        linkedList.add("0");
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add("6");
        linkedList.add("7");
        linkedList.add("8");
        linkedList.add("9");
        linkedList.add("10");
    }

    @Test
    public void testAdd() {
        linkedList.add("11");


        assertEquals(12, linkedList.size());
        assertEquals("11", linkedList.get(11));

    }

    @Test
    public void testAddForIndex() {
        linkedList.add("21", 5);
        linkedList.add("11", 11);

        assertEquals(13, linkedList.size());
        assertEquals("9", linkedList.get(10));
        assertEquals("11", linkedList.get(11));
    }

    @Test
    public void testRemove() {
        linkedList.remove(0);
        linkedList.remove(2);
        linkedList.remove(8);

        assertEquals("1", linkedList.get(0));
        assertEquals(8, linkedList.size());
        assertEquals("4", linkedList.get(2));
        assertEquals("9", linkedList.remove(7).toString());
    }

    @Test
    public void testRemoveFirstElementWhenListHasThreeElements() {

        List list = new LinkedList();
        list.add("22");
        list.add("33");
        list.add("44");

        list.remove(1);

        assertEquals(2, list.size());
        assertEquals("22", list.get(0));
        assertEquals("44", list.get(1));
    }

    @Test
    public void testGet() {
        assertEquals("0", linkedList.get(0));
        assertEquals("6", linkedList.get(6));
        assertEquals("10", linkedList.get(10));
    }

    @Test
    public void testSet() {
        linkedList.set("99", 3);
        linkedList.set(null, 0);
        linkedList.set("100", 10);

        assertEquals("99", linkedList.get(3));
        assertEquals(null, linkedList.get(0));
        assertEquals("100", linkedList.get(10));
        assertEquals(11, linkedList.size());
    }

    @Test
    public void testClear() {
        linkedList.clear();

        assertEquals(0, linkedList.size());
    }

    @Test
    public void testSize() {
        assertEquals(11, linkedList.size());
        linkedList.add("0");
        assertEquals(12, linkedList.size());
        linkedList.remove(5);
        linkedList.remove(5);
        assertEquals(10, linkedList.size());
    }

    @Test
    public void testIsEmpty() {
        linkedList = new LinkedList();

        assertTrue(linkedList.isEmpty());
        linkedList.add("1");
        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void testContains() {

        assertTrue(linkedList.contains("5"));
        assertTrue(linkedList.contains("0"));
        assertTrue(linkedList.contains("10"));
        assertFalse(linkedList.contains("15"));
    }

    @Test
    public void testIndexOf() {

        linkedList.set("2", 4);

        assertEquals(0, linkedList.indexOf("0"));
        assertEquals(2, linkedList.indexOf("2"));
        assertEquals(-1, linkedList.indexOf("20"));
        assertEquals(10, linkedList.indexOf("10"));
    }

    @Test
    public void testLastIndexOf() {
        linkedList.set("4", 8);

        assertEquals(8, linkedList.lastIndexOf("4"));
        assertEquals(0, linkedList.lastIndexOf("0"));
        assertEquals(10, linkedList.lastIndexOf("10"));
        assertEquals(-1, linkedList.lastIndexOf("20"));
    }

    @Test
    public void testToString() {
        linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");

        assertEquals("[1, 2, 3]", linkedList.toString());
    }
}