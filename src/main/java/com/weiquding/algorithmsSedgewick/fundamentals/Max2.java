package com.weiquding.algorithmsSedgewick.fundamentals;

import java.util.Arrays;

/**
 * 问题：从数组区间A[lo, hi)中找出最大的两个整数A[x1]和A[x2]元素，要求元素的比较次数尽可能地少。
 * 分别使用迭代法、分治法实现
 */
public class Max2 {


    /**
     * 采用迭代法实现
     * 1.先扫描nums[lo, hi)找出最大值nums[x1],比较次数为n-1
     * 2.再分别打描nums[lo, x1)和nums(x1, hi)，找出次大值nums[x2]，比较次数为原比较次数减去一个已经去除的最大值，即为n-2
     * 3.所以总的比较次数为2n - 3
     *
     * @param nums
     * @return
     */
    public int[] max2Iterative(int[] nums){
        if(nums == null || nums.length < 3){
            return nums;
        }
        int x1 = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[x1]){
                x1 = i;
            }
        }
        int x2 =0;
        for(int i = 1; i < x1; i++){
            if(nums[i] > nums[x2]){
                x2 = i;
            }
        }
        for(int i = x1 + 1; i < nums.length; i++){
            if(nums[i] > nums[x2]){
                x2 = i;
            }
        }
        return new int[]{nums[x1], nums[x2]};
    }


    /**
     * 采用迭代法2：
     *  构建两个下标，分别代表最大值和次大值所在下标，再遍历一次nums,所取得的nums[i]，先与次大值比较，如大再与最大值比较。
     *
     *  比较次数分析：
     *  最好情况 1 + 1 * (n-2) = n -1，即遍历过程只发生一次nums[i]与次大值的比较
     *  最坏情况 1 + 2 * (n -2) = 2n -3，即遍历过程只发生一次nums[i]与次大值及最大值的比较
     *
     * @param nums
     * @return
     */
    public int[] max2Iterative2(int[] nums){
        if(nums == null || nums.length < 3){
            return nums;
        }
        int x1 = 0; // 最大值下标
        int x2 = 1; // 次大值下标
        //确定最大值与次大值下标
        if(nums[x2] > nums[x1]){
            x1 = 1;
            x2 = 0;
        }
        // 迭代nums
        for(int i = 2; i < nums.length; i++){
            //先与次大值比较
            if(nums[i] > nums[x2]){
                x2 = i;
                if(nums[x2] > nums[x1]){
                    int temp = x2;
                    x2 = x1;
                    x1 = temp;
                }
            }
        }
        return new int[]{nums[x1], nums[x2]};
    }

    /**
     * 采用分治法进行求解
     * 将数组拆成左右两边，分别求出最大值和次大值，再两边的最大值比较，得出真正的最大值，再将胜出一边的次大值与另一边的最大值比较得到真正次大值。
     *
     * 比较次数
     *  递推方程： T(n) = 2* T(n/2) +2
     *
     *  求解
     *      T(n) = 2* T(n/2) +2 ===> 5n/3 -2
     *
     *      T(n) -2 = 2 * 2 *(T(n/4) +2）
     *              = ...
     *              = 2^logn(T(5) + 2)
     * @param nums
     * @return
     */
    public int[] max2DivideAndConquer(int[] nums){
        if(nums == null || nums.length < 3){
            return nums;
        }
        int[] indexs = new int[]{0, 1};
        int lo = 0; //范围 [lo, hi)
        int hi = nums.length;
        max2DivideAndConquer(nums, lo, hi, indexs);
        return new int[]{nums[indexs[0]], nums[indexs[1]]};
    }

    private void max2DivideAndConquer(int[] nums, int lo, int hi, int[] indexs) {
        // 只有两个元素
        if(hi - lo == 2){
            if(nums[lo] > nums[lo + 1]){
                indexs[0] = lo;
                indexs[1] = lo +1;
            }else{
                indexs[0] = lo + 1;
                indexs[1] = lo;
            }
            return;
        }
        // 只有三个元素
        if(hi - lo == 3){
            if(nums[lo] > nums[lo + 1]){
                indexs[0] = lo;
                indexs[1] = lo +1;
            }else{
                indexs[0] = lo + 1;
                indexs[1] = lo;
            }
            if(nums[lo + 2] > nums[indexs[1]]){
                indexs[1] = lo + 2;
                if(nums[indexs[1]] > nums[indexs[0]]){
                    int temp = indexs[1];
                    indexs[1] = indexs[0];
                    indexs[0] = temp;
                }
            }
            return;
        }
        //拆分
        int mid = lo + (hi - lo) /2;
        int[] lIndexs = new int[2];
        int[] rIndexs = new int[2];
        max2DivideAndConquer(nums, lo, mid, lIndexs);
        max2DivideAndConquer(nums, mid, hi, rIndexs);
        if(nums[lIndexs[0]] > nums[rIndexs[0]]){
            indexs[0] = lIndexs[0];
            indexs[1] = nums[lIndexs[1]] > nums[rIndexs[0]] ? lIndexs[1] : rIndexs[0];
        }else{
            indexs[0] = rIndexs[0];
            indexs[1] = nums[rIndexs[1]] > nums[lIndexs[0]] ? rIndexs[1] : lIndexs[0];
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 5, 9, 8, 4, 2, 1, 7, 6};
        System.out.println(Arrays.toString(new Max2().max2Iterative(nums)));
        System.out.println(Arrays.toString(new Max2().max2Iterative2(nums)));
        System.out.println(Arrays.toString(new Max2().max2DivideAndConquer(nums)));
    }


}
