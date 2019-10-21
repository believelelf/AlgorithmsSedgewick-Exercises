package com.weiquding.algorithmsSedgewick.fundamentals;

/**
 * 问题：计算任意n个整数之和，分别使用迭代法、递归法、分治法进行求解。
 */
public class SumDemo {

    /**
     * 迭代法实现: 逐一取出每个元素，累加之
     * 时间复杂度分析：
     *  T(n) = 1 + n*1 + 1 = n + 2 = O(n)
     *  空间复杂度分析：
     *    无额度空间增加，空间复杂度为O(1)
     * @param nums
     * @return
     */
    public int sumIterative(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0; // O(1)
        for (int num : nums) { // O(n)
            sum += num; // O(1)
        }
        return sum;  // O(1)
    }

    /**
     * 递归法实现
     * 时间复杂度分析
     *  从递推的角度看，为求解sum(nums, n)，即T(n)
     *      需递归求解规模为n-1问题的解，即T(n-1)
     *      再累加上nums[n-1]，即O(1)
     *  递归基：sum(nums, 0),即O(1)
     *
     *  递归方程：
     *  T(n) = T(n-1) + O(1)
     *  T(0) = O(1)
     *
     *  求解：
     *  T(n) - n = T(n-1) -(n-1) = ...
     *           = T(2) - 2
     *           = T(1) - 1
     *           = T(0)
     *  T(n) = O(1) + n = O(n)
     *
     * @param nums
     * @return
     */
    public int sumRecursion(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return sumRecursion(nums, nums.length);
    }

    private int sumRecursion(int[] nums, int length) {
        if (length == 1) {
            return nums[0];
        }
        return sumRecursion(nums, length - 1) + nums[length - 1];
    }

    /**
     * 采用分治法.
     * 时间复杂度分析：
     *  从递推的角度看，为求解sum(nums, lo, hi)，即T(n),
     *  需递归求解sum(nums, lo, mid)和sum(nums, mid + 1, hi)两个子问题，即2*T(n/2)
     *  进而将子问题的解累加，即O(1)
     *  递归基: sum(nums, lo, lo)的时间复杂度为O(1)
     *
     *  递归关系：T(n) = 2*T(n/2) + O(1)
     *            T(1) = O(1)
     *
     *   求解： T(n) = 2 * T(n/2) + c1
     *          T(n) + c1 =  2 * ( T(n/2) + c1 )
     *                    = 2^2 * (T(n/4) + c1)
     *                    = ...
     *                    = 2^logn(T(1) + c1) = n*(c2 + c1)
     *          T(n) = (c1 + c2) * n - c1 = O(n)
     *
     *
     * @param nums
     * @return
     */
    public int sumDivideAndConquer(int[] nums){
        return sumDivideAndConquer(nums, 0, nums.length -1);

    }

    private int sumDivideAndConquer(int[] nums, int lo, int hi){
        if(lo == hi){
            return nums[lo];
        }
        int mid = lo + ((hi - lo) >> 1);
        return sumDivideAndConquer(nums, lo, mid) + sumDivideAndConquer(nums, mid + 1, hi);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(new SumDemo().sumIterative(nums));
        System.out.println(new SumDemo().sumRecursion(nums));
        System.out.println(new SumDemo().sumDivideAndConquer(nums));
    }

}
