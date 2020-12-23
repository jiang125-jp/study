package com.jp.test.calc;

import java.util.Arrays;

/**
 * 希尔排序：插入排序的优化版本
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {16, 4, 15, 7, 10, 2, 8, 5, 11, 6, 3, 13, 9, 14, 0, 1, 12};
        //希尔排序，循环168次，但是交换或者覆盖的次数少
        shellSort(array);
        int[] array2 = Arrays.copyOf(array, array.length);
        //插入排序，循环136次，但是交换或者覆盖的次数多
        bubbleSortByTargetIndex(array2);
    }

    static void shellSort(int[] array) {
        //间隔采用Knuth序列,按照{1,4,13,40}的间隔来计算：公式为 h*3+1的增量
        int gap = 1;
        while (gap <= array.length / 3) { //保证间隔gap的值 小于 length长度的三分之一
            gap = gap * 3 + 1;
        }
        int count = 0;
        for (int h = gap; h > 0; h = (h - 1) / 3) {
            for (int i = h; i < array.length; i++) {
                for (int j = i; j > h - 1; j -= h) {
                    if (array[j] < array[j - h]) { //每次和前h个数对比
                        CalcUtils.swap(array, j, j - h);
                    }
                    count++;
                }
            }
        }
        System.out.println("循环：" + count + "次");
        CalcUtils.printArray(array);
    }

    static void bubbleSortByTargetIndex(int[] array) {
        //从第二个开始比较，一直到数组最后一个数
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            count += moveTarget(array, i);
        }
        System.out.println("循环：" + count + "次");
        CalcUtils.printArray(array);
    }

    static int moveTarget(int[] array, int i) {
        int count = 0;
        int tempIndex = array[i]; //标记本来的位置
        int target = i; //记录应该移到的位置
        for (int j = i; j > 0; j--) {
            count++;
            if (tempIndex < array[j - 1]) {
                array[j] = array[j - 1];
                target--;
            }
        }
        array[target] = tempIndex;
        return count;
    }
}
