package com.study.datastructures.list;

import java.util.StringJoiner;

public class LinkedList implements List {

    private Node head;
    private Node tail;
    private int size;


    public void add(Object value) {
        this.add(value, size);
    }


    public void add(Object value, int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between '0' and " + size + " (including)");
        }
        Node ourNode = null;
        if (size == 0) {
            head = tail = new Node(value);
        } else if (index == size) {

            ourNode = head;
            while (ourNode.getNext() != null) {
                ourNode = ourNode.getNext();
            }
            ourNode.setNext(new Node(value, tail, null));
            tail = ourNode.getNext();
        } else {
            Node node = new Node(value, getNode(index - 1), this.getNode(index));
            if (index < size / 2) {
                getNode(index).setPrev(node);
                getNode(index - 1).setNext(node);
            } else {
                getNode(index - 1).setNext(node);
                getNode(index).setPrev(node);
            }
        }
        size++;
    }


    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should be between '0' and " + size + " (including)");
        }
        Object value = get(index);
        if (index == 0) {
            getNode(1).setPrev(null);
            head = getNode(1);
        } else if (index == size - 1) {
            tail = getNode(size - 2);
            tail.setNext(null);
        } else if (size == 3 && index == 1) {
            head.setNext(tail);
            tail.setPrev(head);
        } else {
            if (index + 1 <= size / 2) {
                getNode(index + 1).setPrev(getNode(index - 1));
                getNode(index - 1).setNext(getNode(index + 1));
            } else {
                getNode(index - 1).setNext(getNode(index + 1));
                getNode(index + 1).setPrev(getNode(index - 1));

            }

        }
        size--;

        return value;
    }


    public Object get(int index) {
        return getNode(index).getValue();
    }


    public Object set(Object value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should be between '0' and " + size + " (including)");
        }
        Object oldValue = getNode(index).getValue();
        getNode(index).setValue(value);
        return oldValue;
    }


    public void clear() {
        head = tail = null;
        size = 0;
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        if (head == null && tail == null) {
            return true;
        }
        return false;
    }


    public boolean contains(Object value) {
        if (head.getValue().equals(value)) {
            return true;
        }
        if (tail.getValue().equals(value)) {
            return true;
        }

        Node ourNode = head;
        while (ourNode.getNext() != null) {
            if (ourNode.getValue().equals(value)) {
                return true;
            }
            ourNode = ourNode.getNext();
        }
        return false;
    }


    public int indexOf(Object value) {
        int index = 0;
        Node node = head;
        while (node.getNext() != null) {
            if (node.getValue().equals(value)) {
                return index;
            }
            node = node.getNext();
            index++;
        }
        if (tail.getValue().equals(value)) {
            return size - 1;
        }
        return -1;
    }


    public int lastIndexOf(Object value) {
        int index = size - 1;
        Node node = tail;
        while (node.getPrev() != null) {
            if (node.getValue().equals(value)) {
                return index;
            }
            node = node.getPrev();
            index--;
        }
        if (head.getValue().equals(value)) {
            return 0;
        }
        return -1;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");

        Node ourNode = head;


        if (ourNode != null) {
            stringJoiner.add(String.valueOf(ourNode.getValue()));
        }
        while (ourNode.getNext() != null) {
            ourNode = ourNode.getNext();
            stringJoiner.add(String.valueOf(ourNode.getValue()));
        }
        return stringJoiner.toString();

    }

    @Override
    public Iterator getIterator() {
        return new LinkedListIterator();
    }

    public Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should be between '0' and " + size + " (including)");
        }
        if (index == 0) {
            return head;
        }
        if (index == size - 1) {
            return tail;
        }
        Node ourNode = null;
        if (index <= size / 2) {
            ourNode = head;
            int count = 0;
            while (count < index) {
                ourNode = ourNode.getNext();
                count++;
            }
        } else {
            ourNode = tail;
            int count = size - 1;
            while (count > index) {
                ourNode = ourNode.getPrev();
                count--;
            }
        }

        return ourNode;
    }

    private class Node {
        private Object value;

        private Node prev;
        private Node next;

        public Node(Object value) {
            this.value = value;
        }

        public Node(Object value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }


        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value.equals(node.value);
        }


        @Override
        public String toString() {
            return "" + value;
        }
    }

    private class LinkedListIterator implements Iterator {


        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    }
}
