package com.weiquding.algorithmsSedgewick.fundamentals;

/**
 * @author wubai
 * @date 2019/4/8 21:14
 */
public class ToBinaryString {

    public static void main(String[] args) {
        String s = "";
        int n = 66552;
        for(int i = n; i>0; i=i/2){
            s = i%2 + s;
        }
        System.out.println(s);
    }
}
