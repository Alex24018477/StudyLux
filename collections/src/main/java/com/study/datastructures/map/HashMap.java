package com.study.datastructures.map;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAUOLT_INITIAL_CAPACITY = 5;

    private List<Entry<K, V>>[] buckets;
    private int size;

    public HashMap() {
        buckets = new List[DEFAUOLT_INITIAL_CAPACITY];
    }

    private void copyMap() {

        List<Entry<K, V>>[] oldBuckets = buckets;
        int newCapacity = buckets.length * 2;
        buckets = new List[newCapacity];

        int currentSize = size;
        for (List<Entry<K, V>> list : oldBuckets) {
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    putWithoutCopy(list.get(i).key, list.get(i).value);
                }
            }
        }
        size = currentSize;
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> currentEntry = getEntry(key);
        if (currentEntry != null) {
            Entry<K, V> oldEntry = new Entry<>(null, currentEntry.getValue());
            currentEntry.value = value;
            return oldEntry.getValue();
        }
        if (size >= buckets.length * 0.75) {
            copyMap();
        }
        return putWithoutCopy(key, value);
    }

    private V putWithoutCopy(K key, V value) {

        Entry<K, V> entry = new Entry<>(key, value);
        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }
        entry.setHashCode(key.hashCode());
        buckets[index].add(entry);
        size++;
        return null;
    }


    @Override
    public V get(K key) {
        return (V) getEntry(key).getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        return getEntry(key) != null;
    }

    @Override
    public V remove(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            List<Entry<K, V>> bucket = buckets[getIndex(key)];
            Entry<K, V> currentEntry = entry;
            bucket.remove(entry);
            size--;
            return currentEntry.getValue();
        }
        return null;
    }

    public int getCapacity() {
        return buckets.length;
    }

    private Entry getEntry(K key) {
        int index = getIndex(key);
        int hash = key.hashCode();

        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.getHashCode() == hash) {
                    if (entry.getKey().equals(key)) {
                        return entry;
                    }
                }
            }
        }
        return null;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "{", "}");

//        while (iterator().hasNext()){
//            stringJoiner.add(iterator().next().toString());
//        }

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (Entry<K, V> entry : buckets[i]) {
                    stringJoiner.add(entry.toString());
                }
            }
        }
        return stringJoiner.toString();
    }


    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    @Override
    public Iterator iterator() {
        return new HashMapIterator();
    }

//    @Override
//    public void forEach(Consumer action) {
//
//    }

    private static class Entry<K, V> {
        private int hashCode;
        private K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getHashCode() {
            return hashCode;
        }

        private void setHashCode(int hashCode) {
            this.hashCode = hashCode;
        }


        @Override
        public String toString() {
            return key +
                    "=" + value;
        }
    }

    private class HashMapIterator implements Iterator<Entry<K, V>> {

        List<Entry<K, V>> copyEntries = copyAllEntries();
        private int indexOfBuckets;
        private int indexOfList;
        private int index;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Entry<K, V> next() {
            if (hasNext()) {
                return copyEntries.get(index+1);
            }
            return null;
        }

        private List<Entry<K,V>> copyAllEntries(){
            List<Entry<K, V>> copyEntries = new ArrayList<>();
            for (List<Entry<K, V>> list : buckets) {
                if (list != null) {
                    for (Entry<K, V> entry : list) {
                        if (entry != null) {
                            copyEntries.add(entry);
                        }
                    }
                }
            }
            return copyEntries;
        }
    }

}
