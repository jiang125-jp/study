package com.jp.test.calc;

/**
 * 冒泡排序，相同数字，不会移动顺序，稳定，慢
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {16, 4, 15, 7, 10, 2, 8, 5, 11, 6, 3, 13, 9, 14, 0, 1, 12};
        bubbleSort(array);
        int[] array2 = {16, 4, 15, 7, 10, 2, 8, 5, 11, 6, 3, 13, 9, 14, 0, 1, 12};
        bubbleSortByCompareCount(array2);
    }

    //每移动一次，数组最后一位数字就不用再移动，数组循环长度 -1，直到数组的最后一个数也要比较
    static void bubbleSort(int[] array) {
        int count = 0;
        for (int i = array.length; i > 0; i--) {
            count += moveMax(array);
        }
        System.out.println("循环：" + count + "次");
        CalcUtils.printArray(array);
    }


    //优化1：记录最后一次交换的位置，说明之后的数字都是正确的顺序，不用再次比较，减少比较次数
    static void bubbleSortByCompareCount(int[] array) {
        int count = 0;
        int swapIndex = array.length - 1;
        while (swapIndex > 1) {
            int lastChangeIndex = 0;
            for (int j = 0; j < swapIndex; j++) {
                count++;
                if (array[j] > array[j + 1]) {
                    CalcUtils.swap(array, j, j + 1);
                    lastChangeIndex = j + 1;
                }
            }
            swapIndex = lastChangeIndex;
        }
        System.out.println("循环：" + count + "次");
        CalcUtils.printArray(array);
    }

    //移动最大的数到最后面
    static int moveMax(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            count++;
            if (array[i] > array[i + 1]) {
                CalcUtils.swap(array, i, i + 1);
            }
        }
        return count;
    }

}
