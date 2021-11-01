package com.study.datastructures.map;

public interface Map<K, V> extends Iterable{
    V put(K key, V value);

    V get(K key);

    int size();

    boolean containsKey(K key);

    V remove(K key);

    String toString();

    int getCapacity();

}
