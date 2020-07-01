package com.jp.test.jpa.Utils;

import java.util.Comparator;

/**
 * 此类实现的是java.util的Comparator,其实和自己实现的是一样的
 */
public class CatWeightComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o1, Cat o2) {
        if (o1.weight > o2.weight) return 1;
        else if (o1.weight < o2.weight) return -1;
        return 0;
    }
}
