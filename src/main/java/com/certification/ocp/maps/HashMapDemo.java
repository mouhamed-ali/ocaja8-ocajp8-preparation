package com.certification.ocp.maps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ref : https://www.youtube.com/watch?v=c3RVW3KGIIE&t=26s&ab_channel=Ranjithramachandran
public class HashMapDemo {

    public static void main(String... args){

        // if two objects are equals, theirs h-codes must be equals. If two hash codes are equals, that does not mean that the objects are equal (example modulo function)
        // hash map allows null key, which always goes to index 0 as hash of null is 0
        // number of buckets by default in a hashmap is 16 ; from 0 to 15
        // Have a look at the image to know more about the hash structure. It contains a table of Nodes. Each node represent a linked list which means that
        // evey node contains the key ,the value, the hash and a Node-Pointer to the next Node

        HashMap<String,Integer> scores = new HashMap<>();
        List<String> keys = Arrays.asList("CLARK" , "SMITH", "KING", "BLAKE", "WARD");
        keys.forEach(key ->
                System.out.printf ("%-7s , hash code : %-10s , bucket number : %-2s %n", key, key.hashCode(), hash(key.hashCode())&15)
        );

        // as you can see the three last keys will share the same bucket

        scores.put("CLARK", 99);
        scores.put("SMITH", 75);
        scores.put("KING", 115);
        scores.put("BLAKE", 23);
        scores.put("WARD", 208);

        scores.get("CLARK"); // hash 64205105
        // bucket is 2 , we compare hash of CLARK (64205105) with the hash of the first and the only node of the bucket 2, they are equals
        // equals hash codes does not mean equals keys. compare CLARK to the the first and the only node of the bucket 2, they are equals
        // return the value of the first and the only node of the bucket 2

        scores.get("BLAKE"); // hash 63281361
        // calculate the bucket from hash, returns 4
        // compare the hash with the hash of the first node ; they are not equal; first 2306967, BLAKE 63281361;
        // this node gives a pointer to the next node
        // compare with the hash of the second which is 63281361; they are equals
        // compare "BLAKE" with the key of this second node : ; they are equans
        // return the value

        // with java java 8 ; if the number of nodes in a bucket reaches the number 8 the 'linked list' will be transformed to a 'balanced tree' ot TreeNode
        // instead of Node in HashMap which will reduce complexity of the search and improves performance
        // like a tree, we will put greater hash codes on left and lower hash codes on right
    }

    // I get this method from HashMap class so i can get the hash function used by it
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
