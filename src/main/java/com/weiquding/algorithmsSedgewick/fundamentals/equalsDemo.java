package com.weiquding.algorithmsSedgewick.fundamentals;

/**
 * @author wubai
 * @date 2019/4/8 20:47
 */
public class equalsDemo {

    public static void main(String[] args) {
        if(args.length != 3){
            throw  new IllegalArgumentException("args.length must be 3");
        }
        if(Integer.parseInt(args[0]) == Integer.parseInt(args[1]) && Integer.parseInt(args[1]) == Integer.parseInt(args[2])){
            System.out.println("equals");
            return;
        }
        System.out.println("not equals");
    }
}
