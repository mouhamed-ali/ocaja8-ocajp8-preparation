package com.certification.ocp.maps;

import java.util.TreeSet;

class TreeSetDemo<T> {

    T item;
    public void clean(T item) {
        System.out.println("Clean " + item);
    }

    public static void main(String[] a){

        TreeSet<String> treeSet = new java.util.TreeSet<>();
        treeSet.add("One");
        treeSet.add("one");
        treeSet.add("One"); // does not accept duplicates
        System.out.println(treeSet);

        treeSet.add(null);  // does not accept nulls as null does not implement comparable<T>
        System.out.println(treeSet);
    }
}
