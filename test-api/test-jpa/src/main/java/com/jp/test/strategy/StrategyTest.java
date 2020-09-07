package com.jp.test.strategy;

import com.jp.test.jpa.Utils.*;

import java.util.Arrays;

public class StrategyTest {

    public static void main(String[] args) {
        int[] arrays = {1, 5, 3, 9, 7};
        Sorter.sortInt(arrays);
        System.out.println(Arrays.toString(arrays));

        double[] doubleArrays = {1.1, 5.2, 3.3, 9.4, 7.5};
        Sorter.sortDouble(doubleArrays);
        System.out.println(Arrays.toString(doubleArrays));

        Cat[] cats = {new Cat(1, 10), new Cat(4, 40), new Cat(3, 30),new Cat(2,20)};
        Sorter<Cat> catSorter = new Sorter<>();
        //comparable
        catSorter.sortComparable(cats);
        System.out.println(Arrays.toString(cats));
        //comparator 策略模式
        catSorter.sort(cats, new CatComparator());
        System.out.println(Arrays.toString(cats));

        Dog[] dogs = {new Dog(1), new Dog(3), new Dog(2)};
        Sorter<Dog> dogSorter = new Sorter<>();
        dogSorter.sort(dogs, new DogComparator());
        System.out.println(Arrays.toString(dogs));


    }

}
