package com.jp.test.calc;

/**
 * 归并排序,不会改变数字原本顺序，安全
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = {16, 4, 15, 7, 10, 2, 8, 5, 11, 6, 3, 13, 9, 14, 0, 1, 12, 17};
        mergeSort(array, 0, array.length - 1);
    }

    //对半递归排序，对半递归到只有两个数字，再执行merge对比排序，直到返回正确的结果
    static void mergeSort(int[] array, int left, int right) {
        //一直递归到只有一个数字为止
        if (left == right) return;
        //分成两半 17
        int mid = (left + right) / 2;
        //左半边做递归排序
        mergeSort(array, left, mid);
        //右半边做递归排序
        mergeSort(array, mid + 1, right);
        //排序
        merge(array, left, mid + 1, right);
    }

    /**
     * 最小单位为两个数  比如{2,1}
     *
     * @param left  左指针  下标0
     * @param right 右指针  下标1
     * @param bound 右边界  下标1
     */
    static void merge(int[] array, int left, int right, int bound) {
        int mid = right - 1;
        int i = left;
        int j = right;
        int k = 0;
        //创建一个新的数组，接收排好序的数字
        int[] tempArray = new int[bound - left + 1];
        //从中间开始划分，比较左边的数字和右边的数字
        while (i <= mid && j <= bound) {
            tempArray[k++] = array[i] <= array[j] ? array[i++] : array[j++];
        }
        while (i <= mid) tempArray[k++] = array[i++];
        while (j <= bound) tempArray[k++] = array[j++];
        for (int value : tempArray) array[left++] = value;
        CalcUtils.printArray(array);
    }
}
