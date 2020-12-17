package com.jp.test.cacl;

/**
 * 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] array = {4, 1, 7, 2, 8, 5, 6, 3, 9, 0};
        int[] array2 = {4, 15, 7, 10, 2, 8, 5, 11, 6, 3, 13, 9, 14, 0, 1, 12, 16};

        //方法一，最小的放最前面， 10个数字循环9次
        selectionSortMethodOne(array);
        System.out.println();
        //方法儿，同时找出最大的和最小的，放最前面和最后面
        selectionSortMethodTwo(array2);

    }

    //{4, 1, 7, 2, 8, 5, 6, 3, 9, 0};
    static void selectionSortMethodOne(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minPosition = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minPosition]) {
                    minPosition = j;
                }
            }
            swap(array, i, minPosition);
            System.out.print("方法一, 第" + (i + 1) + "次循环：");
            printArray(array);
        }
    }

    //{4, 1, 7, 2, 8, 5, 6, 3, 9, 0};
    static void selectionSortMethodTwo(int[] array) {
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
            }
            swap(array, i, minPosition);
            if (i == maxPosition) {
                swap(array, (array.length - 1) - i, minPosition);
            } else {
                swap(array, (array.length - 1) - i, maxPosition);
            }

            System.out.print("方法二, 第" + (i + 1) + "次循环：");
            printArray(array);
        }
    }

    static void printArray(int[] array) {
        for (int a : array) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
