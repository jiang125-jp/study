package com.jp.test.strategy;

public class Sorter<T> {

    public static void sortInt(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
            }
        }
    }

    public static void sortDouble(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                double temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
            }
        }
    }

    public void sortComparable(Comparable[] comparables) {
        for (int i = 0; i < comparables.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < comparables.length; j++) {
                index = comparables[index].compareTo(comparables[j]) == 1 ? j : index;
            }
            sortComparableSwap(i, index, comparables);
        }
    }

    public void sortComparableSwap(int i, int j, Comparable[] comparables) {
        Comparable temp = comparables[i];
        comparables[i] = comparables[j];
        comparables[j] = temp;
    }

    public void sort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                index = comparator.comparator(array[index], array[j]) == 1 ? j : index;
            }
            sortSwap(i, index, array);
        }
    }

    public void sortSwap(int i, int j, T[] array) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
