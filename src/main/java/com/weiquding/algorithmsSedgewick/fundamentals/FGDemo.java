package com.weiquding.algorithmsSedgewick.fundamentals;

/**
 * @author wubai
 * @date 2019/4/8 20:56
 */
public class FGDemo {

    public static void main(String[] args) {
        int f = 0;
        int g = 1;
        for(int i = 0; i <= 15; i++){
            System.out.println(f);
            f = f + g;
            g = f - g;
        }
    }

    /**
     * 0
     * 1
     * 1
     * 2
     * 3
     * 5
     * 8
     * 13
     * 21
     * 34
     * 55
     * 89
     * 144
     * 233
     * 377
     * 610
     */
}
