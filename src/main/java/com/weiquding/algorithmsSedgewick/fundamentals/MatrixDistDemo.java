package com.weiquding.algorithmsSedgewick.fundamentals;

/**
 * ##### 问题
 * 假设我们有一个 n 乘以 n 的矩阵 w[n][n]。矩阵存储的都是正整数。棋子起始位置在左上角，终止位置在右下角。我们将棋子从左上角移动到右下角。每次只能向右或者向下移动一位。从左上角到右下角，会有很多不同的路径可以走。我们把每条路径经过的数字加起来看作路径的长度。那从左上角移动到右下角的最短路径长度是多少呢？
 * <p>
 * 列0 | 列1 | 列2 | 列3
 * ---|---|---|---
 * 1| 3| 5| 9
 * 2| 1| 3| 4
 * 5|  2| 6| 7
 * 6| 8| 4| 3
 * <p>
 * <p>
 * ##### 分析
 * 1. 使用回溯算法实现，对于一个纵坐标与横坐标确定的点节点(i,j),其发展方向只能为向右或向下，对应的下一节点状态即为(i, j+1)或(i+1, j),每个阶段决策后，对应的移动距离都会累加当前次决策节点的值（以坐标系右上角到左下角划线为阶段向右下角移动，总计划分2(n-1)阶段），最终到达(n,n)节点，即完成了矩阵的移动，对于最后一个节点，其只要取左边节点和上方节点的移动小值，再加当前最后个节点(n-1, n -1)的值即可，公式为:min(min_dist(n-1, n -2), min_dist(n -2, n-1)) + w[n-1][n-1]。
 * 2. 此问题可以使用动态规划来解决。其符合动态规划适合解决的问题三特征：**重复子节点** 节点在向右或向下移动过程中会产生重复节点；**最优解子结构** 从(0,0)到(i,j)的最短移动距离 min_dist(i, j)可以通过其前置阶段节点(i, j-1)和(i-1, j)的最优解min_dist(i, j-1)和min_dist(i-1, j)得出。**无后效性** (i,j)节点的状态只有前一阶段节点(i, j-1)和(i-1, j)的状态有关，另外前一阶段的决策不受后一阶段决策的影响。
 * 3. 在使用动态规划解决的思路中，状态转移表法和状态转移方程法都可使用，其状态转移方法为：min_dist(i, j) = w[i][j] + min(min_dist(i, j-1), min_dist(i-1, j))。在代码实现上，可以使用迭代递推和递归+"备忘录"两种方式。
 */
public class MatrixDistDemo {

    /**
     * 到达(n, n)节点的最小移动距离
     */
    public int minDist = Integer.MAX_VALUE;

    /**
     * 矩阵每个节点的移动距离，
     * i --> 纵向 行
     * j --> 横向 列
     */
    public final int[][] distTable = new int[][]{
            {1, 3, 5, 9},
            {2, 1, 3, 4},
            {5, 2, 6, 7},
            {6, 8, 4, 3},
    };

    /**
     * 矩阵大小
     */
    public final int n = 4;

    /**
     * 使用回溯算法求解
     *
     * @param distTable 矩阵移动距离表
     * @param i         纵向 行
     * @param j         横向 列
     * @param dist      从(0, 0)到(i, j)的距离
     * @param n         矩阵大小
     */
    public void minDistBacktracking(int[][] distTable, int i, int j, int dist, int n) {
        // 先明确递归退出条件
        if (i == n - 1 && j == n - 1) {
            // 已经过了在矩阵上右下角的节点(n-1, n-1)。
            if (minDist > dist) {
                // 此处存在多次比较，从(n-2, n-1)节点下移或(n-1, n-2)节点右移，多次比较后就得到了最小值。
                minDist = dist;
            }
            return;
        }
        // 节点向右移动,即(i, j)--->(i, j+1),要注意i越界情况的处理。
        if (j < n - 1) {
            minDistBacktracking(distTable, i, j + 1, dist + distTable[i][j + 1], n);
        }

        // 节点向下移动,即(i, j)--->(i+1, j),要注意j越界情况的处理。
        if (i < n - 1) {
            minDistBacktracking(distTable, i + 1, j, dist + distTable[i + 1][j], n);
        }
    }

    /**
     * 使用动态规划解题，解题思路为状态转移表方法，代码实现方法为迭代递推。
     *
     * @param distTable 矩阵移动距离表
     * @param n         矩阵大小
     * @return 最小距离
     */
    public int minDistDP(int[][] distTable, int n) {
        // 初始化状态表,数组中的值为经过(i,j)后距离。
        int[][] states = new int[n][n];
        // 初始填表第一行
        int dist = 0;
        for (int j = 0; j < n; j++) {
            dist += distTable[0][j];
            states[0][j] = dist;
        }
        // 初始填表第一列
        dist = 0;
        for (int i = 0; i < n; i++) {
            dist += distTable[i][0];
            states[i][0] = dist;
        }

        // 后续决策阶段填表
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                // 关键
                states[i][j] = Math.min(states[i - 1][j], states[i][j - 1]) + distTable[i][j];
            }
        }
        // 返回最右下角节点的距离值
        return states[n - 1][n - 1];
    }

    /**
     * 节点备忘录
     */
    private final int[][] mem = new int[n][n];

    /**
     * 使用动态规划解题，使用状态转移方程，代码实现使用递归+“备忘录”
     * 状态转移方程：min_dist(i, j) = w[i][j] + min(min_dist(i, j-1), min_dist(i-1, j))
     * 从最后一个节点反向递归，直到(0,0)节点，递归终止。
     *
     * @param distTable 矩阵移动距离表
     * @param n         矩阵大小
     * @param i         纵向 行
     * @param j         横向 列
     * @return
     */
    public int minDistDPRecursion(int[][] distTable, int n, int i, int j) {
        // 先写递归终止条件
        if (i == 0 && j == 0) {
            return distTable[0][0];
        }
        // 使用备忘录，返回缓存节点
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        // 取当前节点的左边节点最小距离
        int leftDist = Integer.MAX_VALUE;
        if (j - 1 >= 0) { // 防止后续的j-1越界
            leftDist = minDistDPRecursion(distTable, n, i, j - 1);
        }
        // 取当前节点的上边节点最小距离
        int topDist = Integer.MAX_VALUE;
        if (i - 1 >= 0) {  // 防止后续i-1越界
            topDist = minDistDPRecursion(distTable, n, i - 1, j);
        }
        int currDist = distTable[i][j] + Math.min(leftDist, topDist);
        mem[i][j] = currDist;
        return currDist;
    }


    public static void main(String[] args) {
        // 回溯算法
        MatrixDistDemo matrixDistDemo = new MatrixDistDemo();
        matrixDistDemo.minDistBacktracking(matrixDistDemo.distTable, 0, 0, 1, matrixDistDemo.n);
        System.out.println(matrixDistDemo.minDist);

        // 动态规划（状态转移表法+迭代递推）
        matrixDistDemo = new MatrixDistDemo();
        int minDist = matrixDistDemo.minDistDP(matrixDistDemo.distTable, matrixDistDemo.n);
        System.out.println(minDist);

        // 动态规划（状态转移方程法+递归+“备忘录”）
        matrixDistDemo = new MatrixDistDemo();
        minDist = matrixDistDemo.minDistDPRecursion(matrixDistDemo.distTable, matrixDistDemo.n, matrixDistDemo.n - 1, matrixDistDemo.n - 1);
        System.out.println(minDist);
    }

}
