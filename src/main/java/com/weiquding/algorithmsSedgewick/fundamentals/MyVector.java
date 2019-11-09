package com.weiquding.algorithmsSedgewick.fundamentals;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * 向量实现
 *
 * @param <E> 元素类型
 */
public class MyVector<E> implements Vector<Integer, E> {


    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 容量
     */
    private int size;
    /**
     * 使用数组作为内部容器
     */
    private transient Object[] elementData;

    public MyVector() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyVector(int capacity) {
        elementData = new Object[Math.max(DEFAULT_CAPACITY, capacity)];
    }

    public MyVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector must not be null");
        }
        elementData = vector.toArray();
        size = elementData.length;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(Integer index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    @Override
    public void put(Integer index, E e) {
        Objects.checkIndex(index, size);
        elementData[index] = e;
    }

    @Override
    public void insert(Integer index, E e) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index:" + index + " Size: " + size);
        }
        if (size == elementData.length) {
            // 如有必要进行扩容
            grow();
        }
        // 将index及之后的元素后移
        int hi = size;
        while (hi > index) {
            elementData[hi] = elementData[hi - 1];
            hi--;
        }
        elementData[index] = e;
        size = size + 1;
    }

    @Override
    public E remove(Integer index) {
        Objects.checkIndex(index, size);
        // 从前向后进行移动
        E e = elementData(index);
        while (index < size) {
            elementData[index] = elementData[index + 1];
            index++;
        }
        size--;
        elementData[size] = null;
        return e;
    }

    @Override
    public int remove(Integer lo, Integer hi) {
        Objects.checkFromToIndex(lo, hi, size);
        while (hi < size) {
            elementData[lo++] = elementData[hi++];
        }
        int oldSize = size;
        size = lo;
        while (lo < oldSize) {
            elementData[lo++] = null;
        }
        return oldSize - size;
    }


    @Override
    public int disordered() {
        int count = 0;
        for (int i = 1; i < size; i++) {
            if (compare(i, i - 1) < 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void sort() {
        switch (new Random().nextInt() % 3) {
            case 0:
                insertSort();
                break;
            case 1:
                bubbleSort();
                break;
            case 2:
                mergeSort();
                break;
            default:
                insertSort();
        }
    }

    /**
     * 冒泡排序
     */
    private void bubbleSort() {
        int lo = 0;
        int hi = size;
        // 逐一扫描交换，直到全序
        while (lo < (hi = bubble(lo, hi))) {
            // do nothing
        }

    }

    private int bubble(int lo, int hi) {
        int last = lo; // 最右侧的逆序对初始化为[lo-1, lo]
        while (++lo < hi) { // 自左向右，逐一检查各对相邻元素
            if (compare(lo - 1, lo) > 0) { //若逆序，则
                last = lo; // 更新最右侧逆序对位置记录，并
                exch(lo - 1, lo); // 交换
            }
        }
        return last; // 返回最右侧的逆序对位置
    }


    private Object[] mergeDataTemp;

    private void mergeSort() {
        mergeDataTemp = new Object[size];
        mergeSort(0, size);
        mergeDataTemp = null;
    }

    private void mergeSort(int lo, int hi) {
        if (hi - lo < 2) { // [lo, hi)
            // 只有一个元素
            return;
        }
        int mid = (hi + lo) >> 1;
        mergeSort(lo, mid); // 左侧子区间
        mergeSort(mid, hi); // 右侧子区间
        merge(lo, mid, hi); // 归并两个区间
    }

    private void merge(int lo, int mid, int hi) {
        // 定义两个子区间的起始下标
        int i = lo;
        int j = mid;
        // 复制元素到临时空间中
        for (int k = lo; k < hi; k++) {
            mergeDataTemp[k] = elementData[k];
        }
        // 拉链法
        for (int k = lo; k < hi; k++) {
            if (i >= mid) {
                // 左侧子区间已经完全归并了
                elementData[k] = mergeDataTemp[j++];
            } else if (j >= hi) {
                // 右侧子区间已经完全归并了
                elementData[k] = mergeDataTemp[i++];
            } else if (compare((E) mergeDataTemp[i], (E) mergeDataTemp[j]) > 0) {
                // 取两个子区间待比较元素的小值
                elementData[k] = mergeDataTemp[j++];
            } else {
                elementData[k] = mergeDataTemp[i++];
            }
        }
    }

    /**
     * 插入排序
     */
    private void insertSort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j > 0 && compare(j, j - 1) < 0; j--) {
                exch(j, j - 1);
            }
        }
    }

    @Override
    public Integer find(E e) {
        return find(e, 0, size);
    }

    @Override
    public Integer find(E e, Integer lo, Integer hi) {
        // 在while要注意前--和后--的区别；防止hi--等对while循环里变量值的改变
        while (--hi >= lo) {
            if ((elementData(hi) == null && e == null)
                    || elementData(hi).equals(e)) {
                return hi;
            }
        }
        return -1;
    }

    @Override
    public Integer search(E e) {
        sort();
        return binarySearch(e, 0, size);
    }

    private Integer binarySearch(E e, int lo, int hi) {
        while (lo < hi) { // 不变性：elem[0, lo) <= e < elem[hi, n)
            Integer mid = (lo + hi) >> 1; // 以中点为轴点，经比较后确定深入
            if (compare(e, elementData(mid)) < 0) {
                hi = mid; // ==>[lo, mid)
            } else {
                lo = mid + 1; // ===> (mid, hi)
            }
        } // 出口时，elem[lo = hi]为大于e的最小元素
        return --lo;
    }

    @Override
    public int deduplicate() {
        int index = 1;
        int oldSize = size;
        while (index < size) {
            if (find(elementData(index), 0, index) < 0) {
                index++;
            } else {
                remove(index);
            }
        }
        return oldSize - size;
    }

    @Override
    public int uniquify() {
        // 先进行排序，时间复杂度为O(NlonN)
        sort();
        // 再进行删除，时间复杂度为O(n),总体时间复杂度为O(NlogN)
        // i和j为各对互异“相邻”元素的秩。
        int oldSize = size;
        int i = 0;
        int j = 1;
        //逐一扫描，直至末元素；
        while (j < size) {
            if (!((elementData[i] == null && elementData[j] == null)
                    || elementData[i].equals(elementData[j]))) {
                // 跳过雷同者；发现不同元素时，向前移至紧邻于前者的右侧；
                elementData[++i] = elementData[j];
            }
            j++;
        }
        // 直到截除尾部多余元素；
        size = ++i;
        while (i < oldSize) {
            elementData[i++] = null;
        }
        // 向量规模变化量，即被删除元素的总数。
        return oldSize - size;
    }

    @Override
    public void traverse(Visitor visitor) {
        for (int i = 0; i < size; i++) {
            visitor.visit(elementData(i));
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    private int compare(int i, int j) {
        return ((Comparable) elementData(i)).compareTo((Comparable) elementData(j));
    }

    private int compare(E e1, E e2) {
        return ((Comparable) e1).compareTo((Comparable) e2);
    }

    private void exch(int i, int j) {
        E temp = elementData(i);
        elementData[i] = elementData[j];
        elementData[j] = temp;
    }

    private E elementData(int index) {
        return (E) elementData[index];
    }

    private void grow() {
        grow(size + 1);
    }

    private void grow(int minCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity << 1;
        if (newCapacity - oldCapacity <= 0) {
            if (minCapacity < 0) {
                // overflow
                throw new OutOfMemoryError();
            }
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return (newCapacity - MAX_ARRAY_SIZE) <= 0 ? newCapacity : hugeCapacity(minCapacity);
    }

    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return minCapacity > MAX_ARRAY_SIZE ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    public static void main(String[] args) {
        Vector<Integer, Integer> vector = new MyVector<>();
        vector.insert(0, 9);
        System.out.println(Arrays.toString(vector.toArray()));
        vector.insert(0, 4);
        System.out.println(Arrays.toString(vector.toArray()));
        vector.insert(1, 5);
        System.out.println(Arrays.toString(vector.toArray()));
        vector.put(1, 2);
        System.out.println(Arrays.toString(vector.toArray()));
        vector.insert(3, 6);
        System.out.println(Arrays.toString(vector.toArray()));
        vector.insert(1, 7);
        System.out.println(Arrays.toString(vector.toArray()));
        vector.remove(2);
        System.out.println(Arrays.toString(vector.toArray()));
        vector.insert(1, 3);
        System.out.println(Arrays.toString(vector.toArray()));
        vector.insert(3, 4);
        System.out.println(Arrays.toString(vector.toArray()));
        System.out.println(vector.size());
        System.out.println(vector.disordered());
        System.out.println(vector.find(9));
        System.out.println(vector.find(5));
        vector.deduplicate();
        System.out.println(Arrays.toString(vector.toArray()));
        System.out.println(vector.find(3, 0, 2));
        System.out.println(vector.find(3, 0, 1));
        vector.insert(0, 7);
        System.out.println(Arrays.toString(vector.toArray()));
        vector.insert(4, 3);
        System.out.println(Arrays.toString(vector.toArray()));
        vector.sort();
        System.out.println(Arrays.toString(vector.toArray()));
        System.out.println(vector.search(2));
        System.out.println(vector.search(3));
        System.out.println(vector.search(7));
        System.out.println(vector.search(8));
        System.out.println(vector.search(9));
        System.out.println(vector.search(10));
        vector.uniquify();
        System.out.println(Arrays.toString(vector.toArray()));
        vector.remove(0, 2);
        System.out.println(Arrays.toString(vector.toArray()));

    }
}
