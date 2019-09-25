package com.weiquding.algorithmsSedgewick.sorting;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 倒排一个LinkedList
 */
public class LinkedListReverse {

    private static LinkedList<Integer> reverse(LinkedList<Integer> head){
        ListIterator<Integer> fwd = head.listIterator();
        ListIterator<Integer> prev = head.listIterator(head.size());
        for(int i =0, mid = head.size() >> 1; i < mid; i++){
            Integer temp = fwd.next();
            fwd.set(prev.previous());
            prev.set(temp);
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedList<Integer> head = new LinkedList<>();
        head.addAll(Arrays.asList(1,2,3,4));
        System.out.println(reverse(head));
    }

}
