package com.study.datastructures.map;


import java.util.*;

public class HashMap<K, V> implements Map {

    private List[] buckets;
    private List[] oldBuckets;
    private int size;
    private int capacity;

    public HashMap() {
        capacity = 5;
        buckets = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new ArrayList<Entry>();
        }
    }

    private void copyMap() {
        oldBuckets = buckets;
        capacity = capacity * 2;
        List[] buckets = new List[capacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Entry>();
        }

        for (List<Entry> list : oldBuckets) {
            for (int i = 0; i < list.size(); i++) {
                put(list.get(i).key, list.get(i).value);
            }
        }
    }

    @Override
    public V put(Object key, Object value) {
        if (countUsedBacket() >= capacity * 0.75) {
            copyMap();
        }
        return putWithoutCopy(key, value);
    }

    private V putWithoutCopy(Object key, Object value) {

        Entry currentEntry = getEntry(key);
        if (currentEntry != null) {
            Entry oldEntry = new Entry(null, currentEntry.getValue());
            currentEntry.value = (V) value;
            return oldEntry.getValue();
        } else {
            Entry entry = new Entry((K) key, (V) value);
            int index = getIndex(key);
            entry.setHashCode(key.hashCode());
            buckets[index].add(entry);
            size++;
        }
        return null;
    }


    @Override
    public V get(Object key) {
        return getEntry(key).getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    @Override
    public V remove(Object key) {
        Entry entry = getEntry(key);
        if (entry != null) {
            List bucket = buckets[getIndex(key)];
            Entry currentEntry = entry;
            bucket.remove(entry);
            size--;
            return currentEntry.getValue();
        }
        return null;
    }

    public int getCapacity() {
        return capacity;
    }

    private Entry getEntry(Object key) {
        int index = getIndex(key);
        int hash = key.hashCode();

        List<Entry> entries = buckets[index];
        if (entries.isEmpty()) {
            return null;
        }
        for (Entry entry : entries) {
            if (entry.getHashCode() == hash) {
                if (entry.getKey().equals(key)) {
                    return entry;
                }
            }

        }
        return null;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "{", "}");

        for (int i = 0; i < buckets.length; i++) {
            for (Object o : buckets[i]) {
                stringJoiner.add(((Entry) o).toString());
            }
        }
        return stringJoiner.toString();
    }


    private int getIndex(Object key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private int countUsedBacket(){
        int count = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (!buckets[i].isEmpty()){
                count++;
            }
        }
        return count;
    }


    private class Entry {
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

        private void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

//        public void setValue(Object value) {
//            this.value = value;
//        }

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

}
