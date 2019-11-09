package com.weiquding.algorithmsSedgewick.fundamentals;

/**
 * 抽象数据类型-向量
 */
public interface Vector<R,E> {

    /**
     * 	报告向量当前的规模（元素总数）
     * @return 元素个数
     */
    int size();

    /**
     * 获取秩为r的元素
     * @param index 秩
     * @return 元素
     */
    E get(R index);

    /**
     *  用e替换秩为r元素的数值
     * @param r 秩
     * @param e 新的元素
     */
    void put(R r, E e);

    /**
     * e作为秩为r元素插入，原后继元素依次后移
     * @param r
     * @param e
     */
    void insert(R r, E e);

    /**
     * 删除秩为r的元素，返回该元素中原存放的对象
     * @param r
     * @return
     */
    E remove(R r);

    /**
     *  删除区间[lo, hi), 0 <= lo <= hi <= size, [hi, _size)顺序前移hi-lo位，返回被删除元素的数目
     * @param lo 下限
     * @param hi 上限
     * @return 删除的数目
     */
    int remove(Integer lo, Integer hi);

    /**
     * 判断所有元素是否已按非降序排列,返回相邻元素中逆序对个数，如为0则代表有序
     * @return
     */
    int disordered();

    /**
     * 	调整各元素的位置，使之按非降序排列
     */
    void sort();

    /**
     * 查找目标元素e,返回对应的秩。
     * @param e
     * @return
     */
    R find(E e);

    /**
     * 在秩范围[lo, hi)内，查询元素E,返回对应的秩。
     * @param e 元素
     * @param lo 下限
     * @param hi 上限
     * @return 元素E对应的秩
     */
    R find(E e, R lo, R hi);

    /**
     * 查找目标元素e,返回对应的秩
     * @param e 元素
     * @return 查找目标元素e,返回不大于e且秩最大的元素
     */
    R search(E e);


    /**
     * 剔除重复元素(无序向量版本)
     * 时间复杂度 O(n^2)
     */
    int deduplicate();

    /**
     * 剔除重复元素(无序向量版本)
     * 时间复杂度O(NlogN)
     * @return
     */
    int uniquify();

    /**
     * 	遍历向量并统一处理所有元素，处理方法由函数对象指定
     */
    void traverse(Visitor visitor);

    /**
     * 转化为数组
     * @return 数组
     */
    Object[] toArray();

    /**
     * 观察者
     * @param <E>
     */
    interface Visitor<E>{

        /**
         * 观察方法
         * @param e
         */
         void visit(E e);
    }
}
