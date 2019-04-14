package com.weiquding.algorithmsSedgewick.fundamentals;

/**
 * @author wubai
 * @date 2019/4/8 21:01
 */
public class PrintDemo {

    public static void main(String[] args) {
        double t = 9.0;
        while(Math.abs(t - 9.0/t) > 0.001){
            t = (9.0/t + t)/2.0;
        }
        System.out.printf("%.5f\n", t);

        int sum = 0;
        for(int i = 1; i < 1000; i++){
            for(int j = 0; j < i; j++){
                sum++;
            }
        }
        System.out.println(sum);

         sum = 0;
        for(int i = 1; i < 1000; i*=2){
            for(int j = 0; j < 1000; j++){
                sum++;
            }
        }
        System.out.println(sum);
        System.out.println('b');
        System.out.println('b' + 'c');
        System.out.println((char)('a' + 4));
    }


}
