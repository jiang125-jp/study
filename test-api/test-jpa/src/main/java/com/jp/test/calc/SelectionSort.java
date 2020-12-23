package com.jp.test.calc;

import java.util.Arrays;

/**
 * 选择排序, 相同数字也会改变数字顺序，所以不稳定
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] array = {16, 4, 15, 7, 10, 2, 8, 5, 11, 6, 3, 13, 9, 14, 0, 1, 12};
        int[] array2 = Arrays.copyOf(array, array.length);

        selectionSortMethodOne(array);
        System.out.println();
        //优化：同时找出最大的和最小的，放最前面和最后面，减少循环次数
        selectionSortMethodTwo(array2);

    }

    static void selectionSortMethodOne(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int minPosition = i;
            for (int j = i + 1; j < array.length; j++) {
                count++;
                if (array[j] < array[minPosition]) {
                    minPosition = j;
                }
            }
            CalcUtils.swap(array, i, minPosition);
            System.out.print("方法一, 第" + (i + 1) + "次循环：");
            CalcUtils.printArray(array);
        }
        System.out.print("方法一, 一共循环" + count + "次");
    }

    static void selectionSortMethodTwo(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length - 1 - i; i++) {
            int minPosition = i;
            int maxPosition = i;
            for (int j = i + 1; j < array.length - i; j++) {
                if (array[j] < array[minPosition]) {
                    minPosition = j;
                }
                if (array[j] > array[maxPosition]) {
                    maxPosition = j;
                }
                count++;
            }
            CalcUtils.swap(array, i, minPosition);
            if (i == maxPosition) {
                CalcUtils.swap(array, (array.length - 1) - i, minPosition);
            } else {
                CalcUtils.swap(array, (array.length - 1) - i, maxPosition);
            }
            System.out.print("方法二, 第" + (i + 1) + "次循环：");
            CalcUtils.printArray(array);
        }
        System.out.print("方法一, 一共循环" + count + "次");
    }
}
