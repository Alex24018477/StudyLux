package com.study.datastructures.list;


import java.util.Arrays;
import java.util.StringJoiner;

public class ArrayList<V> implements List {
    private static final int DEFAULT_CAPACITY = 10;

    private V[] array;
    private int size;

    public ArrayList() {
        array = (V[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        this.array = (V[]) new Object[capacity];
    }

    public void add(Object value) {
        add(value, size);
    }

    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between '0' and " + size);
        }
        if (size == array.length) {
            V[] arrayCopy = (V[]) new Object[array.length + array.length / 2];
            System.arraycopy(array, 0, arrayCopy, 0, size);
            array = arrayCopy;
        }
        V[] arrayCopy = (V[]) new Object[array.length];
        System.arraycopy(array, 0, arrayCopy, 0, index);
        arrayCopy[index] = (V) value;
        System.arraycopy(array, index, arrayCopy, index + 1, size - index);
        array = arrayCopy;
        size++;
    }

    public V remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should be between '0' and " + size);
        }
        V result = array[index];
        V[] arrayCopy = (V[]) new Object[array.length - 1];
        if (index == 0) {
            System.arraycopy(array, 1, arrayCopy, 0, size - 1);
            array = arrayCopy;
        } else if (index == size - 1) {
            System.arraycopy(array, 0, arrayCopy, 0, size - 1);
            array = arrayCopy;
        } else {
            System.arraycopy(array, 0, arrayCopy, 0, index);
            System.arraycopy(array, index + 1, arrayCopy, index, size - index);
            array = arrayCopy;
        }
        size--;
        return result;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should be between '0' and " + size);
        }
        return array[index];
    }

    public V set(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between '0' and " + size);
        }
        Object result = array[index];
        array[index] = (V) value;
        return (V) result;
    }

    public void clear() {
        array = (V[]) new Object[size];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public boolean contains(Object value) {
        boolean isContains = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == (value)) {
                isContains = true;
            }
        }
        return isContains;
    }

    public int indexOf(Object value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                index = i;
            }
        }
        return index;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator getIterator() {
        return null;
    }

    private class ArrayListIterator implements Iterator {

        private ArrayList arrayList;
        private int index;

        public ArrayListIterator(ArrayList arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public boolean hasNext() {
            return arrayList.get(index + 1) != null;
        }

        @Override
        public Object next() {
            return null;
        }
    }
}
