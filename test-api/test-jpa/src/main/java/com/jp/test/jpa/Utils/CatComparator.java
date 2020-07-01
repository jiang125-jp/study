package com.jp.test.jpa.Utils;

/**
 * 此类实现的是自己写的Comparator<T>
 */
public class CatComparator implements Comparator<Cat> {

    @Override
    public int comparator(Cat c1, Cat c2) {
        if(c1.age > c2.age) return -1;
        else if(c1.age < c2.age) return 1;
        return 0;
    }
}
