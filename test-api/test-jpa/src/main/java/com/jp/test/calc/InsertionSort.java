package com.jp.test.calc;

import java.util.Arrays;

/**
 * 插入排序，相同数字不移动位置，稳定,简单排序里面最优
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {16, 4, 15, 7, 10, 2, 8, 5, 11, 6, 3, 13, 9, 14, 0, 1, 12};
        bubbleSort(array);
        int[] array2 = Arrays.copyOf(array, array.length); //复制数组
        //优化：记录当前数，然后把小于这个数的数字直接往后移动,不用每次比较都移动一下,复杂度：O[n] or O[n * n]?
        bubbleSortByTargetIndex(array2);
    }

    static void bubbleSort(int[] array) {
        //从第二个开始比较，一直到数组最后一个数
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            count += moveMin(array, i);
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

    //每次都是后面一个对比前一个，小的往前移动,循环i次
    static int moveMin(int[] array, int i) {
        int count = 0;
        for (int j = i; j > 0; j--) {
            if (array[j] < array[j - 1]) {
                CalcUtils.swap(array, j, j - 1);
            }
            count++;
        }
        return count;
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
